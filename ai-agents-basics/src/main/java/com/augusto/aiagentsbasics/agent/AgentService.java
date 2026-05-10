package com.augusto.aiagentsbasics.agent;

import com.augusto.aiagentsbasics.tools.MemoryTool;
import com.augusto.aiagentsbasics.tools.NoteTool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    interface Agent {
        String chat(String message);
    }

    private Agent agent;

    public AgentService(OpenAiChatModel model,
                        MemoryTool memoryTool,
                        NoteTool noteTool) {
        this.agent = AiServices.builder(Agent.class)
                .chatLanguageModel(model)
                .tools(memoryTool, noteTool)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }

    public String chat(String message) {
        return agent.chat(message);
    }
}
