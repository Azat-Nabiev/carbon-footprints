# Initializing the build environment
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app

# Copies the source code from the local src directory
# to the src directory inside the /app directory of the Docker image
COPY src /app/src

# Copies the Maven project object model (POM) file from the local directory
# to the /app directory inside the Docker image.
COPY pom.xml /app

# Executes Maven to clean previous builds and packages the application,
# skipping unit tests during the build process
RUN mvn clean package -DskipTests

# Starts a new build stage using the OpenJDK 17 slim image
# to create a smaller runtime environment.
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copies the packaged JAR file from the /app/target directory of the build stage
# to the /app directory of the runtime stage, renaming it to app.jar
COPY --from=build /app/target/carbon-footprints-0.0.1-SNAPSHOT.jar /app/app.jar

# Configures the container to run as an executable; when the container starts,
# it will automatically execute the java -jar app.jar command to start the application.
ENTRYPOINT ["java", "-jar", "app.jar"]




