package com.yyh.authoritymanagement.websocket;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author yyh
 */
@Api(tags = "webSocket服务")
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocket {
    @ApiModelProperty(value = "在线计数", name = "onlineCount", notes = "静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。")
    private static int onlineCount = 0;
    @ApiModelProperty(value = "会话集合", name = "webSocketSet", notes = "concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。")
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();
    @ApiModelProperty(value = "会话", name = "session", notes = "与某个客户端的连接会话，需要通过它来给客户端发送数据")
    private Session session;

    @ApiOperation(value = "成功新建会话")
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        // 加入set中
        addOnlineCount(); // 在线数加1
        System.out.println("有新连接加入！当前在线人数为 : " + getOnlineCount());
        try {
            sendMessage("您已成功连接！");
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    @ApiOperation(value = "连接关闭时调用方法")
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        // 从set中删除
        subOnlineCount(); // 在线数减1
        System.out.println("有一连接关闭！当前在线人数为 : " + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @ApiOperation(value = "收到客户端消息后调用的方法")
    @OnMessage
    public void onMessage(@ApiParam(value = "来自客户端的消息") String message, Session session) {
        // 群发消息
        for (WebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        // this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        for (WebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

}
