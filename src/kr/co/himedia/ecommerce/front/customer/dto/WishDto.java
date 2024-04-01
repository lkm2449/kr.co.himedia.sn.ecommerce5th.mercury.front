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
 * Program		: kr.co.himedia.sn.ecommerce5th.mercury
 * Description	:
 * Environment	: JRE 1.8 or more
 * File			: WishDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240314105814][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.customer.dto;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-03-14
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class WishDto {
	
	private int seq_cst;
	private int seq_sle;
	
	public int getSeq_cst() {
		return seq_cst;
	}
	public void setSeq_cst(int seq_cst) {
		this.seq_cst = seq_cst;
	}
	public int getSeq_sle() {
		return seq_sle;
	}
	public void setSeq_sle(int seq_sle) {
		this.seq_sle = seq_sle;
	}
}
