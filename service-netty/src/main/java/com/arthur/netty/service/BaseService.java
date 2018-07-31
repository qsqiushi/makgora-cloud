/**
 * All rights Reserved, Designed By www.baiwang.com
 *
 * @author: 云平台技术部
 * @date: 2018年5月11日 下午3:45:10
 * @version v1.0
 * @Copyright: 2018 www.baiwang.com Inc. All rights reserved. 注意：本内容仅限于百望股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.arthur.netty.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 * @Description: 抽象服务基类
 * @author: wangwei
 * @date: 2018年5月11日 下午3:45:10
 */
public abstract class BaseService implements IService {
  protected final AtomicBoolean started = new AtomicBoolean();

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.Service#start(com.baiwang.spush.api.service.Listener)
   */
  public void start(IListener l, FunctionEx function) {
    FutureListener listener = wrap(l);
    if (started.compareAndSet(false, true)) {
      try {
        init();
        function.apply(listener);
        listener.monitor(this);// 主要用于异步，否则应该放置在function.apply(listener)之前
      } catch (Throwable e) {
        listener.onFailure(e);
        throw new ServiceException(e);
      }
    } else {
      if (throwIfStarted()) {
        listener.onFailure(new ServiceException("service already started."));
      } else {
        listener.onSuccess();
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.Service#stop(com.baiwang.spush.api.service.Listener)
   */
  public void stop(IListener l, FunctionEx function) {
    FutureListener listener = wrap(l);
    if (started.compareAndSet(true, false)) {
        try {
            function.apply(listener);
            listener.monitor(this);//主要用于异步，否则应该放置在function.apply(listener)之前
        } catch (Throwable e) {
            listener.onFailure(e);
            throw new ServiceException(e);
        }
    } else {
        if (throwIfStopped()) {
            listener.onFailure(new ServiceException("service already stopped."));
        } else {
            listener.onSuccess();
        }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.Service#start()
   */
  @Override
  public CompletableFuture<Boolean> start() {
    FutureListener listener = new FutureListener(started);
    start(listener);
    return listener;
  }
  
  @Override
  public void start(IListener listener) {
      tryStart(listener, this::doStart);
  }

  @Override
  public void stop(IListener listener) {
      tryStop(listener, this::doStop);
  }
  
  protected void doStart(IListener listener) throws Throwable {
      listener.onSuccess();
  }

  protected void doStop(IListener listener) throws Throwable {
      listener.onSuccess();
  }
  
  protected void tryStart(IListener l, FunctionEx function) {
      FutureListener listener = wrap(l);
      if (started.compareAndSet(false, true)) {
          try {
              init();
              function.apply(listener);
              listener.monitor(this);//主要用于异步，否则应该放置在function.apply(listener)之前
          } catch (Throwable e) {
              listener.onFailure(e);
              throw new ServiceException(e);
          }
      } else {
          if (throwIfStarted()) {
              listener.onFailure(new ServiceException("service already started."));
          } else {
              listener.onSuccess();
          }
      }
  }

  protected void tryStop(IListener l, FunctionEx function) {
      FutureListener listener = wrap(l);
      if (started.compareAndSet(true, false)) {
          try {
              function.apply(listener);
              listener.monitor(this);//主要用于异步，否则应该放置在function.apply(listener)之前
          } catch (Throwable e) {
              listener.onFailure(e);
              throw new ServiceException(e);
          }
      } else {
          if (throwIfStopped()) {
              listener.onFailure(new ServiceException("service already stopped."));
          } else {
              listener.onSuccess();
          }
      }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.Service#stop()
   */
  @Override
  public CompletableFuture<Boolean> stop() {
    FutureListener listener = new FutureListener(started);
    stop(listener);
    return listener;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.Service#syncStart()
   */
  @Override
  public boolean syncStart() {
    return start().join();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.Service#syncStop()
   */
  @Override
  public boolean syncStop() {
    return stop().join();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.Service#init()
   */
  @Override
  public void init() {

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.Service#isRunning()
   */
  @Override
  public boolean isRunning() {
    return this.started.get();
  }

  /**
   * 控制当服务已经启动后，重复调用start方法，是否抛出服务已经启动异常 默认是true
   *
   * @return true:抛出异常
   */
  protected boolean throwIfStarted() {
    return true;
  }

  /**
   * 控制当服务已经停止后，重复调用stop方法，是否抛出服务已经停止异常 默认是true
   *
   * @return true:抛出异常
   */
  protected boolean throwIfStopped() {
    return true;
  }

  /**
   * 服务启动停止，超时时间, 默认是10s
   *
   * @return 超时时间
   */
  protected int timeoutMillis() {
    return 1000 * 10;
  }

  protected interface FunctionEx {
    void apply(IListener l) throws Throwable;
  }

  /**
   * 防止Listener被重复执行
   *
   * @param l listener
   * @return FutureListener
   */
  public FutureListener wrap(IListener l) {
    if (l == null)
      return new FutureListener(started);
    if (l instanceof FutureListener)
      return (FutureListener) l;
    return new FutureListener(l, started);
  }
}
