FROM openjdk:17-oracle

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

ARG GOVEY_DATASOURCE_URL
ENV GOVEY_DATASOURCE_URL ${GOVEY_DATASOURCE_URL}

COPY ./build/libs/*.jar application.jar

EXPOSE 8080

# NOTE: 환경변수로 변경
CMD ["java", "-jar", "-Dspring.datasource.url=jdbc:postgresql://gopush.app:5432/govey", "application.jar"]