FROM openjdk
COPY gitCliShell.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","gitCliShell.jar"]
