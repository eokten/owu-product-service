# Як працювати з проектом локально

## 1. Необхідні компоненти

Перед початком переконайся, що встановлено:

- Java **17 або вище**
- **Maven**

## 2. Запуск Docker-сервісів (MongoDB, Keycloak)

Перейди до кореневої директорії проекту (де знаходиться [compose.yaml](compose.yaml)) і виконай:

```bash
docker-compose -f compose.yaml up -d
```

## 3. Запуск Spring Boot застосунку

### Через термінал:

```bash
mvn clean spring-boot:run
```

### Через IntelliJ IDEA:

Відкрий
файл [OwuProductServiceApplication.java](src/main/java/ua/com/owu/productservice/OwuProductServiceApplication.java),
натисни зелений трикутник біля класу або методу `main`.

## 4. Зупинка застосунку

- Якщо запускав через термінал — натисни `Ctrl+C`.
- Якщо з’явиться запит **"Terminate batch job (Y/N)?"**, натисни `Y` та Enter, або ще раз `Ctrl+C`.

## 5. Зупинка Docker-сервісів

Для зупинки контейнерів та видалення volumes:

```bash
docker-compose down -v
```

> Параметр `-v` видаляє volumes, створені під час роботи. Рекомендується очищати їх, щоб уникнути непередбачуваної
> ситуації та поведінки.

---

## Додаткова документація

[OAuth2 overview](docs/oauth2-overview.md)