/** 
 * All rights Reserved, Designed By www.baiwang.com
 *
 * @author: 云平台技术部   
 * @date: 2018年5月11日 下午3:43:07  
 * @version v1.0
 * @Copyright: 2018 www.baiwang.com Inc. All rights reserved. 注意：本内容仅限于百望股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.arthur.netty.service;

/**  
 * @Description: 启动监听器接口 
 * @author: wangwei
 * @date: 2018年5月11日 下午3:43:07
 */
public interface IListener {
  /**
   * 成功
   * @Description: 监听成功方法 
   * @param args    参数
   */
  void onSuccess(Object... args);
  /**
   * 失败
   * @Description: 监听失败方法
   * @param cause   异常栈
   */
  void onFailure(Throwable cause);
}
