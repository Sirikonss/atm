FROM maven:3.6.1-jdk-11
WORKDIR /usr/atm
COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY src ./src/
RUN mvn compile
EXPOSE 8090
CMD ["mvn", "spring-boot:run"]