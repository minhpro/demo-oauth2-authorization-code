## Demo oauth2 client authorization code

Login with Canvas LMS

### Đăng ký oauth2 application (client_id and client_secret)

Để có thể sử dụng OAuth2 flow thì cần đăng ký application, ở trong Canvas gọi là thêm Developer Keys

Go to Admin > Site Admin > Developer Keys > + Developer Key > API Key

Điền Key Name và Owner Email, ví dụ

* Key Name: OAuth2 Client Key
* Owner Email: admin@example.com

Rồi **Save** lại

Ra ngoài màn hình danh sách Developer Keys sẽ thấy Key mới được thêm, hãy **ON** nó lên, ở cột *Details* sẽ là
* client_id: ví dụ 10000000000009
* client_secret: chọn *Show key* để thấy

Vậy là ta đã đăng ký xong oauth2 client app với Canvas.

### Cấu hình Client Application

Điền các thông tin sau vào `application.properties`:

```properties
# Thay localhost:3000 bằng domain trỏ tới Canvas server
oauth2.provider.authorization-uri=http://localhost:3000/login/oauth2/auth
oauth2.provider.access-token-uri=http://localhost:3000/login/oauth2/token
# client_id và client_secret được tạo từ bước đăng ký Developer Key
oauth2.client-id=10000000000008
oauth2.client-secret=zFBCNrWBtxSuJKXzonAEXjGJRpNZ8sOl2kcwcUJagiwnByCs3icUyFD1rbM4BB5u
# Thay localhost:8080 bằng domain trỏ tới Application server
oauth2.redirect-uri=http://localhost:8080/api/auth/oauth2response
# Giá trị bất kỳ dùng để tránh lỗi bảo mật cross origin khi exchange authorization code
oauth2.state.value=jGJRpNZ8sOl2k
```

Sau đó chạy Application lên và thực hiện login thông qua Canvas
