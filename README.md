# 상품 주문 시스템

## 실행방법

### 1. 빌드

```
./gradlew build
```

### 2. 실행

```
java -jar -Dfile.encoding=utf-8 build/libs/server1-1.0-SNAPSHOT.jar
```

## 요구사항
- 한 번에 여러개의 상품을 같이 주문할 수 있다.
- 키트 상품의 경우, 주문 금액이 5만원 미만일 경우 배송료 5,000원이 추가된다. 
  `[상품번호 9296 하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누]` 를 1개 구입할 경우에는 9,900 * 1개 + 5,000원이 된다.
  `[상품번호 45947 일러스트레이터 집시의 매력적인 얼굴 그리기]` 와 `[상품번호 91008 작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트]` 1개를 구입할 경우에는 249,500 + 28,000 이 되어 5만원이 넘어가므로 배송료는 추가되지 않는다.
- 결제 시 재고확인을 하여야 하며 재고가 없을 경우 결제를 시도하면 SoldOutException 이 발생한다.
  주문은 여러 유저가 동시에 주문할 수 있다는 것을 감안하여 multi thread 요청 을 할 수 있어야하고, 그에 맞게 주문 수량이 관리되어야 한다)
- 주문은 상품번호, 수량을 입력받는다.
- 상품번호, 수량은 반복적으로 입력 받을 수 있다. 
  단, 클래스의 경우는 하나의 유저당 한 번의 결제만 가능하다. 동일한 유저가 동일 클래스를 중복 결제하는 것을 막아주어야 한다.
  키트 상품의 경우 여러 번에 걸쳐 복수 개의 구매가 가능하다.
- empty 입력 (space + ENTER 이 되었을 경우 해당 건에 대한 주문이 완료된 것으 로 판단한다.
- 주문이 완료 되었을 경우, 주문 내역과 주문 금액, 결제 금액 (배송비 포함) 을 화면에 display 한다.
- 'quit' 을 입력하면 프로그램이 종료된다.