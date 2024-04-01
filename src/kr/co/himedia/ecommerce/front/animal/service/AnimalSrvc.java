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
 * File			: AnimalSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240227101722][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.animal.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.front.animal.dao.AnimalDao;
import kr.co.himedia.ecommerce.front.animal.dto.AnimalDto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-02-27
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Service("kr.co.himedia.ecommerce.front.animal.service.AnimalSrvc")
public class AnimalSrvc {
	
	@Inject
	AnimalDao animalDao;
	
	public AnimalDto view(AnimalDto animalDto) {
		return animalDao.view(animalDto);
	}
	
	public PagingListDto list(PagingDto pagingDto) {
		
		PagingListDto pagingListDto = new PagingListDto();
		
		pagingDto.setLinePerPage(9);
		
		int totalLine = animalDao.count(pagingDto);
		int totalPage = (int)Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(0);
		
		pagingListDto.setPaging(pagingDto);
		pagingListDto.setList(animalDao.list(pagingDto));
		
		return pagingListDto;
	}
	
	public int counting() {
		return animalDao.counting();
	}
	
	public List<AnimalDto> listing(AnimalDto animalDto) {
		animalDto.setCurrentPage(animalDto.getCurrentPage() * 10);
		
		return animalDao.listing(animalDto);
	}
	
	@Transactional("txFront")
	public boolean insert(List<AnimalDto> animalList) {
		
		int result = 0;

		for(int i = 0; i < animalList.size(); i++) {
			
			result += animalDao.insert(animalList.get(i));
			
		}
		
		if (result == animalList.size()) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

}
