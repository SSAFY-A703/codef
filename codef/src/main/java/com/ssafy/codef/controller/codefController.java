package com.ssafy.codef.controller;

import com.ssafy.codef.dto.CreateConnectIdRequestDto;
import com.ssafy.codef.dto.GetBankAccountRequestDto;
import com.ssafy.codef.dto.GetCardListRequestDto;
import com.ssafy.codef.dto.addReferenceRequestDto;
import com.ssafy.codef.service.CodefService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codef")
@RequiredArgsConstructor
@Slf4j
public class codefController {
    private final CodefService codefService;

    @GetMapping("/getToken")
    public String getToken(){
        log.info("getToken controller");
        String token = codefService.getToken();
        return token;
    }

    @PostMapping("/create/ConnectedId")
    public String CreateConnectedId(@RequestBody CreateConnectIdRequestDto dto) {
        codefService.encryptPassword(dto);
        log.info("createConnected controller parameter {}",dto);
        return codefService.createConnectId(dto);
    }
    @PostMapping("/bank/account/list")
    public String getBankAccountList(@RequestBody GetBankAccountRequestDto dto){
        log.info("bank Account List get , Controller {}",dto);
        return codefService.getBankAccountList(dto);
    }

    @PostMapping("/card/list")
    public String getCardList(@RequestBody GetCardListRequestDto dto) {
        log.info("카드리스트 정보 가져오기 , Controller {}",dto);
        return codefService.getCardList(dto);
    }

    //기존 커넥션 아이디로 다른 계정들 추가하기
    @PostMapping("/add/accounts")
    public String addAccounts(@RequestBody addReferenceRequestDto dto) {
        log.info("connection id에 추가적인 은행 카드 등록하기");
        return codefService.addReferencesAccount(dto);
    }
}
