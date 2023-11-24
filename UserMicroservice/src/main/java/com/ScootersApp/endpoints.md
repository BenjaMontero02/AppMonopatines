# User Endpoints
GET: http://localhost:8080/user-microservice/api/users/mail
GET ALL: http://localhost:8080/user-microservice/api/users/
SAVE: http://localhost:8080/user-microservice/api/users/
DELETE: http://localhost:8080/user-microservice/api/users/id
UPDATE: http://localhost:8080/user-microservice/api/users/id
PUT: http://localhost:8080/user-microservice/api/users/id
PUT (disable user): http://localhost:8080/user-microservice/api/users/mail/disable
PUT (enable user): http://localhost:8080/user-microservice/api/users/mail/enable

USER JSON EXAMPLE:

{
"name":"Lucas",
"surname": "Amendola",
"mail": "lucasamendola@gmail.com",
"password": "AyZ2001",
"phoneNumber": "2494245153",
"roles":["user"]
}

# ACCOUNT ENDPOINTS

GET: http://localhost:8080/user-microservice/api/accounts/id
GET ALL: http://localhost:8080/user-microservice/api/accounts/
SAVE: http://localhost:8080/user-microservice/api/accounts/
DELETE: http://localhost:8080/user-microservice/api/accounts/id
UPDATE: http://localhost:8080/user-microservice/api/accounts/id

ACCOUNT JSON EXAMPLE:

{
"id":"1",
"wallet": 203985.69,
"dateUp": "2023-01-27 14:00:00"
}


# ROLE ENDPOINTS

GET ALL: http://localhost:8080/user-microservice/api/roles/
SAVE: http://localhost:8080/user-microservice/api/roles/

ROLE JSON EXAMPLE:

{
"type": "user"
}


# USERACCOUNT ENDPOINTS
GET ALL: http://localhost:8080/user-microservice/api/users/accounts/
GET BY ACCOUNT : http://localhost:8080/user-microservice/api/accounts/{id}/users
GET BY USER: http://localhost:8080/user-microservice/api/users/{id}/accounts
SAVE: http://localhost:8080/user-microservice/api/users/accounts/
DELETE: http://localhost:8080/user-microservice/api/users/{id}/accounts/{idAccount}

USERACCOUNT JSON EXAMPLE

{
"accountId":19,
"userID":1
}
    