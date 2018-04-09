FROM openjdk:10-jdk-slim

LABEL maintainer = "biosphere.dev@gmx.de"

COPY target/discordLog-1.0-SNAPSHOT-jar-with-dependencies.jar discordLog.jar

ENTRYPOINT ["java", "-jar", "discordLog.jar"]
