# =============================================================
# Stage 1 — Builder
# =============================================================
FROM gradle:8.7-jdk21 AS builder

WORKDIR /app

# Copy Gradle configuration files first to leverage Docker cache:
# dependencies are only re-downloaded when build.gradle or
# settings.gradle change
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Download dependencies as a separate cacheable layer
RUN gradle dependencies --no-daemon

# Copy source code and generate the fat jar
COPY src ./src
RUN gradle bootJar --no-daemon

# =============================================================
# Stage 2 — Runtime
# =============================================================
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Create a non-root user to run the application
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

# Copy only the jar from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Transfer ownership to the non-root user
RUN chown appuser:appgroup app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]