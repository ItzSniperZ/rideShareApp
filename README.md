## RideShareApp

A JavaFX-based **ride-sharing app** that models core features of a modern transportation service, including user login, profile management, ride requests, map visualization, and payment handling.  

## Features

- **Login System** – basic username/password validation with transition to main dashboard.  
- **Modular UI Navigation** – profile, map, and ride-request pages loaded dynamically via `FXMLLoader`.  
- **Profile Management** – update username, password, and contact info; submit support tickets.  
- **Payment Methods** – add validated card information with CVV and expiration checks.  
- **Ride Request** – request rides, calculate estimated cost/distance.  
- **Map Display** – placeholder for integration with a mapping service.  
- **Clean MVC Structure** – separation between controllers, models (services), and views.

---

## Project Structure

```
rideShareApp/
├── src/
│   ├── main/
│   │   ├── java/org/example/rideshareapp/
│   │   │   ├── Main.java
│   │   │   ├── controllers/
│   │   │   │   ├── MainController.java
│   │   │   │   ├── LoginController.java
│   │   │   │   ├── ProfileController.java
│   │   │   │   ├── RideRequestController.java
│   │   │   │   ├── MapController.java
│   │   │   │   ├── SignupController.java
│   │   │   ├── services/
│   │   │   │   ├── ProfileService.java
│   │   │   │   ├── PaymentService.java
│   │   │   │   ├── RideRequestService.java
│   │   │   │   └── MapService.java
│   │   └── resources/org/example/rideshareapp/
│   │       ├── login.fxml
│   │       ├── main.fxml
│   │       ├── ProfilePage.fxml
│   │       ├── RideRequestPage.fxml
│   │       ├── MapPage.fxml
│   │       └── SignupPage.fxml
│   └── module-info.java
└── pom.xml  (or Maven config)
```

---

## Components/Libraries

| Component | Purpose |
|------------|----------|
| **Java 17** | Core application language |
| **JavaFX 17** | UI framework for FXML-based scenes |
| **FXML** | Declarative UI layout |
| **Maven** | Dependency management |
| **IntelliJ IDEA** | Recommended IDE for JavaFX development |

---

## Future Improvements

- Add database integration to payment and profile services.  
- Add map API support (e.g. Google Maps).  
- Add saved ride history.

---

## UI Overview

- **Login Screen** → user credentials → dashboard  
- **Main Dashboard** → buttons for Profile, Map, Ride Request  
- **Profile Page** → editable info and payment methods  
- **Map Page** → placeholder for route visualization  
- **Ride Request Page** → select destination and confirm ride  

---
