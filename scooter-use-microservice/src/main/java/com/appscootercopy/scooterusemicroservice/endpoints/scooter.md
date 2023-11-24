{
	"info": {
		"_postman_id": "f660992d-8495-45f3-acc5-4f330177ee52",
		"name": "Endpoints Scooter",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "30069639",
		"_collection_link": "https://www.postman.com/brianfreijomil/workspace/scooter-use-microservice/collection/30069639-f660992d-8495-45f3-acc5-4f330177ee52?action=share&source=collection_link&creator=30069639"
	},
	"item": [
		{
			"name": "get Scooter",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/scooters/286PP",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"scooters",
						"286PP"
					]
				}
			},
			"response": []
		},
		{
			"name": "get all scooter",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/scooters/",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"scooters",
						""
					]
				}
			},
			"response": []
		},
		{
			"name": "delete scooter",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/scooters/5",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"scooters",
						"5"
					]
				}
			},
			"response": []
		},
		{
			"name": "add scooter",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"licensePlate\": \"1807J\",\r\n    \"available\": true,\r\n    \"ubication\": {\r\n        \"x\": 23.15,\r\n        \"y\": 32.73\r\n    }\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/scooters",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"scooters"
					]
				}
			},
			"response": []
		},
		{
			"name": "report kms",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/scooters/report/kms",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"scooters",
						"report",
						"kms"
					]
				}
			},
			"response": []
		},
		{
			"name": "scooters close to me",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"x\": 40.15,\r\n    \"y\": 100.73\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/scooters/close",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"scooters",
						"close"
					]
				}
			},
			"response": []
		},
		{
			"name": "report cc pauses",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/scooters/report/pauses",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"scooters",
						"report",
						"pauses"
					]
				}
			},
			"response": []
		},
		{
			"name": "report sin pauses",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/scooters/report/non&pauses",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"scooters",
						"report",
						"non&pauses"
					]
				}
			},
			"response": []
		},
		{
			"name": "availability scooter",
			"request": {
				"method": "PATCH",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"available\": true\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/scooters/16",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"scooters",
						"16"
					]
				}
			},
			"response": []
		},
		{
			"name": "scooters by trips and year",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"minCountTrips\": 1,\r\n    \"year\":2023\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/scooters/trips&year",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"scooters",
						"trips&year"
					]
				}
			},
			"response": []
		},
		{
			"name": "availability",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/scooters/availability",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"scooters",
						"availability"
					]
				}
			},
			"response": []
		}
	]
}