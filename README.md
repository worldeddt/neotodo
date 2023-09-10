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

#### Docker 컨테이너 접속 후 아래와 같이 데이터베이스 생성
```bash
docker exec -it {container id} /bin/sh

# mariadb -u root -p
Enter password: eddy

MariaDB [(none)]> create database todo;
```

#### Build
#### !IntalliJ 및 기타 IDE 사용 시 로컬 빌드 바로 하시면 됩니다. 
```bash
./gradlew build -x test
nohup java -jar build/libs/neotodo-backend-{version}.jar &
```