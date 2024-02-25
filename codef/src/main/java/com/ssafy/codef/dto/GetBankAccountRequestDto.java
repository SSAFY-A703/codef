package com.ssafy.codef.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBankAccountRequestDto {
    private String organization;
    private String connectedId;
    private String birthDate;

    // 대구은행 추가 파라미터 출금계좌번호랑 출금계좌비밀번호 추가해야됨 비밀번호는 RSA 암호화해야함
    private String withdrawAccountNo;
    private String withdrawAccountPassword;

}
