
package com.arthur.netty.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spush.server")
@PropertySource("classpath:nettyServerConfig.properties") // 用来指定配置文件的位置
public class SystemNettyServerConfig {
    private int port;
    private int iothreads;
    private int workthreads;
    private int tcpbacklog;
    private String tcpnodelay = "true";
    private int tcpTimeOut;
    private int maxHeartBeat;
    private int maxHeartBeatTimeOut;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getIothreads() {
        return iothreads;
    }

    public void setIothreads(int iothreads) {
        this.iothreads = iothreads;
    }

    public int getWorkthreads() {
        return workthreads;
    }

    public void setWorkthreads(int workthreads) {
        this.workthreads = workthreads;
    }

    public int getTcpbacklog() {
        return tcpbacklog;
    }

    public void setTcpbacklog(int tcpbacklog) {
        this.tcpbacklog = tcpbacklog;
    }

    public boolean getTcpnodelay() {
        return Boolean.parseBoolean(tcpnodelay);
    }

    public void setTcpnodelay(String tcpnodelay) {
        this.tcpnodelay = tcpnodelay;
    }

    public int getTcpTimeOut() {
        return tcpTimeOut;
    }

    public void setTcpTimeOut(int tcpTimeOut) {
        this.tcpTimeOut = tcpTimeOut;
    }

    public int getMaxHeartBeat() {
        return maxHeartBeat;
    }

    public void setMaxHeartBeat(int maxHeartBeat) {
        this.maxHeartBeat = maxHeartBeat;
    }

    public int getMaxHeartBeatTimeOut() {
        return maxHeartBeatTimeOut;
    }

    public void setMaxHeartBeatTimeOut(int maxHeartBeatTimeOut) {
        this.maxHeartBeatTimeOut = maxHeartBeatTimeOut;
    }
}
