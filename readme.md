# 🏥 Clinic Appointment JDBC

This project is a **Java console application** built to manage clinic appointments using **JDBC** for database connectivity.  
It was developed **for practice purposes**, focusing on database access patterns **without using any frameworks** (pure JDBC only).

---

## 📘 Overview

The system allows for basic management of doctors, patients, and their appointments.  
It follows the **DAO Factory pattern**, promoting loose coupling and making database operations modular and maintainable.

---

## 🧩 Project Structure

```
ClinicAppointmentJDBC/
│
├── .idea/
├── out/
├── resources/
│ └── db.properties
│
├── src/
│ ├── application/
│ │ └── Main.java
│ │
│ ├── db/
│ │ ├── DBConfig.java
│ │ └── DBException.java
│ │
│ └── model/
│ ├── dao/
│ │ ├── impl/
│ │ │ ├── AppointmentJDBC.java
│ │ │ ├── DoctorJDBC.java
│ │ │ └── PatientJDBC.java
│ │ │
│ │ ├── AppointmentDao.java
│ │ ├── DoctorDao.java
│ │ ├── PatientDao.java
│ │ └── DaoFactory.java
│ │
│ └── entities/
│ ├── Appointment.java
│ ├── Doctor.java
│ └── Patient.java
│
├── .gitignore
├── ClinicAppointmentJDBC.iml
└── readme.md
```


---

## ⚙️ Database Access Layer (DAO)

All database interactions are encapsulated using the **DAO pattern**.  
Each entity (`Doctor`, `Patient`, `Appointment`) has its own DAO interface and JDBC implementation.

### 🩺 Doctor Methods

- `insert(Doctor d)`
- `findById(Integer id)`
- `findBySpecialty(String specialty)`
- `findAll()`
- `update(Integer id, String specialty, String email, String phone)`
- `delete(Integer id)`

### 🧍 Patient Methods

- `insert(Patient p)`
- `findById(Integer id)`
- `findByName(String name)`
- `findAll()`
- `update(Integer id, String name, String email, String phone)`
- `delete(Integer id)`

### 📅 Appointment Methods

- `schedule(Appointment a)`
- `changeDoctor(Integer appointmentId, Integer doctorId)`
- `changeDateTime(Integer appointmentId, LocalDateTime newDateTime)`
- `findById(Integer id)`
- `findByPatient(Patient patient)`
- `findByDoctor(Doctor doctor)`
- `findByDate(LocalDate date)`
- `cancel(Integer id)`

---

## 🏗️ Design Pattern: DAO Factory

The **DaoFactory** class provides a centralized way to instantiate DAO objects without exposing JDBC implementation details.

Example:

```java
DoctorDao doctorDao = DaoFactory.createDoctorDao();
PatientDao patientDao = DaoFactory.createPatientDao();
AppointmentDao appointmentDao = DaoFactory.createAppointmentDao();
```

---

## 🛠️ Technologies Used

- Java 21
- JDBC (Java Database Connectivity)
- MySQL Connector
- DAO Factory Pattern
---

## 🔧 Configuration

The database connection settings are defined in the `db.properties` file located in the `resources` folder:

````
dburl=jdbc:mysql://localhost:3306/clinicdb
user=root
password=yourpassword
````

---

##  ▶️ How️ to Run

1. Clone the repository.

2. Configure your MySQL database and credentials in `resources/db.properties`.

3. Import the project in **IntelliJ IDEA** or any Java IDE.

4. Run `Main.java` from the `application` package.

---

## 📚 Learning Purpose

This project was created to practice JDBC concepts, including:

- Connection management

- Prepared statements

- ResultSet handling

- Exception handling with custom DBException

- Data access abstraction using the DAO pattern

---

## 🧑‍💻 Author

**Luiz Felipe (Luix)**
Java Developer — practicing clean architecture and JDBC concepts.