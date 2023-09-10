# todo 리스트 구현

---

## Version
`0.0.1`

## Requirements

spring boot 3.1.3 \
jdk 17 \
mariadb

## Development

#### Before
```
docker 설치
postman 사용을 권장
```


#### Docker local db 생성
```bash
docker run -p 3309:3306 -e MARIADB_ROOT_PASSWORD=eddy -e MARIADB_DB=prototype --name neotodo -d mariadb;
```

#### Docker 컨테이너 접속 후 아래와 같이 데이터베이스 생성
```bash
docker exec -it {container id} /bin/sh

# mariadb -u root -p
Enter password: eddy

MariaDB [(none)]> create database todo;
```

#### Build
#### !IntalliJ 및 기타 IDE 사용 시 로컬 빌드 바로 적용 
```bash
./gradlew build -x test
nohup java -jar build/libs/neotodo-backend-{version}.jar &
```

## TEST

#### before
```
spring security 적용으로 인하여 토큰을 발급 필요.
http://localhost:8090/v1/user/signUp 로 호출 후 회원가입.
http://localhost:8090/v1/user/login 로 호출 후 로그인 & 토큰 발급 진행.
http://localhost:8090/v1/auth/token/refresh 로 토큰을 재발급.
```

```
나머지 호출 포인트에서는 토큰이 없으면 접근이 불가.

header 에 아래 예시와 같이 토큰 설정. 
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpZCI6InhvZGlkNzk4NiIsImlhdCI6MTY5NDM2Mzk5NCwiZXhwIjoxNjk0Mzc0Nzk0fQ.DaFHJoltUmgXuzrsJLPqMYu_izBhv_-yuGSYSiDq2bg
```


# Achitecture

---

전체 적인 코드 구조를 계층화 하여 infra(data) <> application <> presentation \
구조로 설계되어 있으며 각 층별로 infra 에서 데이터를 application 에서 usecase 를 \
presentation 에서 접근 인터페이스를 관리하도록 책임을 분산하였습니다.

아울러, request body 와 response body 의 인터페이스 적용으로 역할(호출 조건과, 응답값 반환) 을 \
추상화 및 구현하는데 중심을 두었으며 application 에서는 호출을 위한 조건을 conditions 로 \
받아 캡슐화를 시도하였습니다.

좀 더 고칠 점이나 리뷰가 생각나시면 언제든 환영합니다 😊