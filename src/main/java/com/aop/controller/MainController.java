package com.aop.controller;

import com.aop.performance.LogExecutionTime;
import com.aop.performance.LogKafkaMessageTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @LogExecutionTime
    @LogKafkaMessageTrace
    @GetMapping("/teste")
    public String enableTest(){
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        performanceTeste();
        return "Sucesso";
    }

    private void performanceTeste(){
        logger.info("performanceTeste");
        for (int i=0;i<1000000000;i++){
            int a = i;
        }
        this.metodo2();
    }

    private void metodo2(){
        logger.info("metodo2");
    }
}
