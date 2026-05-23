package com.littleperson.personaiagent.invoke;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云灵积 ai http 调用
 */
public class HttpAiInvoke {

    // 请从环境变量或配置中获取真实的 API Key
    private static final String API_KEY = TestApiKey.Api_key;

    private static final String GENERATION_URL = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

    /**
     * 调用通义千问模型进行对话
     * @param systemPrompt 系统提示词（角色设定）
     * @param userMessage  用户消息
     * @return 模型的回复内容
     */
    public static String chat(String systemPrompt, String userMessage) {
        // 构建请求体 JSON
        JSONObject body = new JSONObject();
        body.set("model", "qwen-plus");

        // 构建 messages 数组
        JSONArray messages = new JSONArray();
        JSONObject systemMsg = new JSONObject();
        systemMsg.set("role", "system");
        systemMsg.set("content", systemPrompt);
        messages.add(systemMsg);

        JSONObject userMsg = new JSONObject();
        userMsg.set("role", "user");
        userMsg.set("content", userMessage);
        messages.add(userMsg);

        JSONObject input = new JSONObject();
        input.set("messages", messages);
        body.set("input", input);

        // 设置 parameters
        JSONObject parameters = new JSONObject();
        parameters.set("result_format", "message");
        body.set("parameters", parameters);

        // 发送 POST 请求
        try (HttpResponse response = HttpRequest.post(GENERATION_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .body(body.toString())
                .execute()) {

            if (response.isOk()) {
                // 解析响应，提取 assistant 的回复内容
                JSONObject respJson = JSONUtil.parseObj(response.body());
                JSONObject output = respJson.getJSONObject("output");
                JSONArray choices = output.getJSONArray("choices");
                if (choices != null && !choices.isEmpty()) {
                    JSONObject firstChoice = choices.getJSONObject(0);
                    JSONObject message = firstChoice.getJSONObject("message");
                    return message.getStr("content");
                } else {
                    throw new RuntimeException("响应中未找到回复内容");
                }
            } else {
                throw new RuntimeException("请求失败，状态码：" + response.getStatus() + "，响应：" + response.body());
            }
        }
    }

    // 示例用法
    public static void main(String[] args) {
        String reply = chat("You are a helpful assistant.", "你是谁？");
        System.out.println("模型回复：" + reply);
    }
}
