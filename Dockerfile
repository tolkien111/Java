# Choose the base image with Java 17
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /address_searcher_app

# Copy the compiled JAR file into the image
# Note: Make sure to adjust the JAR file name according to the actual file name in your project
COPY target/task2googleapi-0.0.1-SNAPSHOT.jar /address_searcher_app/app.jar

# Run the application
CMD ["java", "-jar", "/address_searcher_app/app.jar"]
