package com.augusto.airagbasics.ingestion;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class DocumentIngestionService {

    private static final Integer CHUNK_SIZE = 500;
    private static final Integer CHUNK_OVERLAP = 50;

    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;

    private final String documentPath;


    public DocumentIngestionService(EmbeddingModel embeddingModel,
                                    EmbeddingStore<TextSegment> embeddingStore,
                                    @Value("${documents.path}") String documentPath) {
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
        this.documentPath = documentPath;
    }

    public void ingestDocument() {
        List<Document> documents = FileSystemDocumentLoader.loadDocuments(Path.of(documentPath));

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(CHUNK_SIZE, CHUNK_OVERLAP))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        ingestor.ingest(documents);
        System.out.println("Indexed " + documents.size() + " documents");
    }
}
