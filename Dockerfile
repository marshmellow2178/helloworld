# JDK 공식 17버전
FROM eclipse-temurin:17-jdk

# 파일 복사하기
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]