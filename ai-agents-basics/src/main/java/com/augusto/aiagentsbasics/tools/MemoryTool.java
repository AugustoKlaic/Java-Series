package com.augusto.aiagentsbasics.tools;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MemoryTool {

    private final StringRedisTemplate redis;

    public MemoryTool(StringRedisTemplate redis) {
        this.redis = redis;
    }

    @Tool("Save information in memory to remember later. Use when user asks for remembering something.")
    public String saveInMemory(String key, String value) {
        redis.opsForValue().set(key,value);
        return "Information saved sucessfully! Key: " + key;
    }

    @Tool("Search one information saved in memory. Use when user asks for something that were saved before.")
    public String loadFromMemory(String key) {
        String value = redis.opsForValue().get(key);

        if (value == null) {
            return "None information were found for key: " + key;
        }

        return "Information found - " + key + ": "  + value;
    }

    @Tool("List all keys saved in memory. Use when user asks to see all information saved")
    public String dumpMemory() {
        var keys = redis.keys("*");
        if (keys.isEmpty()) {
            return "There is no information to dump!";
        }

        return "Keys found: " + String.join(",", keys);
    }
}
