package com.prac.perfumePalette.perfume;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Perfume {

    private int perfumeNo;			// 품번
    private String perfumeName;		// 품명
    private String perfumeBrand;	// 브랜드
    private int perfumeVolume;		// 용량
    private int perfumePrice;		// 가격
    private int perfumeQuantity;	// 수량
    private String pScentCategory;	// 향 분류
}
