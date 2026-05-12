# 🍋 합동 세미나 서버파트 컨밴션

- [Git/Github 컨벤션](https://www.notion.so/a88b16d857ec82918db981ad58f02417)
- [코드 컨벤션](https://www.notion.so/e6ab16d857ec835081d3816115b15d00)
- [역할 분담](https://www.notion.so/916b16d857ec82ac8fec81a16ca3961f)
- [아키텍처](https://www.notion.so/621b16d857ec8259a33c011311423b9d)
- [API 설계](https://www.notion.so/0d8b16d857ec82aaa55101cbce8a7c6d)
- [회의록](https://www.notion.so/59fb16d857ec82a8bc4101d495a21968)
- [기술스택](https://www.notion.so/3b1b16d857ec82d2981a017659065314)
- [ERD](https://www.notion.so/d4cb16d857ec83bebe5a01c4502a1718)

- application-prod.yml 추가해야됨 (배포할때)

## Git/Github 컨벤션

### Git / GitHub 컨벤션
#### 1. 브랜치 전략
**결정: Git Flow의 단순화 (`main` + `develop` + `feature/*`)**
Git Flow에서 release/hotfix를 제외한 **3종 단순화 모델**
<table header-row="true">
<tr>
<td>브랜치</td>
<td>역할</td>
<td>머지 대상</td>
</tr>
<tr>
<td>`main`</td>
<td>운영 배포본. 항상 안정 상태.</td>
<td>`develop`에서 릴리즈 시점에만 머지</td>
</tr>
<tr>
<td>`develop`</td>
<td>통합 개발 브랜치. 다음 릴리즈 후보.</td>
<td>`feature/*`에서 PR로 머지</td>
</tr>
<tr>
<td>`feature/*`</td>
<td>기능 단위 작업 브랜치</td>
<td>`develop`로 PR</td>
</tr>
</table>
develop 브랜치가 default 브랜치
**참고 자료**: [A successful Git branching model — Vincent Driessen](https://nvie.com/posts/a-successful-git-branching-model/)
#### 2. 브랜치 네이밍
**결정: `prefix/#issuenumber/description`**
이 형식은 [Conventional Branch](https://conventional-branch.github.io/)의 변형이며, prefix는 [Conventional Commits](https://www.conventionalcommits.org/)에서 정의한 type을 사용한다.
**Prefix 종류** (Conventional Commits 기준):
<table header-row="true">
<tr>
<td>prefix</td>
<td>의미</td>
</tr>
<tr>
<td>`feat`</td>
<td>새 기능 추가</td>
</tr>
<tr>
<td>`fix`</td>
<td>버그 수정</td>
</tr>
<tr>
<td>`refactor`</td>
<td>동작 변화 없는 구조 개선</td>
</tr>
<tr>
<td>`chore`</td>
<td>빌드/설정/잡일 (의존성 추가, gradle 수정 등)</td>
</tr>
<tr>
<td>`docs`</td>
<td>문서 변경</td>
</tr>
<tr>
<td>`style`</td>
<td>코드 포맷 변경 (동작 변화 없음)</td>
</tr>
<tr>
<td>`test`</td>
<td>테스트 코드 추가/수정</td>
</tr>
<tr>
<td>~~`perf`~~</td>
<td>~~성능 개선~~</td>
</tr>
<tr>
<td>~~`build`~~</td>
<td>~~빌드 시스템 변경~~</td>
</tr>
<tr>
<td>~~`ci`~~</td>
<td>~~CI 설정 변경~~</td>
</tr>
</table>
**예시**:
- `feat/#12/user-signup`
- `fix/#34/login-redirect`
- `refactor/#56/extract-user-service`
- `chore/#78/add-swagger-dependency`
#### 3. 커밋 메시지 컨벤션
**결정**:
```plain text
{prefix}: {제목}

- 상세설명1 (선택)
- 상세설명2 (선택)
```
prefix는 브랜치 prefix와 동일하다 (Conventional Commits)
**예시**:
```plain text
feat: 회원가입 API 추가

- 이메일 중복 검사 로직 추가
- 비밀번호 해싱 적용
- BaseResponse 형식으로 응답 통일
```
```plain text
fix: 로그인 실패 시 401 대신 500이 떨어지던 문제 수정
```
**참고 자료**: [Conventional Commits 1.0.0](https://www.conventionalcommits.org/)
#### 4. PR 템플릿
```plain text
#### 📌 Summary
_해당 PR에 대한 작업 내용을 요약하여 작성해주세요._

- close #

#### 📄 Tasks
_해당 PR에 수행한 작업을 작성해주세요._

-

#### 🔍 To Reviewer
_리뷰어에게 요청하는 내용을 작성해주세요._

-
```
#### 5. 이슈 템플릿
이슈 종류 별 템플릿 설정 / [깃허브](https://github.com/SOPT-all/38-COLLABORATION-SERVER-KAKAOTALK/tree/develop/.github/ISSUE_TEMPLATE) 참고
![](https://prod-files-secure.s3.us-west-2.amazonaws.com/0f901230-6100-44af-9719-e47dbcd41fde/10e260ae-5e6e-4e0b-bbc9-e2e8b03a77eb/image.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIAZI2LB466T5ED6BUE%2F20260509%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20260509T235711Z&X-Amz-Expires=3600&X-Amz-Security-Token=IQoJb3JpZ2luX2VjECgaCXVzLXdlc3QtMiJGMEQCIB2pzgtALYP2OGkPE5gDjSS4qx29KFxFt52SNi6OsoX%2BAiAQncDWSbB%2B45HFvFbvJSAco9cSlAzuZNb4t3HIiMlV2CqIBAjx%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDYzNzQyMzE4MzgwNSIM2fvsgAlgoF9fqvbpKtwD%2FC6B1Q%2BzP0B6iasnLwwZ7%2BJWaSI9upDQ9DcYJg637Z5tBswEyCzV2%2FZ1az5ZRW2DSfOf11Y6kvFGmPsxYYw2SpReNJM%2FGyxhPaTAE%2F9I67tmyvERyU09%2FESxLmhS1P5QiJQa0P0OQMjbicTRFdFk%2FRwncP67t1SUxsyWbRoSWe19PQvs3hFLNNPtCD%2FF2va%2Bm2rnRr0QzCfIcw6WBjP%2BjHyBmEPwH5D9Te2Tgx24uon8u0riZLSSWPcAKvYCG5zXPUvqwJPqCXtGhiMneVb6rIN5YfdY5DYGEZoj3RboyYH0mqZO%2BngkgU7E3QQxOA%2B%2B9U27g3nlLAvOga5kM4Ct%2B2myOaZA3XQR3TI8H1f0IrEm6t2R4YYU2QPJm9wNYd%2B54U%2FBmnsecOZOio9ROB1Ms1twHRjvLdPr8aQfQeJpbWjybbAZmHcPOUn6P4BA5ZnUYwUXg4jgwkDyBBse04NIHQAq%2FrhZzi7IHzpzvFsJiROGhUnDE7wVnEiEB9lGnqc1Ex70CG9PDZMWvoTFCTXEtuTblms6fkc3Juoa3RzlFgvf4Bkrzc9oBuydzX921AAx4zRJ9CPYrlaMvPZk%2BRGOMfN3S0of%2BAyX4bYZMt0%2FS8arpBNDHRhUgEUASwUwioj%2FzwY6pgF0Y%2Fcln2pDpwCPyzmR%2BYnnWXoh4YNwjzHm0CVS%2B%2F92rvpv2N%2FO25kZH9CYta4JkFgnLDWDzl31oLkmcWJwRkE7wI1RlkaCeI%2FhlVSRnTDqVsVC8vUvuxu0InKtr043G8qRu7KkCjtqz9irJUeBIIs9r6rh8Zj%2BC1h4OJHFZ%2BHrzg%2FJR7VOAnjvz5FpWTCgtzdGTjAqf5HfwTkdjlmUqvynL6fkUNsw&X-Amz-Signature=d9207a0381b7e3a0f5c6d149e78d9efe45f0ec607d187212d4dd6a9df2a8f326&X-Amz-SignedHeaders=host&x-amz-checksum-mode=ENABLED&x-id=GetObject)
#### 6. 머지 정책
<table header-row="true">
<tr>
<td>항목</td>
<td>결정</td>
</tr>
<tr>
<td>PR 리뷰 인원</td>
<td>**최소 1명**</td>
</tr>
<tr>
<td>셀프 머지</td>
<td>**가능** (단, 리뷰 1명 이상 받은 후)</td>
</tr>
<tr>
<td>머지 방식</td>
<td>**Squash and Merge (develop 히스토리 깔끔)**</td>
</tr>
<tr>
<td>머지 전 필수 통과</td>
<td>CI 빌드 + 테스트 통과</td>
</tr>
<tr>
<td>충돌 해결 책임</td>
<td>PR 작성자가 base 브랜치를 최신화한 뒤 충돌을 해결한다</td>
</tr>
</table>

## 코드 컨벤션

[스타일 / 포맷팅](https://www.notion.so/6beb16d857ec823283e301a873518d79)
[네이밍 컨벤션](https://www.notion.so/3c8b16d857ec8368a19b01b34d87b60c)
[클래스](https://www.notion.so/9a3b16d857ec821baa6b011fe5ac0bab)
[응답 / 예외 처리](https://www.notion.so/648b16d857ec83b6b54801856941b03b)
[문서화(swagger) / 로깅](https://www.notion.so/af3b16d857ec826eae7501caacee096a)

### 스타일 / 포맷팅

**기본**: Google Java Style Guide를 채택한다.
**IntelliJ 설정**:
- `Settings → Editor → Code Style → Java → Scheme`: GoogleStyle (XML import 또는 빌트인 선택)
- `Tab and Indents`:
  - Tab size: **4**
  - Indent: **4**
  - Continuation Indent: **4**
  - Label Indent: **0**
- `Hard wrap at`: **100**
- `Wrap on typing`: 사용
- `Reformat on Save`: 사용
- `Optimize imports on the fly`: 사용
**참고 자료**: [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

### 네이밍 컨벤션

<table header-row="true">
<tr>
<td>대상</td>
<td>규칙</td>
<td>예시</td>
</tr>
<tr>
<td>변수</td>
<td>camelCase</td>
<td>`userName`, `totalCount`</td>
</tr>
<tr>
<td>클래스</td>
<td>PascalCase</td>
<td>`UserService`, `BaseResponse`</td>
</tr>
<tr>
<td>패키지</td>
<td>모두 소문자</td>
<td>`domain.user.controller`</td>
</tr>
<tr>
<td>상수 (`static final`)</td>
<td>UPPER_SNAKE_CASE</td>
<td>`MAX_RETRY_COUNT`</td>
</tr>
<tr>
<td>도메인 클래스 접미사</td>
<td>계층 명을 그대로 붙임</td>
<td>`UserController`, `UserService`, `UserRepository`</td>
</tr>
</table>
**메서드 prefix 가이드**:
<table header-row="true">
<tr>
<td>분류</td>
<td>prefix</td>
<td>의미</td>
</tr>
<tr>
<td>데이터 조회</td>
<td>`get~`</td>
<td>단순 조회</td>
</tr>
<tr>
<td></td>
<td>`find~`</td>
<td>조건에 따른 조회 (Optional 반환 가능)</td>
</tr>
<tr>
<td></td>
<td>`fetch~`</td>
<td>외부 시스템에서 가져오기</td>
</tr>
<tr>
<td></td>
<td>`load~`</td>
<td>내부 로딩, 상태 업데이트 포함</td>
</tr>
<tr>
<td>상태 확인</td>
<td>`is~`</td>
<td>변수 상태/속성 확인 (boolean 반환)</td>
</tr>
<tr>
<td></td>
<td>`has~`</td>
<td>소유/존재 여부</td>
</tr>
<tr>
<td></td>
<td>`can~`</td>
<td>수행 가능 여부</td>
</tr>
<tr>
<td>데이터 변경</td>
<td>`set~`</td>
<td>필드/값 수정</td>
</tr>
<tr>
<td></td>
<td>`add~`</td>
<td>컬렉션에 항목 추가</td>
</tr>
<tr>
<td></td>
<td>`update~`</td>
<td>기존 데이터 갱신</td>
</tr>
<tr>
<td></td>
<td>`create~`</td>
<td>새 객체 생성</td>
</tr>
<tr>
<td></td>
<td>`save~`</td>
<td>영속 저장</td>
</tr>
<tr>
<td></td>
<td>`register~`</td>
<td>시스템 등록 (회원가입 등)</td>
</tr>
<tr>
<td></td>
<td>`remove~`, `delete~`</td>
<td>항목 삭제</td>
</tr>
<tr>
<td></td>
<td>`replace~`</td>
<td>기존 항목 교체</td>
</tr>
<tr>
<td>초기화/변환</td>
<td>`init~`</td>
<td>객체 초기 설정</td>
</tr>
<tr>
<td></td>
<td>`reset~`</td>
<td>기본 상태로 재설정</td>
</tr>
<tr>
<td></td>
<td>`convert~`</td>
<td>타입 변환</td>
</tr>
<tr>
<td></td>
<td>`to~`</td>
<td>다른 타입으로 매핑 (예: `toEntity`, `toResponse`)</td>
</tr>
</table>
---

### 클래스

##### 클래스 내 멤버 순서
다음 순서로 작성한다.
1. 상수 (`static final`)
2. `static` 변수
3. 인스턴스 변수
4. 생성자
5. `public` 메서드 (getter/setter는 다른 public 메서드 다음에 배치)
6. `protected` 메서드
7. package-private 메서드
8. `private` 메서드
9. nested 클래스 / 인터페이스
외부에서 클래스를 읽을 때 위에서부터 "공개된 면 → 내부 구현"으로 자연스럽게 흐른다. 또한 IntelliJ의 Structure 창(⌘ 7 단축키, 좌측 사이드바에 클래스 멤버를 트리로 보여주는 패널)에서 어떤 클래스를 열어도 같은 종류의 멤버가 같은 위치에 있어, 매번 어디 있는지 찾을 필요 없이 빠르게 점프할 수 있다.
![좌측 하단이 Structure 창](https://prod-files-secure.s3.us-west-2.amazonaws.com/0f901230-6100-44af-9719-e47dbcd41fde/503c6fe6-33be-4cfc-8c2c-b114956589cd/image.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIAZI2LB466Z3HE5S42%2F20260509%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20260509T235821Z&X-Amz-Expires=3600&X-Amz-Security-Token=IQoJb3JpZ2luX2VjECgaCXVzLXdlc3QtMiJHMEUCIQCmLpfLIJ7x4pxQisVAw89gNdKN7HtOXL%2FdnxFadS4cCAIgfWhR40A%2Fab2g2YuLaDMEO5J2fZZsNExXLGHDHhXgx6QqiAQI8f%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgw2Mzc0MjMxODM4MDUiDF8MKrz0%2BvnRL0hTiCrcA82bJbKJ9fBoZzZvxVKFkhwg5rRfh2bgiAYJMrS6SIJa7Jcjy%2B3wR93%2B%2FFY9pOahV7PARHbeNBPmFtPHm29DhlLsfBPcN6YiEnxeaWhnluKFQRKJ5UfOMiH7xj8yRz36fOEJT1yrKXlvu7qljk8lr8gAxgfxKj0ZSQHRQOsEi5sBCfr7eO4wOEUtGEctc74QweWRFSzHwJpXgjY2ecOPZoGyYHrNsn5ogPX4z7tcYabAl3xvcX6vrDGaN1vr78VvPfdOwEZXd0nGb6p%2Fb6YG%2FTa8x2Fm1%2BKla6PLdw6oocIQy6uIey8nE9gAoCcZUfiVrkiEgNYQ456Pcm7FNQt80WQuOJye%2FmBdiHaAKzWIaAxveMnH%2BQjBYkRhZbgZpRvleoQkwzwWe3bP14G%2BC%2FotdxnpocCpSkGpzDvKKiPHrPPvKbovNSY6atwyz3f11PzWo%2FWixU%2FBDg%2BrEhIGl7IoVfkfff7JDC4ktlfqlzRkASCG5JxE0B%2FIsVJr7B5UEdjtSAwuyCJGEqpetTjTKFRO75erg0gd9JfbZDEApKnh9WuzNtwPUL4TpZwytGQMTh63a8tBG969fAzERNiKmXEcfg16iMHv5EO72%2FzkELv7hcBlez2pMtF85xiSE6AhMMWH%2F88GOqUBiACmQExWBTNXTS0L6oAo2dyu0fg43XHp%2F0GCYoZmNAwOWXdb6fhSLhYASFZO6VYorpB%2BAEC2Hkj4zzcFr%2FaB335k9T74TWJDh2xiGNb6DWzuUGPUOmZFHy9WOtgHl4uyXvQLciaGSvBBNOoEd77x1Y%2BIbOToEYCAkkBsAE7lTmfGMkzefD%2BFnkw7Z9b%2Btm%2B5RkcQof4Gu8Kjzw4UXKz1W328PyMj&X-Amz-Signature=3b7aa947e792f541343172ef6b2e0efdbf66fc5a35d521dba78f409224239d1c&X-Amz-SignedHeaders=host&x-amz-checksum-mode=ENABLED&x-id=GetObject)

##### DTO / Entity 작성
###### Entity
- 클래스 어노테이션: `@Entity`, `@Getter`, `@NoArgsConstructor(access = AccessLevel.PROTECTED)`
- ID: `@GeneratedValue(strategy = GenerationType.IDENTITY)`
- 빌더는 클래스 단위 `@Builder`보다 정적 팩토리 메서드 안에서 사용하는 편을 권장한다.
- Setter는 노출하지 않는다. 변경 메서드를 명시적으로 정의한다 (예: `changeName`, `updateProfile`).
- 비즈니스 로직 위치
  - 자기 자신의 상태 변경/검증 로직은 Entity 내부에 메서드로 둔다 (예: user.changeName(newName))
  - 여러 Entity 조율, repository 호출, 트랜잭션 경계, 외부 시스템 호출은 Service에 둔다.
<table header-row="true">
<tr>
<td>구분</td>
<td>어디에 둘까</td>
<td>예시</td>
</tr>
<tr>
<td>자기 자신의 상태를 변경/검증/계산하는 비즈니스 로직</td>
<td>Entity 내부</td>
<td>User.changeName(newName), Order.cancel(),</td>
</tr>
<tr>
<td>Money.add(other)</td>
<td></td>
<td></td>
</tr>
<tr>
<td>여러 Entity를 조율하거나 외부 시스템을 호출하는 비즈니스 로직</td>
<td>Service</td>
<td>UserService.signup() (User 생성 → 저장 →</td>
</tr>
<tr>
<td>이메일 발송), OrderService.placeOrder() (User/Product 조회 → Order 생성)</td>
<td></td>
<td></td>
</tr>
</table>
```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Builder
    private User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static User create(String name, String email) {
        return User.builder().name(name).email(email).build();
    }

    public void changeName(String newName) {
        this.name = newName;
    }
}
```
###### DTO
- **`record` 우선** (Java 17부터 가능). class DTO는 다음 경우에만 사용한다.
  - 기본 생성자가 필요한 경우
  - Jackson/JPA/외부 라이브러리 호환이 필요한 경우
  - 필드 기본값, 복잡한 생성 로직, 명확한 빌더가 필요한 경우
- Request / Response를 명확히 분리한다 (`{도메인}/dto/request`, `{도메인}/dto/response`).
- Entity ↔ DTO 변환 메서드의 위치:
  - Entity → Response: Response DTO에 `from` 정적 메서드를 둔다.
  - Request → Entity: Request DTO에 `toEntity` 메서드를 둔다.
- DTO는 도메인 패키지 안에만 둔다 (`global`에 두지 않는다). 단, 공통 응답 객체(`BaseResponse`)는 예외다.
- **Swagger 어노테이션 `@Schema`로 모든 필드를 문서화한다** (문서화 페이지 참고).
```java
@Schema(description = "회원 가입 요청")
public record UserSignupRequest(
    @Schema(description = "회원 이름", example = "임지성", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "이름은 필수입니다.")
    @Size(max = 50, message = "이름은 50자 이내여야 합니다.")
    String name,

    @Schema(description = "이메일", example = "test@example.com", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email
) {
    public User toEntity() {
        return User.create(name, email);
    }
}

@Schema(description = "회원 응답")
public record UserResponse(
    @Schema(description = "회원 ID", example = "1")
    Long userId,

    @Schema(description = "회원 이름", example = "임지성")
    String name,

    @Schema(description = "이메일", example = "test@example.com")
    String email
) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
```

##### Lombok 사용 가이드
**✅ Best Practices:**
<table header-row="true">
<colgroup>
<col>
<col width="371">
</colgroup>
<tr>
<td>어노테이션</td>
<td>용도</td>
</tr>
<tr>
<td>`@Getter`</td>
<td>모든 필드의 getter 자동 생성</td>
</tr>
<tr>
<td>`@Builder`</td>
<td>빌더 패턴 자동 생성 / 불변 객체(Immutable Object) 생성에 적합하며, 파라미터가 많은 경우 가독성을 크게 향상</td>
</tr>
<tr>
<td>`@RequiredArgsConstructor`</td>
<td>스프링 의존성 주입(DI) 시, `final` 필드에 대한 생성자를 자동으로 생성해줌</td>
</tr>
<tr>
<td>`@Slf4j`</td>
<td>로그 객체를 직접 선언하지 않고 `log` 변수를 사용해 로깅 코드를 간결하게 함</td>
</tr>
<tr>
<td>`@NoArgsConstructor(access = AccessLevel.PROTECTED)`</td>
<td>JPA Entity 사용 시 기본 생성자는 필수 — 롬복을 사용하여 `protected` 접근 제어자로 생성자를 만들어 안전하게 JPA 기술을 활용</td>
</tr>
</table>
```java
// 권장되는 DTO 예시
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUser(Long userId) {
        log.info("회원 조회: userId={}", userId);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));
        return UserResponse.from(user);
    }
}
```
**❌ Anti-Patterns:**
<table header-row="true">
<colgroup>
<col>
<col width="378">
</colgroup>
<tr>
<td>어노테이션</td>
<td>이유</td>
</tr>
<tr>
<td>`@Data` 남발</td>
<td>Entity/DTO에 불필요한 Setter가 노출되거나 원치 않는 `equals` 로직이 생길 수 있음</td>
</tr>
<tr>
<td>`@Setter` 남발</td>
<td>객체의 불변성을 깨뜨려 사이드 이펙트의 주범이 됨. 꼭 필요한 경우(DTO)에만 제한적으로 사용</td>
</tr>
<tr>
<td>`@AllArgsConstructor` 사용</td>
<td>필드 순서를 변경했을 때, 생성자 호출 코드에서 오류를 감지하지 못하고 다른 타입에 값이 들어가는 버그가 발생 가능</td>
</tr>
</table>
  **+) 컨벤션 및 팁<br><br>- 불변 객체 우선:** DTO는 가능한 한 불변(Immutable)으로 설계합니다. @Getter + @Builder 조합을 기본으로 합니다.<br><br>- **lombok.config 설정:** 프로젝트 최상단에 `lombok.config` 파일을 생성하여, 위험한 어노테이션(`@Data`, `@AllArgsConstructor` 등) 사용 시 **경고** 혹은 **에러**를 발생시키도록 설정하여 팀 컨벤션을 강제할 수 있습니다.<br><br>- **Modern Java와 조화:** Java 17 이상을 사용한다면, 데이터 캐리어 역할을 하는 DTO는 Lombok 대신 자바의 `record`를 사용하는 것이 더 안전하고 깔끔합니다.<br><br>- **Delombok 활용:** 롬복이 생성한 코드가 궁금하거나, 롬복 의존성을 줄이고 싶을 때 IDE 기능(Refactor -> Delombok)을 통해 실제 소스 코드로 변환할 수 있습니다.

### 응답 / 예외 처리

##### 공통 응답 — BaseResponse
**결정**: 모든 API 응답은 `BaseResponse<T>` 4필드로 감싼다
```java
package {base}.global.response;

import {base}.global.code.ErrorCode;
import {base}.global.code.SuccessCode;

public record BaseResponse<T>(
    int status,
    String code,
    String message,
    T data // 데이터 필드는 항상 응답에 포함된다 (null인 경우에도 "data": null 형태로 응답에 포함)
) {

    public static <T> BaseResponse<T> success(SuccessCode successCode, T data) {
        return new BaseResponse<>(
            successCode.getHttpStatus().value(),
            successCode.getCode(),
            successCode.getMessage(),
            data
        );
    }

    public static BaseResponse<Void> success(SuccessCode successCode) {
        return new BaseResponse<>(
            successCode.getHttpStatus().value(),
            successCode.getCode(),
            successCode.getMessage(),
            null
        );
    }

    public static BaseResponse<Void> failure(ErrorCode errorCode) {
        return new BaseResponse<>(
            errorCode.getHttpStatus().value(),
            errorCode.getCode(),
            errorCode.getMessage(),
            null
        );
    }

    public static <T> BaseResponse<T> failure(ErrorCode errorCode, T data) {
        return new BaseResponse<>(
            errorCode.getHttpStatus().value(),
            errorCode.getCode(),
            errorCode.getMessage(),
            data
        );
    }
}
```
**필드 의미**:
<table header-row="true">
<tr>
<td>필드</td>
<td>타입</td>
<td>설명</td>
</tr>
<tr>
<td>status</td>
<td>Integer</td>
<td>HTTP 상태 코드 (200, 400, 404 등)</td>
</tr>
<tr>
<td>code</td>
<td>String</td>
<td>서버 커스텀 코드 (COM_200_001, MEM_404_001 등)</td>
</tr>
<tr>
<td>message</td>
<td>String</td>
<td>응답 메시지</td>
</tr>
<tr>
<td>data</td>
<td>T</td>
<td>성공 시 응답 데이터 / Validation 실패 시 필드별 에러 메시지 / 그 외 실패 시 null</td>
</tr>
</table>
**Controller 사용 예**:
```java
// 200 OK — 도메인 success 코드 사용
return ResponseEntity.ok(BaseResponse.success(UserSuccessCode.USER_FOUND, response));

// 201 Created — HTTP status는 ResponseEntity로 명시, 응답 객체는 도메인 success 코드
return ResponseEntity.status(HttpStatus.CREATED)
    .body(BaseResponse.success(UserSuccessCode.USER_SIGNUP_SUCCESS, response));
```
**성공 응답 예시**:
```json
{
  "status": 200,
  "code": "COM_200_001",
  "message": "요청이 성공했습니다.",
  "data": {
    "userId": 1,
    "name": "임지성",
    "email": "test@example.com"
  }
}
```
**실패 응답 예시**:
```json
{
  "status": 404,
  "code": "MEM_404_001",
  "message": "존재하지 않는 회원입니다.",
  "data": null
}
```
**Validation 실패 응답 예시** (아래 GlobalExceptionHandler 참고):
```json
{
  "status": 400,
  "code": "COM_400_001",
  "message": "잘못된 요청입니다.",
  "data": {
    "userId": "userId는 필수입니다."
  }
}
```
##### 예외 처리 — 도메인별 ErrorCode + 5개 핸들러
###### ApiCode / SuccessCode / ErrorCode 인터페이스 계층
도메인별로 enum을 분리하기 위해 공통 인터페이스를 정의한다. enum은 상속이 불가능하므로 인터페이스를 구현하는 형태로 통일한다.

`SuccessCode`와 `ErrorCode`가 같은 시그니처(`getHttpStatus`, `getCode`, `getMessage`)를 공유하므로 공통 부모 `ApiCode`를 두고, 두 인터페이스는 marker로 두어 타입 단위 구분만 유지한다. 상태 코드는 `int` 대신 `HttpStatus` enum을 사용하여 오타를 컴파일 단에서 차단한다.
```java
package {base}.global.code;

import org.springframework.http.HttpStatus;

public interface ApiCode {
    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}
```
```java
package {base}.global.code;

public interface SuccessCode extends ApiCode {
}
```
```java
package {base}.global.code;

public interface ErrorCode extends ApiCode {
}
```
**코드 형식**: `{도메인 3글자}_{상태코드 3자리}_{도메인 내 카운팅 3자리}`
ex: `MEM_404_001` (회원, 404 Not Found, 첫 번째 정의), `COM_200_001` (공통, 200 OK, 첫 번째)
**카운팅 초기화**: 도메인×상태코드 조합마다 001부터 시작한다.
###### GlobalErrorCode (공통)
**예시:**
```java
package {base}.global.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    // 400 — GlobalExceptionHandler의 @Valid / 잘못된 JSON / PathVariable 검증 실패 처리
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "COM_400_001", "잘못된 요청입니다."),

    // 500 — 예상하지 못한 모든 예외의 fallback
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COM_500_001", "서버 내부 오류가 발생했습니다.");

    // UNAUTHORIZED / FORBIDDEN / RESOURCE_NOT_FOUND 등은 사용처가 생기는 시점의 PR에서 도입한다 (YAGNI).

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
```
###### GlobalSuccessCode (공통)
**기본적으로 생성하지 않는다.** 대부분의 엔드포인트는 도메인 의미가 명확하므로 도메인 success 코드(`{Domain}SuccessCode.XXX`)로 응답하는 게 자연스럽다. 모든 엔드포인트가 도메인 success 코드를 사용한다면 `GlobalSuccessCode`는 사용처가 0개이므로 만들지 않는다 (YAGNI).

generic 성공 응답이 필요한 엔드포인트(헬스 체크, 단순 ping 등)가 등장하는 시점의 PR에서 한 줄씩 도입한다. 도입 시 시그니처는 `GlobalErrorCode`와 같이 `HttpStatus` enum을 사용한다.
###### 도메인별 ErrorCode / SuccessCode 예시
도메인별 `XxxSuccessCode`와 `XxxErrorCode`는 같은 `domain/{name}/code/` 폴더에 함께 둔다 (도메인별 `exception/` 폴더는 만들지 않는다 — `BusinessException`이 통합 사용되어 도메인 예외 클래스가 별도로 필요 없음).

**`domain/user/code/UserErrorCode.java`**:
```java
package {base}.domain.user.code;

import {base}.global.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    // 400
    AGE_MUST_UPPER_THAN_20(HttpStatus.BAD_REQUEST, "MEM_400_001", "20세 미만은 가입할 수 없습니다."),

    // 404
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEM_404_001", "존재하지 않는 회원입니다."),

    // 409
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEM_409_001", "이미 가입된 이메일입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
```
**`domain/user/code/UserSuccessCode.java`**:
```java
package {base}.domain.user.code;

import {base}.global.code.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements SuccessCode {

    USER_SIGNUP_SUCCESS(HttpStatus.CREATED, "MEM_201_001", "회원가입이 완료되었습니다."),
    USER_FOUND(HttpStatus.OK, "MEM_200_001", "회원 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
```
###### BusinessException
도메인 비즈니스 예외의 베이스 클래스다. **모든 도메인이 같은 `BusinessException`을 던지고**, 안에 도메인별 `ErrorCode`를 담는다. 도메인별로 별도 Exception 클래스를 두지 않는다 (예외 타입을 구분해야 할 진짜 필요가 생기면 그때 추가).
**+) 합세때는 도메인이 많지 않을 것 같아 Global로 하나만 선언? 도메인 별 분리? → Global에서 관리(나올 수 있는 도메인이 많지 않음)**
```java
package {base}.global.exception;

import {base}.global.code.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
```
디버깅용 detail 메시지가 필요한 케이스가 생기면 그 시점에 별도 생성자 `BusinessException(ErrorCode, String detail)`를 추가한다 (YAGNI).
**Service에서 던지는 예시**:
```java
public UserResponse getUser(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));
    return UserResponse.from(user);
}
```
###### GlobalExceptionHandler
<table header-row="true">
<tr>
<td>핸들러</td>
<td>트리거 상황</td>
</tr>
<tr>
<td>`BusinessException`</td>
<td>service에서 우리가 직접 던지는 도메인 예외</td>
</tr>
<tr>
<td>`MethodArgumentNotValidException`</td>
<td>`@Valid` Request DTO 검증 실패</td>
</tr>
<tr>
<td>`Exception`</td>
<td>위 모두에 안 잡힌 예상 못한 예외 (fallback)</td>
</tr>
<tr>
<td>`HttpMessageNotReadableException`</td>
<td>잘못된 JSON 본문 (콤마 빠뜨림, 타입 불일치 등)</td>
</tr>
<tr>
<td>`HandlerMethodValidationException`</td>
<td>`@PathVariable` / `@RequestParam` 검증 실패</td>
</tr>
</table>
**위 5개정도 초기세팅 할 때 넣어두면 좋을 것 같아요**
```java
package {base}.global.exception;

import {base}.global.code.ErrorCode;
import {base}.global.code.GlobalErrorCode;
import {base}.global.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. 비즈니스 예외
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<Void>> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.warn("[BusinessException] {} - {}", errorCode.getCode(), e.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(BaseResponse.failure(errorCode));
    }

    // 2. @Valid Request DTO 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> handleMethodArgumentNotValid(
        MethodArgumentNotValidException e
    ) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            fieldErrors.put(
                fieldError.getField(),
                Optional.ofNullable(fieldError.getDefaultMessage()).orElse("Invalid value")
            );
        }
        log.warn("[MethodArgumentNotValid] {}", fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(BaseResponse.failure(GlobalErrorCode.INVALID_REQUEST, fieldErrors));
    }

    // 3. 잘못된 JSON 본문
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse<Void>> handleHttpMessageNotReadable(
        HttpMessageNotReadableException e
    ) {
        log.warn("[HttpMessageNotReadable] {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(BaseResponse.failure(GlobalErrorCode.INVALID_REQUEST));
    }

    // 4. @PathVariable / @RequestParam 검증 실패 (Spring Boot 3.2+)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<BaseResponse<Void>> handleHandlerMethodValidation(
        HandlerMethodValidationException e
    ) {
        log.warn("[HandlerMethodValidation] {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(BaseResponse.failure(GlobalErrorCode.INVALID_REQUEST));
    }

    // 5. fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception e, HttpServletRequest request) {
        log.error("[UnhandledException] uri={}, message={}", request.getRequestURI(), e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(BaseResponse.failure(GlobalErrorCode.INTERNAL_SERVER_ERROR));
    }
}
```

### 문서화(swagger) / 로깅

##### Swagger 문서화 컨벤션
springdoc-openapi가 자동으로 `/swagger-ui/index.html` UI를 생성한다. 어노테이션은 4개를 기본으로 사용한다.
<table header-row="true">
<tr>
<td>어노테이션</td>
<td>사용 위치</td>
<td>역할</td>
</tr>
<tr>
<td>`@Tag`</td>
<td>Controller 클래스</td>
<td>API 그룹 정의 (예: "User")</td>
</tr>
<tr>
<td>`@Operation`</td>
<td>Controller 메서드</td>
<td>단일 API 설명 (`summary`, `description`)</td>
</tr>
<tr>
<td>`@Parameter`</td>
<td>`@PathVariable` / `@RequestParam` 파라미터</td>
<td>파라미터 설명 (`description`, `required`, `example`)</td>
</tr>
<tr>
<td>`@Schema`</td>
<td>DTO 클래스/필드</td>
<td>스키마 설명 (`description`, `example`, `requiredMode`)</td>
</tr>
</table>
**Controller 예시**:
```java
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "회원 API")
public class UserController {

    private final UserService userService;

    @Operation(
        summary = "회원 단건 조회",
        description = "userId로 회원 정보를 조회한다."
    )
    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse<UserResponse>> getUser(
        @Parameter(description = "회원 ID", required = true, example = "1")
        @PathVariable Long userId
    ) {
        UserResponse response = userService.getUser(userId);
        return ResponseEntity.ok(BaseResponse.success(UserSuccessCode.USER_FOUND, response));
    }
}
```
**접속 URL**:
<table header-row="true">
<tr>
<td>환경</td>
<td>URL</td>
</tr>
<tr>
<td>로컬</td>
<td>`http://localhost:8080/swagger-ui/index.html`</td>
</tr>
<tr>
<td>운영</td>
<td>`https://{domain}/swagger-ui/index.html`</td>
</tr>
</table>
- 응답 객체 안의 record 필드도 `@Schema`로 description/example을 채우면 클라이언트가 응답 형태를 추측할 필요가 없다.

##### 로거 사용 규칙
- **`@Slf4j` 기본 사용** (Lombok)
- 문자열 연결(`+`) 대신 `{}` placeholder 사용
- 민감 정보(비밀번호, 주민번호 등)는 로그에 포함하지 않는다.

## 역할 분담

- aws 배포
- ci/cd 파이프라인 구축
- ~~깃허브 기본 세팅 (브랜치 설정, 이슈/PR 템플릿, 라벨 설정 등)~~
- ~~기본 개발 환경 세팅~~
- 공통응답 등 세팅
- ERD
- API 명세 확정
- …

- 재훈
  - 코드 포맷팅 세팅
- 지성
  - 공통응답 등 세팅
⇒ 맡을 도메인 사다리타기로

## 아키텍처

#### 1. 패키지 구조 — Layered (domain / global)
```plain text
  src/main/java/{base.package}/
  ├── domain/
  │   ├── user/
  │   │   ├── controller/      ← UserController
  │   │   ├── service/         ← UserService
  │   │   ├── entity/          ← User
  │   │   ├── repository/      ← UserRepository
  │   │   ├── dto/
  │   │   │   ├── request/     ← UserSignupRequest
  │   │   │   └── response/    ← UserResponse
  │   │   └── code/            ← UserSuccessCode, UserErrorCode
  │   ├── post/
  │   │   └── ... (동일 구조)
  │   └── ...
  ├── global/
  │   ├── code/                ← ApiCode, SuccessCode, ErrorCode 인터페이스 + GlobalErrorCode
  │   ├── config/              ← Spring 설정 (Security, CORS, Swagger, JPA 등)
  │   ├── entity/              ← BaseTimeEntity 등 공통 엔티티
  │   ├── exception/           ← BusinessException, GlobalExceptionHandler
  │   ├── response/            ← BaseResponse
  │   └── util/                ← DateUtils, StringUtils 등 공통 유틸
  └── {Project}Application.java
```
<table header-row="true">
<colgroup>
<col>
<col width="411">
</colgroup>
<tr>
<td>폴더</td>
<td>책임</td>
</tr>
<tr>
<td>domain/\{name\}/controller</td>
<td>HTTP 요청 진입점. 검증 후 service로 위임. 비즈니스 로직 X</td>
</tr>
<tr>
<td>domain/\{name\}/service</td>
<td>비즈니스 로직 조율. 트랜잭션 경계. repository 호출</td>
</tr>
<tr>
<td>domain/\{name\}/entity</td>
<td>JPA 엔티티</td>
</tr>
<tr>
<td>domain/\{name\}/repository</td>
<td>DB 접근 (Spring Data JPA)</td>
</tr>
<tr>
<td>domain/\{name\}/dto/request</td>
<td>요청 DTO</td>
</tr>
<tr>
<td>domain/\{name\}/dto/response</td>
<td>응답 DTO</td>
</tr>
<tr>
<td>domain/\{name\}/code</td>
<td>도메인별 XxxSuccessCode와 XxxErrorCode enum (각각 SuccessCode / ErrorCode 인터페이스 구현)</td>
</tr>
<tr>
<td>global/config</td>
<td>모든 Spring Bean 설정 (@Configuration)</td>
</tr>
<tr>
<td>global/entity</td>
<td>BaseTimeEntity 같이 모든 엔티티가 상속할 공통 엔티티</td>
</tr>
<tr>
<td>global/code</td>
<td>ApiCode 부모 인터페이스 + SuccessCode / ErrorCode marker 인터페이스 + GlobalErrorCode enum</td>
</tr>
<tr>
<td>global/exception</td>
<td>BusinessException, GlobalExceptionHandler</td>
</tr>
<tr>
<td>global/response</td>
<td>BaseResponse</td>
</tr>
<tr>
<td>global/util</td>
<td>도메인에 종속되지 않은 순수 유틸</td>
</tr>
</table>
`src/main/java/` 바깥에 위치하는 파일들의 위치:
```plain text
{project-root}/
├── .github/
│   └── workflows/
│       ├── ci.yml                  ← PR 시 빌드/테스트 (CI)
│       └── deploy.yml              ← develop/main 푸시 시 배포 (CD)
├── build.gradle                    ← 의존성과 빌드 정의
├── settings.gradle                 ← 프로젝트 메타데이터
├── gradle/wrapper/                 ← Gradle Wrapper
├── gradlew, gradlew.bat            ← Gradle 실행 스크립트
├── .gitignore
├── README.md
└── src/
    ├── main/
    │   ├── java/{base.package}/   ← 자바 소스 (위 트리 참고)
    │   └── resources/
    │       ├── application.yml             ← 공통 설정 (모든 환경)
    │       ├── application-local.yml       ← 로컬 환경 (개발자 PC)
    │       ├── application-dev.yml         ← 개발 환경 (테스트 서버)
    │       └── application-prod.yml        ← 운영 환경
    └── test/
        ├── java/                  ← 테스트 코드
        └── resources/
```
+) 로컬 MySQL 개발 환경 통일을 위한 Docker 적용

#### 2. AWS 인프라 아키텍처
**결정**: VPC + S3 구조. VPC 안에 public subnet (EC2), private subnet (RDS).
![](https://prod-files-secure.s3.us-west-2.amazonaws.com/0f901230-6100-44af-9719-e47dbcd41fde/8f16c06e-1fb8-4d05-8bcc-b032d450dc79/image.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIAZI2LB4662D3PJAGC%2F20260509%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20260509T235731Z&X-Amz-Expires=3600&X-Amz-Security-Token=IQoJb3JpZ2luX2VjECgaCXVzLXdlc3QtMiJGMEQCIAEUP%2FkChZTiZkp0yDwM%2BtvQPwNmWtbpAFiGWASoRb1SAiASV9K%2FoS5xEf%2FYZWW8I9zuRwvHw3wD096QXYQR854AziqIBAjw%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDYzNzQyMzE4MzgwNSIMTzQ2ZQubjL%2BLCtf3KtwDz%2BCzU0M6zqMb%2F%2FQc4qreDSC80OfGULcMOC3mY80ykHOzQ74TSwwWJqmdu5YAPAE1Bb01Y9hTQPZ9ViYXli6BAwjuftyk8vS47dTN1RBu3UrzXp%2Fwt5E7c%2F0HTiv7%2BRSpzZUffUi3EKy9k0IiZ8R3MbXaW0t9joGhh7WUPdqjRLR56j4q%2FZQAvpd%2FqXj%2F62qd3ZCs572XzaH%2BiYcCeoxiGcyrTUVO6FzMRDM6o4Q7RnMDXgCWSoUxJpQRsGqzUqzaz2gAmrnAYgCbT3MvMfquiRu7orAOP5yyUWHa4q%2B6dWrvV1JJU1a8oDw1aVl9Vh0NCe5UC80%2FryV8iAKE%2BEyE%2F2qXDLnV93%2F7vpXvVTibLO%2Be3ROswFeOEwKjJwomv8I%2BR1e0fB8fGD2wU8CTHnIX4h8NQyH79V7As6S5yOP5%2FgRNhd0itYcFh2E%2Bnzwin5QDLafA0X0BYNQsywH66TrU%2BMBM9Q3OjZIbDUwLo8JiY0L5x16twTUDXjf9d0xyzxHpyjRGucWT%2FdtAiCdlG14mLc0v3HlyJY%2F5S11bP9cEo%2BNVmdRUwcYBYvPQd63hspiLoRiSSP%2BnVrB0im5lyI%2FpY6jvRFbQGI9K%2Bm%2BmFW7E9Y%2FzmCYKgt9u9LvQtlwwj4b%2FzwY6pgHWlbqepDFfP6e3%2FP%2Bv%2BsG%2Fov5%2BkOsG17iswKD6CHETR1MOvx3S3TGivXXJu7yTsesszRJwLXxSscqPWfHg8kJGPYYFv8gfnaxaaksiFTuPmkKTiJ%2FKC53UZX%2B5h6jL8lZXlgjLyN7Tm6yPx3Z5dtH3o1JrYy4QLLV0YnsWQKDhmLMwokzwl0B0TYZxBR%2F3fZ2RWBnY9tll2OMw6tP6wq%2Bdw8JYW%2B1d&X-Amz-Signature=9dc8214bff4e96a18a6d649bc737ac0bb535b52d1d15fb72143c2809af77e80b&X-Amz-SignedHeaders=host&x-amz-checksum-mode=ENABLED&x-id=GetObject)
**컴포넌트별 책임**:
<table header-row="true">
<tr>
<td>컴포넌트</td>
<td>역할</td>
</tr>
<tr>
<td>VPC</td>
<td>네트워크 격리. public/private subnet 분리</td>
</tr>
<tr>
<td>Public Subnet (EC2)</td>
<td>외부 접근 가능. Spring Boot 서버 실행</td>
</tr>
<tr>
<td>Private Subnet (RDS)</td>
<td>외부에서 직접 접근 불가. EC2를 통해서만 접근</td>
</tr>
<tr>
<td>S3</td>
<td>이미지/파일 등 정적 리소스 저장 (S3는 필요 시 적용)</td>
</tr>
</table>
**보안 그룹 기본 규칙**:
<table header-row="true">
<tr>
<td>대상</td>
<td>인바운드</td>
</tr>
<tr>
<td>EC2</td>
<td>22(SSH, 개발자 IP 한정), 80/443(HTTP/HTTPS, 모든 IP)</td>
</tr>
<tr>
<td>RDS</td>
<td>3306(EC2 보안 그룹에서만 허용)</td>
</tr>
<tr>
<td>S3</td>
<td>IAM 권한으로 EC2의 IAM 역할이 접근</td>
</tr>
</table>

## API 설계

#### 1. URL 설계 규칙
<table header-row="true">
<colgroup>
<col>
<col width="326">
</colgroup>
<tr>
<td>규칙</td>
<td>예시</td>
</tr>
<tr>
<td>리소스는 **복수형**</td>
<td>`/users` (O), `/user` (X)</td>
</tr>
<tr>
<td>리소스명은 **kebab-case**</td>
<td>`/user-profiles` (O), `/userProfiles` (X)</td>
</tr>
<tr>
<td>리소스 변수명은 **camelCase**</td>
<td>`/users/{userId}`</td>
</tr>
<tr>
<td>**리소스 위치**는 path variable</td>
<td>`/users/{userId}/posts/{postId}`</td>
</tr>
<tr>
<td>**리소스 조회 조건/필터**는 query string</td>
<td>`/posts?category=tech&page=1`</td>
</tr>
<tr>
<td>HTTP 메서드로 동작을 표현하고 URL에 동사를 쓰지 않는다</td>
<td>`POST /users` (O), `POST /users/create` (X)</td>
</tr>
<tr>
<td>깊은 중첩은 지양한다 (3단계 이내 권장)</td>
<td>`/users/{userId}/posts` (O)</td>
</tr>
</table>
**참고 자료**: [REST API URL 설계 가이드 — jojoldu](https://jojoldu.tistory.com/783)
#### 2. API 명세 작성 도구
<table header-row="true">
<tr>
<td>도구</td>
<td>사용 시점</td>
</tr>
<tr>
<td>**Notion**</td>
<td>1차 명세 — 회의록과 함께 API 설계 단계에서 작성. 안드로이드와 공유</td>
</tr>
<tr>
<td>**Swagger (springdoc-openapi)**</td>
<td>2차 명세 — 구현 후 자동 생성. `/swagger-ui/index.html`에서 최신화</td>
</tr>
</table>
노션: 설계 단계의 토론과 결정 기록에 사용 / Swagger 배포 전 클라 참고용
#### 3. API 명세 템플릿
[작성 예시 (3차 세미나 과제)](https://www.notion.so/26bb16d857ec8207b23101419647df48)
[Template](https://www.notion.so/4f3b16d857ec8310913f013b36128365)
#### 4. Validation 어노테이션 가이드
Request DTO에 사용할 Validation 어노테이션 목록이다.
**기본 제약**:
<table header-row="true">
<tr>
<td>어노테이션</td>
<td>설명</td>
<td>예시</td>
</tr>
<tr>
<td>`@NotNull`</td>
<td>null 불가 (공백은 허용)</td>
<td>필수 객체/숫자</td>
</tr>
<tr>
<td>`@NotBlank`</td>
<td>null/공백 모두 불가</td>
<td>필수 문자열</td>
</tr>
<tr>
<td>`@NotEmpty`</td>
<td>null/빈 컬렉션 불가</td>
<td>필수 리스트/배열</td>
</tr>
</table>
**크기 제약**:
<table header-row="true">
<tr>
<td>어노테이션</td>
<td>설명</td>
<td>예시</td>
</tr>
<tr>
<td>`@Size(min, max)`</td>
<td>문자열 또는 컬렉션 크기 제한</td>
<td>`@Size(max = 100)`</td>
</tr>
<tr>
<td>`@Min(value)`</td>
<td>숫자 최소값</td>
<td>`@Min(1)`</td>
</tr>
<tr>
<td>`@Max(value)`</td>
<td>숫자 최대값</td>
<td>`@Max(150)`</td>
</tr>
</table>
**기타 제약**:
<table header-row="true">
<tr>
<td>어노테이션</td>
<td>설명</td>
<td>예시</td>
</tr>
<tr>
<td>`@Email`</td>
<td>이메일 형식 검증</td>
<td>`@Email`</td>
</tr>
<tr>
<td>`@Positive`</td>
<td>0보다 큰 양수</td>
<td>`@Positive`</td>
</tr>
<tr>
<td>`@PositiveOrZero`</td>
<td>0 이상 양수</td>
<td>`@PositiveOrZero`</td>
</tr>
<tr>
<td>`@Pattern(regex)`</td>
<td>정규식 패턴 매칭</td>
<td>`@Pattern(regexp = "[가-힣a-zA-Z0-9]+")`</td>
</tr>
</table>
**컬렉션 요소 검증** — 컬렉션 안 요소도 검증할 때:
```java
public record TagsRequest(
    @Size(max = 10) List<@NotBlank String> tags
) {}
```
#### 5. 날짜 / 시간 형식
<table header-row="true">
<tr>
<td>타입</td>
<td>형식</td>
<td>예시</td>
</tr>
<tr>
<td>`LocalDate`</td>
<td>`YYYY-MM-DD`</td>
<td>`2026-05-08`</td>
</tr>
<tr>
<td>`LocalDateTime`</td>
<td>`YYYY-MM-DDTHH:mm:ss`</td>
<td>`2026-05-08T19:30:00`</td>
</tr>
<tr>
<td>`LocalTime`</td>
<td>`HH:mm:ss`</td>
<td>`19:30:00`</td>
</tr>
</table>
**JSON 직렬화/역직렬화**: Spring Boot 3.x + Jackson 기본 설정으로 위 ISO-8601 형식이 자동 적용된다. 별도 설정 없이 그대로 사용한다.
#### 6. HTTP Status Code
**성공 응답**:
<table header-row="true">
<tr>
<td>Status</td>
<td>사용 예</td>
</tr>
<tr>
<td>200 OK</td>
<td>요청 성공 (조회, 수정 등)</td>
</tr>
<tr>
<td>201 Created</td>
<td>리소스 생성 성공 (회원가입, 게시글 작성 등)</td>
</tr>
<tr>
<td>204 No Content</td>
<td>응답 본문 없는 성공 (삭제 등)</td>
</tr>
</table>
**클라이언트 에러**:
<table header-row="true">
<tr>
<td>Status</td>
<td>사용 예</td>
</tr>
<tr>
<td>400 Bad Request</td>
<td>Validation 실패, JSON 파싱 실패</td>
</tr>
<tr>
<td>401 Unauthorized</td>
<td>인증되지 않은 요청 (로그인 필요)</td>
</tr>
<tr>
<td>403 Forbidden</td>
<td>인증은 됐지만 권한이 없음</td>
</tr>
<tr>
<td>404 Not Found</td>
<td>존재하지 않는 리소스</td>
</tr>
<tr>
<td>409 Conflict</td>
<td>리소스 충돌 (이메일 중복 등)</td>
</tr>
<tr>
<td>429 Too Many Requests</td>
<td>Rate limiting 초과</td>
</tr>
</table>
**서버 에러**:
<table header-row="true">
<tr>
<td>Status</td>
<td>사용 예</td>
</tr>
<tr>
<td>500 Internal Server Error</td>
<td>예상하지 못한 서버 오류</td>
</tr>
<tr>
<td>503 Service Unavailable</td>
<td>일시적 서비스 중단 (배포, 점검 중)</td>
</tr>
<tr>
<td>504 Gateway Timeout</td>
<td>게이트웨이 타임아웃 (Caddy → 백엔드 응답 지연)</td>
</tr>
</table>

- 롱탭 했을 때의 알림 기능은 UI 표시용?
- ‘키워드 추천’의 구체적인 기준?
- 안읽음 관련
  - 채팅 기능을 구현 안하는데 읽음/안읽음/안읽음 카운팅은 어떻게 관리?
  - 어떤 인터렉션이 있을 때 읽음 처리?

- 채팅창
  - **채팅창 안읽음 → 읽음 POST**
  - **~~채팅방을 새 폴더에 추가 POST~~**
  - **채팅방 GET (응답 필드에 어느 채팅방에 포함되는지 전달 → 클라에서 필터링)**
    폴더 생성 API가 없으므로 기존에 존재하는 폴더에만 추가 가능
- 폴더 관련
  - 폴더 수정
    ![](https://prod-files-secure.s3.us-west-2.amazonaws.com/0f901230-6100-44af-9719-e47dbcd41fde/441ac341-cad2-42ec-a897-159dce116e3c/image.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIAZI2LB466XGN5I2DI%2F20260509%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20260509T235740Z&X-Amz-Expires=3600&X-Amz-Security-Token=IQoJb3JpZ2luX2VjECgaCXVzLXdlc3QtMiJHMEUCIQD35ViYmHRHmyrrYoxWNV3ye%2Fy7XDEm8O3piMvh1UYjxAIgJmcb3yHtnQUbQDQnYMZ6BbeEDJ6aoDUA8Kb0E%2FwyP%2BQqiAQI8f%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgw2Mzc0MjMxODM4MDUiDNaYDOziybkZM5uOdSrcA%2F7Tb%2F0%2FslSh9kZpBWZinLkTTOMFT4o%2FxIe7%2Bj%2F1zUZjEwqqO%2BqdOVWa0I8FW03406ZV2f9%2F8ynJxwfTNgR7CzsDmW8SnswQiqy4Q0XuE3hj74lrQDVp1%2Fp2%2FQzwiZ%2FVoT3sUGu3cG0jsSgFvoQ8pXX5IO1YXpoaiwR%2Bf6uDuvfQwvmz%2FOMi%2Bm0rYexutFsDxchkS1G%2Buacpy%2Bt89A2AyzsccogiiiiIeSb2mMqpUcfxcXVPahKbzzK5tPvH4AsD2%2Fh66mF2rkcmCbZj418J%2F0ih4hyOj1q0HmGMJic5hZgA1y%2FihEF0kP%2FmEzjSdlgZcjm%2FK6bzZchHZ7c1St%2FCspA4kw8CWKmQdqSYRUcIwnxkFSbxaOf1RL4G3tE1U8Ji7cDXJ3IXQpu7R2RY3%2Bu5kMfQDpkc4%2FfGCDWumD7IMJgrDdOP9lTWszH%2FNlfM5760y5sYt7B9NN8VTmN4h%2BfuKz%2BIVMXYIKOQM%2BRylMRlPK7onOeFtU6WR%2BbhqF%2BVA%2FOYDySEdRoXJ1ofJzQ00CdnxKLa7uw3Un3lNw0TEPVmL15z2NyO6V62fZcrVLb%2Bsy%2FeQeWnj0CCKMkg7tDo1QHAZ4fA2r9cQFpUt73T7DWEXuV%2FEDuulNzXnG7nwoWLMIiI%2F88GOqUBX7q29QawbI2df0iEOLhrVYrB%2FDzItqWlhHg9DOfeo8JRyg9dabTfALAPM8hssyHZ87Lm7s7genzHG4oUWvLH73DJnHAfScqR0PAEOPz%2FXvXMhpjUJEglNcGIfvjutlYGOTZJ%2FO50Q1J9806RFMpy9iCyqSU8FIW2SObs2gxC9L8t3nidx4%2Fa9t2qiKHb5k%2BuOETUi7wB6eANneWR3fd%2Byr3gqdcY&X-Amz-Signature=ef1717c3815a1971d4d8c6eb73920bceb59c048659f58e0b3f40827c6cce547e&X-Amz-SignedHeaders=host&x-amz-checksum-mode=ENABLED&x-id=GetObject)
    채팅방 GET API 가능, 한 채팅방이 여러 폴더에 들어가는 것 가능
  - 폴더 삭제 DELETE
    폴더 안에 채팅방이 있는 상태로 삭제하면 폴더만 삭제, 채팅방은 유지
  - ~~폴더 즐겨찾기 POST~~
  - ~~폴더 순서변경 / 상단고정~~
- 서버에서 내려주는 추천폴더 GET
---
최종
- 채팅창
  - 채팅창 안읽음 → 읽음
  - 채팅창 GET
    - 쿼리파라미터
    - 폴더 별 전체 채팅 개수도 응답에 포함해서 반환
- 폴더
  - 폴더 수정
  - 폴더 삭제
    폴더 안에 채팅방이 있는 상태로 삭제하면 폴더만 삭제, 채팅방은 유지
- ~~추천폴더 ← ?~~

---
[재훈](https://www.notion.so/c6cb16d857ec825eaa1601bc69a3bae5)

[하위 페이지](https://www.notion.so/eedb16d857ec835e85b001995cb454c1)

### 재훈

###### 전체 폴더 탭
1. 전체 채팅방 목록 조회
2. 채팅방 클릭 시 안읽음 해제
3. 폴더 수정
4. 폴더 삭제
###### 안읽은 탭

### New page

```json
{
  "status": 200,
  "code": "CHT_200_001",
  "message": "채팅방 목록 조회 성공",
  "data": {
    "folderCounts": {
      "ALL": 8,
      "UNREAD": 8,
      "SOPT": 3,
      "GROUP_SEMINAR": 2
    },
    "chats": [
      {
        "chatId": 5,
        "name": "[SOPT] 운영진",
        "thumbnailUrl": "https://cdn.example.com/sopt-mgmt.png",
        "memberCount": 12,
        "lastMessage": "내일 회의 시간 변경됐습니다.\n저녁 8시로 옮겨주세요.",
        "lastMessageAt": "2026-05-09T18:25:00",
        "unreadCount": 5,
        "folder": "SOPT"
      },
      {
        "chatId": 1,
        "name": "[SOPT 38기] 공지방",
        "thumbnailUrl": "https://cdn.example.com/sopt-notice.png",
        "memberCount": 250,
        "lastMessage": "[공지] 5월 정기 세션 안내",
        "lastMessageAt": "2026-05-09T17:42:00",
        "unreadCount": 1,
        "folder": "SOPT"
      },
      {
        "chatId": 13,
        "name": "카카오톡 선물하기",
        "thumbnailUrl": "https://cdn.example.com/gift.png",
        "memberCount": 1,
        "lastMessage": "(광고) 친구에게 선물 보내기 좋은 순간!",
        "lastMessageAt": "2026-05-09T16:23:00",
        "unreadCount": 11,
        "folder": null
      },
      {
        "chatId": 12,
        "name": "카카오페이",
        "thumbnailUrl": "https://cdn.example.com/kakaopay.png",
        "memberCount": 1,
        "lastMessage": "대출 상환 정보를 확인하지 않았어요.\n변경된 잔액이 맞는지 확인해주세요.",
        "lastMessageAt": "2026-05-09T15:57:00",
        "unreadCount": 6,
        "folder": null
      },
      {
        "chatId": 6,
        "name": "[합세] KakaoTalk 클론 전체",
        "thumbnailUrl": "https://cdn.example.com/group-all.png",
        "memberCount": 10,
        "lastMessage": "오늘 회의록 노션에 올렸어요.\n확인 부탁드립니다.",
        "lastMessageAt": "2026-05-09T14:30:00",
        "unreadCount": 2,
        "folder": "GROUP_SEMINAR"
      },
      {
        "chatId": 3,
        "name": "SOPT YB·OB 네트워킹",
        "thumbnailUrl": "https://cdn.example.com/yb-ob.png",
        "memberCount": 80,
        "lastMessage": "다음 주 네트워킹 일정 공유드려요.",
        "lastMessageAt": "2026-05-09T13:15:00",
        "unreadCount": 3,
        "folder": "SOPT"
      },
      {
        "chatId": 8,
        "name": "[합세] 디자인-기획",
        "thumbnailUrl": "https://cdn.example.com/group-design.png",
        "memberCount": 5,
        "lastMessage": "와이어프레임 V2 올렸습니다",
        "lastMessageAt": "2026-05-09T11:48:00",
        "unreadCount": 1,
        "folder": "GROUP_SEMINAR"
      },
      {
        "chatId": 10,
        "name": "가족방",
        "thumbnailUrl": "https://cdn.example.com/family.png",
        "memberCount": 4,
        "lastMessage": "이번 주말 외식 어디로 갈까",
        "lastMessageAt": "2026-05-09T10:12:00",
        "unreadCount": 2,
        "folder": null
      },
      {
        "chatId": 2,
        "name": "[SOPT] 서버 파트",
        "thumbnailUrl": "https://cdn.example.com/sopt-server.png",
        "memberCount": 15,
        "lastMessage": "Spring Boot 3.5 실습 자료 공유합니다",
        "lastMessageAt": "2026-05-09T09:30:00",
        "unreadCount": 0,
        "folder": "SOPT"
      },
      {
        "chatId": 11,
        "name": "친구들",
        "thumbnailUrl": "https://cdn.example.com/friends.png",
        "memberCount": 8,
        "lastMessage": "오늘 저녁 약속 ㄱ",
        "lastMessageAt": "2026-05-08T22:18:00",
        "unreadCount": 0,
        "folder": null
      },
      {
        "chatId": 7,
        "name": "[합세] 서버 파트",
        "thumbnailUrl": "https://cdn.example.com/group-server.png",
        "memberCount": 2,
        "lastMessage": "내일 셋업 같이 마저 해요",
        "lastMessageAt": "2026-05-08T20:05:00",
        "unreadCount": 0,
        "folder": "GROUP_SEMINAR"
      },
      {
        "chatId": 9,
        "name": "[합세] 안드로이드",
        "thumbnailUrl": "https://cdn.example.com/group-android.png",
        "memberCount": 3,
        "lastMessage": "API 명세 좀 확인 부탁드립니다",
        "lastMessageAt": "2026-05-08T18:42:00",
        "unreadCount": 0,
        "folder": "GROUP_SEMINAR"
      },
      {
        "chatId": 4,
        "name": "SOPT 38기 동기들",
        "thumbnailUrl": "https://cdn.example.com/sopt-38th.png",
        "memberCount": 200,
        "lastMessage": "OT 사진 업로드했어요",
        "lastMessageAt": "2026-05-08T15:20:00",
        "unreadCount": 0,
        "folder": "SOPT"
      }
    ]
  }
}
```

### 작성 예시 (3차 세미나 과제)

[
The title of this Database is: 작성 예시 (3차 세미나 과제)
Here are the Database's Data Sources:
You can use the "view" tool on the URL of any Data Source to see its full schema configuration.
<data-sources>
<data-source url="{{collection://455b16d8-57ec-83c1-b734-079da85498e6}}">
The title of this Data Source is: 작성 예시 (3차 세미나 과제)

Here is the database's configurable state:
Properties with `readOnly: true` are synced or system-managed. Do not try to update their values with page update tools.
<data-source-state>
{"name":"작성 예시 (3차 세미나 과제)","page_templates":[{"name":"API 명세 템플릿","url":"https://www.notion.so/d28b16d857ec837ab78381aa48374384"}],"schema":{"Method":{"description":"","name":"Method","options":[{"color":"brown","description":"","name":"Post","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/aWticA/YmVlZWE0ZjktZmE0MS00N2NhLTk0ZTktOWQxZjgwODVhYmRj"},{"color":"purple","description":"","name":"Get","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/aWticA/N2RlYzE4NzAtNTRkYS00MWM3LTgyMDctMDQ0Y2M1NjIzYzFk"},{"color":"blue","description":"","name":"Put","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/aWticA/OThmM2NlYzEtMzA0ZS00NzdkLTkzMDUtZDE0YjFkZWU1YjNm"},{"color":"yellow","description":"","name":"Patch","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/aWticA/MzUwYTRkOGYtOTk0Mi00OGQwLWFjNjAtMTNiMDIxNjcyNTc4"},{"color":"gray","description":"","name":"DELETE","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/aWticA/MmZlNThlOGMtMWEzOC00NzNkLWE3Y2MtODEyYjZjZWFhNzk1"}],"type":"select"},"userDefined:URL":{"description":"","name":"URL","type":"text"},"명세서":{"description":"","name":"명세서","type":"title"},"서버 구현상태":{"description":"","groups":{"complete":[{"color":"green","description":"","name":"완료","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/fnpIVw/YWFlYzQ4NTctYWJkZC00NzhmLWE2NTMtNzMwYWQ0M2E1YmVm"}],"current":[],"future":[],"in_progress":[{"color":"blue","description":"","name":"진행 중","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/fnpIVw/MGZmMmI0ZDAtZDE3NS00ODdiLThlZmUtNzkwZDAxOTQxYjI1"}],"to_do":[{"description":"","name":"시작 전","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/fnpIVw/YjUxOTAzNzItZmVmMy00YjU2LWE4ZWEtMjU1NTY3YjU1ZGE4"}]},"name":"서버 구현상태","type":"status"},"서버 담당자":{"description":"","name":"서버 담당자","type":"person"},"웹 구현 상태":{"description":"","groups":{"complete":[{"color":"green","description":"","name":"완료","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/YE1haQ/YWE1MTMyN2QtYzRhMi00ZTZmLWJlOGEtOGFiOGIwZjk1ZjA0"}],"current":[],"future":[],"in_progress":[{"color":"blue","description":"","name":"진행 중","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/YE1haQ/MGEyYzg5ZjMtNDYwOS00YWUzLWE2ODQtZGZiYmUwY2JjMzMy"}],"to_do":[{"color":"green","description":"","name":"앱잼 내 구현 X","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/YE1haQ/VHpkWQ"},{"description":"","name":"시작 전","url":"collectionPropertyOption://455b16d8-57ec-83c1-b734-079da85498e6/YE1haQ/OTJiMzc1YTYtMTdhMS00YjRhLWEwYjItYjk4ZGI4ZGU0ODFi"}]},"name":"웹 구현 상태","type":"status"},"웹 담당자":{"description":"","name":"웹 담당자","type":"person"}},"url":"collection://455b16d8-57ec-83c1-b734-079da85498e6"}
</data-source-state>

Here is the SQLite table definition for this data source.
<sqlite-table>
CREATE TABLE IF NOT EXISTS "collection://455b16d8-57ec-83c1-b734-079da85498e6" (
  url TEXT UNIQUE,
  createdTime TEXT, -- ISO-8601 datetime string, automatically set. This is the canonical time for when the page was created.
  "웹 담당자" TEXT, -- JSON array of zero or more user IDs
  "userDefined:URL" TEXT,
  "서버 담당자" TEXT, -- JSON array of zero or more user IDs
  "웹 구현 상태" TEXT, -- one of ["앱잼 내 구현 X", "시작 전", "진행 중", "완료"]
  "Method" TEXT, -- one of ["Post", "Get", "Put", "Patch", "DELETE"]
  "서버 구현상태" TEXT, -- one of ["시작 전", "진행 중", "완료"]
  "명세서" TEXT
)
</sqlite-table>

<templates>
<template id="d28b16d8-57ec-837a-b783-81aa48374384" name="API 명세 템플릿"/>
</templates>
</data-source>
</data-sources>
Here are the Database's Views:
You can use the "view" tool on the URL of any View to see its full configuration.
<views>
<view url="{{view://b9ab16d8-57ec-83be-be5c-885d698bb17d}}">
{"dataSourceUrl":"{{collection://455b16d8-57ec-83c1-b734-079da85498e6}}","displayProperties":["Method","명세서","userDefined:URL","서버 구현상태","서버 담당자","웹 구현 상태","웹 담당자"],"name":"","type":"table"}
</view>
</views>
]({{https://www.notion.so/26bb16d857ec8207b23101419647df48}})

### Template

[
The title of this Database is: Template
Here are the Database's Data Sources:
You can use the "view" tool on the URL of any Data Source to see its full schema configuration.
<data-sources>
<data-source url="{{collection://041b16d8-57ec-82b1-8d05-07530a34c17a}}">
The title of this Data Source is: Template

Here is the database's configurable state:
Properties with `readOnly: true` are synced or system-managed. Do not try to update their values with page update tools.
<data-source-state>
{"name":"Template","page_templates":[{"name":"{API 이름}","url":"https://www.notion.so/388b16d857ec8236997481a8923b2821"}],"schema":{"Method":{"description":"","name":"Method","options":[{"color":"brown","description":"","name":"Post","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/aWticA/YmVlZWE0ZjktZmE0MS00N2NhLTk0ZTktOWQxZjgwODVhYmRj"},{"color":"purple","description":"","name":"Get","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/aWticA/N2RlYzE4NzAtNTRkYS00MWM3LTgyMDctMDQ0Y2M1NjIzYzFk"},{"color":"blue","description":"","name":"Put","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/aWticA/OThmM2NlYzEtMzA0ZS00NzdkLTkzMDUtZDE0YjFkZWU1YjNm"},{"color":"yellow","description":"","name":"Patch","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/aWticA/MzUwYTRkOGYtOTk0Mi00OGQwLWFjNjAtMTNiMDIxNjcyNTc4"},{"color":"gray","description":"","name":"DELETE","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/aWticA/MmZlNThlOGMtMWEzOC00NzNkLWE3Y2MtODEyYjZjZWFhNzk1"}],"type":"select"},"userDefined:URL":{"description":"","name":"URL","type":"text"},"명세서":{"description":"","name":"명세서","type":"title"},"서버 구현상태":{"description":"","groups":{"complete":[{"color":"green","description":"","name":"완료","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/fnpIVw/YWFlYzQ4NTctYWJkZC00NzhmLWE2NTMtNzMwYWQ0M2E1YmVm"}],"current":[],"future":[],"in_progress":[{"color":"blue","description":"","name":"진행 중","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/fnpIVw/MGZmMmI0ZDAtZDE3NS00ODdiLThlZmUtNzkwZDAxOTQxYjI1"}],"to_do":[{"description":"","name":"시작 전","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/fnpIVw/YjUxOTAzNzItZmVmMy00YjU2LWE4ZWEtMjU1NTY3YjU1ZGE4"}]},"name":"서버 구현상태","type":"status"},"서버 담당자":{"description":"","name":"서버 담당자","type":"person"},"웹 구현 상태":{"description":"","groups":{"complete":[{"color":"green","description":"","name":"완료","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/YE1haQ/YWE1MTMyN2QtYzRhMi00ZTZmLWJlOGEtOGFiOGIwZjk1ZjA0"}],"current":[],"future":[],"in_progress":[{"color":"blue","description":"","name":"진행 중","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/YE1haQ/MGEyYzg5ZjMtNDYwOS00YWUzLWE2ODQtZGZiYmUwY2JjMzMy"}],"to_do":[{"color":"green","description":"","name":"앱잼 내 구현 X","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/YE1haQ/VHpkWQ"},{"description":"","name":"시작 전","url":"collectionPropertyOption://041b16d8-57ec-82b1-8d05-07530a34c17a/YE1haQ/OTJiMzc1YTYtMTdhMS00YjRhLWEwYjItYjk4ZGI4ZGU0ODFi"}]},"name":"웹 구현 상태","type":"status"},"웹 담당자":{"description":"","name":"웹 담당자","type":"person"}},"url":"collection://041b16d8-57ec-82b1-8d05-07530a34c17a"}
</data-source-state>

Here is the SQLite table definition for this data source.
<sqlite-table>
CREATE TABLE IF NOT EXISTS "collection://041b16d8-57ec-82b1-8d05-07530a34c17a" (
  url TEXT UNIQUE,
  createdTime TEXT, -- ISO-8601 datetime string, automatically set. This is the canonical time for when the page was created.
  "웹 담당자" TEXT, -- JSON array of zero or more user IDs
  "userDefined:URL" TEXT,
  "서버 담당자" TEXT, -- JSON array of zero or more user IDs
  "웹 구현 상태" TEXT, -- one of ["앱잼 내 구현 X", "시작 전", "진행 중", "완료"]
  "Method" TEXT, -- one of ["Post", "Get", "Put", "Patch", "DELETE"]
  "서버 구현상태" TEXT, -- one of ["시작 전", "진행 중", "완료"]
  "명세서" TEXT
)
</sqlite-table>

<templates>
<template id="388b16d8-57ec-8236-9974-81a8923b2821" name="{API 이름}"/>
</templates>
</data-source>
</data-sources>
Here are the Database's Views:
You can use the "view" tool on the URL of any View to see its full configuration.
<views>
<view url="{{view://714b16d8-57ec-829a-961f-08ac2dff85c2}}">
{"dataSourceUrl":"{{collection://041b16d8-57ec-82b1-8d05-07530a34c17a}}","displayProperties":["Method","명세서","userDefined:URL","서버 구현상태","서버 담당자","웹 구현 상태","웹 담당자"],"name":"","type":"table"}
</view>
</views>
]({{https://www.notion.so/4f3b16d857ec8310913f013b36128365}})

## 회의록

[README](https://www.notion.so/b69b16d857ec83129a3b81a2cb180fa4)

### README

##### 요구 환경
- Java 17 (Temurin)
- Docker (OrbStack 또는 Docker Desktop)
- IntelliJ IDEA
##### 초기 셋업
###### 1. 클론
git clone [https://github.com/SOPT-all/38-COLLABORATION-SERVER-KAKAOTALK.git](https://github.com/SOPT-all/38-COLLABORATION-SERVER-KAKAOTALK.git)<br>cd 38-COLLABORATION-SERVER-KAKAOTALK
###### 2. MySQL 컨테이너 실행
docker-compose up -d
###### 3. IntelliJ에서 프로젝트 Open
- Gradle 동기화 자동 실행 (1~3분 대기)
- Lombok 플러그인 활성화 확인
- Annotation Processing 활성화 (Settings → Build → Compiler → Annotation Processors)
###### 4. 실행
KakaotalkApplication.java 우클릭 → Run
###### 5. 확인
- 콘솔에 `Started KakaotalkApplication` 로그 확인
- [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) 접속 (빈 스웨거 페이지가 뜨면 성공!)
##### 환경 정보
- Spring Boot 3.5.14
- Java 17
- MySQL 8.0 (Docker)
##### 컨테이너 명령어
<table header-row="true">
<tr>
<td>명령</td>
<td>의미</td>
</tr>
<tr>
<td>`docker-compose up -d`</td>
<td>컨테이너 백그라운드 실행</td>
</tr>
<tr>
<td>`docker-compose down`</td>
<td>컨테이너 중지 + 제거 (데이터는 volume에 유지)</td>
</tr>
<tr>
<td>`docker-compose logs -f mysql`</td>
<td>MySQL 로그 실시간 보기</td>
</tr>
<tr>
<td>`docker exec -it kakaotalk-mysql mysql -u kakaotalk -p`</td>
<td>MySQL CLI 접속 (비번: kakaotalk1234)</td>
</tr>
</table>
##### 프로젝트 구조
```plain text
src/main/java/org/sopt/kakaotalk/
├── domain/             # 도메인별 패키지 (chatroom, folder 등)
└── global/             # 공통 베이스 코드
    ├── code/           # ApiCode 부모 인터페이스 + SuccessCode / ErrorCode marker 인터페이스 + GlobalErrorCode
    ├── config/         # Spring Bean 설정
    ├── entity/         # BaseTimeEntity 등 공통 엔티티
    ├── exception/      # BusinessException, GlobalExceptionHandler
    ├── response/       # BaseResponse
    └── util/           # 공통 유틸
```

## 기술스택

### 4. 기술 스택
#### 4.1 언어 / 런타임 / 프레임워크
<table header-row="true">
<tr>
<td>항목</td>
<td>버전</td>
</tr>
<tr>
<td>Java</td>
<td>**17** (LTS)</td>
</tr>
<tr>
<td>Spring Boot</td>
<td>**3.5.14**</td>
</tr>
<tr>
<td>Gradle</td>
<td>**8.x**</td>
</tr>
</table>
#### 4.2 의존성 목록
**필수**:
<table header-row="true">
<tr>
<td>의존성</td>
<td>용도</td>
<td>비고</td>
</tr>
<tr>
<td>Spring Web</td>
<td>REST API</td>
<td>필수</td>
</tr>
<tr>
<td>Spring Data JPA</td>
<td>ORM</td>
<td>필수</td>
</tr>
<tr>
<td>MySQL Connector</td>
<td>DB 드라이버</td>
<td>필수</td>
</tr>
<tr>
<td>**Lombok**</td>
<td>**보일러플레이트 제거**</td>
<td>**`@Getter`, `@Builder` 등**</td>
</tr>
<tr>
<td>Spring Validation</td>
<td>요청 검증</td>
<td>`@Valid`, `@NotNull` 등</td>
</tr>
<tr>
<td>springdoc-openapi (Swagger)</td>
<td>API 문서 자동 생성</td>
<td>UI: `/swagger-ui/index.html`</td>
</tr>
<tr>
<td>**SLF4J + Logback**</td>
<td>**로깅**</td>
<td>**Spring Boot 기본 포함**</td>
</tr>
</table>
**선택**:
<table header-row="true">
<tr>
<td>의존성</td>
<td>추가 시점</td>
</tr>
<tr>
<td>Spring Security</td>
<td>인증/인가 도입 시</td>
</tr>
<tr>
<td>jjwt</td>
<td>JWT 발급/검증</td>
</tr>
<tr>
<td>Prometheus + Grafana + Loki</td>
<td>모니터링 (시간 여유 시)</td>
</tr>
<tr>
<td>Spring Boot Test, JUnit5</td>
<td>테스트 작성 시 (Spring Boot 기본 포함)</td>
</tr>
</table>
앱잼급에는 적용해보면 좋을 듯!
#### 4.3 빌드 / 배포 도구
<table header-row="true">
<colgroup>
<col>
<col width="290">
</colgroup>
<tr>
<td>항목</td>
<td>결정</td>
</tr>
<tr>
<td>빌드</td>
<td>Gradle (`./gradlew bootJar`)</td>
</tr>
<tr>
<td>배포 환경</td>
<td>AWS EC2(운영) + RDS(DB) + S3(정적)</td>
</tr>
<tr>
<td>배포 자동화</td>
<td>GitHub Actions</td>
</tr>
<tr>
<td>서버 프로세스</td>
<td>systemd 등록 (24/7 운영 시)</td>
</tr>
<tr>
<td>HTTPS</td>
<td>**Caddy 리버스 프록시 + Let's Encrypt<br>→ 배포할 때 시간 여유 되면 Nginx 적용해보는 걸로**</td>
</tr>
</table>

## ERD
