package com.sample.mq.controller;

import com.sample.mq.constant.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author: Arthas
 * @date: 2019-07-19 23:18
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/mq")
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/sendMsg")
    public String sendMsg() {
        String message = "你好，今天是" + LocalDate.now();
        rabbitTemplate.convertAndSend(MqConstants.EXCHANGE_TEST, MqConstants.QUEUE_TEST, message);
        return "发送成功！";
    }

    @GetMapping(value = "/delay")
    public String sendDelayMsg() {
        String message = "你好，今天是" + LocalDate.now();
        rabbitTemplate.convertAndSend(MqConstants.EXCHANGE_TEST_DEMO_DELAY, MqConstants.QUEUE_TEST_DEMO_DELAY, message);
        log.info("向延迟队列【{}】发送了一条消息", MqConstants.QUEUE_TEST_DEMO_DELAY);
        return "发送成功！";
    }
}
