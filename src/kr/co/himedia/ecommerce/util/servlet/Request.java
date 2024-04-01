/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF HIMEDIA.CO.KR.
 * HIMEDIA.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2023 HIMEDIA.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 himedia.co.kr에 있으며,
 * himedia.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * himedia.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2023 himedia.co.kr All Rights Reserved.
 *
 *
 * Program		: kr.co.himedia.sn.ecommerce5th.moon
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Request.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231128164828][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.util.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.himedia.ecommerce.util.Strings;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-11-28
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class Request {
	
	/**
	 * @param request [요청 서블릿]
	 * @param type [타입(mobile or pc)]
	 * @return boolean
	 * 
	 * @since 2018-10-05
	 * <p>DESCRIPTION: 디바이스 확인</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입 및 형식을 만족하여야 한다.</p>
	 * <p>EXAMPLE:</p>
	 */
	public static boolean isDevice(HttpServletRequest request, String type) {
		
		boolean result = false;
		
		String devices[] = null;
		
		if (type.equalsIgnoreCase("mobile")) {
			devices = Strings.split("iPhoneAndroid", "");
		}
		else if (type.equalsIgnoreCase("pc")) {
			devices = Strings.split("Windows NTJava", "");
		}
		
		String agent = request.getHeader("User-Agent");
		
		for (int nLoop = 0; nLoop < devices.length; nLoop++) {
			//System.out.println(agent);
			if (agent.indexOf(devices[nLoop]) > -1) result = true;
		}
		
		return result;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param key [세션 키]
	 * @param defaultValue [기본값]
	 * @param state [상태]
	 * @throws Exception [예외]
	 * @return String
	 * 
	 * @since 2016-01-15
	 * <p>DESCRIPTION: HttpServletRequest 객체(request)를 이용하여 특정 세션(key)의 값 문자열 얻기</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입을 만족하여야 한다.<br />[주의] 세션에 저장 시 값은 문자열로만 저장해야 한다.<br />문자열이 NULL일 경우 지정한 문자열(defaultValue)로 반환한다.</p>
	 * <p>EXAMPLE:</p>
	 */
	public static String getSession(HttpServletRequest request, String key, String defaultValue, boolean state) throws Exception {
		
		String value = defaultValue;
		
		HttpSession session = ((HttpServletRequest) request).getSession(state);
		
		if (session != null)	value = (String) session.getAttribute(key);
		if (value == null)		value = defaultValue;
		
		return value;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @return String
	 * 
	 * @since 2017-07-03
	 * <p>DESCRIPTION: HttpServletRequest 객체(request)를 이용하여 Full URL(Query String 포함) 문자열 얻기</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입을 만족하여야 한다.</p>
	 * <p>EXAMPLE:</p>
	 */
	public static String getFullURL(HttpServletRequest request) {
		
		String uri			= ((HttpServletRequest)request).getRequestURI();
		String queryString	= request.getQueryString();
		
		if (queryString == null || queryString == "") {
			return uri;
		} 
		else {
			return uri + "?" + queryString;
		}
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param webServer [웹 서버]
	 * @return String
	 * @throws Exception [예외]
	 * 
	 * @since 2015-08-20
	 * <p>DESCRIPTION: HttpServletRequest 객체(request)를 이용하여 Client IP 문자열 얻기</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입을 만족하여야 한다.<br>문자열이 NULL일 경우 빈 문자열을 반환한다.</p>
	 * <p>EXAMPLE:</p>
	 */
	public static String getRemoteAddr(HttpServletRequest request, String webServer) {
		
		String returnValue = "[UNKNOWN IP]";
		
		if (webServer.equalsIgnoreCase("nginx")) {
			returnValue = request.getHeader("X-Forwarded-For");
			if (returnValue == null || returnValue.equals("")) returnValue = request.getRemoteAddr();
		}
		else if (webServer.equalsIgnoreCase("apache"))		returnValue = request.getRemoteAddr();
		else if (webServer.equalsIgnoreCase("tomcat"))		returnValue = request.getRemoteAddr();
		else returnValue = "[UNDEFINED AGENT]";
		
		return returnValue.trim();
	}

}
