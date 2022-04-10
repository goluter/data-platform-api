FROM openjdk:17-oracle

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY ./build/libs/*.jar application.jar

EXPOSE 8080

CMD ["java", "-jar", "application.jar", "-Dspring.datasource.username=$GOVEY_DATASOURCE_URL"]