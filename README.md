# 🛒 ShopStore

ShopStore là một website thương mại điện tử đơn giản hỗ trợ khách hàng mua sắm, nhân viên theo dõi đơn hàng, và quản lý kiểm soát hệ thống sản phẩm/danh mục hiệu quả.

---

## 📌 Hình ảnh trang web

### 👥 Trang chủ 
![Image](https://github.com/user-attachments/assets/eca5751b-9a34-4004-9253-38b2630f8711)
### 👥 Trang đăng nhập
![Image](https://github.com/user-attachments/assets/b97650a7-3261-4e3e-856b-067ffa685597)
### 👥 Trang đăng ký
![Image](https://github.com/user-attachments/assets/3cc57c5c-7a71-4da0-a926-10c864552cc7)
### 👥 Trang tìm kiếm theo tên sản phẩm
![Image](https://github.com/user-attachments/assets/e4182d8d-a2de-4a58-832c-08cfcf925009)
### 👥 Trang giỏ hàng
![Image](https://github.com/user-attachments/assets/bf59001b-45cc-4fad-b378-374e5560b6b1)
### 👥 Khung thanh toán
![Image](https://github.com/user-attachments/assets/11f82546-8e41-4b97-bd40-90fc7019ec07)
### 👥 Quản lý sản phẩm
![Image](https://github.com/user-attachments/assets/907983c2-8180-4b23-a80f-0bddf216f4fa)
![Image](https://github.com/user-attachments/assets/ce8c0bb6-8a2c-4482-8bd4-6aa520b4cf89)
### 👥 Quản lý danh mục
![Image](https://github.com/user-attachments/assets/89cfee6b-668e-4283-8282-01c0cd83ba1e)
![Image](https://github.com/user-attachments/assets/55311a0c-30bc-495a-9b90-3de0503b1677)
### 👥 Quản lý đơn hàng
![Image](https://github.com/user-attachments/assets/48636724-f7e1-499a-8818-4ace7c0ba4ac)
### 👥 Quản lý tài khoản nhân viên
![Image](https://github.com/user-attachments/assets/3118e1d8-864e-4fd6-b9a5-b167b90c9ab1)
![Image](https://github.com/user-attachments/assets/7467414c-e32b-46cf-a62f-56db438a8abc)

## 📌 Tính năng chính

### 👥 Dành cho khách hàng:
- Đăng ký / Đăng nhập / Đăng xuất
- 
- Tìm kiếm sản phẩm(Tìm kiếm theo tên, theo danh mục)
- Xem tất cả sản phẩm hoặc theo danh mục
- Thêm sản phẩm vào giỏ hàng
- Thanh toán đơn hàng
- Xem lịch sử đơn hàng đã mua

### 🔐 Dành cho admin & nhân viên:
- CRUD sản phẩm ( Thêm, xóa sản phẩm. Cập nhật trừ id)
- CRUD danh mục sản phẩm( Thêm, xóa sản phẩm. Cập nhật trừ id)
- CRUD đơn hàng( Xóa đơn hàng)
- Cấp quyền nhân viên cho user(Chỉ cập nhật quyền nhân viên cho user)
  



## 🛠 Công nghệ sử dụng

- 💻 **Backend:** Spring Boot, Spring Security, JWT
- 🌐 **Frontend:** Thymeleaf, Bootstrap
- 🗄 **Cơ sở dữ liệu:** SQL Server 2017

---

## ▶️ Hướng dẫn cài đặt & chạy dự án

### 1. Yêu cầu hệ thống
- Java 17+
- Maven 3.9+
- SQL Server 2017 (đã khởi động SQL Server và tạo sẵn database)
- IDE: Eclipse hoặc IntelliJ IDEA

### 2. Clone và cấu hình

```bash
git clone https://github.com/khanh4689/WebStore2025.git
cd WebStore2025
