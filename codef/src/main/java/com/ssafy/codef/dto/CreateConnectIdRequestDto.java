package com.ssafy.codef.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
public class CreateConnectIdRequestDto {
    private String countryCode;
    private String businessType;
    private String clientType;
    private String organization;
    private String loginType;
    private String password;
    private String id;
    private String birthday;

    @Builder
    public CreateConnectIdRequestDto(String countryCode, String businessType, String clientType, String organization, String loginType, String password, String id, String birthDate) {
        this.countryCode = countryCode;
        this.businessType = businessType;
        this.clientType = clientType;
        this.organization = organization;
        this.loginType = loginType;
        this.password = password;
        this.id = id;
        this.birthday = birthDate;
    }

    @Override
    public String toString() {
        return "CreateConnectIdRequestDto{" +
                "countryCode='" + countryCode + '\'' +
                ", businessType='" + businessType + '\'' +
                ", clientType='" + clientType + '\'' +
                ", organization='" + organization + '\'' +
                ", loginType='" + loginType + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", birthDate='" + birthday + '\'' +
                '}';
    }
}
