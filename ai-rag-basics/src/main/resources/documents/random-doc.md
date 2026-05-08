# Introduction to RAG (Retrieval-Augmented Generation)

## What is RAG?
- A technique that combines information retrieval with AI text generation.
- Allows the model to query external data before generating answers.
- Commonly used in chatbots, internal documentation, and semantic search systems.

## Basic RAG Flow
1. The user sends a question.
2. The system converts the question into embeddings.
3. Relevant content is retrieved from a vector database.
4. Retrieved context is injected into the prompt.
5. The AI generates an answer based on that context.

## Important Concepts

### Embeddings
- Numerical representations of text.
- Used to measure semantic similarity between documents.

### Vector Database
- Database optimized for similarity search.
- Common examples:
    - ChromaDB
    - Weaviate
    - Pinecone
    - Qdrant

### Chunking
- Process of splitting documents into smaller pieces.
- Improves retrieval quality and reduces unnecessary context.

### Retrieval
- Step responsible for finding relevant chunks.
- Usually based on vector similarity search.

### Context Window
- Maximum amount of text the model can process at once.
- RAG helps provide only the most relevant context.

## Benefits of RAG
- Reduces AI hallucinations.
- Enables usage of private and updated data.
- No need to retrain the model.
- Works well with PDFs, wikis, and internal documentation.

## Common Tech Stack for RAG Projects
- Backend: Java + Spring Boot
- AI Model/API: Ollama, OpenAI, or LM Studio
- Embeddings: Sentence Transformers
- Vector Database: ChromaDB or PostgreSQL + pgvector
- Orchestration: LangChain4j

## Common Use Cases
- Chat with PDFs
- Semantic search
- Internal company assistants
- Intelligent FAQ systems
- Technical documentation assistants