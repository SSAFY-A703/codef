package com.ssafy.codef.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCardListRequestDto {
    private String organization;
    private String connectedId;

    //card 정보 KB는 카드번호랑 앞자리 2개 생년월일 입력받아야됨
    //inquiryType -> 카드이미지 정보 포함 미포함 여부 0 미포함
    private String cardNo;
    private String cardPassword;
    private String birthDate;
    private String inquiryType;
}
