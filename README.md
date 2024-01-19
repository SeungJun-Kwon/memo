# memo
Spring으로 만드는 간단한 메모장 프로젝트

### 메모장 기능 설계

1. 접속 하자마자 메모 전체 목록 조회하기
   
        GET API 사용해서 메모 목록 불러오기
   
3. 메모 생성하기
   
        POST API 사용해서 메모 신규 생성하기
        생성된 메모 반환
   
3. 메모 변경하기
   
        PUT API 사용해서 메모 내용 변경하기
        사용자가 클릭한 메모가 DB에 존재하는지 확인하기
        해당 메모 내용 변경
   
5. 메모 삭제하기
   
        DELETE API 사용해서 메모 삭제하기
        사용자가 삭제하려는 메모가 DB에 존재하는지 확인하기
        DB에서 해당 메모 삭제

### API 테이블

|기능|Method|URL|Return|
|------|---|---|---|
|메모 생성하기|POST|/api/memos|MemoResponseDto|
|메모 조회하기|GET|/api/memos|List<MemoResponseDto>|
|메모 변경하기|PUT|/api/memos/{id}|Long|
|메모 삭제하기|DELETE|/api/memos/{id}|Long|
