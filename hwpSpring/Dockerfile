FROM openjdk:17-jdk

ARG JAR_FILE=build/libs/*.jar
# CodeBuild에서 전달된 환경 변수를 ENV로 받기
ENV JWT_SECRET=$JWT_SECRET
ENV DATABASE_URL=$DATABASE_URL
ENV DATABASE_USERNAME=$DATABASE_USERNAME
ENV DATABASE_PASSWORD=$DATABASE_PASSWORD

# Spring Boot 애플리케이션의 application.yml 파일에 환경 변수 주입
RUN echo "jwt:" >> ./application.yml
RUN echo "  secret: ${JWT_SECRET}" >> ./application.yml
RUN echo "spring:" >> ./application.yml
RUN echo "  profiles:" >> ./application.yml
RUN echo "    include: aws" >> ./application.yml
RUN echo "    active: dev" >> ./application.yml
RUN echo "  datasource:" >> ./application.yml
RUN echo "    url: ${DATABASE_URL}" >> ./application.yml
RUN echo "    username: ${DATABASE_USERNAME}" >> ./application.yml
RUN echo "    password: ${DATABASE_PASSWORD}" >> ./application.yml
RUN echo "    driver-class-name: org.mariadb.jdbc.Driver" >> ./application.yml

# 나머지 application.yml의 내용 추가
RUN echo "  jpa:" >> ./application.yml
RUN echo "    properties:" >> ./application.yml
RUN echo "      hibernate:" >> ./application.yml
RUN echo "        show_sql: true" >> ./application.yml
RUN echo "        format_sql: true" >> ./application.yml
RUN echo "        dialect: org.hibernate.dialect.MariaDBDialect" >> ./application.yml
RUN echo "    hibernate:" >> ./application.yml
RUN echo "      ddl-auto: update" >> ./application.yml

cat ./application.yml

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]