FROM openjdk:17-jdk

ARG JAR_FILE=build/libs/*.jar
# CodeBuild에서 전달된 환경 변수를 ENV로 받기
ENV JWT_SECRET=$JWT_SECRET
ENV DATABASE_URL=$DATABASE_URL
ENV DATABASE_USERNAME=$DATABASE_USERNAME
ENV DATABASE_PASSWORD=$DATABASE_PASSWORD

# Spring Boot 애플리케이션의 application.yml 파일에 환경 변수 주입
RUN echo "jwt:" >> /app/application.yml
RUN echo "  secret: ${JWT_SECRET}" >> /app/application.yml
RUN echo "spring:" >> /app/application.yml
RUN echo "  profiles:" >> /app/application.yml
RUN echo "    include: aws" >> /app/application.yml
RUN echo "    active: dev" >> /app/application.yml
RUN echo "  datasource:" >> /app/application.yml
RUN echo "    url: ${DATABASE_URL}" >> /app/application.yml
RUN echo "    username: ${DATABASE_USERNAME}" >> /app/application.yml
RUN echo "    password: ${DATABASE_PASSWORD}" >> /app/application.yml
RUN echo "    driver-class-name: org.mariadb.jdbc.Driver" >> /app/application.yml

# 나머지 application.yml의 내용 추가
RUN echo "  jpa:" >> /app/application.yml
RUN echo "    properties:" >> /app/application.yml
RUN echo "      hibernate:" >> /app/application.yml
RUN echo "        show_sql: true" >> /app/application.yml
RUN echo "        format_sql: true" >> /app/application.yml
RUN echo "        dialect: org.hibernate.dialect.MariaDBDialect" >> /app/application.yml
RUN echo "    hibernate:" >> /app/application.yml
RUN echo "      ddl-auto: update" >> /app/application.yml

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]