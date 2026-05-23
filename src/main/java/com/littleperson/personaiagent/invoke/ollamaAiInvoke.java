package com.littleperson.personaiagent.invoke;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ollamaAiInvoke implements CommandLineRunner {

  @Resource
    private  ChatModel ollamaChatModel;

    @Override
    public void run(String... args) throws Exception {
        AssistantMessage assistantMessage = ollamaChatModel.call(new Prompt("你好我是梁炎烽")).getResult().getOutput();
        System.out.println("deepseek模型回复："+ assistantMessage.getText());
    }
}
