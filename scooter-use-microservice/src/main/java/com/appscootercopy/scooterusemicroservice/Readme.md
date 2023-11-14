Entities (description):
-
- Scooter, Trip, ScooterStop (like a bus stop), ScooterTrip and ScooterTripId (recor of a scooter trip) Ubication

Controllers (description):
-
- ScooterController (Scooter, ScooterStop and ScooterTrip and Ubication management) talk with ScooterService
- TripController (Trip and ScooterTrip management) talk with TripService

Services (description):
-
- ScooterService (Scooter, ScooterStop and ScooterTrip management) 
  - Talk with ScooterRepository, ScooterTripRepository and ScooterStopRepository

- TripService (Trip and ScooterTrip management) 
  - Talk with TripRepository and ScooterTripRepository

Repositories (description):
-
- ScooterRepository
- TripRepository
- ScooterTripRepository
- ScooterStopRepository
- UbicationRepository