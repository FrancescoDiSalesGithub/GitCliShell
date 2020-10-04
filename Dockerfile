FROM openjdk
COPY gitcli.jar /usr/app
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","gitcli.jar"]
