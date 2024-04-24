# Use base image with Java 17 and Maven
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app

# Copy application source code
COPY src /app/src
COPY pom.xml /app

# Download dependencies and build the application
RUN mvn clean package -DskipTests

# Use base image with Java 17
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built files from the previous stage
COPY --from=build /app/target/carbon-footprints-0.0.1-SNAPSHOT.jar /app/app.jar

# Run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]