/** 
 * All rights Reserved, Designed By www.baiwang.com
 *
 * @author: 云平台技术部   
 * @date: 2018年5月11日 下午3:41:28  
 * @version v1.0
 * @Copyright: 2018 www.baiwang.com Inc. All rights reserved. 注意：本内容仅限于百望股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.arthur.netty.service;

import java.util.concurrent.CompletableFuture;

/**  
 * @Description: 服务顶层接口  
 * @author: wangwei
 * @date: 2018年5月11日 下午3:41:28
 */
public interface IService {
  /**
   * 启动服务
   * @Description: 启动服务
   * @param listener    监听器
   */
  void start(IListener listener);
  /**
   * 停止服务
   * @Description: 停止服务
   * @param listener    监听器
   */
  void stop(IListener listener);
  /**
   * 
   * @Description: TODO(这里用一句话描述这个方法的作用) 
   * @return
   */
  CompletableFuture<Boolean> start();

  CompletableFuture<Boolean> stop();
  /**
   * 同步启动服务
   * @Description: 同步启动服务 
   * @return
   */
  boolean syncStart();
  /**
   * 同步停止服务
   * @Description: TODO(这里用一句话描述这个方法的作用) 
   * @return
   */
  boolean syncStop();
  /**
   * 初始化
   * @Description: 初始化服务参数或变量赋值
   */
  void init();
  /**
   * 是否启动
   * @Description: 判断服务是否启动 
   * @return
   */
  boolean isRunning();
}
