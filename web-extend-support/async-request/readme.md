1. 1. 编写异步servlet
```text
public class MyAsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 开启异步处理，标记此请求为异步
        // 标记为异步处理后，当此请求相关的Servlet与Filter执行完成后，不会关闭其请求与响应
        AsyncContext asyncContext = req.startAsync() ;
        // 创建异步任务, 异步对response写入
        Runnable runnable = () ->{
            //模拟5秒处理
            simulateLongTimeProcess(5000L) ;
            try {
                //异步通过响应返回数据
                asyncContext.getResponse().getWriter().write("Hello world");
            }catch (IOException e){
                e.printStackTrace();
            }
            // 标记异步处理完成，可以释放与此请求相关的request与response了
            asyncContext.complete();
        } ;
        // 创建线程，执行异步任务
        new Thread(runnable).start();
        // 此处Servlet逻辑处理完成，在其相关的Filter处理返回后，与该请求关联的Http处理线程就释放了
    }
    // 模拟长时间请求，参数为休眠毫秒数
    private void simulateLongTimeProcess(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
2. 配置servlet并注意 asyncSupported = true
```text
@WebServlet(name = "myAsyncServlet", urlPatterns = "/myAsyncServlet", asyncSupported = true)
```
3. 在springboot入口添加注解扫描servlet
```text
@ServletComponentScan("com.yicj.study.servlet")
```