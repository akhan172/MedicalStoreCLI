# 💊 MedicalStoreCLI

A **Java Console-based Medical Store Management System** built using **Hibernate JPA**, **MySQL**, and **Java Collections**, designed to manage medicines, customers, and orders efficiently.

---

## 🚀 Features

- 🏪 **Admin Management**
  - Add new medicines
  - Update quantity and price
  - Delete items
  - See all orders status
  - Update and change order status

- 👤 **Customer Management**
  - Register new customers
  - Retrieve customer-specific order history

- 📦 **Order Management**
  - Place new orders
  - Track order status (PLACED, SHIPPED, CANCELLED)
  - View all customer orders

- ✅ Enum-based status handling
- 📄 Hibernate JPA for ORM
- 🧵 Clean, layered architecture

---

## 🛠️ Technologies Used

- Java (JDK 17+)
- Maven
- Hibernate JPA
- MySQL
- Eclipse IDE
- Git & GitHub

---

## 📂 Project Structure
```src/
└── main/
    ├── java/
    │   └── com/infosys/med_store/
    │       ├── controller/
    │       │   └── MainController.java
    │       ├── dao/
    │       │   ├── AdminDao.java
    │       │   ├── CustomerDao.java
    │       │   ├── MedicineDao.java
    │       │   └── OrderDao.java
    │       ├── entity/
    │       │   ├── Admin.java
    │       │   ├── Customer.java
    │       │   ├── Medicine.java
    │       │   ├── Order.java
    │       │   └── OrderItem.java
    │       └── service/
    │           ├── InsertAdmin.java
    │           ├── InsertCustomer.java
    │           ├── LoginAdmin.java
    │           ├── LoginCustomer.java
    │           ├── MedicineByName.java
    │           ├── MedicineInsert.java
    │           ├── PlaceOrder.java
    │           ├── UpdateOrderStatus.java
    │           └── UpdateStock.java
    └── resources/
        └── META-INF/
            └── persistence.xml
```

## Setup

1. Clone the repo
2. Import as Maven project in Eclipse
3. Set up MySQL DB and update persistence.xml
4. Run MainController.java

## Author

- [@akhan172](https://github.com/akhan172)

