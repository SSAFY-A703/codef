package com.ssafy.codef.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.codef.dto.CreateConnectIdRequestDto;
import io.micrometer.common.util.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  API 요청 템플릿 클래스
 */
@Component
@Slf4j
public class ApiRequest {
    @Autowired
    private RequestToken requestToken;

    @Autowired
    private  ObjectMapper mapper;
    @SneakyThrows
    public <T> String request(String urlPath, T dto){
        RestTemplate restTemplate = new RestTemplate();
        String accessToken = StringUtils.isEmpty(CommonConstant.ACCESS_TOKEN) ? requestToken.getToken() : CommonConstant.ACCESS_TOKEN;
        String bodyString = prepareBody(dto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(bodyString, headers);

        ResponseEntity<String> response = restTemplate.exchange(urlPath, HttpMethod.POST, entity, String.class);
        String decodedResponse = URLDecoder.decode(response.getBody(), "UTF-8");

        if("invalid_token".equals(decodedResponse)) {
            accessToken = requestToken.getToken();
            CommonConstant.ACCESS_TOKEN = accessToken;
            headers.set("Authorization", "Bearer " + accessToken);
            entity = new HttpEntity<>(bodyString, headers);
            response = restTemplate.exchange(urlPath, HttpMethod.POST, entity, String.class);
            decodedResponse = URLDecoder.decode(response.getBody(), "UTF-8");
        } else if("access_denied".equals(decodedResponse)) {
            System.out.println("access_denied은 API 접근 권한이 없는 경우입니다.");
            System.out.println("코드에프 대시보드의 API 설정을 통해 해당 업무 접근 권한을 설정해야 합니다.");
        }

        return decodedResponse;
    }

    @SneakyThrows
    private <T> String prepareBody(T dto) {
        String bodyString;

        if (dto.getClass().equals(CreateConnectIdRequestDto.class)) {
            Map<String, Object> map = new HashMap<>();
            List<T> list = new ArrayList<T>();
            list.add(dto);
            map.put("accountList",list);
            bodyString = mapper.writeValueAsString(map);
        }else{
            bodyString = mapper.writeValueAsString(dto);
        }
        bodyString = URLEncoder.encode(bodyString, "UTF-8");

        return bodyString;
    }

}
