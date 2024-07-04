# Sử dụng hình ảnh chính thức của OpenJDK 17
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR của ứng dụng vào container
COPY target/BE_Chat-0.0.1-SNAPSHOT.jar /app/BE_Chat-0.0.1-SNAPSHOT.jar

# Thiết lập biến môi trường cho Spring Boot
ENV SPRING_APPLICATION_NAME=demo \
    SERVER_PORT=8081 \
    SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/chat \
    SPRING_DATASOURCE_USERNAME=root \
    SPRING_DATASOURCE_PASSWORD=123456 \
    SPRING_JPA_HIBERNATE_DDL_AUTO=update \
    SPRING_JPA_SHOW_SQL=true \
    SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQL8Dialect \
    SPRING_JPA_HIBERNATE_NAMING_PHYSICAL_STRATEGY=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl \
    SPRING_JPA_PROPERTIES_HIBERNATE_HBM2DDL_AUTO=update \
    SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT_STORAGE_ENGINE=innodb \
    LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_SECURITY=DEBUG \
    JWT_EXPIRATION_MS=86400000 \
    EXPIRATION_MS=86400000 \
    JWT_SECRET=htx7AOKmtPI2g8IXWedoLIbpSD1MwseB7mlgWIkudKdktZ8IPYWpmW7qx4OG \
    SPRING_SERVLET_MULTIPART_ENABLED=true \
    SPRING_SERVLET_MULTIPART_MAX_FILE_SIZE=10MB \
    SPRING_SERVLET_MULTIPART_MAX_REQUEST_SIZE=10MB \
    SPRING_CODEC_MAX_IN_MEMORY_SIZE=2MB \
    ROOT_FILE_UPLOADS=src/main/java/com/example/demo/resources

# Chỉ định lệnh để chạy file JAR
ENTRYPOINT ["java", "-jar", "BE_Chat-0.0.1-SNAPSHOT.jar"]


 