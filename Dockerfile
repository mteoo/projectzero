# Usando o java 8
FROM openjdk:8-jdk-alpine

# Nome do pacote gerado pelo Maven
ARG JAR_FILE=target/projectzero-0.0.1-SNAPSHOT.jar

# Altera internamente para o diretório /opt/app
WORKDIR /opt/projectzero

# Copia a aplicação com um nome diferente para WORKDIR
COPY ${JAR_FILE} app.jar

# Expôe a porta 8081
EXPOSE 8081

# Executa o comando java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]





