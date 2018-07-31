package com.arthur.netty.protocol;
/**  
 * @Description: 命令状态接口
 */
public enum Command {
  /**心跳**/
  HEARTBEAT(1),
  /**握手**/
  HANDSHAKE(2),
  /**登录**/
  LOGIN(3),
  /**退出**/
  LOGOUT(4),
  /**绑定**/
  BIND(5),
  /**解绑**/
  UNBIND(6),
  /**重新连接**/
  RESUME(7),
  /**错误**/
  ERROR(8),
  /**正确**/
  OK(9),
  /**推送**/
  PUSH(10),
  /**确认**/
  ACK(11),
  GATEWAY_PUSH(16),
  /**未知**/
  UNKNOWN(-1);

  Command(int cmd) {
      this.cmd = (byte) cmd;
  }

  public final byte cmd;

  public static Command toCMD(byte b) {
      Command[] values = values();
      if (b > 0 && b < values.length) return values[b - 1];
      return UNKNOWN;
  }
}
