# 🛒 WebStore2025

**WebStore2025** là một ứng dụng web thương mại điện tử phát triển bằng **Spring Boot** và **Thymeleaf**, hỗ trợ trải nghiệm mua sắm trực tuyến và quản lý bán hàng một cách hiệu quả cho cả khách hàng và nhân viên.

---

## 📸 Giao diện chính

| Trang | Hình ảnh |
|------|----------|
| **Trang chủ** | ![Trang chủ](https://github.com/user-attachments/assets/eca5751b-9a34-4004-9253-38b2630f8711) |
| **Đăng nhập** | ![Đăng nhập](https://github.com/user-attachments/assets/44d5f907-038c-4a72-8082-a2b71e544b01) |
| **Tài khoản bị khóa** | ![Khóa](https://github.com/user-attachments/assets/3c541569-2997-45e1-97f0-095783f968b2) |
| **Đăng ký** | ![Đăng ký](https://github.com/user-attachments/assets/3e82c574-71fc-4f10-a7c8-ded4c87674d8) |
| **Tìm kiếm sản phẩm** | ![Tìm kiếm](https://github.com/user-attachments/assets/e4182d8d-a2de-4a58-832c-08cfcf925009) |
| **Giỏ hàng** | ![Giỏ hàng](https://github.com/user-attachments/assets/bf59001b-45cc-4fad-b378-374e5560b6b1) |
| **Thanh toán & kết quả** | ![Thanh toán](https://github.com/user-attachments/assets/10781c8c-3076-499f-b864-fe692462ca5d) ![Thành công](https://github.com/user-attachments/assets/96b6d43e-dab9-4510-93a1-f5d82fbff022) |
| **Quản lý sản phẩm** | ![Quản lý sản phẩm](https://github.com/user-attachments/assets/907983c2-8180-4b23-a80f-0bddf216f4fa) |
| **Quản lý danh mục** | ![Danh mục](https://github.com/user-attachments/assets/55311a0c-30bc-495a-9b90-3de0503b1677) |
| **Quản lý đơn hàng** | ![Đơn hàng](https://github.com/user-attachments/assets/4b478357-7b0b-422b-8378-1cd8b80d165a) |
| **Quản lý nhân viên** | ![Tài khoản nhân viên](https://github.com/user-attachments/assets/5625a08a-a0e2-415d-b50d-a8a814d9d5a9) |

---

## 🚀 Tính năng nổi bật

### 👥 Dành cho khách hàng:

- Đăng ký, đăng nhập tài khoản (hỗ trợ Google/Facebook).
- Duyệt danh mục sản phẩm.
- Xem chi tiết sản phẩm.
- Thêm/sửa/xóa sản phẩm trong giỏ hàng.
- Đặt hàng, thanh toán, xem lịch sử đơn hàng.

### 🛠️ Dành cho quản trị viên/nhân viên:

- **Quản lý sản phẩm**: thêm, sửa, xóa sản phẩm; upload ảnh.
- **Quản lý danh mục**: thêm, sửa, xóa danh mục sản phẩm.
- **Quản lý đơn hàng**: cập nhật trạng thái đơn hàng (Chờ xử lý, Đang giao, Hoàn tất, Hủy).
- **Quản lý người dùng**:
  - Gán quyền: `USER`, `STAFF`, `BLOCK`.
  - Khóa/mở tài khoản người dùng.

---

## 🔐 Bảo mật hệ thống

Hệ thống sử dụng **Spring Security**, **JWT** và **OAuth2** để bảo vệ thông tin và xác thực người dùng.

### 🔑 Phân quyền truy cập

- Người dùng cần **đăng nhập** để:
  - Thêm sản phẩm vào giỏ.
  - Đặt hàng, thanh toán.
  - Xem lịch sử đơn hàng.

- Các vai trò chính:
  - `ROLE_USER`: Người mua hàng.
  - `ROLE_ADMIN`: Quản trị viên.

### 🛡️ Xác thực với JWT

- Sau khi đăng nhập thành công → hệ thống sinh ra **JWT token**.
- JWT gắn trong `Authorization` header mỗi lần gọi API:
  ```http
  Authorization: Bearer <token>
