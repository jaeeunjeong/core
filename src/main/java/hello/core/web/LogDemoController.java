package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.logdemo.LogDemoService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogDemoController {
    private final LogDemoService logDemoService;
//    private final MyLogger myLogger;
    private final ObjectProvider<MyLogger> myLoggerObjectProvider;
    public LogDemoController(LogDemoService logDemoService, ObjectProvider<MyLogger>  myLoggerObjectProvider) {
        this.logDemoService = logDemoService;
        this.myLoggerObjectProvider = myLoggerObjectProvider;
    }

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testID");
        return "OKay";
    }
}
