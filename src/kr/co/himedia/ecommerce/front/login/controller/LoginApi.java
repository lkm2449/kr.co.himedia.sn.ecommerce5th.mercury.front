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
package kr.co.himedia.ecommerce.front.login.controller;

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
import kr.co.himedia.ecommerce.front.login.service.LoginSrvc;
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
@Controller("kr.co.himedia.ecommerce.front.login.controller.LoginApi")
public class LoginApi extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(LoginApi.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	LoginSrvc loginSrvc;
	
	@RequestMapping(value = "/front/login/changePasswd.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean changePasswd(@RequestBody final CustomerDto customerDto) {
		
		try {
			
			customerDto.setPasswd(HSwithSHA.encode(customerDto.getPasswd()));
			
			if (loginSrvc.changePasswd(customerDto)) {
				return true;
			}
			else {
				return false;
			}
			
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return false;
	}
	
	@RequestMapping(value = "/front/login/searchPwd.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody CustomerDto searchPwd(@RequestBody final CustomerDto customerDto) {
				
		try {
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			customerDto.setCst_nm(aes.encode(customerDto.getCst_nm()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));
			
			CustomerDto _customerDto = loginSrvc.searchPwd(customerDto);
			
			return _customerDto;
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return null;
	}
	
	@RequestMapping(value = "/front/login/searchId.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody CustomerDto searchId(@RequestBody final CustomerDto customerDto) {
				
		try {
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			customerDto.setCst_nm(aes.encode(customerDto.getCst_nm()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));
			
			CustomerDto _customerDto = loginSrvc.searchId(customerDto);
			
			if(_customerDto != null) {
				
				String getid = _customerDto.getId();
				int length = getid.length();
				
				String cutid =  getid.substring(0, 4);
				
				String id = cutid;
				
				for(int i = 4; i<length; i++) {
					id += "*";
				}
				
				_customerDto.setId(id);
			} else {
				String id = null;
				customerDto.setId(id);
			}
			
			debuggingJSON(_customerDto);
			
			return _customerDto;
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".searchId()] " + e.getMessage(), e);
		}
		finally {}
		
		return null;
	}
	
	/**
	 * @param customerDto [구매자 빈(Bean)]
	 * @return boolean
	 * 
	 * @since 2024-01-09
	 * <p>DESCRIPTION: 로그인 처리</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입 및 형식을 만족하여야 한다.</p>
	 * <p>EXAMPLE:<br>
	 * - headers	: 요청 헤더를 확인<br>
	 * - consumes	: application/json 형태로 요청 확인<br>
	 * - produces	: application/json 형태로 응답 확인<br>
	 * - @RequestMapping(value = requestMappingURL, method = RequestMethod.GET)<br>
	 * - @RequestMapping(value = requestMappingURL, method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")<br>
	 * </p>
	 */
	@RequestMapping(value = "/front/login/loginProc.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody CustomerDto loginProc(@RequestBody final CustomerDto customerDto) {
		
		try {
			
			String id		= customerDto.getId();
			String passwd	= customerDto.getPasswd();
			
			/** [개선] 정상 또는 실패 시 IP, Time, Agent 등을 보안상 저장 */
			CustomerDto _customerDto = loginSrvc.loginProc(id);
			
			// 해쉬 암호화
			if (_customerDto != null &&  HSwithSHA.encode(passwd).equals(_customerDto.getPasswd()))  {
				
				// 대칭키 암호화
				String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
				SKwithAES aes		= new SKwithAES(staticKey);
				
				_customerDto.setCst_nm(aes.decode(_customerDto.getCst_nm()));
				debuggingJSON(_customerDto);
				return _customerDto;
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return null;
	}

}
