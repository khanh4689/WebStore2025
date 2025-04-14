# 🛒 WebStore2025

**WebStore2025** is a modern e-commerce web application built with **Spring Boot** and **Thymeleaf**. It provides a smooth shopping experience for customers and efficient management tools for staff.

---

## 📸 Screenshots

| Page | Preview |
|------|---------|
| **Homepage** | ![Homepage](https://github.com/user-attachments/assets/eca5751b-9a34-4004-9253-38b2630f8711) |
| **Login** | ![Login](https://github.com/user-attachments/assets/44d5f907-038c-4a72-8082-a2b71e544b01) |
| **Account Locked** | ![Locked](https://github.com/user-attachments/assets/3c541569-2997-45e1-97f0-095783f968b2) |
| **Register** | ![Register](https://github.com/user-attachments/assets/3e82c574-71fc-4f10-a7c8-ded4c87674d8) |
| **Product Search** | ![Search](https://github.com/user-attachments/assets/e4182d8d-a2de-4a58-832c-08cfcf925009) |
| **Shopping Cart** | ![Cart](https://github.com/user-attachments/assets/bf59001b-45cc-4fad-b378-374e5560b6b1) |
| **Checkout & Result** | ![Checkout](https://github.com/user-attachments/assets/10781c8c-3076-499f-b864-fe692462ca5d) ![Success](https://github.com/user-attachments/assets/96b6d43e-dab9-4510-93a1-f5d82fbff022) |
| **Product Management** | ![Manage Products](https://github.com/user-attachments/assets/907983c2-8180-4b23-a80f-0bddf216f4fa) |
| **Category Management** | ![Categories](https://github.com/user-attachments/assets/55311a0c-30bc-495a-9b90-3de0503b1677) |
| **Order Management** | ![Orders](https://github.com/user-attachments/assets/4b478357-7b0b-422b-8378-1cd8b80d165a) |
| **Staff Management** | ![Staff](https://github.com/user-attachments/assets/5625a08a-a0e2-415d-b50d-a8a814d9d5a9) |

---

## 🚀 Features

### 👥 For Customers:
- Account registration and login (supports Google/Facebook).
- Browse product categories and view details.
- Add, update, or remove items in the shopping cart.
- Place orders, checkout, and track order history.

### 🛠️ For Admin/Staff:
- **Product Management**: Add, update, delete products with image uploads.
- **Category Management**: Create, edit, and remove categories.
- **Order Management**: Manage order statuses (Pending, Shipping, Completed, Cancelled).
- **User Management**:
  - Assign roles: `USER`, `STAFF`, `BLOCK`.
  - Lock or unlock user accounts.

---

## 🔐 Security

The system uses **Spring Security**, **JWT**,**Password Hasing**, and **OAuth2** for secure authentication and authorization.

### 🔑 Role-Based Access:
- Users must **log in** to:
  - Add items to cart.
  - Place and manage orders.
  - View personal order history.

- Roles:
  - `ROLE_USER`: Customers
  - `ROLE_ADMIN`: Administrators

### 🛡️ JWT Authentication:
After successful login, the server issues a **JWT token** which is attached to all authorized API requests:

```http
Authorization: Bearer <token>

git clone https://github.com/khanh4689/WebStore2025.git
cd WebStore2025

SQL at src/main/resources/sql


