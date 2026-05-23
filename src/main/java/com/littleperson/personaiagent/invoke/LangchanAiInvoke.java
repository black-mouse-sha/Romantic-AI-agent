package com.littleperson.personaiagent.invoke;

import dev.langchain4j.community.model.dashscope.QwenChatModel;

public class LangchanAiInvoke {

    public static void main(String[] args) {
        QwenChatModel qwenChatModel = QwenChatModel.builder()
                .apiKey(TestApiKey.Api_key)
                .modelName("qwen-plus")
                .build();

        String answer = qwenChatModel.chat("你好我是梁炎烽 很高兴认识你");
        System.out.println(answer);

    }


}
