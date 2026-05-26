package com.littleperson.personaiagent.invoke;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ollamaAiInvoke implements CommandLineRunner {

    @Resource(name = "ollamaChatModel")      //
    private ChatModel ollamaChatModel;

    @Resource(name = "dashScopeChatModel")   //
    private ChatModel dashScopeChatModel;

    @Override
    public void run(String... args) throws Exception {
        String userMessage = "你好，你是谁？";

        // 1. 调用 Ollama (deepseek-r1:1.5b)
        System.out.println("=== 测试 Ollama 模型 ===");
        try {
            AssistantMessage ollamaMsg = ollamaChatModel
                    .call(new Prompt(userMessage))
                    .getResult()
                    .getOutput();
            System.out.println("Ollama 回复：" + ollamaMsg.getText());
        } catch (Exception e) {
            System.err.println("Ollama 调用失败：" + e.getMessage());
            e.printStackTrace();  // 建议加上完整堆栈
        }

        // 2. 调用 DashScope (qwen-plus)
        System.out.println("\n=== 测试 DashScope 模型 ===");
        try {
            AssistantMessage dashScopeMsg = dashScopeChatModel
                    .call(new Prompt(userMessage))
                    .getResult()
                    .getOutput();
            System.out.println("DashScope 回复：" + dashScopeMsg.getText());
        } catch (Exception e) {
            System.err.println("DashScope 调用失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}