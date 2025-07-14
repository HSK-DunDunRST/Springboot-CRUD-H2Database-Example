## Spring Boot JPA(H2-Database) 기반 CRUD 타입 Q&A 사이트

### 프로젝트 개요 
> 이 프로젝트는 **Spring Boot**를 기반으로 하며,  데이터 저장을 위해 **H2 Database**를 사용하고,  
> 객체-관계 매핑을 위해 **JPA(Java Persistence API)**를 활용합니다.  
> 템플릿 엔진으로는 **Thymeleaf**를 사용하여 동적으로 HTML을 생성하고,  
> 사용자가 질문과 답변을 등록하고 관리할 수 있는 **Q&A 시스템**을 제공합니다.


### 기술 스택
- **Spring Boot**: 독립 실행형(Spring 기반) 애플리케이션을 빠르게 개발할 수 있는 프레임워크    
- **H2 Database**: 개발 및 테스트용으로 사용되는 인메모리 데이터베이스  
- **JPA (Java Persistence API)**: 관계형 데이터 관리를 위한 ORM(Object Relational Mapping) 기술  
- **Thymeleaf**: 서버 사이드에서 HTML을 동적으로 생성하는 템플릿 엔진  
- **Java 17**: 최신 Java 기능을 활용하여 개발

### 핵심 기능

- **사용자 관리**: 회원 가입, 로그인, 로그아웃 기능, 사용자 프로필 수정 및 삭제  
- **질문 관리**: 질문 작성, 조회, 수정, 삭제 기능  
- **답변 관리**: 질문에 대한 답변 작성, 수정, 삭제 기능  
- **동적 웹 페이지**: Thymeleaf를 활용하여 동적으로 콘텐츠를 생성  
- **H2 데이터베이스 관리**: 인메모리 또는 파일 기반 데이터 저장, H2 콘솔을 통해 데이터 확인 가능   

---

## 필수 요구 사항
- JDK 17 이상
- Maven 또는 Gradle
- 개발 환경 (IntelliJ IDEA 추천)


## 프로젝트 구조

```plaintext
src
├── main
│   ├── java
│   │   └── com.mysite.sbb
│   │       ├── Answer.java              # 답변을 처리하는 클래스
│   │       ├── Question.java            # 질문을 처리하는 클래스
│   │       ├── User.java                # 사용자 정보를 관리하는 클래스
│   │       ├── CommonUtil.java          # 공통 유틸리티 클래스
│   │       ├── DataNotFoundException.java # 데이터가 없을 경우 발생하는 예외 처리 클래스
│   │       ├── MainController.java      # 웹 요청을 처리하는 메인 컨트롤러
│   │       ├── SbbApplication.java      # Spring Boot 애플리케이션의 진입점
│   │       └── SecurityConfig.java       # 보안 설정을 관리하는 클래스
│   ├── resources
│   │   ├── templates                    # Thymeleaf 템플릿 파일 (웹 UI)
│   │   └── application.yml              # 애플리케이션 설정 파일
└── test                                 # 단위 및 통합 테스트 코드 디렉터리
```

## 프로젝트 설정 및 실행 방법

### 1. IntelliJ IDEA에서 프로젝트 열기

1. **IntelliJ IDEA** 실행 후 `File -> Open...`을 클릭하여 프로젝트 디렉터리를 선택합니다.
2. `File -> Settings -> Build, Execution, Deployment -> Build Tools -> Maven/Gradle`로 이동하여, JDK를 **Oracle OpenJDK 17**로 설정합니다.
3. `Apply`를 클릭하면 프로젝트가 자동으로 필요한 라이브러리를 다운로드하여 설정됩니다.

### 1-2. Visual Studio Code에서 프로젝트 열기

1. **Visual Studio Code** 실행 후 `File -> Open Folder...`를 클릭하여 프로젝트 디렉터리를 선택합니다.
2. VS Code에서 Java 및 Spring Boot 확장 프로그램이 설치되어 있는지 확인합니다:
    - Extension Marketplace에서 `Java Extension Pack`, `Spring Boot Extension Pack`을 설치합니다.
3. `.vscode/settings.json` 파일에서 Java 버전이 17로 설정되어 있는지 확인합니다.
4. Maven 프로젝트는 VS Code의 Explorer 탭에서 Maven 섹션을 통해 관리할 수 있습니다.

### 2. 애플리케이션 실행

1. `src/main/java/com/mysite/sbb/SbbApplication.java` 실행
2. 기본적으로 **http://localhost:8080**에서 실행됩니다.

### 3. H2 데이터베이스 콘솔 접속

애플리케이션 실행 후 **H2 데이터베이스 콘솔**에 접속하려면:  
👉 **http://localhost:8080/h2-console**  

기본 설정:
- **저장된 설정**: `Generic H2 (Embedded)`  
- **드라이버 클래스**: `org.h2.Driver`
- **JDBC URL**: `jdbc:h2:~/local`
- **사용자 이름**: `sa`
- **비밀번호**: 없음 (빈칸)

#### H2 데이터베이스 접속 정보 변경 방법

1. 프로젝트의 `src/main/resources` 디렉토리에 `application-local.yml` 파일을 직접 생성해야 합니다.
2. 생성한 파일에 다음 내용을 입력합니다:

```yml
spring:
  datasource:
    url: jdbc:h2:file:./data/exampledb
    driverClassName: org.h2.Driver
    username: 원하는_아이디  # 기본값은 sa
    password: 원하는_비밀번호  # 기본값은 빈 문자열
  h2:
    console:
      enabled: true
      path: /h2-console
```

3. `application.yml` 파일에 아래 내용이 있는지 확인하고, 없다면 추가합니다:

```yml
spring:
  profiles:
    active: local  # 이 설정이 있어야 application-local.yml 파일이 활성화됩니다
```

4. 변경 후 애플리케이션을 재시작하면 새로운 접속 정보가 적용됩니다.
5. H2 콘솔 로그인 시 변경된 아이디와 비밀번호를 사용해주세요.

---

## 애플리케이션 설정

설정 파일은 `src/main/resources/application.yml`에 위치합니다.

설정 항목 설명:

| application.properties 설정 키 | 설명 |
|---------|------|
| `spring.application.name` | 애플리케이션 이름 설정 |
| `server.port` | 웹 서버 포트 설정 (기본값: `8080`) |
| `spring.h2.console.enabled` | H2 콘솔 사용 여부 (`true`로 설정 시 활성화) |
| `spring.h2.console.path` | H2 콘솔 접근 경로 (예: `/h2-console`) |
| `spring.datasource.url` | H2 데이터베이스 URL |
| `spring.datasource.driverClassName` | 데이터베이스 드라이버 클래스 (`org.h2.Driver`) |
| `spring.datasource.username` | 데이터베이스 접속 ID |
| `spring.datasource.password` | 데이터베이스 접속 비밀번호 |
| `spring.h2.console.settings.web-allow-others` | 외부에서 H2 콘솔 접속 허용 여부 |
| `spring.jpa.hibernate.ddl-auto` | 데이터베이스 스키마 자동 생성 전략 (`create`, `create-drop`, `update`, `validate`, `none`) |
| `spring.jpa.properties.hibernate.format_sql` | SQL 출력 형식 지정 (`true`: 보기 좋게 포맷팅) |
| `spring.jpa.properties.hibernate.show_sql` | 실행되는 SQL 쿼리를 로그로 출력 여부 |
| `spring.profiles.active` | 활성화할 프로필 설정 (예: `local`, `dev`, `prod`) |

application.yml 형식 예시:
```yml
spring:
    application:
        name: h2database
    profiles:
        active: local
```

application-local.yml 형식 예시:
```yml
spring:
    h2:
        console:
            enabled: true
            path: /h2-console
    datasource:
        url: jdbc:h2:file:./data/exampledb
        driverClassName: org.h2.Driver
        username: 사용할 로그인 아이디
        password: 사용할 로그인 비밀번호 또는 미설정시 ''
    jpa:
        properties:
            hibernate:
                dialect: org.hibernate.dialect.H2Dialect
                '[format_sql]': true
                '[show_sql]': true
        hibernate:
            ddl-auto: update

```

추가 설정 및 세부 사항은 [Spring Boot 공식 문서](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)를 참고하세요.
