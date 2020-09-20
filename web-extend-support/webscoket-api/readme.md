### 方式一SpringMvc提供的方式来实现WebSocket服务端
1. 添加WebSocket服务的处理器
```text
public class MyWebSocketHandler extends TextWebSocketHandler {
    // 收到消息时的方法
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 收到文本消息，并通过session的api发送给客户端，echo功能
        session.sendMessage(message);
    }
    // 连接开启时的处理方法
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }
    // 连接关闭后的处理方法
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
    // 数据传输出错的处理方法
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }
}
```
2. 配置WebSocket的处理器
```text
@Configuration
// 通过该注解开启WebSocket功能
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    // 注册WebSocket的处理器，同时还添加了一个HandshakeInterceptor
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyWebSocketHandler(), "/myWebSocket")
                .addInterceptors(new HttpSessionHandshakeInterceptor()) ;
    }
}
```
3. 编写index.html并编写websocket脚本
```text
    // 初始化一个WebSocket对象，可接受两个参数
    // 第一个参数时ws协议的WebSocket的连接地址，也可以是wss的安全连接地址
    // 第二个参数是可接收的子协议，入STOMP协议，后面详解
        var ws = new WebSocket("ws://localhost:8080/myWebSocket") ;
    // WebSocket连接成功触发事件
        ws.onopen = function (ev) {
            //使用 send() 方法发送数据
            ws.send("发送数据") ;
        }
    // 接收服务端数据触发事件
        ws.onmessage =function (ev) {
            console.info(ev.data) ;
        }
    // 连接断开触发事件
        ws.onclose = function (ev) {  }
    // 主动关闭WebSocket连接
    //ws.close() ;
```
### 方式二使用Servlet原生方式来实现WebSocket
1.编写服务处理器
```text
Component
@ServerEndpoint("/webSocket/{sid}")
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineNum = new AtomicInteger();
    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();
    //发送消息
    public void sendMessage(Session session, String message) throws IOException {
        if (session != null) {
            synchronized (session) {
                session.getBasicRemote().sendText(message);
            }
        }
    }
    //给指定用户发送信息
    public void sendInfo(String userName, String message) {
        Session session = sessionPools.get(userName);
        try {
            sendMessage(session, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName) {
        sessionPools.put(userName, session);
        addOnlineCount();
        System.out.println(userName + "加入webSocket！当前人数为" + onlineNum);
        try {
            sendMessage(session, "欢迎" + userName + "加入连接！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "sid") String userName) {
        sessionPools.remove(userName);
        subOnlineCount();
        System.out.println(userName + "断开webSocket连接！当前人数为" + onlineNum);
    }
    //收到客户端信息
    @OnMessage
    public void onMessage(String message) throws IOException {
        message = "客户端：" + message + ",已收到";
        System.out.println(message);
        for (Session session : sessionPools.values()) {
            try {
                sendMessage(session, message);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("发生错误");
        throwable.printStackTrace();
    }
    public static void addOnlineCount() {
        onlineNum.incrementAndGet();
    }
    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }
}
```
2. 编写配置使用@ServerEndpoint注解声明的websocket endpoint
```text
@Configuration
public class WebSocketConfig {
    //这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
```


