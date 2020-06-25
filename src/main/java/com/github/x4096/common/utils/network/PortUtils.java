package com.github.x4096.common.utils.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2018/12/20
 * @instructions: 网络端口工具类, 用于检测指定host的指定端口是否能连接
 */
public class PortUtils {

    private PortUtils() {
    }

    /**
     * 检测指定host的指定端口是否能连接,默认连接超时时间2秒
     *
     * @param host 主机
     * @param port 端口
     * @return true/false
     */
    public static boolean isConnect(String host, int port) {
        return isConnect(host, port, 2_000);
    }


    /**
     * 检测指定host的指定端口是否能连接
     *
     * @param host    主机
     * @param port    端口
     * @param timeout 超时时间, 单位毫秒
     * @return true/false
     */
    public static boolean isConnect(String host, int port, int timeout) {
        try (Socket connect = new Socket()) {
            connect.connect(new InetSocketAddress(host, port), timeout);
            return connect.isConnected();
        } catch (IOException e) {
            return false;
        }
    }

}
