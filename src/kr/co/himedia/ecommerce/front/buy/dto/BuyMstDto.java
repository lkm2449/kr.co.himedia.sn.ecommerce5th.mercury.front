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
 * File			: BuyMstDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231208142510][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.buy.dto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class BuyMstDto {
	
	private String rnum;
	
	private int seq_buy_mst;
	private int seq_cst;
	private String buy_info;
	private int buy_t_count;
	private int buy_t_price;
	private String flg_delete;
	private String cd_state_pay;
	private String cd_state_delivery;
	private String state_delivery_nm;
	private String dt_reg;
	private int register;
	private int updater;
	private String dt_upt;
	private String request;
	private String cd_where;
	private String img;
	
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getBuy_t_price() {
		return buy_t_price;
	}

	public void setBuy_t_price(int buy_t_price) {
		this.buy_t_price = buy_t_price;
	}

	private String com_nm;

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public int getSeq_buy_mst() {
		return seq_buy_mst;
	}

	public void setSeq_buy_mst(int seq_buy_mst) {
		this.seq_buy_mst = seq_buy_mst;
	}

	public int getSeq_cst() {
		return seq_cst;
	}

	public void setSeq_cst(int seq_cst) {
		this.seq_cst = seq_cst;
	}

	public String getBuy_info() {
		return buy_info;
	}

	public void setBuy_info(String buy_info) {
		this.buy_info = buy_info;
	}

	public int getBuy_t_count() {
		return buy_t_count;
	}

	public void setBuy_t_count(int buy_t_count) {
		this.buy_t_count = buy_t_count;
	}

	public String getFlg_delete() {
		return flg_delete;
	}

	public void setFlg_delete(String flg_delete) {
		this.flg_delete = flg_delete;
	}

	public String getCd_state_pay() {
		return cd_state_pay;
	}

	public void setCd_state_pay(String cd_state_pay) {
		this.cd_state_pay = cd_state_pay;
	}

	public String getCd_state_delivery() {
		return cd_state_delivery;
	}

	public void setCd_state_delivery(String cd_state_delivery) {
		this.cd_state_delivery = cd_state_delivery;
	}

	public String getState_delivery_nm() {
		return state_delivery_nm;
	}

	public void setState_delivery_nm(String state_delivery_nm) {
		this.state_delivery_nm = state_delivery_nm;
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

	public int getUpdater() {
		return updater;
	}

	public void setUpdater(int updater) {
		this.updater = updater;
	}

	public String getDt_upt() {
		return dt_upt;
	}

	public void setDt_upt(String dt_upt) {
		this.dt_upt = dt_upt;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getCd_where() {
		return cd_where;
	}

	public void setCd_where(String cd_where) {
		this.cd_where = cd_where;
	}

	public String getCom_nm() {
		return com_nm;
	}

	public void setCom_nm(String com_nm) {
		this.com_nm = com_nm;
	}
	
	
	
	
}
