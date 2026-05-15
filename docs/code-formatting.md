# 코드 포매팅

## 기준

본 프로젝트는 Java 코드 포매팅 기준으로 Google Java Style Guide를 따른다.

팀 논의 결과, 별도의 4-size indent 설정을 사용하지 않고 Google Java Style Guide 원본 기준을 적용한다.

## 적용 방식

팀원 개인 IDE 설정에 따라 코드 스타일이 달라지는 것을 방지하기 위해 Gradle 기반 포매터인 Spotless를 사용한다.

현재 설정은 `build.gradle`에 정의되어 있으며, Java 파일에 대해 다음 규칙을 적용한다.

- `src/**/*.java` 파일 대상 포매팅
- Google Java Format 적용
- 사용하지 않는 import 제거
- 줄 끝 공백 제거
- 파일 마지막 개행 보장

## 명령어

포매팅을 실제 코드에 적용하려면 아래 명령어를 실행한다.

```bash
./gradlew spotlessApply
```

포매팅 규칙을 지키고 있는지 검증하려면 아래 명령어를 실행한다.

```bash
./gradlew spotlessCheck
```

`spotlessCheck`가 실패하면 출력에 포매팅 위반 파일과 변경 diff가 표시된다. 이 경우 `spotlessApply`를 실행한 뒤 다시 `spotlessCheck`로 확인한다.

## PR 전 확인

PR을 올리기 전 아래 명령어가 성공하는지 확인한다.

```bash
./gradlew spotlessCheck
```

테스트 실행은 아래 명령어로 확인한다.

```bash
./gradlew test
```

현재 테스트는 기본 `local` profile을 사용하며, `localhost:3306/kakaotalk` MySQL datasource에 의존한다. 로컬 DB가 준비되어 있지 않으면 Spring context 로딩 과정에서 테스트가 실패할 수 있다.
