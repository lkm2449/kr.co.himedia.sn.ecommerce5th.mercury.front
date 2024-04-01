/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF HIMEDIA.CO.KR.
 * HIMEDIA.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 HIMEDIA.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 himedia.co.kr에 있으며,
 * himedia.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * himedia.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 himedia.co.kr All Rights Reserved.
 *
 *
 * Program		: kr.co.himedia.sn.ecommerce5th.moon
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: PayupCmpn.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240208120150][pluto@himedia.co.kr][CREATE: Initial Release]
 *				: [20240208120800][pluto@himedia.co.kr][REPORT: From com.payup.pay.service.ApiService]
 */
package kr.co.himedia.ecommerce.common.component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-02-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Component("kr.co.himedia.ecommerce.common.component.PayupCmpn")
public class PayupCmpn {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(PayupCmpn.class);
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> JsonMApi(String url, Map<String,String> map) {
		
		
		Map<String,Object> returnMap = new HashMap<>();
		
		OkHttpClient client = new OkHttpClient();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonBody = "";
		try {
			jsonBody = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			logger.error("[" + this.getClass().getName() + ".JsonMApi()] " + e.getMessage(), e);
		}


		// JSON 헤더 설정
		MediaType JSON = MediaType.get("application/json; charset=utf-8");
		RequestBody requestBody = RequestBody.create(jsonBody, JSON);

		// POST 요청 생성
		Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.addHeader("User-Agent", "iPhone")
				.build();

		try {
			// 동기적으로 POST 요청 보내기
			Response response = client.newCall(request).execute();
			returnMap = objectMapper.readValue(response.body().string(), Map.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return returnMap;
	}

	
	@SuppressWarnings("unchecked")
	public Map<String,Object> JsonApi(String url, Map<String,String> map) {
		
		Map<String,Object> returnMap = new HashMap<>();
		
		OkHttpClient client = new OkHttpClient();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonBody = "";
		try {
			jsonBody = objectMapper.writeValueAsString(map);
		}
		catch (JsonProcessingException e) {
			logger.error("[" + this.getClass().getName() + ".JsonApi()] " + e.getMessage(), e);
		}
		
		MediaType JSON = MediaType.get("application/json; charset=utf-8");
		RequestBody requestBody = RequestBody.create(jsonBody, JSON);
		
		Request request = new Request.Builder()
			.url(url)
			.post(requestBody)
			.build();
		
		try {
			Response response = client.newCall(request).execute();
			returnMap = objectMapper.readValue(response.body().string(), Map.class);
		}
		catch (IOException e) {
				logger.error("[" + this.getClass().getName() + ".JsonApi()] " + e.getMessage(), e);
		}
		
		return returnMap;
	}
	
	public String getSHA256Hash(String input) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
		
		StringBuilder hexStringBuilder = new StringBuilder();
		
		for (byte b : bytes) {
			String hex = String.format("%02x", b);
			hexStringBuilder.append(hex);
		}
		
		return hexStringBuilder.toString();
	}
}