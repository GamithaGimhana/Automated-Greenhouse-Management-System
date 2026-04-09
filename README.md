# Automated Greenhouse Management System (AGMS)

Microservice-based application for the Software Architectures & Design Patterns II assignment.

## Services

- `service-registry` (Eureka): `8761`
- `config-server`: `8888`
- `api-gateway`: `8080`
- `zone-service`: `8081`
- `sensor-service`: `8082`
- `automation-service`: `8083`
- `crop-service`: `8084`

## Prerequisites

- Java 17
- Maven Wrapper (already included in each service)
- MySQL running locally
- Databases:
  - `agms_zone_db`
  - `agms_automation_db`
  - `agms_crop_db`

## Startup Order

Start services in this order from separate terminals.

1. Service Registry

```powershell
cd service-registry
./mvnw.cmd spring-boot:run
```

2. Config Server

```powershell
cd config-server
./mvnw.cmd spring-boot:run
```

3. API Gateway

```powershell
cd api-gateway
./mvnw.cmd spring-boot:run
```

4. Domain Services

```powershell
cd zone-service
./mvnw.cmd spring-boot:run
```

```powershell
cd sensor-service
./mvnw.cmd spring-boot:run
```

```powershell
cd automation-service
./mvnw.cmd spring-boot:run
```

```powershell
cd crop-service
./mvnw.cmd spring-boot:run
```

## Validation Checklist

- Open Eureka dashboard: `http://localhost:8761`
- Confirm all services are listed as `UP`
- Use API Gateway for client calls: `http://localhost:8080`

## Required Submission Artifacts

- Postman collection: `AGMS.postman_collection.json` (root)
- Eureka screenshot: place in `docs/eureka-services-up.png`

## Notes

- Gateway now enforces Bearer JWT checks for `/api/**` routes.
- Domain services are configured as Spring Cloud Config clients with fallback:
  `spring.config.import=optional:configserver:http://localhost:8888`
