# API Overview
- Base URL: http://localhost:8080
- Мы сделали так, что токен приходит сразу в куках с меткой HttpOnly, поэтому в каждом запросе нужно прописывать credentials: "include"


# Аутентификация

## Регистрация 
## POST /api/auth/sign-up
### Request
```json
{
    "name": String,
    "email": String,
    "password": String,
    "phone": String
}
```
### Response
```json
{
    "name": String,
    "email": String,
    "role": String,
    "phone": String
}
```

## Авторизация 
## POST /api/auth/sign-in
### Request
```json
{
    "login": String,
    "password": String,
}
```
### Response
```json
{
    "name": String,
    "email": String,
    "role": String,
    "phone": String
}
```

## Выход 
## POST /api/auth/logout


# Создание события

## Создание события (Event) 
## POST /api/event/create
### Request
### Структура запроса
Запрос состоит из ДВУХ обязательных частей:

| Имя части    | Тип содержимого     | Описание                      |
|--------------|---------------------|-------------------------------|
| `event`      |`application/json`   | JSON объект с данными события |
| `eventCover` |`multipart/form-data`| Файл обложки события          |
```json
{
    "title": String,
    "subtitle": String,
    "shortDescription": String,
    "fullDescription": String,
    "category": String,
    "organizerName": String,
    "organizerDescription": String,
    "ageRestriction": Integer
}
```
### Response
```json
{
    "id": Integer,
    "title": String,
    "subtitle": String,
    "shortDescription": String,
    "fullDescription": String,
    "category": String,
    "ageRestriction": Integer,
    "coverImageUrl": String,
    "organizerName": String,
    "organizerDescription": String,
    "status": String,
    "createdAt": ISO 8601 ["2024-01-20T18:00:00"],
    "updatedAt": ISO 8601
}
```

## Создание конкретного мероприятия внутри события (EventSession) 
## POST /api/event_session/create
### Request
```json
{
    "eventId": Integer,
    "venue": {
        "name": String,
        "address": String,
        "phone": String
    },
    "startAt": ISO 8601,
    "endAt": ISO 8601,
    "is_online": Boolean ["false"],
    "ticketMinPrice": Decimal,
    "ticketMaxPrice": Decimal,
    "currency": String ["RUB"],
    "ticketUrl": String,
    "ticketServiceName": String
}
```


# Просмотр события

## Получение всех событий (Event) 
## GET /api/event/all
### Response
```json
    "data": [
        {
            "id": Integer,
            "title": String,
            "subtitle": String,
            "shortDescription": String,
            "fullDescription": String,
            "category": String,
            "ageRestriction": Integer,
            "coverImageUrl": String,
            "organizerName": String,
            "organizerDescription": String,
            "status": String,
            "createdAt": ISO 8601,
            "updatedAt": ISO 8601
        },
        {
            ...
        }, ...
    ]
```

## Получение только события (Event) 
## GET /api/event/{event_id}
### Response
```json
{
    "id": Integer,
    "title": String,
    "subtitle": String,
    "shortDescription": String,
    "fullDescription": String,
    "category": String,
    "ageRestriction": Integer,
    "coverImageUrl": String,
    "organizerName": String,
    "organizerDescription": String,
    "status": String,
    "createdAt": ISO 8601,
    "updatedAt": ISO 8601
}
```

## Получение всех мероприятий события (EventSession) 
## GET /api/event_session/{event_id}
### Response
```json
    "data": [
        {
            "id": Integer,
            "startAt": ISO 8601,
            "endAt": ISO 8601,
            "is_online": Boolean,
            "ticketMinPrice": Decimal,
            "ticketMaxPrice": Decimal,
            "currency": String,
            "ticketUrl": String,
            "ticketServiceName": String, 
            "status": String,
            "createdAt": ISO 8601,
            "updatedAt": ISO 8601,
            "venue": {
                "id": Integer,
                "name": String,
                "address": String,
                "phone": String,
                "createdAt": ISO 8601,
                "updatedAt": ISO 8601
            }
        },
        { 
            ...
        }, ...
    ]
```

## Получение события со всеми мероприятиями (Event + EventSession) 
## GET /api/event/full/{event_id}
### Response
```json
{
    "id": Integer,
    "title": String,
    "subtitle": String,
    "shortDescription": String,
    "fullDescription": String,
    "category": String,
    "ageRestriction": Integer,
    "coverImageUrl": String,
    "organizerName": String,
    "organizerDescription": String,
    "status": String,
    "createdAt": ISO 8601,
    "updatedAt": ISO 8601
    "eventSessions": [
        {
            "id": Integer,
            "startAt": ISO 8601,
            "endAt": ISO 8601,
            "is_online": Boolean,
            "ticketMinPrice": Decimal,
            "ticketMaxPrice": Decimal,
            "currency": String,
            "ticketUrl": String,
            "ticketServiceName": String, 
            "status": String,
            "createdAt": ISO 8601,
            "updatedAt": ISO 8601,
            "venue": {
                "id": Integer,
                "name": String,
                "address": String,
                "phone": String,
                "createdAt": ISO 8601,
                "updatedAt": ISO 8601
            }
        },
        { 
            ...
        }, ...
    ]
}
```

## Получение картинки
## GET /files
### Request
Query параметр `filename` String (coverImageUrl)
### Response
Бинарные данные файла