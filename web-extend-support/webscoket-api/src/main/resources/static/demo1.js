function init() {
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
}
