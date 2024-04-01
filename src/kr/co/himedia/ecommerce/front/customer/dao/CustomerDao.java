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
 * File			: CustomerDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231130155840][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.front.board.dto.ReViewDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyMstDto;
import kr.co.himedia.ecommerce.front.common.dao.BaseDao;
import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.customer.dto.WishDto;
import kr.co.himedia.ecommerce.front.sale.dto.SaleDto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-11-30
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("kr.co.himedia.ecommerce.front.customer.dao.CustomerDao")
public class CustomerDao extends BaseDao {
	
	public List<SaleDto> reviewList(CustomerDto customerDto){
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.reviewList", customerDto);
	}
	
	public List<SaleDto> wishList(WishDto wishDto){
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.wishList", wishDto);
	}
	
	public List<ReViewDto> wisList(PagingDto pagingDto) {
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.wisList", pagingDto);
	}
	
	//찜한상품 갯수 확인
	public int countWis(PagingDto pagingDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.countWis", pagingDto);
	}
	
	public int deleteWish(WishDto wishDto) {
		return sqlSessionFront.delete("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.deleteWish", wishDto);
	}
	
	//찜 여부 확인
	public int countWish(WishDto wishDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.countWish", wishDto);
	}
	
	public int insertWish(WishDto wishDto) {
		return sqlSessionFront.insert("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.insertWish", wishDto);
	}
	
	public int updateRev(ReViewDto reViewDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.review.Review.updateRev", reViewDto);
	}
	
	public int sequenceRev() {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.review.Review.sequenceRev");
	}
	
	public int insertRev(ReViewDto reViewDto) {
		return sqlSessionFront.insert("kr.co.himedia.ecommerce.front.mybatis.review.Review.insertRev", reViewDto);
	}
	
	public BuyDtlDto revForm(BuyDtlDto buyDtlDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.buy.BuyDetail.revForm", buyDtlDto);
	}
	
	public int countRev(PagingDto pagingDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.review.Review.countRev", pagingDto);
	}
	
	public List<ReViewDto> selectRev(PagingDto pagingDto) {
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.review.Review.selectRev", pagingDto);
	}
	
	public int withdrawal(CustomerDto customerDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.withdrawal", customerDto); 
	}
	
	public int count(PagingDto pagingDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.buy.BuyMaster.count", pagingDto);
	}
	
	public List<BuyMstDto> mstList(PagingDto pagingDto) {
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.buy.BuyMaster.list", pagingDto);
	}
	
	public List<BuyDtlDto> dtlList(int register) {
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.buy.BuyDetail.list", register);
	}
	
	public int update(CustomerDto customerDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.update", customerDto); 
	}
	
	public CustomerDto select(CustomerDto customerDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.select", customerDto);
	}
	
	public CustomerDto selectPwd(CustomerDto customerDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.selectPwd", customerDto);
	}
	
	public int sequence() {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.sequence");
	}
	
	public int changePasswd(CustomerDto customerDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.changePasswd", customerDto); 
	}
	
	public int countPwd(CustomerDto customerDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.countPwd", customerDto);
	}

}
