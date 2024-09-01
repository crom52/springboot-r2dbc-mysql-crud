# Use a Maven image for the build stage
FROM maven:3.8.1-openjdk-17-slim AS build

WORKDIR /app

# Copy the pom.xml file to download dependencies
COPY pom.xml .

# Download dependencies as specified in pom.xml
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src /app/src

# Build the application
RUN mvn package -DskipTests

# Use a different OpenJDK image for the run stage
FROM openjdk:17

WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
