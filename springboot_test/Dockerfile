# Use a specific version of the OpenJDK image
FROM adoptopenjdk:17-jdk-hotspot

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/*.jar app.jar

# Specify the command to run on container startup
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]