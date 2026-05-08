package com.augusto.airagbasics.controller;

import com.augusto.airagbasics.chat.ChatService;
import com.augusto.airagbasics.ingestion.DocumentIngestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai-rag")
public class ChatController {

    private final ChatService chatService;
    private final DocumentIngestionService documentIngestionService;

    public ChatController(ChatService chatService, DocumentIngestionService documentIngestionService) {
        this.chatService = chatService;
        this.documentIngestionService = documentIngestionService;
    }

    @PostMapping("/ingest")
    public String ingestDocument() {
        documentIngestionService.ingestDocument();
        return "Ingestion Done";
    }

    @DeleteMapping("/ingest")
    public String deleteIngestion() {
        documentIngestionService.deleteIngestion();
        return "Ingestion Cleared";
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String question) {
        return chatService.chat(question);
    }
}
