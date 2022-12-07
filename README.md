# telephone-billing
Telephone Billing gồm 2 API, ghi nhận cuộc gọi và tính tiền cước.

# Mô tả API
**1. POST** - /mobile/{username}/call

**Body Request**

{
    "duration": double //Thoi luong cuoc goi
}

**Path Variable**

_username:_ Username của người dùng tạo cuộc gọi

**Authentication:** None

**Response Body:**

{
    "user_name": string,
    "call_count": double
}

**curl:**

curl --location --request POST 'localhost:8080/mobile/hieunm3538/call' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=BC064EB55D537FDADFFB91BFB23A448A' \
--data-raw '{
"duration": 3000
}'

**2. GET** - /mobile/{username}/billing

**Path Variable**

_username:_ Username của người dùng tạo cuộc gọi

**Authentication:** None

**Response Body:**

{
"block_count": double,
"call_count": double
}

**curl:**

curl --location --request GET 'localhost:8080/mobile/hieunm3538/billing' \
--header 'Cookie: JSESSIONID=BC064EB55D537FDADFFB91BFB23A448A'

# Hướng dẫn khởi chạy
**Bước 1:** Run _docker-compose.yml_

**Bước 2:** Run _MigrationService_ trong module _telephone-billing-migration_

**Bước 3:** Run _ApplicationService_ trong module _telephone-billing-application_

# Testing

Các API được viết Unit Tests cho các happy cases.
Các TCs đã được manual test:

1. Call API _Record Call_ bằng username hợp lệ (dưới 32 ký tự) -> Call API _Get Bill_ để kiểm tra kết quả.
2. Call API _Record Call / Get Bill_ bằng username không hợp lệ (nhiều hơn 32 ký tự) -> Throw Exception
3. Call API _Record Call_ bằng username không tồn tại -> Tạo mới username tương ứng.
4. Call API _Get Billing_ bằng username không tồn tại -> Throw Exception.

# Hướng phát triển

**1. Security.** 

Hiện các API không có Authentication và Authorization.

Hướng phát triển: Thêm API đăng nhập sử dụng username và password, hệ thống dựa vào token (JWT, Basic Auth) đăng nhập để xác nhận username hợp lệ.

**2. Add Test Coverage**

Thêm Test Coverage để đánh giá độ hiệu quả của unittest.

**3. Add CI/CD**

Thêm CI/CD cho hệ thống. Cá nhân em có kinh nghiệm sử dụng GitLab CI/CD phục vụ deploy và automation test.

**4. Add H2 Database for Test/ Migration for Tests**

Các case unit test hiện đang sử dụng chung database với system -> Update application-test sử dụng h2 database hoặc tạo image mysql mới phục vụ test.