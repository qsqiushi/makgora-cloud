/** 
 * All rights Reserved, Designed By www.baiwang.com
 *
 * @author: 云平台技术部   
 * @date: 2018年5月12日 下午2:49:58  
 * @version v1.0
 * @Copyright: 2018 www.baiwang.com Inc. All rights reserved. 注意：本内容仅限于百望股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.arthur.netty.codec;


import com.arthur.netty.protocol.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**  
 * @Description: 拼装发送报文  
 * @author: wangwei
 * @date: 2018年5月12日 下午2:49:58
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
  public static final PacketEncoder INSTANCE = new PacketEncoder();
  @Override
  protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
    encodePacket(msg, out);
  }

}
