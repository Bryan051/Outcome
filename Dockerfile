# 첫 번째 스테이지: 패키지 설치
FROM openjdk:17-jdk-slim

# JAR 파일을 저장할 디렉토리를 설정합니다.
WORKDIR /app

# JAR 파일을 복사합니다.////
#ARG JAR_FILE=build/libs/*.jar
COPY ./build/libs/Outcome-0.0.1-SNAPSHOT.jar app.jar
#COPY ${JAR_FILE} app.jar

# RUN ls -al /app 명령어를 사용하여 JAR 파일이 올바른 경로에 있는지 확인할 수 있습니다.
#RUN ls -al /app

# 애플리케이션 포트를 노출합니다.
EXPOSE 8080

# 애플리케이션을 실행합니다.
ENTRYPOINT ["java","-jar","app.jar"]
