package com.ssafy.codef.service;

import com.ssafy.codef.dto.CreateConnectIdRequestDto;
import com.ssafy.codef.dto.GetBankAccountRequestDto;
import com.ssafy.codef.dto.GetCardListRequestDto;
import com.ssafy.codef.dto.addReferenceRequestDto;

public interface CodefService {
    String getToken();

    String createConnectId(CreateConnectIdRequestDto dto);

    String getBankAccountList(GetBankAccountRequestDto dto);

    String getCardList(GetCardListRequestDto dto);

    void encryptPassword(CreateConnectIdRequestDto dto);

    String addReferencesAccount(addReferenceRequestDto dto);
}
