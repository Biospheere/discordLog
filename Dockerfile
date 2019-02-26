FROM openjdk:11-jdk-slim

LABEL maintainer = "biosphere.dev@gmx.de"

COPY target/discordLog-1.0-SNAPSHOT-shaded.jar discordLog.jar

ENTRYPOINT ["java", "-jar", "discordLog.jar"]
