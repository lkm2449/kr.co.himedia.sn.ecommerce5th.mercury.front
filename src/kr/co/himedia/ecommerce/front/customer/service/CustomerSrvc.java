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
 * Environment	: JRE 1.7 or more
 * File			: CustomerSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240222172056][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.customer.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.front.board.dto.ReViewDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlDto;
import kr.co.himedia.ecommerce.front.customer.dao.CustomerDao;
import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.customer.dto.WishDto;
import kr.co.himedia.ecommerce.front.sale.dto.SaleDto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-02-22
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Service("kr.co.himedia.ecommerce.front.customer.service.CustomerSrvc")
public class CustomerSrvc {
	
	@Inject
	CustomerDao customerDao;
	
	public List<SaleDto> reviewList(CustomerDto customerDto){
		return customerDao.reviewList(customerDto);
	}
	
	public List<SaleDto> wishList(WishDto wishDto){
		return customerDao.wishList(wishDto);
	}
	
	public PagingListDto wisList(PagingDto pagingDto) {
		
		PagingListDto pagingListDto = new PagingListDto();
		
		int totalLine = customerDao.countWis(pagingDto);
		int totalPage = (int)Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(0);
		
		pagingListDto.setPaging(pagingDto);
		pagingListDto.setList(customerDao.wisList(pagingDto));
		
		return pagingListDto;
	}
	
	@Transactional("txFront")
	public boolean deleteWish(WishDto wishDto) {
				
		int result = customerDao.deleteWish(wishDto);
		
		if(result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public int countWish(WishDto wishDto) {
		return customerDao.countWish(wishDto);
	}
	
	@Transactional("txFront")
	public boolean insertWish(WishDto wishDto) {
				
		int result = customerDao.insertWish(wishDto);
		
		if(result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	
	@Transactional("txFront")
	public boolean insertRev(ReViewDto reViewDto) {
		
		reViewDto.setSeq_rev(customerDao.sequenceRev());
		
		int result = customerDao.insertRev(reViewDto);
		int result2nd = customerDao.updateRev(reViewDto);
		
		if(result == 1 && result2nd == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public BuyDtlDto revForm(BuyDtlDto buyDtlDto) {
		return customerDao.revForm(buyDtlDto);
	}
	
	public PagingListDto selectRev(PagingDto pagingDto) {
		
		PagingListDto pagingListDto = new PagingListDto();
		
		int totalLine = customerDao.countRev(pagingDto);
		int totalPage = (int)Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(0);
		
		pagingListDto.setPaging(pagingDto);
		pagingListDto.setList(customerDao.selectRev(pagingDto));
		
		return pagingListDto;
	}
	
	@Transactional("txFront")
	public boolean withdrawal(CustomerDto customerDto) {
		
		int result = customerDao.withdrawal(customerDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public List<BuyDtlDto> dtlList(int register) {
		return customerDao.dtlList(register);
	}
	
	public PagingListDto mstList(PagingDto pagingDto) {
	
		PagingListDto pagingListDto = new PagingListDto();
		
		int totalLine = customerDao.count(pagingDto);
		int totalPage = (int)Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(0);
		
		pagingListDto.setPaging(pagingDto);
		pagingListDto.setList(customerDao.mstList(pagingDto));
		
		return pagingListDto;
	}
	
	@Transactional("txFront")
	public boolean update(CustomerDto customerDto) {
		
		int result = customerDao.update(customerDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public CustomerDto select(CustomerDto customerDto) {
		return customerDao.select(customerDto);
	}
	
	public CustomerDto selectPwd(CustomerDto customerDto) {
		return customerDao.selectPwd(customerDto);
	}
	
	@Transactional("txFront")
	public boolean changePasswd(CustomerDto customerDto) {
		
		int result = customerDao.changePasswd(customerDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public int countPwd(CustomerDto customerDto) {
		return customerDao.countPwd(customerDto);
	}
}
