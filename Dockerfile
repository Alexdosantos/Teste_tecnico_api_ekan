# Etapa 1: Build da aplicação com Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml e baixa dependências antes (para aproveitar cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código-fonte e gera o .jar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final (somente o jar)
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o jar da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
