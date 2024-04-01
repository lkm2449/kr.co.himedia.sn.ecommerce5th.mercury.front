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
 * File			: LoginApi.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240109110207][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.join.controller;

import java.util.Properties;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.join.service.JoinSrvc;
import kr.co.himedia.ecommerce.util.security.HSwithSHA;
import kr.co.himedia.ecommerce.util.security.SKwithAES;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-01-09
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.join.controller.JoinApi")
public class JoinApi extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(JoinApi.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	private JoinSrvc joinSrvc;
	
	@RequestMapping(value = "/front/join/joinProc.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean joinProc(@RequestBody final CustomerDto customerDto) {
		
		boolean isSuccess = false;
		
		try {
						
			
			// 해쉬 암호화
			customerDto.setPasswd(HSwithSHA.encode(customerDto.getPasswd()));
			
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			customerDto.setCst_nm(aes.encode(customerDto.getCst_nm()));
			customerDto.setPhone(aes.encode(customerDto.getPhone()));
			customerDto.setPostcode(aes.encode(customerDto.getPostcode()));
			customerDto.setAddr1(aes.encode(customerDto.getAddr1()));
			//customerDto.setAddr2(aes.encode(customerDto.getAddr2()));
			customerDto.setAddr3(aes.encode(customerDto.getAddr3()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));
			
			
			if (joinSrvc.insert(customerDto)) isSuccess = true;
			
			debuggingJSON(isSuccess);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".joinProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return isSuccess;
	}
	
	@RequestMapping(value = "/front/join/idCheck.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean idCheck(@RequestBody final CustomerDto customerDto) {
		
		boolean isSuccess = false;
		
		try {

			if (joinSrvc.idCheck(customerDto) != 0) {
				isSuccess = false;
			} else {
				isSuccess = true;
			}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".idCheck()] " + e.getMessage(), e);
		}
		finally {}
		
		return isSuccess;
	}
	
	@RequestMapping(value = "/front/join/duplicateCheck.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean duplicateCheck(@RequestBody final CustomerDto customerDto) {
		
		boolean isSuccess = false;
		
		try {
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			customerDto.setCst_nm(aes.encode(customerDto.getCst_nm()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));

			if (joinSrvc.duplicateCheck(customerDto) >= 1) {
				isSuccess = false;
			}
			else {
				isSuccess = true;
			}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".duplicateCheck()] " + e.getMessage(), e);
		}
		finally {}
		
		return isSuccess;
	}
	
}


