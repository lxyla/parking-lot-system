# Parking Lot System

A Java 21 and Spring Boot project for tracking parking spots and tickets. The current version defines the data model and loads five example spots into an in-memory H2 database at startup.

## Run locally

Install a JDK 21 and use the Maven wrapper from the repository root.

```sh
./mvnw spring-boot:run
```

On Windows, run `mvnw.cmd spring-boot:run`.

Open the [H2 console](http://localhost:8080/h2-console) while the application is running. Use the JDBC URL `jdbc:h2:mem:parkingdb`, username `sa`, and an empty password. Run the following query to inspect the example spots.

```sql
SELECT * FROM PARKING_SPOT ORDER BY FLOOR_NUMBER, ROW_NUMBER, SPOT_NUMBER;
```

The database resets when the application restarts. The sample layout has two compact spots, two standard spots, and one large spot.

## Run tests

```sh
./mvnw test
```

The existing smoke test starts the Spring application context.

## Current implementation

- `model` defines parking spots, vehicles, and tickets.
- `repository` queries available parking spots.
- `config/DataLoader.java` seeds the sample layout when the spot table is empty.
- `services/ParkingService.java` is a placeholder for parking and checkout logic. REST endpoints are still to be implemented.
