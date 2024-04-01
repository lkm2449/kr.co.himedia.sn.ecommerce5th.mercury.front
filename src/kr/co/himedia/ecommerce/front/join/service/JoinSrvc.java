package kr.co.himedia.ecommerce.front.join.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.join.dao.JoinDao;
/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-07
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Service("kr.co.himedia.ecommerce.front.join.service.JoinSrvc")
public class JoinSrvc {
	
	@Inject
	JoinDao joinDao;
	
	@Transactional("txFront")
	public boolean update(CustomerDto customerDto) {
		
		int result = joinDao.update(customerDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public List<CustomerDto> listing(String id) {
		return joinDao.listing(id);
	}

	@Transactional("txFront")
	public boolean insert(CustomerDto customerDto) {
		
		customerDto.setSeq_cst(joinDao.sequence());
		customerDto.setRegister(customerDto.getSeq_cst());
		
		int result = joinDao.insert(customerDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public int search(CustomerDto customerDto) {
		return joinDao.search(customerDto);
	}
	
	public int idCheck(CustomerDto customerDto) {
		return joinDao.idCheck(customerDto);
	}
	
	public int duplicateCheck(CustomerDto customerDto) {
		return joinDao.duplicateCheck(customerDto);
	}
}
