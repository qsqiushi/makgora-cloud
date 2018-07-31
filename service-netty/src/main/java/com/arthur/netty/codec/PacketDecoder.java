/**
 * All rights Reserved, Designed By www.baiwang.com
 *
 * @author: 云平台技术部
 * @date: 2018年5月12日 下午2:48:15
 * @version v1.0
 * @Copyright: 2018 www.baiwang.com Inc. All rights reserved. 注意：本内容仅限于百望股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.arthur.netty.codec;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.arthur.netty.properties.SystemNettyServerConfig;
import com.arthur.netty.protocol.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
/**
 * @Description: 报文解包器
 * @author: wangwei
 * @date: 2018年5月12日 下午2:48:15
 */
public final class PacketDecoder extends ByteToMessageDecoder {
  @Autowired
  private SystemNettyServerConfig config = null;
  /*
   * (non-Javadoc)
   * 
   * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.ChannelHandlerContext,
   * io.netty.buffer.ByteBuf, java.util.List)
   */
  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    //读取心跳信息
    decodeHeartbeat(in, out);
    //读取报文信息
    decodeFrames(in, out);
  }

  /**
   * 读取心跳包数据
   * 
   * @Description: 读取心跳包数据
   * @param in
   * @param out
   */
  private void decodeHeartbeat(ByteBuf in, List<Object> out) {
    while (in.isReadable()) {
      if (in.readByte() == Packet.HB_PACKET_BYTE) {
        out.add(Packet.HB_PACKET);
      } else {
        in.readerIndex(in.readerIndex() - 1);
        break;
      }
    }
  }
  /**
   * 读取报文体
   * @Description: 读取报文体数据 
   * @param in
   * @param out
   */
  private void decodeFrames(ByteBuf in, List<Object> out) {
    if (in.readableBytes() >= Packet.HEADER_LEN) {
      // 1.记录当前读取位置位置.如果读取到非完整的frame,要恢复到该位置,便于下次读取
      in.markReaderIndex();

      Packet packet = decodeFrame(in);
      if (packet != null) {
        out.add(packet);
      } else {
        // 2.读取到不完整的frame,恢复到最近一次正常读取的位置,便于下次读取
        in.resetReaderIndex();
      }
    }
  }
  /**
   * 读取报文体
   * @Description: 读取报文体
   * @param in
   * @return
   */
  private Packet decodeFrame(ByteBuf in) {
    int readableBytes = in.readableBytes();
    int bodyLength = in.readInt();
    if (readableBytes < (bodyLength + Packet.HEADER_LEN)) {
      return null;
    }
//    if (bodyLength > Integer.parseInt(config.getCoreConfig().getMaxPacketSize())) {
//      throw new TooLongFrameException("packet body length over limit:" + bodyLength);
//    }
    return Packet.decodePacket(new Packet(in.readByte()), in, bodyLength);
  }
}
