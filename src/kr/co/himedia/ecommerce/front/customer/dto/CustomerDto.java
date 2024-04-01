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
 * File			: CustomerDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231130155846][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-11-30
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

	private int seq_cst;
	private String id;
	private String passwd;
	private int tc_state;
	private String cst_nm;
	private String phone;
	private String postcode;
	private String addr1;
	private String addr2;
	private String addr3;
	private String cst_email;
	private String flg_sms;
	private String dt_sms;
	private String flg_email;
	private String dt_email;
	private String dt_reg;
	private int register;
	private String dt_upt;
	private int updater;
	private String sso;
	
	private String before_flg_sms;
	private String before_flg_email;
	
	public String getSso() {
		return sso;
	}
	public void setSso(String sso) {
		this.sso = sso;
	}
	public String getAddr3() {
		return addr3;
	}
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
	public String getBefore_flg_sms() {
		return before_flg_sms;
	}
	public void setBefore_flg_sms(String before_flg_sms) {
		this.before_flg_sms = before_flg_sms;
	}
	public String getBefore_flg_email() {
		return before_flg_email;
	}
	public void setBefore_flg_email(String before_flg_email) {
		this.before_flg_email = before_flg_email;
	}
	public String getFlg_sms() {
		return flg_sms;
	}
	public void setFlg_sms(String flg_sms) {
		this.flg_sms = flg_sms;
	}
	public String getDt_sms() {
		return dt_sms;
	}
	public void setDt_sms(String dt_sms) {
		this.dt_sms = dt_sms;
	}
	public String getFlg_email() {
		return flg_email;
	}
	public void setFlg_email(String flg_email) {
		this.flg_email = flg_email;
	}
	public String getDt_email() {
		return dt_email;
	}
	public void setDt_email(String dt_email) {
		this.dt_email = dt_email;
	}
	public int getSeq_cst() {
		return seq_cst;
	}
	public void setSeq_cst(int seq_cst) {
		this.seq_cst = seq_cst;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public int getTc_state() {
		return tc_state;
	}
	public void setTc_state(int tc_state) {
		this.tc_state = tc_state;
	}
	public String getCst_nm() {
		return cst_nm;
	}
	public void setCst_nm(String cst_nm) {
		this.cst_nm = cst_nm;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getCst_email() {
		return cst_email;
	}
	public void setCst_email(String cst_email) {
		this.cst_email = cst_email;
	}
	public String getDt_reg() {
		return dt_reg;
	}
	public void setDt_reg(String dt_reg) {
		this.dt_reg = dt_reg;
	}
	public int getRegister() {
		return register;
	}
	public void setRegister(int register) {
		this.register = register;
	}
	public String getDt_upt() {
		return dt_upt;
	}
	public void setDt_upt(String dt_upt) {
		this.dt_upt = dt_upt;
	}
	public int getUpdater() {
		return updater;
	}
	public void setUpdater(int updater) {
		this.updater = updater;
	}
}
