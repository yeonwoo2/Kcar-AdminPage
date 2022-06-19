# Kcar-AdminPage
kcar 관리자페이지 토이프로젝트
# 차량 관리자페이지

###  kcar 방대한 데이터관리 및 상품을 효율적으로 모니터링 할 수 있는 관리자페이지 제공
* 백엔드에 초점을 맞춰 백엔드 개발에 주력
* 단순 기능 구현 뿐 아니라 성능, 코드의 재사용성 및 유지보수성을 고려하여 구현하는 것을 목표로 개발  
* 프론트엔드와 백엔드분리(Api 서버)


### :white_check_mark: 사용기술 및 개발환경

Java, Spring Boot, JPA, QueryDSL, MySQL

### :white_check_mark: Application UI
자세한 사항은  :point_right: https://github.com/JeongbinYoon/admin

예시)
![판매관리_교환관리 – 1](https://user-images.githubusercontent.com/82079635/174500346-48b5e224-1da2-4c1a-b01f-2927395eb34d.png)


### :white_check_mark: ERD 설계
자세한 사항은  :point_right: https://www.erdcloud.com/d/LMEPReTKZJzAYXa6C

![erd](https://user-images.githubusercontent.com/82079635/174471588-519ad501-7db9-427e-9876-400097d00bf8.jpg)

### :white_check_mark: API 설계

![api](https://user-images.githubusercontent.com/82079635/174473155-7f21ed9c-4d9f-4d55-a663-19abf0eed180.jpg)

### :white_check_mark: 각 기능별 Use Case
#### :file_folder: Item
* 차량은 여러개의 주문요청에 들어갈 수 있다.
* 차량은 하나의 카테고리에만 들어갈 수 있다.
* 차량은 판매종료된 것만 삭제할 수 있다.
* 차량은 한명의 차량판매사만 들어갈 수 있다.
#### :file_folder: Order
* 하나의 주문요청에는 하나의 차량만 들어갈 수 있다.
* 하나의 주문요청에는 하나의 주문폼이 들어간다.
#### :file_folder: User
* 회원은 하나의 주문폼을 작성할 수 있다. 
### :white_check_mark: 각 기능별 비지니스 로직
#### :file_folder: Item
* 차량등록시 가격,보험이력,점검이력,차량평가사가 동시에 등록된다.
* 차량삭제는 판매종료된 상품만 할 수있다.
* 차량 검색조건에 따라 보여지는 리스트가 다르다.
#### :file_folder: Order
* 주문 검색조건에 따라 보여지는 리스트가 다르다.
* 주문상태가 완료된것만 결제건수에 보여진다.
#### :file_folder: Delivery
* 배송상태를 수정할 수 있다.
* 배송 검색조건에 따라 보여지는 리스트가 다르다.
