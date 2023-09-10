# todo 리스트 구현

---

### Version
`0.0.1`

### Requirements

spring boot 3.1.3 \
jdk 17 \
mariadb

## Development

#### Before
```docker 설치```

#### Docker local db 생성
```bash
docker run -p 3309:3306 -e MARIADB_ROOT_PASSWORD=eddy -e MARIADB_DB=prototype --name neotodo -d mariadb;
```

#### Build
```bash
./gradlew build -x test
nohup java -jar build/libs/neotodo-backend-{version}.jar &
```