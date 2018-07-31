/**
 * All rights Reserved, Designed By www.baiwang.com
 *
 * @author: 云平台技术部
 * @date: 2018年5月11日 下午3:56:49
 * @version v1.0
 * @Copyright: 2018 www.baiwang.com Inc. All rights reserved. 注意：本内容仅限于百望股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.arthur.netty.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description: 异步监听器
 * @author: wangwei
 * @date: 2018年5月11日 下午3:56:49
 */
public class FutureListener extends CompletableFuture<Boolean> implements IListener {
  private final IListener listener;
  private final AtomicBoolean started;

  /**
   * 构造函数
   * 
   * @param started 启动标志位
   */
  public FutureListener(AtomicBoolean started) {
    this.started = started;
    this.listener = null;
  }

  /**
   * 构造函数
   * 
   * @param listener 监听器
   * @param started 启动标志位
   */
  public FutureListener(IListener listener, AtomicBoolean started) {
    this.listener = listener;
    this.started = started;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.Listener#onSuccess(java.lang.Object[])
   */
  @Override
  public void onSuccess(Object... args) {
    if (isDone())
      return;// 防止Listener被重复执行
    complete(started.get());
    if (listener != null)
      listener.onSuccess(args);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baiwang.spush.api.service.Listener#onFailure(java.lang.Throwable)
   */
  @Override
  public void onFailure(Throwable cause) {
    if (isDone())
      return;// 防止Listener被重复执行
    completeExceptionally(cause);
    if (listener != null)
      listener.onFailure(cause);
    throw cause instanceof ServiceException ? (ServiceException) cause
        : new ServiceException(cause);
  }

  /**
   * 防止服务长时间卡在某个地方，增加超时监控
   *
   * @param service 服务
   */
  public void monitor(BaseService service) {
    if (isDone())
      return;// 防止Listener被重复执行
    runAsync(() -> {
      try {
        this.get(service.timeoutMillis(), TimeUnit.MILLISECONDS);
      } catch (Exception e) {
        this.onFailure(new ServiceException(
            String.format("service %s monitor timeout", service.getClass().getSimpleName())));
      }
    });
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    throw new UnsupportedOperationException();
  }

}
