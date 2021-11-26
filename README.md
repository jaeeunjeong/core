# 스프링 핵심 원리 - 기본편
 > 스프링 핵심 원리 - 기본편 에 대한 강의를 듣고 예제를 통해 기본기를 다진다.
 
## 비즈니스 요구사항과 설계
### 회원
- 가입 및 조회
- 회원 등급은 BASIC/VIP 두 가지 -> ENUM 이용
- 회원 데이터 베이스 형태는 결정되지 않음.
### 주문과 할인 정책
- 회원은 상품을 주문할 수 있다.
- 등급에 따라 할인 정책을 적용
- ~~할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라.~~  
  -> 고정할인에서 정률%할인 (10%)로 변경