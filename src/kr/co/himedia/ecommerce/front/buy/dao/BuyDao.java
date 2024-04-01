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
 * File			: BuyDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231208154934][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.buy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyMstDto;
import kr.co.himedia.ecommerce.front.common.dao.BaseDao;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("kr.co.himedia.ecommerce.front.buy.dao.BuyDao")
public class BuyDao extends BaseDao {
	
	public List<BuyDtlDto> listD(BuyMstDto buyMstDto){
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.buy.BuyDetail.listD", buyMstDto);
	}
	
	public List<BuyMstDto> listM(int seq_cst){
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.buy.BuyMaster.listM", seq_cst);
	}
	
	public int cancelPay(BuyMstDto buyMstDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.buy.BuyMaster.cancelPay", buyMstDto);
	}
	
	public int updateM(BuyMstDto buyMstDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.buy.BuyMaster.updateM", buyMstDto);
	}
	
	public int insertDetailM(BuyDtlDto buyDtlDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.buy.BuyDetail.insertM", buyDtlDto);
	}
	
	public int insertMasterM(BuyMstDto buyMstDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.buy.BuyMaster.insertM", buyMstDto);
	}
	
	public int update(BuyMstDto buyMstDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.buy.BuyMaster.update", buyMstDto);
	}
	
	public int count(PagingDto pagingDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.buy.BuyMaster.count", pagingDto);
	}
	
	public List<BuyMstDto> list(PagingDto pagingDto) {
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.buy.BuyMaster.list", pagingDto);
	}
	
	public List<BuyDtlDto> select(BuyMstDto buyMstDto) {
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.buy.BuyDetail.select", buyMstDto);
	}
	
	public int insertMaster(BuyMstDto buyMstDto) {
		return sqlSessionFront.insert("kr.co.himedia.ecommerce.front.mybatis.buy.BuyMaster.insert", buyMstDto);
	}
	
	public int sequenceMaster() {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.buy.BuyMaster.sequence");
	}
	
	public int insertDetail(BuyDtlDto buyDtlDto) {
		return sqlSessionFront.insert("kr.co.himedia.ecommerce.front.mybatis.buy.BuyDetail.insert", buyDtlDto);
	}
	
	public int sequenceDetail() {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.buy.BuyDetail.sequence");
	}
}
