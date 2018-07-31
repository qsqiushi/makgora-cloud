/** 
 * All rights Reserved, Designed By www.baiwang.com
 *
 * @author: 云平台技术部   
 * @date: 2018年5月12日 下午3:25:40  
 * @version v1.0
 * @Copyright: 2018 www.baiwang.com Inc. All rights reserved. 注意：本内容仅限于百望股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.arthur.netty.options;

import java.util.concurrent.TimeUnit;
/**  
 * @Description: NettyServerOptions
 * @author: wangwei
 * @date: 2018年5月12日 下午3:25:40
 */
public class ServerOptions<T> {

    // default value for every option
    private final T defaultValue;

    public ServerOptions(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public T defaultValue() {
        return defaultValue;
    }

    /**
     * max tcp accept compulish queue.
     *
     * NOT effect on max connections restriction. just a hint value for linux listen(fd, backlog)
     * increase the number if you have lots of short connection which sockets will be filled
     * in tcp accept compulish queue too much.
     *
     */
    public static final ServerOptions<Integer> TCP_BACKLOG = new ServerOptions<>(1024);

    /**
     * max connections in this httpserver instance. default is 8192
     *
     * incoming http connection over the number of maxConnections will be reject
     * and return http code 503
     */
    public static final ServerOptions<Integer> MAX_CONNETIONS = new ServerOptions<>(8192);

    /**
     * max received packet size. default is 16MB
     */
    public static final ServerOptions<Integer> MAX_PACKET_SIZE = new ServerOptions<>(16 * 1024 * 1024);

    /**
     * network socket io threads number. default is cpu core - 1
     *
     * NOT set the number more than cpu core number
     */
    public static final ServerOptions<Integer> IO_THREADS = new ServerOptions<>(Runtime.getRuntime().availableProcessors() - 1);

    /**
     * logic handler threads number. default is 128
     *
     * we suggest adjust the ioThreads number bigger if there are more. critical region
     * code or block code in your handler logic (io intensive). and smaller if your code
     * has no block almost (cpu intensive)
     */
    public static final ServerOptions<Integer> WORKER_THREADS = new ServerOptions<>(128);

    /**
     * logic handler timeout. default is 30s
     */
    public static final ServerOptions<Integer> TCP_TIMEOUT = new ServerOptions<>((int) TimeUnit.SECONDS.toMillis(30));


    public static final ServerOptions<Boolean> TCP_NODELAY = new ServerOptions<>(Boolean.TRUE);

    public static final ServerOptions<String> ACCESS_LOG = new ServerOptions<>("");

    public static final ServerOptions<Boolean> ACCESS_LOG_BUFFER_IO = new ServerOptions<>(false);
    //最大心跳时间
    public static final ServerOptions<Integer> MAX_HEART_BEAT = new ServerOptions<>(100);
    //最大心跳超时时间
    public static final ServerOptions<Integer> MAX_HEART_BEAT_TIMEOUT = new ServerOptions<>(100);
    //客户端连接超时
    public static final ServerOptions<Integer> CLIENT_CONNECT_TIMEOUT = new ServerOptions<>(2000);
    //客户端设置等待数据超时时间5秒钟
    public static final ServerOptions<Integer> CLIENT_SCOKET_TIMEOUT = new ServerOptions<>(3000);
    //客户端连接池最大连接数
    public static final ServerOptions<Integer> CLIENT_POOL_SIZE = new ServerOptions<>(3000);
    //客户端每个主机的并发最多只有1500
    public static final ServerOptions<Integer> CLIENT_MAX_PER_ROUTE = new ServerOptions<>(1500);
    //客户端请求服务端发送报文字符编码
    public static final ServerOptions<String> CLIENT_REQUEST_CHARSET = new ServerOptions<>("UTF-8");
}
