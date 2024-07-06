# BusReservationSystem

![logo](https://raw.githubusercontent.com/vibiya2000/BusReservationSystem/main/bus%20logo.png)


I have developed this REST API for a Bus Reservation System Portal Application. This API performs all the fundamental CRUD operations of any Bus Reservation Application platform with user validation at every step.

# Development Tools
- JAVA
- SPRING BOOT
- SPRING DATA JPA
- HIBERNATE
- MYSQL
# Modules
- Login and Logout Module
- Admin Module
- User Module
- Route Module
- Bus Module
- Reservation Module
# Features
- User and Admin authentication & validation with session uuid.
- Admin Features:
    + Administrator Role of the entire application.
    + Only registered admins with valid session token can add/update/delete route and bus from main database.
    + Admin can access the details of different users and reservations.
- User Features:
    + Registering themselves with application, and logging in to get the valid session token
    + Viewing list of available buses and booking a reservation
    + Only logged in user can access his reservations, profile updation and other features.
# Api Root EndPoint
http://localhost:8080/swagger-ui/index.html#/
# API Module Endpoints
    # Login Module
    
    
