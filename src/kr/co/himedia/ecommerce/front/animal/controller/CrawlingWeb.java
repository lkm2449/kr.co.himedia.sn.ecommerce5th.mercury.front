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
 * File			: CrawlingWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240226105315][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.animal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.himedia.ecommerce.front.animal.dto.AnimalDto;
import kr.co.himedia.ecommerce.front.animal.service.AnimalSrvc;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-02-26
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.animal.controller.CrawlingWeb")
public class CrawlingWeb {
	
	@Inject
	private AnimalSrvc animalSrvc;
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(CrawlingWeb.class);
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/front/animal/crawling.web")
	public void crawling(HttpServletRequest request, HttpServletResponse response) {
		
		try {
//		String key = "f92de172e7a044ffb8ffad948b55a89e";
//		String type = "json";
//		String pSize = "1000";
//		String state_nm = "보호중";
		
//		String url = "https://openapi.gg.go.kr/AbdmAnimalProtect?KEY=" + key + "&Type=" + type + "&pSize= " + pSize + "&STATE_NM=" + state_nm;
		
		String url="https://openapi.gg.go.kr/AbdmAnimalProtect?KEY=f92de172e7a044ffb8ffad948b55a89e&Type=json&pSize=1000&STATE_NM=보호중&SPECIES_NM=개";
		
		Map<String, String> requestMap = new HashMap<>();
		
		Map<String, Object> responseMap = Request(url, requestMap);
		
		// 전체(2개: 헤더, 데이터들)
		ArrayList<Object> arrayList = (ArrayList<Object>) responseMap.get("AbdmAnimalProtect");
		
		// 데이터들
		Map<String, Object> rowMap = (HashMap<String, Object>) arrayList.get(1);
		
		ArrayList<Object> rowList = (ArrayList<Object>) rowMap.get("row");
		
		List<AnimalDto> animalList = new ArrayList<AnimalDto>();
		
		for (int loop = 0; loop < rowList.size(); loop++) {
			Map<String, String> valueMap = (HashMap<String, String>) rowList.get(loop);
			
			AnimalDto animalDto = new AnimalDto();
			
			animalDto.setCd_sig(valueMap.get("SIGUN_CD"));
			animalDto.setSig_nm(valueMap.get("SIGUN_NM"));
			animalDto.setAbd_no(valueMap.get("ABDM_IDNTFY_NO"));
			animalDto.setDt_recept(valueMap.get("RECEPT_DE"));
			animalDto.setDisc_info(valueMap.get("DISCVRY_PLC_INFO"));
			animalDto.setState(valueMap.get("STATE_NM"));
			animalDto.setPbl_no(valueMap.get("PBLANC_IDNTFY_NO"));
			animalDto.setDt_pbl_start(valueMap.get("PBLANC_BEGIN_DE"));
			animalDto.setDt_pbl_end(valueMap.get("PBLANC_END_DE"));
			animalDto.setSpe_nm(valueMap.get("SPECIES_NM"));
			animalDto.setCol_nm(valueMap.get("COLOR_NM"));
			animalDto.setAge(valueMap.get("AGE_INFO"));
			animalDto.setBdwgh(valueMap.get("BDWGH_INFO"));
			animalDto.setSex(valueMap.get("SEX_NM"));
			animalDto.setNeut_yn(valueMap.get("NEUT_YN"));
			animalDto.setSfert(valueMap.get("SFETR_INFO"));
			animalDto.setShter_nm(valueMap.get("SHTER_NM"));
			animalDto.setShter_telno(valueMap.get("SHTER_TELNO"));
			animalDto.setPostcode(valueMap.get("REFINE_ZIP_CD"));
			animalDto.setAddr1(valueMap.get("REFINE_ROADNM_ADDR"));
			animalDto.setAddr2(valueMap.get("REFINE_LOTNO_ADDR"));
			animalDto.setJurisd(valueMap.get("JURISD_INST_NM"));
			animalDto.setImg(valueMap.get("IMAGE_COURS"));
			animalDto.setImg_thumb(valueMap.get("THUMB_IMAGE_COURS"));
			
			animalList.add(animalDto);
		}
		
		animalSrvc.insert(animalList);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> Request(String url, Map<String,String> map) {
		
		Map<String,Object> returnMap = new HashMap<>();
		
		OkHttpClient client = new OkHttpClient();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonBody = "";
		try {
			jsonBody = objectMapper.writeValueAsString(map);
		}
		catch (JsonProcessingException e) {
			logger.error("[kr.co.himedia.ecommerce.util.Request().1st] " + e.getMessage(), e);
		}
		
		MediaType JSON = MediaType.get("application/json; charset=utf-8");
		RequestBody requestBody = RequestBody.create(jsonBody, JSON);
		
		Request request = new Request.Builder()
					.url(url)
					.post(requestBody)
					.build();
		
		try {
			Response response = client.newCall(request).execute();
			
			// System.out.println(response.body().string());
			returnMap = objectMapper.readValue(response.body().string(), Map.class);
		}
		catch (IOException e) {
				logger.error("[kr.co.himedia.ecommerce.util.Request().2nd] " + e.getMessage(), e);
		}
		
		return returnMap;
	}
	
}
