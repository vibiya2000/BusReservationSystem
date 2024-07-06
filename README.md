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

# Sample API Response for Admin Login

  Post   http://localhost:8080/swagger-ui/index.html#/admin-login-controller/logInAdmin
   - Sample Request Body
     
     + Admin can login with mobile number and password provided at the time of registation
          

     
            
          
              {
                "adminPassword": "dna960",
                "mobileNumber": "9486090837"
               }
             
  - Sample Response Body
    
            {
              "adminId": 1,
              "uuid": "0422",
              "adminLoginTime": 2024,7,6,16,00,00
             }
 # Swagger UI
   Admin Login Controller
   
  ![image](https://github.com/vibiya2000/BusReservationSystem/blob/main/admin%20login%20controller.png)

  Admin Controller

  ![image](https://github.com/vibiya2000/BusReservationSystem/blob/main/admin%20controller.png)
  
    
    
