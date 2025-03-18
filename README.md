게시판 과제

## 요구사항
- "a " 라는 문자 뒤에 명령어를 받음
- URL 분석 기능
  - 입력받는 URL은 `/구분/기능?파라미터` 형태로 입력
  - 동일한 이름의 파라미터는 제일 마지막 값 하나만 취급
  - 유효하지 않은 URL 입력 시 예외 발생
- "종료" 혹은 "exit" 등의 특정 명령어 입력 시 종료
- 명령어를 입력받았을 경우 요청(request) 객체 생성
  - 요청 객체는 입력받은 명령어(URL)에 대한 정보를 포함하고 있음
- 세션(session) 객체 사용
  - 회원의 인증과 관련된 내용을 세션 객체에서 다룸
  - 세션 객체는 요청 객체 안에 포함되도록 설계
  - 세션 객체 안에 필요하다면 인증정보 외 다른 정보를 포함해도 무관
- 컨테이너 객체 도입
  - 컨테이너 객체에서 모든 객체를 생성하고 관리하도록 구성
  - 모든 객체는 한 번만 생성되고 생성된 객체를 사용하게 설계 -> 싱글톤
  - 모든 객체에서 필요한 객체가 있다면 Container 에 생성된 객체를 사용
- 필터(Filter) 기능 
  - 입력 받은 명령어를 필터링
  - URL에 대한 접근 권한


- 게시판 기능 구현
  - 기능 구분은 `boards`
  - URL 기반 동작

| URL            | 인증 여부 | 인가 범위 | 파라미터      | 동작                                                                                                    |
|----------------|-------|-------|-----------|-------------------------------------------------------------------------------------------------------|
| /boards/add    | O     | 관리자   | -         | 새로운 게시판 작성                                                                                            |
| /boards/edit   | O     | 관리자   | boardId   | `boardId` 게시판을 수정                                                                                     |
| /boards/remove | O     | 관리자   | boardId   | `boardId` 게시판을 삭제                                                                                     |
| /boards/view   | -     | -     | boardName | `boardName` 게시판의 게시글 목록을 확인합니다.<br/>다음과 같이 출력됩니다.<br/>글 번호/글 제목/작성일<br/>글 번호/글 제목/작성일<br/>.../.../... |

- 게시물 기능 구현
  - 기능 구분은 `posts`
  - URL 기반 동작
  - 여러 개의 게시물 작성 가능
  - 게시물 번호는 작성할 때마다 1씩 증가

| URL           | 인증 여부 | 인가 범위 | 파라미터    | 동작                                                                                               |
|---------------|-------|-------|---------|--------------------------------------------------------------------------------------------------|
| /posts/add    | O     | 회원    | boardId | `boardId` 게시판에 게시글을 작성, 제목과 내용을 입력받으며 작성된 시점이 저장                                                 |
| /posts/remove | O     | 회원    | postId  | `postId`에 해당하는 게시글 삭제                                                                            |
| /posts/edit   | O     | 회원    | postId  | `postId`에 해당하는 게시글 수정, 수정시 제목과 내용을 모두 수정하며 수정된 시점이 저장                                            |
| /posts/view   | -     | -     | postId  | `postId`에 해당하는 게시글 조회, 다음과 같이 출력<br/>[postId]번 게시글<br/>작성일:...<br/>수정일:...<br/>제목:...<br/>내용:... |


- 회원 기능 구현
  - 기능 구분은 `accounts`
  - URL 기반 동작
  - 일반 회원과 관리자 등급이 존재
  - 게시판은 관리자만 작성 가능
  - 게시글을 로그인 하여야만 작성 가능
  - 본인의 게시글만 수정하거나 삭제 가능 -> 관리자는 본인의 작성 유무와 관계 없이 게시글 삭제, 수정 가능
  - 게시물은 로그인을 하지 않아도 열람 가능

| URL               | 인증 여부 | 인가 범위 | 파라미터      | 동작                                                                                                       |
|-------------------|-------|-------|-----------|----------------------------------------------------------------------------------------------------------|
| /accounts/signup  | X     | 익명    | -         | 로그인을 위한 계정, 비밀번호 그리고 이름 또는 닉네임, 이메일을 기반으로 회원을 등록                                                         |
| /accounts/signin  | X     | 익명    | -         | 로그인을 위한 계정과 비밀번호를 기반으로 로그인을 수행. 이미 로그인 되어 있을 경우 예외가 발생하며, 로그아웃 하여야 새로운 계정에 로그인 가능 -> 한 번에 하나의 계정만 로그인 가능 |
| /accounts/signout | O     | 회원    | -         | 현재 로그인 되어있는 계정을 로그아웃 처리, 로그인 되지 않은 상태에서 수행 시 예외                                                          |
| /accounts/detail  | O     | 회원    | accountId | `accountId`에 해당하는 계정 정보를 조회하며 다음과 같이 출력됩니다.<br/>[accountId]번 회원<br/>계정:..<br/>이메일:..<br/>가입일:..<br/>     |
| /accounts/edit    | O     | 회원    | accountId | `accountId`에 해당하는 계정의 정보를 변경, 비밀번호와 이메일만 변경 가능하고 변깅일자가 기록                                                |
| /accounts/remove  | O     | 회원    | accountId | `accountId`에 해당하는 계정을 탈퇴처리 합니다. 만일 로그인 되어있다면 로그아웃 처리를 먼저 수행합니다.                                          |

