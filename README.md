# 미세먼지 프로젝트 4팀

* 미세먼지 정보를 보여주는 웹과 모바일앱 서비스를 제작합니다.

## 팀원

* BackEnd(Spring Boot): [엘리](https://github.com/bohyeon-n), [Sigrid](https://github.com/jypthemiracle)
* FrontEnd(Vanilla JS, ES Modules): [baekCo](https://github.com/baekCode)
* iOS(Swift): [Lin](https://github.com/Limwin94)

## 배포 링크

* 웹 링크: [13.124.46.74](ec2-13-124-46-74.ap-northeast-2.compute.amazonaws.com)
* iOS 데모: [gif 보기](https://github.com/codesquad-member-2020/dust-4/blob/master/iOS_dust-4_demo.md)

## API 링크

* [공식 API 문서](https://github.com/codesquad-member-2020/dust-4/wiki/%EB%AF%B8%EC%84%B8%EB%A8%BC%EC%A7%80-4%ED%8C%80-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-API-%EC%95%88%EB%82%B4)
* [Swagger API 문서 자동화 툴 링크](13.124.46.74:8080/swagger-ui.html)

## 요구사항 명세

### 백앤드 요구사항

* spring boot 사용 (logback, assertJ)
* 데이터베이스는 Spring Data JDBC, H2 사용
* 프론트 및 모바일에서 필요한 API를 제공한다.
* 예보값에는 이미지의 시간대별 이미지의 URL이 포함되어야 한다.
* API은 json의 형태로 응답한다.
* 사용자 요청 -> OpenAPI에서 필요한 정보 수집 -> 응답의 형태로 구현한다.
* GitHub과 AWS EC2를 이용해 배포한다. 수동으로 배포를 진행하며 자동배포는 금지, 배포도구 사용도 금지한다.
* 리눅스 서버에서 소스를 가져올 때는 git을 사용한다.
* 배포 서버에는 항상 동작하고 있는 버전이 배포되어 있어야 한다. 또한 master 브랜치를 이용해서 서비스를 배포한다.
* 가능하면 매일 배포가 가능하도록 협업 및 배포 전략을 구성해 본다.
* 프론트는 Apache를 설정하고 해당 아파치의 기본 루트 디렉토리에 배포한다.
* 백엔드는 gradle을 이용해서 실행한다. 8080포트로 동작하며 아파치와의 연동은 설정하지 않는다.
* ec2 서버의 80포트와 8080포트를 외부에 개방한다.

### 프론트엔드 요구사항

* 모바일웹UI에 어울리는 화면을 구성한다.
* 터치가 필요한 인터랙션은 마우스 이벤트가 아니고 터치 이벤트를 사용한다.
* 막대차트의 표현방법은 본인이 방법을 찾아서 활용한다(html tag, canvas, SVG 등)
* 미세먼지 애니메이션 동작 제어는, requestAnimationFrame을 활용한다.
* 서버에서 데이터를 요구할때는 fetch API를 활용한다.

#### 미구현 사항

* 회원가입 완료 시 요청확인 후 메인 페이지 이동

* 로그인 페이지 기능

### iOS 요구사항

* 아이폰 11 Pro 화면 사이즈를 기준으로 스토리보드에서 작업한다. (오토레이아웃을 적용하지 않아도 된다)
* 화면별로 MVC 구조를 명확하게 구분한다.
* Delegate 프로토콜 구현을 별도의 클래스 등으로 분리하여 ViewController에 모든 Delegate를 포함하지 않도록 한다.
* 화면 전환 시점에 필요한 데이터 구조와 흐름을 효율적으로 설계하고 구현한다.
* 서버에 요청할 때는 URLSession 클래스를 활용한다.
* 등급에 따른 배경색은 단색이 아니라 Gradient로 색칠한다. CAGradientLayer 참고
* 가이드에 있는 이모티콘 대신에 더 적절한 이모티콘 혹은 다른 이미지로 표시해도 된다.
* TableView를 활용해서 바 그래프를 출력한다.
* Custom Cell을 이용해서 바 그래프 색상과 크기, 값을 표시한다.
* 위-아래 스크롤 동작에 따라 상단 정보가 변경되도록 구현한다.

* [분야별 기능 요구사항 구글 스프레드시트로 정리](https://docs.google.com/spreadsheets/d/1UXr-8TI4j3CX-In4SZjvrmIllVYK08JPFHaxLu9PPyk/edit#gid=0)

## 기타 링크

* [4팀 공식 위키](https://github.com/codesquad-memeber-2020/dust-4/wiki)
* [데일리 스크럼 및 회고 모음](https://github.com/codesquad-member-2020/dust-4/wiki/%EB%8D%B0%EC%9D%BC%EB%A6%AC-%EC%8A%A4%ED%81%AC%EB%9F%BC-%EB%B0%8F-%ED%9A%8C%EA%B3%A0-%EB%AA%A8%EC%9D%8C)

## 4팀 그라운드 룰

## 스크럼 시간
* 코어 타임(필수 작업 시간)
    * 11:00 - 18:00
    * 점심시간: 12:00 - 13:00
* 시작 시간
    * 10시 30분까지 슬랙에 남기기
* 중간 스크럼 
    * 슬랙(단톡)으로 궁금하거나 이야기하고 싶은것 자유롭게 남기기
    * 온라인이니까 2시간에 한 번씩 슬랙에 올리는 것을 권고
* 회고 시간
    * 6시에 슬랙으로 회고

## 작업 관련
### 커밋 메시지
- 한글로 통일
    - [#??] feat: 새로운 기능을 추가하였다.
    - [#??] fix: 무슨무슨 버그를 고쳤다.
    - [#??] style: 스타일링을 바꿨다. CSS 추가
    - [#??] refactor: 어떤 함수를 리팩토링했다.
    - [#??] test: 무엇인가를 테스트했다.
    - [#??] docs: 문서를 업데이트 했다.
    - [#??] chore: 패키지 및 빌드 관련 프로젝트 설정을 했다.
- Camel case 디폴트 적용
### 이슈 관리
* Github issue 사용
    * 라벨은 FE/BE/iOS + todo/doing/done 정도면 좋을 것 같아요
    * 깃헙 이슈에 pull request 매핑시키면 좋을 것 같음. (기능 있음)
### 브랜치 관리
* 기본 브랜치 전략: 분야 별 dev-기능명으로 브랜치를 생성하고, 개발이 완성되면 develop에 머지한다. 배포본은 deploy에서 관리하고 배포하며, 최종 완성본은 master로 머지한다.
* master: 최종 완성 버전
* deploy: 배포된 현재 버전
    * develop: 개발이 진행되고 있는 기준 브랜치
        * dev_iOS-기능명: iOS 개발을 위한 브랜치로, 개발하는 기능에 따라 브랜치를 생성함
        * dev_FE-기능명: FrontEnd 개발을 위한 브랜치로, 개발하는 기능에 따라 브랜치를 생성함
        * dev_BE-기능명: BackEnd 개발을 위한 브랜치로, 개발하는 기능에 따라 브랜치를 생성함
