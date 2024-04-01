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
 * File			: LoginDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231207110722][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.login.dao;

import org.springframework.stereotype.Repository;

import kr.co.himedia.ecommerce.front.common.dao.BaseDao;
import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-07
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("kr.co.himedia.ecommerce.front.login.dao.LoginDao")
public class LoginDao extends BaseDao {
	
	public CustomerDto loginProc(String id) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.login.Login.loginProc", id);
	}
	
	public CustomerDto searchId(CustomerDto customerDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.login.Login.searchId", customerDto);
	}
	
	public CustomerDto searchPwd(CustomerDto customerDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.login.Login.searchPwd", customerDto);
	}
	
	public int changePasswd(CustomerDto customerDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.login.Login.changePasswd", customerDto);
	}
	

}
