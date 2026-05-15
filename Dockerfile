# Java 17 실행 환경만 포함된 이미지이다.
# Gradle 빌드는 GitHub Actions에서 이미 끝나기 때문에 JDK 전체가 필요 없다.
FROM eclipse-temurin:17-jre

# 컨테이너 안에서 애플리케이션이 위치할 작업 디렉터리이다.
WORKDIR /app

ARG JAR_FILE=build/libs/*.jar
# GitHub Actions에서 만든 JAR 파일을 이미지 안으로 복사한다.
COPY ${JAR_FILE} app.jar

# 컨테이너가 실행될 때 java -jar app.jar를 실행한다.
ENTRYPOINT ["java", "-jar", "app.jar"]
