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
 * File			: PagingDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231201130452][pluto@himedia.co.kr][CREATE: Initial Release]
 *				: [20231219175400][pluto@himedia.co.kr][INSERT: 1(공지사항), 2(자주찾는질문), 3(고객문의) for cd_bbs_type]
 */
package kr.co.himedia.ecommerce.common.dto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-01
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class PagingDto {
	
	private int totalLine				= 0;
	private int totalPage				= 0;
	private int linePerPage			= 10;
	private int currentPage			= 1;
	
	private String searchKey			= "";
	private String searchWord			= "";
	
	private Integer cd_bbs_type			= 0;
	private Integer register			= 0;
	private String cd_ctg;
	private String sort					= "";
	private String sex					= "";
	private String neut_yn					= "";
	private String age						= "";
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNeut_yn() {
		return neut_yn;
	}
	public void setNeut_yn(String neut_yn) {
		this.neut_yn = neut_yn;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getCd_ctg() {
		return cd_ctg;
	}
	public void setCd_ctg(String cd_ctg) {
		this.cd_ctg = cd_ctg;
	}
	public Integer getRegister() {
		return register;
	}
	public void setRegister(Integer register) {
		this.register = register;
	}
	public Integer getCd_bbs_type() {
		return cd_bbs_type;
	}
	public void setCd_bbs_type(Integer cd_bbs_type) {
		this.cd_bbs_type = cd_bbs_type;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public int getTotalLine() {
		return totalLine;
	}
	public void setTotalLine(int totalLine) {
		this.totalLine = totalLine;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getLinePerPage() {
		return linePerPage;
	}
	public void setLinePerPage(int linePerPage) {
		this.linePerPage = linePerPage;
	}
	public int getCurrentPage() {
		if (this.currentPage == 0) 	currentPage = 1;
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		if (currentPage == 0) currentPage = 1;
		this.currentPage = currentPage;
	}
}