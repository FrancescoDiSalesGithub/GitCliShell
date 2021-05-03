FROM openjdk
COPY gitCliShell-0.2.1.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","gitCliShell.jar"]


