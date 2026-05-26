package com.littleperson.personaiagent;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class PersonAiAgentApplication {

//    @Autowired
//    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(PersonAiAgentApplication.class, args);
    }

//    @PostConstruct
//    public void printChatModelBeanNames() {
//        String[] beanNames = context.getBeanNamesForType(ChatModel.class);
//        System.out.println("=== 发现的 ChatModel Bean 名称 ===");
//        System.out.println(Arrays.toString(beanNames));
//        // 同时打印每个 Bean 的运行时类型（便于确认）
//        for (String name : beanNames) {
//            Object bean = context.getBean(name);
//            System.out.println("Bean 名称: " + name + " -> 类型: " + bean.getClass().getName());
//        }
//    }
}