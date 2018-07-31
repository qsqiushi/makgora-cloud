/**
 * All rights Reserved, Designed By www.baiwang.com
 *
 * @author: 云平台技术部
 * @date: 2018年5月11日 下午8:04:19
 * @version v1.0
 * @Copyright: 2018 www.baiwang.com Inc. All rights reserved. 注意：本内容仅限于百望股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.arthur.netty.server;


import com.arthur.netty.codec.PacketDecoder;
import com.arthur.netty.codec.PacketEncoder;
import com.arthur.netty.options.ServerOptionProvider;
import com.arthur.netty.options.ServerOptions;
import com.arthur.netty.service.BaseService;
import com.arthur.netty.service.IListener;
import com.arthur.netty.service.IServer;
import com.arthur.netty.service.ServiceException;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicReference;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @Description: Netty Server
 * @author: wangwei
 * @date: 2018年5月11日 下午8:04:19
 */
public abstract class NettyTCPServer extends BaseService implements IServer {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  public static ServerOptionProvider options = new ServerOptionProvider();
  public enum State {
    Created, Initialized, Starting, Started, Shutdown
  }

  protected final AtomicReference<State> serverState = new AtomicReference<>(State.Created);
  protected final int port;
  protected final String host;
  protected EventLoopGroup bossGroup;
  protected EventLoopGroup workerGroup;
  protected static boolean isEpollAvailable = false;
  static {
    isEpollAvailable = Epoll.isAvailable();
  }

  public NettyTCPServer(int port) {
    this.port = port;
    this.host = null;
  }

  public NettyTCPServer(int port, String host) {
    this.port = port;
    this.host = host;
  }

  @Override
  public void init() {
    if (!serverState.compareAndSet(State.Created, State.Initialized)) {
      throw new ServiceException("Server already init");
    }
  }

  @Override
  public boolean isRunning() {
    return serverState.get() == State.Started;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.IService#start(com.baiwang.spush.api.service.IListener)
   */
  @Override
  public void start(IListener listener) {
    if (!serverState.compareAndSet(State.Initialized, State.Starting)) {
      throw new ServiceException("Server already already started or have not init");
    }
    if (isEpollAvailable) {
      createEpollServer(listener);
    } else {
      createNioServer(listener);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.IService#stop(com.baiwang.spush.api.service.IListener)
   */
  @Override
  public void stop(IListener listener) {
    if (!serverState.compareAndSet(State.Started, State.Shutdown)) {
      if (listener != null)
        listener.onFailure(new ServiceException("server was already shutdown."));
      logger.error("{} was already shutdown.", this.getClass().getSimpleName());
      return;
    }
    logger.info("try shutdown {}...", this.getClass().getSimpleName());
    if (bossGroup != null)
      bossGroup.shutdownGracefully().syncUninterruptibly();// 要先关闭接收连接的main reactor
    if (workerGroup != null)
      workerGroup.shutdownGracefully().syncUninterruptibly();// 再关闭处理业务的sub reactor
    logger.info("{} shutdown success.", this.getClass().getSimpleName());
    if (listener != null) {
      listener.onSuccess(port);
    }
  }

  private void createServer(IListener listener, EventLoopGroup boss, EventLoopGroup work,
      ChannelFactory<? extends ServerChannel> channelFactory) {
    /***
     * NioEventLoopGroup 是用来处理I/O操作的多线程事件循环器， Netty提供了许多不同的EventLoopGroup的实现用来处理不同传输协议。
     * 在一个服务端的应用会有2个NioEventLoopGroup会被使用。 第一个经常被叫做‘boss’，用来接收进来的连接。 第二个经常被叫做‘worker’，用来处理已经被接收的连接，
     * 一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上。 如何知道多少个线程已经被使用，如何映射到已经创建的Channels上都需要依赖于EventLoopGroup的实现，
     * 并且可以通过构造函数来配置他们的关系。
     */
    this.bossGroup = boss;
    this.workerGroup = work;
    try {
      /**
       * ServerBootstrap 是一个启动NIO服务的辅助启动类 你可以在这个服务中直接使用Channel
       */
      ServerBootstrap b = new ServerBootstrap();
      /**
       * 这一步是必须的，如果没有设置group将会报java.lang.IllegalStateException: group not set异常
       */
      b.group(bossGroup, workerGroup);
      /***
       * ServerSocketChannel以NIO的selector为基础进行实现的，用来接收新的连接 这里告诉Channel如何获取新的连接.
       */
      b.channelFactory(channelFactory);
      /***
       * 这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。 ChannelInitializer是一个特殊的处理类， 他的目的是帮助使用者配置一个新的Channel。
       * 也许你想通过增加一些处理类比如NettyServerHandler来配置一个新的Channel 或者其对应的ChannelPipeline来实现你的网络程序。
       * 当你的程序变的复杂时，可能你会增加更多的处理类到pipeline上， 然后提取这些匿名类到最顶层的类上。
       */
      b.childHandler(new ChannelInitializer<Channel>() { // (4)
        @Override
        public void initChannel(Channel ch) throws Exception {// 每连上一个链接调用一次
          initPipeline(ch.pipeline());
        }
      });
      initOptions(b);
      /***
       * 绑定端口并启动去接收进来的连接
       */
      InetSocketAddress address = (null == host || host.length() == 0) ? new InetSocketAddress(port)
          : new InetSocketAddress(host, port);
      b.bind(address).addListener(future -> {
        if (future.isSuccess()) {
          serverState.set(State.Started);
          logger.info("server start success on:{}", port);
          if (listener != null)
            listener.onSuccess(port);
        } else {
          logger.error("server start failure on:{}", port, future.cause());
          if (listener != null)
            listener.onFailure(future.cause());
        }
      });
    } catch (Exception e) {
      logger.error("server start exception", e);
      if (listener != null)
        listener.onFailure(e);
      throw new ServiceException("server start exception, port=" + port, e);
    }
  }

  /**
   * 每连上一个链接调用一次
   *
   * @param pipeline
   */
  protected void initPipeline(ChannelPipeline pipeline) {
    pipeline.addLast("decoder", getDecoder());
    pipeline.addLast("encoder", getEncoder());
    pipeline.addLast("handler", getChannelHandler());
  }

  /***
   * option()是提供给NioServerSocketChannel用来接收进来的连接。 childOption()是提供给由父管道ServerChannel接收到的连接，
   * 在这个例子中也是NioServerSocketChannel。
   */
  protected void initOptions(ServerBootstrap b) {
    // b.childOption(ChannelOption.SO_KEEPALIVE, false);// 使用应用层心跳
    /**
     * 在Netty 4中实现了一个新的ByteBuf内存池，它是一个纯Java版本的 jemalloc （Facebook也在用）。
     * 现在，Netty不会再因为用零填充缓冲区而浪费内存带宽了。不过，由于它不依赖于GC，开发人员需要小心内存泄漏。 如果忘记在处理程序中释放缓冲区，那么内存使用率会无限地增长。
     * Netty默认不使用内存池，需要在创建客户端或者服务端的时候进行指定
     */
    b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            .option(ChannelOption.SO_TIMEOUT, options.option(ServerOptions.TCP_TIMEOUT))
    //BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
    .option(ChannelOption.SO_BACKLOG, options.option(ServerOptions.TCP_BACKLOG))
    .option(ChannelOption.SO_REUSEADDR, true)
    //在TCP/IP协议中，无论发送多少数据，总是要在数据前面加上协议头，同时，对方接收到数据，也需要发送ACK表示确认。为了尽可能的利用网络带宽，
    //TCP总是希望尽可能的发送足够大的数据。这里就涉及到一个名为Nagle的算法，该算法的目的就是为了尽可能发送大块数据，避免网络中充斥着许多小数据块。
    //TCP_NODELAY就是用于启用或关于Nagle算法。如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法；
    //如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。默认为false。
    .option(ChannelOption.TCP_NODELAY, options.option(ServerOptions.TCP_NODELAY))
    .childOption(ChannelOption.SO_KEEPALIVE, true)
    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
  }


  public abstract ChannelHandler getChannelHandler();

  protected ChannelHandler getDecoder() {
    return new PacketDecoder();
  }

  protected ChannelHandler getEncoder() {
    return PacketEncoder.INSTANCE;// 每连上一个链接调用一次, 所有用单利
  }

  private void createNioServer(IListener listener) {
    EventLoopGroup bossGroup = getBossGroup();
    EventLoopGroup workerGroup = getWorkerGroup();
    //Rector模型中的接受处理线程池大小
    int acceptThreads = Math.max(2, ((int) (options.option(ServerOptions.IO_THREADS) * 0.3) & 0xFFFFFFFE));
    //Rector模型中的工作线程池大小
    int rwThreads = Math.max(4, options.option(ServerOptions.IO_THREADS));
    if (bossGroup == null) {
//      NioEventLoopGroup nioEventLoopGroup =
//          new NioEventLoopGroup(acceptThreads, getBossThreadFactory(), getSelectorProvider());
//      nioEventLoopGroup.setIoRatio(100);
      NioEventLoopGroup pnioEventLoopGroup = new NioEventLoopGroup();
      bossGroup = pnioEventLoopGroup;
    }

    if (workerGroup == null) {
//      NioEventLoopGroup nioEventLoopGroup =
//          new NioEventLoopGroup(rwThreads, getWorkThreadFactory(), getSelectorProvider());
//      nioEventLoopGroup.setIoRatio(getIoRate());
      NioEventLoopGroup cnioEventLoopGroup = new NioEventLoopGroup();
      workerGroup = cnioEventLoopGroup;
    }

    createServer(listener, bossGroup, workerGroup, getNioChannelFactory());
  }

  public SelectorProvider getSelectorProvider() {
    return SelectorProvider.provider();
  }

  @Override
  public void start(IListener l, FunctionEx function) {
    super.start(l, function);
  }

  @Override
  public CompletableFuture<Boolean> start() {
    return super.start();
  }

  @Override
  protected void doStart(IListener listener) throws Throwable {
    super.doStart(listener);
  }

  public ChannelFactory<? extends ServerChannel> getChannelFactory() {
    return NioServerSocketChannel::new;
  }

  public ChannelFactory<? extends ServerChannel> getNioChannelFactory() {
    return NioServerSocketChannel::new;
  }

  public ChannelFactory<? extends ServerChannel> getEpollChannelFactory() {
    return EpollServerSocketChannel::new;
  }

  private void createEpollServer(IListener listener) {
    EventLoopGroup bossGroup = getBossGroup();
    EventLoopGroup workerGroup = getWorkerGroup();

    int acceptThreads = Math.max(2, ((int) (options.option(ServerOptions.IO_THREADS) * 0.3) & 0xFFFFFFFE));
    
    int rwThreads = Math.max(4, options.option(ServerOptions.IO_THREADS));
    
    if (bossGroup == null) {
//      EpollEventLoopGroup epollEventLoopGroup = new EpollEventLoopGroup(acceptThreads, getBossThreadFactory());
//      epollEventLoopGroup.setIoRatio(100);
//      bossGroup = epollEventLoopGroup;
      EpollEventLoopGroup epollEventLoopGroup = new EpollEventLoopGroup();
      bossGroup = epollEventLoopGroup;
    }

    if (workerGroup == null) {
//      NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(rwThreads, getWorkThreadFactory());
//      nioEventLoopGroup.setIoRatio(getIoRate());
//      workerGroup = nioEventLoopGroup;
      EpollEventLoopGroup nioEventLoopGroup = new EpollEventLoopGroup();
      workerGroup = nioEventLoopGroup;
    }

    createServer(listener, bossGroup, workerGroup, getEpollChannelFactory());
  }

  /**
   * netty 默认的Executor为ThreadPerTaskExecutor 线程池的使用在SingleThreadEventExecutor#doStartThread
   * <p>
   * eventLoop.execute(runnable); 是比较重要的一个方法。在没有启动真正线程时， 它会启动线程并将待执行任务放入执行队列里面。
   * 启动真正线程(startThread())会判断是否该线程已经启动， 如果已经启动则会直接跳过，达到线程复用的目的
   *
   * @return
   */
  protected ThreadFactory getBossThreadFactory() {
    return new DefaultThreadFactory(getBossThreadName());
  }

  protected ThreadFactory getWorkThreadFactory() {
    return new DefaultThreadFactory(getWorkThreadName());
  }
  protected String getBossThreadName() {
    return "NettyTcpServer-Boss";
  }

  protected String getWorkThreadName() {
    return "NettyTcpServer-Work";
  }

  protected int getIoRate() {
    return 70;
  }

  public EventLoopGroup getBossGroup() {
    return bossGroup;
  }

  public void setBossGroup(EventLoopGroup bossGroup) {
    this.bossGroup = bossGroup;
  }

  public EventLoopGroup getWorkerGroup() {
    return workerGroup;
  }

  public void setWorkerGroup(EventLoopGroup workerGroup) {
    this.workerGroup = workerGroup;
  }
}
