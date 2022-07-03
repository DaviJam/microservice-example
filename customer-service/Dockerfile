# From maven and java image
FROM maven:3.8.4-openjdk-11

# Define environment variable
ARG PROJECT

# Update
RUN apt-get update

# Create data directory in container and copy content into it
COPY ./${PROJECT} ./data

# Choose data directory as working directory
WORKDIR ./data

# Clean and create package using maven project management tool. Skip running test
RUN mvn clean package -DskipTests=true

# Expose port 80 on this container
EXPOSE 80
EXPOSE 8082

# Go to target directory
WORKDIR ./target

# Run the springboot app
CMD ["sh","-c","java -jar ${PROJECT}-0.0.1-SNAPSHOT.jar"]