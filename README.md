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

**2. GET** - /mobile/{username}/billing

**Path Variable**

_username:_ Username của người dùng tạo cuộc gọi

**Authentication:** None

**Response Body:**

{
"block_count": double,
"call_count": double
}

# Hướng dẫn khởi chạy
**Bước 1:** Run _docker-compose.yml_

**Bước 2:** Run _TelephoneBillingApplication_

# Testcases

Do thời gian hạn chế, nên project chưa được viết unit tests.

Các TCs đã được thử:

1. Call API _Record Call_ bằng username hợp lệ (dưới 32 ký tự) -> Call API _Get Bill_ để kiểm tra kết quả.
2. Call API _Record Call / Get Bill_ bằng username không hợp lệ (nhiều hơn 32 ký tự) -> Throw Exception
3. Call API _Record Call_ bằng username không tồn tại -> Tạo mới username tương ứng.
4. Call API _Get Billing_ bằng username không tồn tại -> Throw Exception.