package edu.zut.vlog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class VlogApplication {

    @Value("${server.port}")
    private static String serverPort;

    public static void main(String[] args) {
        SpringApplication.run(VlogApplication.class, args);
        log.info("项目启动中...localhost:{}",serverPort);
    }

}
