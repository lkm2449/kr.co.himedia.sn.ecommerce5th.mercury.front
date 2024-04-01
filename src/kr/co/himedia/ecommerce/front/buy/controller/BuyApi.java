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
 * File			: BuyApi.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240222142405][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.buy.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.component.PayupCmpn;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlListDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyMstDto;
import kr.co.himedia.ecommerce.front.buy.dto.PayDto;
import kr.co.himedia.ecommerce.front.buy.service.BuySrvc;
import kr.co.himedia.ecommerce.front.customer.service.CustomerSrvc;
import kr.co.himedia.ecommerce.util.Datetime;
import kr.co.himedia.ecommerce.common.Common;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-02-22
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.buy.controller.BuyApi")
public class BuyApi extends Common{
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BuyApi.class);
	
	@Autowired
	Properties staticProperties;
	
	@Autowired
	PayupCmpn payupCmpn;
	
	@Inject
	BuySrvc buySrvc;
	
	@Inject
	CustomerSrvc customerSrvc;
	
	@RequestMapping("/front/buy/orderPayProcess.api")
	public ModelAndView orderPayProcess(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> param){
		
		ModelAndView mav = new ModelAndView();

		try {

			String res_cd = param.get("res_cd");
			String res_msg = param.get("res_msg");
			String enc_data = param.get("enc_data");
			String enc_info = param.get("enc_info");
			String tran_cd = param.get("tran_cd");
			String buyr_mail = param.get("buyr_mail");
			String ordr_idxx = param.get("ordr_idxx");
			
			if(res_cd.equals("0000")) {
				String url = "https://api.testpayup.co.kr/ap/api/payment/" + ordr_idxx + "/pay";
				
				Map<String, String> apiMap = new HashMap<>();
				apiMap.put("res_cd", res_cd);
				apiMap.put("res_msg", res_msg);
				apiMap.put("enc_data", enc_data);
				apiMap.put("enc_info", enc_info);
				apiMap.put("tran_cd", tran_cd);
				apiMap.put("buyr_mail", buyr_mail);
				
				Map<String, Object> apiResult = new HashMap<>();
				
				apiResult = payupCmpn.JsonMApi(url, apiMap);
				
				logger.info("결제 결과 : " + apiResult.toString() + "\n");
				
				if(apiResult.get("responseCode").equals("0000")) {
					
					PayDto payDto = new PayDto();
					payDto.setDeal_num(ordr_idxx);
					payDto.setFlg_success("Y");
					payDto.setCard_nm(apiResult.get("cardName").toString());
					payDto.setCard_no(apiResult.get("cardNo").toString());
					payDto.setQuota(Integer.parseInt(apiResult.get("quota").toString()));

					
					if(buySrvc.updateM(payDto)) {
						request.setAttribute("script"	, "window.TestPay.toastM('결제 성공');");
					} else {
						request.setAttribute("script"	, "window.TestPay.toastM('결제 오류! 관리자에게 문의 바랍니다!');");
					}
					
				}
				else {
					request.setAttribute("script"	, "window.TestPay.toastM('결제 실패! 잠시 후에 다시 이용해 주세요!');");
				}
				
			}

			mav.setViewName("forward:/servlet/result.web");
			
		}catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".orderPayProcess()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/buy/payForm.api")
	public @ResponseBody ModelAndView payForm(HttpServletRequest request, HttpServletResponse response, BuyDtlListDto buyDtlListDto){
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			BuyMstDto buyMstDto = new BuyMstDto();
			
			int price = 0;
			
			String buyInfo = "";
			for(int i=0; i<buyDtlListDto.getBuyList().size(); i++) {
				int dtlPrice = 0;
				dtlPrice = buyDtlListDto.getBuyList().get(i).getPrice();
				
				int dtlCount = 0;
				dtlCount = buyDtlListDto.getBuyList().get(i).getCount();
				
				price += dtlPrice * dtlCount;
				
				if(i==0 && buyDtlListDto.getBuyList().size() == 1 ) {
					buyInfo += buyDtlListDto.getBuyList().get(i).getSle_nm();
				} else if(i==0 && buyDtlListDto.getBuyList().size() != 1) {
					buyInfo += buyDtlListDto.getBuyList().get(i).getSle_nm() + "(외 " + (buyDtlListDto.getBuyList().size() - 1) + "개)";
				}
			}

			buyMstDto.setSeq_cst(buyDtlListDto.getBuyList().get(0).getRegister());
			buyMstDto.setBuy_info(buyInfo);
			buyMstDto.setRegister(buyDtlListDto.getBuyList().get(0).getRegister());
			buyMstDto.setBuy_t_price(price);
			// buyMstDto.setRequest("");
			
			String yyyyMMddHHmmss = Datetime.getNow("yyyyMMddHHmmss");
			
			String merchantId			= staticProperties.getProperty("front.payup.id", "");
			String apiCertKey			= staticProperties.getProperty("front.payup.key", "");
			
			String url = "https://api.testpayup.co.kr/ap/api/payment/himedia/order";
			
			String orderNumber = "HM-" + yyyyMMddHHmmss;
			String amount = String.valueOf(price);

			String itemName = buyInfo;

			String userName = buyDtlListDto.getBuyList().get(0).getCst_nm();
			String timestamp = yyyyMMddHHmmss;
			
			// 서버 url
			String auth_return = "http://210.91.85.54:8080//front/buy/orderPayProcess.api";
				
			// 로컬 url
			// String auth_return = "http://210.91.85.54:8083/front/buy/orderPayProcess.api";
			
			
			String signature = payupCmpn.getSHA256Hash(merchantId + "|" + orderNumber + "|" + amount + "|" + apiCertKey + "|" + timestamp);
			
			//고객일련번호, 상품일련번호, 판매자 일련번호, 판매상품 일련번호, 가격, 개수
			// String bypassValue = seq_cst + "," + seq_prd + "," + seq_sll + "," + seq_sle + "," + sle_nm + "," + price + "," + count;
			
			Map<String, String> apiMap = new HashMap<>();
			apiMap.put("orderNumber", orderNumber);
			apiMap.put("amount", amount);
			apiMap.put("itemName", itemName);
			apiMap.put("userName", userName);
			apiMap.put("auth_return", auth_return);
			apiMap.put("signature", signature);
			apiMap.put("timestamp", timestamp);
			// apiMap.put("bypassValue", bypassValue);
			
			Map<String, Object> apiResult = new HashMap<>();
			apiResult = payupCmpn.JsonMApi(url, apiMap);
			
			logger.info("주문 결과 : " + apiResult.toString() + "\n");
			
			if(apiResult.get("responseCode").equals("0000")) {				
				
				PayDto payDto = new PayDto();
				payDto.setDeal_num(apiResult.get("ordr_idxx").toString());
				
				if(buySrvc.insertM(buyMstDto, buyDtlListDto, payDto)) {
					
					mav.addObject("pay_url", apiResult.get("pay_url"));
					mav.addObject("ordr_idxx", apiResult.get("ordr_idxx"));
					mav.addObject("good_name", apiResult.get("good_name"));
					mav.addObject("good_mny", apiResult.get("good_mny"));
					mav.addObject("buyr_name", apiResult.get("buyr_name"));
					mav.addObject("site_cd", apiResult.get("site_cd"));
					mav.addObject("Ret_URL", apiResult.get("Ret_URL"));
					mav.addObject("approval_key", apiResult.get("approval_key"));
					
					mav.setViewName("/front/buy/payForm");
				} else {
					request.setAttribute("script"	, "alert('주문 실패! 잠시 후에 다시 이용해주세요')");
						
					mav.setViewName("forward:/servlet/result.web");
				}
			}else {
				
			}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".payForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
