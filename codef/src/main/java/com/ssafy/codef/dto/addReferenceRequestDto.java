package com.ssafy.codef.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class addReferenceRequestDto {
    private String connectedId;
    private String countryCode;
    private String businessType;
    private String organization;
    private String clientType;
    private List<CreateConnectIdRequestDto> accountList = new ArrayList<>();
}
