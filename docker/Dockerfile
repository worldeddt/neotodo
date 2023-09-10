FROM ubuntu:18.04
MAINTAINER EDDY
LABEL authors="worldeddt"
WORKDIR /home/springKafka

CMD ["java", "-jar", "/app/app.jar", "--spring.profiles.active=local"]
EXPOSE 8080

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get install -y net-tools &&  \
    apt-get clean;

COPY ../entrypoint/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
