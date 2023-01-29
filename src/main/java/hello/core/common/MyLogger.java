package hello.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {


    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) { //생성이 언제인지 모르기때문엥
        this.requestURL = requestURL;
    }


    public void log(String message){
        System.out.println("[" + uuid +"]" + "["+ requestURL + "] " + message );
    }


    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString(); // 유니크한 아이디
        System.out.println("[" + uuid +"] request scope bean create:" + this);
    }


    @PreDestroy
    public void close(){
        System.out.println("[" + uuid +"] request scope bean close:" + this);
    }
}
