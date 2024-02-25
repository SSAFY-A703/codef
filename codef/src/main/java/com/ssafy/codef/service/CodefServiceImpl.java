package com.ssafy.codef.service;

import com.ssafy.codef.dto.CreateConnectIdRequestDto;
import com.ssafy.codef.dto.GetBankAccountRequestDto;
import com.ssafy.codef.dto.GetCardListRequestDto;
import com.ssafy.codef.dto.addReferenceRequestDto;
import com.ssafy.codef.util.ApiRequest;
import com.ssafy.codef.util.CommonConstant;
import com.ssafy.codef.util.RSAUtil;
import com.ssafy.codef.util.RequestToken;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ssafy.codef.util.CommonConstant.*;

@Service
public class CodefServiceImpl implements  CodefService{
    @Autowired
    private RequestToken requestToken;
    @Autowired
    private CommonConstant commonConstant;
    @Autowired
    private ApiRequest apiRequest;
    @Override
    public String getToken() {
        return requestToken.getToken();
    }
    //현재는 connectionId를 걍 저장하고 꺼내쓰는데 이제 -> 유저별로 connectionId 관리하는 코드가 있어야됨
    @Override
    public String createConnectId(CreateConnectIdRequestDto dto) {
        return apiRequest.request(TEST_DOMAIN+CREATE_ACCOUNT, dto);
    }

    @Override
    public String getBankAccountList(GetBankAccountRequestDto dto) {
        return apiRequest.request(TEST_DOMAIN+KR_BK_1_P_001,dto);
    }

    @Override
    public String getCardList(GetCardListRequestDto dto) {
        return apiRequest.request(TEST_DOMAIN+KR_CD_P_001,dto);
    }

    @SneakyThrows
    @Override
    public void encryptPassword(CreateConnectIdRequestDto dto) {
        dto.setPassword(RSAUtil.encryptRSA(dto.getPassword(),commonConstant.getPUBLIC_KEY()));
    }

    @Override
    public String addReferencesAccount(addReferenceRequestDto dto) {
        return apiRequest.request(TEST_DOMAIN+ADD_REFERENCE,dto);
    }
}
