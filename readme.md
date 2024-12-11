# Сборка сервисов
```bash
cd ./composition
./mvnw clean package

cd ../decision
./mvnw clean package

cd ../scoring
./mvnw clean package

cd ../
docker-compose up -d
```

# swagger
http://composition.docker.localhost/swagger-ui/index.html