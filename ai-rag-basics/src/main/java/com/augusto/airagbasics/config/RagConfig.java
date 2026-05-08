package com.augusto.airagbasics.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.e5smallv2.E5SmallV2EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RagConfig {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

    @Value("${pgvector.host}")
    private String pgHost;

    @Value("${pgvector.port}")
    private Integer pgPort;

    @Value("${pgvector.database}")
    private String pgDatabase;

    @Value("${pgvector.user}")
    private String pgUser;

    @Value("${pgvector.password}")
    private String pgPassword;

    /*
        for understanding language and generating responses
     */
    @Bean
    public OpenAiChatModel chatModel() {
        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .modelName("openai/gpt-oss-120b:free")
                .build();
    }

    /*
        to convert text into numeric vectors
     */
    @Bean
    public EmbeddingModel embeddingModel() {
        return new E5SmallV2EmbeddingModel();
    }

    /*
        to store and retrieve the vectors
     */
    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return PgVectorEmbeddingStore.builder()
                .host(pgHost)
                .port(pgPort)
                .database(pgDatabase)
                .user(pgUser)
                .password(pgPassword)
                .table("documents")
                .dimension(384)
                .createTable(true)
                .build();
    }
}
