SCOOTER ENDPOINTS:

GET: http://localhost:8080/api/scooters/id
GET ALL: http://localhost:8080/api/scooters/
SAVE: http://localhost:8080/api/scooters
DELETE: http://localhost:8080/api/scooters/id
UPDATE: http://localhost:8080/api/scooters/id

EXAMPLE JSON SCOOTER

{
"licensePlate": "91218RP",
"available": true,
"ubication": {
"x": 3.1,
"y": 1.3
}
}
--------------------------------------------------------------------------------------
TRIP ENDPOINTS:

GET: http://localhost:8080/api/trips/id
GET ALL: http://localhost:8080/api/trips/
SAVE: http://localhost:8080/api/trips
DELETE: http://localhost:8080/api/trips/id
UPDATE: http://localhost:8080/api/trips/id

EXAMPLE JSON TRIP

{
"id": 37,
"initTime": "2023-10-27T15:19:00.000+00:00",
"endTime": "2023-10-27T21:20:00.000+00:00",
"kms": 12.84,
"ended": false
}
--------------------------------------------------------------------------------------
SCOOTERTRIP ENDPOINTS:

GET: http://localhost:8080/api/scooters/idScooter/trip/idtrip
GET BY TRIP: http://localhost:8080/api/trips/id/scooter
GET ALL: http://localhost:8080/api/scooters/trips
GET ALL BY SCOOTER: http://localhost:8080/api/scooters/id/trips
SAVE: http://localhost:8080/api/scooters/trip (request)
DELETE BY SCOOTER: http://localhost:8080/api/scooters/id
DELETE BY TRIP: http://localhost:8080/api/trips/id
UPDATE: 

EXAMPLE JSON TRIP

{
"scooterId": 11,
"tripId": 18
}
--------------------------------------------------------------------------------------
SCOOTERSTOP ENDPOINTS:

GET: http://localhost:8080/api/scooters/stops/id
GET ALL: http://localhost:8080/api/scooters/stops/
SAVE: http://localhost:8080/api/scooters/stops
DELETE: http://localhost:8080/api/scooters/stops/id
UPDATE: http://localhost:8080/api/scooters/stops/id

EXAMPLE JSON TRIP

{
"ubication": {
"x": 34.0,
"y": 117.55
}
}
--------------------------------------------------------------------------------------
UBICATION ENDPOINTS:

GET: http://localhost:8080/api/scooters/ubications/id
GET ALL: http://localhost:8080/api/scooters/ubications/