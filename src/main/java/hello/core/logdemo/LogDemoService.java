package hello.core.logdemo;

import hello.core.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
public class LogDemoService {
    /*
    private final MyLogger myLogger;

    public LogDemoService(MyLogger myLogger) {
        this.myLogger = myLogger;
    }*/
    private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    public LogDemoService(ObjectProvider<MyLogger> myLoggerObjectProvider) {
        this.myLoggerObjectProvider = myLoggerObjectProvider;
    }

    public void logic(String id) {
        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.log("service id : " + id);
    }
}
