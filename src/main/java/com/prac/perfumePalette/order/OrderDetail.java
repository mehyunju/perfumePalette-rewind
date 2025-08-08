package com.prac.perfumePalette.order;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {

    // 주문 정보
    private String orderNo;
    private int orderQuantity;
    private String orderStatus;

    // 회원 정보
    private int memberNo;

    // 향수 정보
    private int perfumeNo;
    private String perfumeName;
    private int perfumePrice;

    // 결제금액
    private int totalPrice;

    // 결제금액 계산 메서드
    public void calculateTotalPrice() {
        this.totalPrice = this.perfumePrice * this.orderQuantity;
    }
}
