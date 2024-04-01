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
 * File			: AnimalDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240226142745][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.animal.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.front.animal.dto.AnimalDto;
import kr.co.himedia.ecommerce.front.common.dao.BaseDao;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-02-26
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("kr.co.himedia.ecommerce.front.animal.dao.dao.AnimalDao")
public class AnimalDao extends BaseDao {
	
	public AnimalDto view(AnimalDto animalDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.animal.Animal.view", animalDto);
	}
	
	public List<AnimalDto> list(PagingDto pagingDto) {
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.animal.Animal.list", pagingDto);
	}
	
	public int count(PagingDto pagingDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.animal.Animal.count", pagingDto);
	}
	
	public int counting() {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.animal.Animal.counting");
	}
	
	public List<AnimalDto> listing(AnimalDto animalDto) {
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.animal.Animal.listing", animalDto);
	}
	
	public int insert(AnimalDto animalDto) {
		return sqlSessionFront.insert("kr.co.himedia.ecommerce.front.mybatis.animal.Animal.insert", animalDto);
	}

}
