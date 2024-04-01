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
 * File			: BuyWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240304111243][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.buy.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.common.component.PayupCmpn;
import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.front.board.dto.ReViewDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlListDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyMstDto;
import kr.co.himedia.ecommerce.front.buy.dto.PayDto;
import kr.co.himedia.ecommerce.front.buy.service.BuySrvc;
import kr.co.himedia.ecommerce.front.sale.dto.SaleDto;
import kr.co.himedia.ecommerce.front.sale.service.SaleSrvc;
import kr.co.himedia.ecommerce.util.Datetime;
import kr.co.himedia.ecommerce.util.servlet.Request;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-03-04
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.buy.controller.BuyWeb")
public class BuyWeb extends Common {
	
	@Inject
	SaleSrvc saleSrvc;
	
	@Inject
	BuySrvc buySrvc;
	
	@Autowired
	private PayupCmpn payupCmpn;
	
	@Autowired
	Properties staticProperties;
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BuyWeb.class);
		
	// 네이버페이 결제
	@RequestMapping(value = "/front/buy/payNaverProcess.web")
	public ModelAndView payNaverProcess(@RequestParam Map<String,String> param, HttpServletRequest request) throws NoSuchAlgorithmException {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			logger.info("[" + this.getClass().getName() + ".payKakaoProcess().REQ] " + param.toString());
			
			String res_cd			= param.get("res_cd");
			String ordr_idxx		= param.get("ordr_idxx");
			String enc_data			= param.get("enc_data");
			String enc_info			= param.get("enc_info");
			String tran_cd			= param.get("tran_cd");
			
			String url = "https://api.testpayup.co.kr/ep/api/naver/himedia/pay";
			Map<String,String> apiMap = new HashMap<>();		
			apiMap.put("res_cd",res_cd);
			apiMap.put("ordr_idxx",ordr_idxx);
			apiMap.put("enc_data",enc_data);
			apiMap.put("enc_info",enc_info);
			apiMap.put("tran_cd",tran_cd);
			
			Map<String,Object> apiResult = new HashMap<>();
			apiResult = payupCmpn.JsonApi(url, apiMap);
			
			logger.info("[" + this.getClass().getName() + ".payNaverProcess().RES] " + apiResult.toString());
			
			/** 페이업 거래번호 */
			boolean isResult = true;
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
			if ("0000".equals(apiResult.get("responseCode"))) {
				
				PayDto payDto = new PayDto();
				payDto.setDeal_num(ordr_idxx);
				payDto.setFlg_success("Y");
				payDto.setCard_nm("naverPay");
				payDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
				
				isResult = buySrvc.update(payDto);
				
				request.setAttribute("script"	, "alert('결제에 성공하셨습니다');");
				request.setAttribute("redirect", "/front/cart/main.web");
			}
			else {
				
				PayDto payDto = new PayDto();
				payDto.setDeal_num(ordr_idxx);
				payDto.setFlg_success("N");
				payDto.setCard_nm(apiResult.get("cardName").toString());
				payDto.setCard_no(apiResult.get("cardNo").toString());
				payDto.setQuota(Integer.parseInt(apiResult.get("quota").toString()));
				payDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
				
				logger.error("[" + this.getClass().getName() + ".payNaverProcess().RES.FAILURE] " + apiResult.toString());
				isResult = buySrvc.update(payDto);
				
				request.setAttribute("redirect", "/");

			}
			
			// 결제 결과에 대한 업데이트 실패 시
			if (!isResult) {
				
				request.setAttribute("redirect"	, "/");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".payNaverProcess()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;	
	}
	
	// 장바구니 결제창(네이버페이)
	@RequestMapping(value = "/front/buy/payNaverForm.json", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> payNaverForm(@RequestParam Map<String, String> param, HttpServletRequest request, BuyDtlListDto buyDtlListDto) throws NoSuchAlgorithmException {
		
		Map<String,Object> returnMap = new HashMap<>();
		
		try {
			
			if(getSession(request,"SEQ_CST")=="") {
				returnMap.put("login", "login");
			} else if(buyDtlListDto.getBuyList() == null){
				returnMap.put("cart", "cart");
			} else {
				
				BuyMstDto buyMstDto = new BuyMstDto();
				BuyDtlDto buyDtlDto = new BuyDtlDto();
				String requests = param.get("requests");
				
				int price = 0;
				
				String buyInfo = "";
				int buyCount = 0;
				for(int i = 0; i < buyDtlListDto.getBuyList().size(); i++) {
					
					int dtlPrice = 0;
					dtlPrice = buyDtlListDto.getBuyList().get(i).getPrice();
					
					int dtlCount = 0;
					dtlCount = buyDtlListDto.getBuyList().get(i).getCount();
					
					price += dtlPrice * dtlCount;
					buyCount += dtlCount;
					
					if(i==0 && buyDtlListDto.getBuyList().size() == 1 ) {
						buyInfo += buyDtlListDto.getBuyList().get(i).getSle_nm();
					} else if(i==0 && buyDtlListDto.getBuyList().size() != 1) {
						buyInfo += buyDtlListDto.getBuyList().get(i).getSle_nm() + "(외 " + (buyDtlListDto.getBuyList().size() - 1) + "개)";
					}
				}
				
				buyMstDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
				buyMstDto.setBuy_info(buyInfo);
				buyMstDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
				buyMstDto.setBuy_t_price(price);
				buyMstDto.setRequest(requests);
				
				String yyyyMMddHHmmss = Datetime.getNow("yyyyMMddHHmmss");
				
				String merchantId			= staticProperties.getProperty("front.payup.id", "");
				String key					= staticProperties.getProperty("front.payup.key", "");
				String orderNumber			= "HM-" + yyyyMMddHHmmss;
				String amount				= String.valueOf(price);
				String itemName 			= buyInfo;				
				
				String userName				= getSession(request, "CST_NM");
				String userAgent			= "WP";
				String payType				= "CARD";
				String returnUrl			= "returnUrl";
				String signature			= "";
				String timestamp			= yyyyMMddHHmmss;	
				
				signature = payupCmpn.getSHA256Hash(merchantId + "|" + orderNumber + "|" + amount + "|" + key + "|" + timestamp);

				String url = "https://api.testpayup.co.kr/ep/api/naver/" + merchantId + "/order";
				Map<String,String> apiMap = new HashMap<>();
				apiMap.put("orderNumber",orderNumber);
				apiMap.put("amount",amount);
				apiMap.put("itemName",itemName);
				apiMap.put("userName",userName);
				apiMap.put("signature",signature);
				apiMap.put("timestamp",timestamp);

				apiMap.put("userAgent",userAgent);
				apiMap.put("returnUrl",returnUrl);
				apiMap.put("payType", payType);
				
				
				apiMap.put("bypassValue","himediaValue");
				
				returnMap = payupCmpn.JsonApi(url, apiMap);
				
				if ("0000".equals(returnMap.get("responseCode"))) {
					// logger.info("[" + this.getClass().getName() + ".orderProcess().RES.SUCCESS] " + returnMap.toString());
					
					if (buyDtlDto != null) {
						
						/** 페이업 거래번호 */
						logger.info("주문정보 = " +  returnMap);
						PayDto payDto = new PayDto();
						payDto.setDeal_num(returnMap.get("ordr_idxx").toString());
						
						// 마스터 설정
						buyMstDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
						buyMstDto.setBuy_info(buyInfo);
						buyMstDto.setBuy_t_price(Integer.parseInt(amount));
						buyMstDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
						buyMstDto.setBuy_t_count(buyCount);
						
						if (!buySrvc.insertForm(buyMstDto, buyDtlListDto, payDto)) {
							// 구매 정보 저장 에러
							returnMap.put("responseCode", "B001");
							returnMap.put("responseMsg", "[Error]Insert Buy Information");
						}
					}
				}
				else {
					logger.error("[" + this.getClass().getName() + ".payNaverForm().RES.FAILURE] " + returnMap.toString());
				}
			}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".payNaverForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return returnMap;
	}
	
	// 카카오페이 결제
	@RequestMapping(value = "/front/buy/payKakaoProcess.web")
	public ModelAndView payKakaoProcess(@RequestParam Map<String,String> param, HttpServletRequest request) throws NoSuchAlgorithmException {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			logger.info("[" + this.getClass().getName() + ".payKakaoProcess().REQ] " + param.toString());
			
			String res_cd			= param.get("res_cd");
			String ordr_idxx		= param.get("ordr_idxx");
			String enc_data			= param.get("enc_data");
			String enc_info			= param.get("enc_info");
			String tran_cd			= param.get("tran_cd");
			String card_pay_method 	= param.get("card_pay_method");
			
			String url = "https://api.testpayup.co.kr/ep/api/kakao/himedia/pay";
			Map<String,String> apiMap = new HashMap<>();		
			apiMap.put("res_cd",res_cd);
			apiMap.put("ordr_idxx",ordr_idxx);
			apiMap.put("enc_data",enc_data);
			apiMap.put("enc_info",enc_info);
			apiMap.put("tran_cd",tran_cd);
			apiMap.put("card_pay_method", card_pay_method);
			
			Map<String,Object> apiResult = new HashMap<>();
			apiResult = payupCmpn.JsonApi(url, apiMap);
			
			logger.info("[" + this.getClass().getName() + ".payKakaoProcess().RES] " + apiResult.toString());
			
			/** 페이업 거래번호 */
			boolean isResult = true;
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
			if ("0000".equals(apiResult.get("responseCode"))) {
				
				PayDto payDto = new PayDto();
				payDto.setDeal_num(ordr_idxx);
				payDto.setFlg_success("Y");
				payDto.setCard_nm("kakaoPay");
				payDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
				
				isResult = buySrvc.update(payDto);
				
				request.setAttribute("script"	, "alert('결제에 성공하셨습니다');");
				request.setAttribute("redirect", "/front/cart/main.web");
			}
			else {
				
				PayDto payDto = new PayDto();
				payDto.setDeal_num(ordr_idxx);
				payDto.setFlg_success("N");
				payDto.setCard_nm(apiResult.get("cardName").toString());
				payDto.setCard_no(apiResult.get("cardNo").toString());
				payDto.setQuota(Integer.parseInt(apiResult.get("quota").toString()));
				payDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
				
				
				
				logger.error("[" + this.getClass().getName() + ".payKakaoProcess().RES.FAILURE] " + apiResult.toString());
				isResult = buySrvc.update(payDto);
				
				
				request.setAttribute("redirect", "/");
			}
			
			// 결제 결과에 대한 업데이트 실패 시
			if (!isResult) {
				
				request.setAttribute("redirect"	, "/");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".payKakaoProcess()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;	
	}
	
	// 장바구니 결제창(카카오페이)
	@RequestMapping(value = "/front/buy/payKakaoForm.json", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> payKakaoForm(@RequestParam Map<String, String> param, HttpServletRequest request, BuyDtlListDto buyDtlListDto) throws NoSuchAlgorithmException {
		
		Map<String,Object> returnMap = new HashMap<>();
		
		try {
			
			if(getSession(request,"SEQ_CST")=="") {
				returnMap.put("login", "login");
			} else if(buyDtlListDto.getBuyList() == null){
				returnMap.put("cart", "cart");
			} else {
				
				BuyMstDto buyMstDto = new BuyMstDto();
				BuyDtlDto buyDtlDto = new BuyDtlDto();
				String requests = param.get("requests");
				
				int price = 0;
				
				String buyInfo = "";
				int buyCount = 0;
				for(int i = 0; i < buyDtlListDto.getBuyList().size(); i++) {
					
					int dtlPrice = 0;
					dtlPrice = buyDtlListDto.getBuyList().get(i).getPrice();
					
					int dtlCount = 0;
					dtlCount = buyDtlListDto.getBuyList().get(i).getCount();
					
					price += dtlPrice * dtlCount;
					buyCount += dtlCount;
					
					if(i==0 && buyDtlListDto.getBuyList().size() == 1 ) {
						buyInfo += buyDtlListDto.getBuyList().get(i).getSle_nm();
					} else if(i==0 && buyDtlListDto.getBuyList().size() != 1) {
						buyInfo += buyDtlListDto.getBuyList().get(i).getSle_nm() + "(외 " + (buyDtlListDto.getBuyList().size() - 1) + "개)";
					}
				}
				
				buyMstDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
				buyMstDto.setBuy_info(buyInfo);
				buyMstDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
				buyMstDto.setBuy_t_price(price);
				buyMstDto.setRequest(requests);
				
				String yyyyMMddHHmmss = Datetime.getNow("yyyyMMddHHmmss");
				
				String merchantId			= staticProperties.getProperty("front.payup.id", "");
				String key					= staticProperties.getProperty("front.payup.key", "");
				String orderNumber			= "HM-" + yyyyMMddHHmmss;
				String amount				= String.valueOf(price);
				String quota				= "0";
				String itemName 			= buyInfo;
				String userName				= getSession(request, "CST_NM");
				String userAgent			= "WP";
				String returnUrl			= "returnUrl";
				String signature			= "";
				String timestamp			= yyyyMMddHHmmss;	
				
				signature = payupCmpn.getSHA256Hash(merchantId + "|" + orderNumber + "|" + amount + "|" + key + "|" + timestamp);

				String url = "https://api.testpayup.co.kr/ep/api/kakao/" + merchantId + "/order";
				Map<String,String> apiMap = new HashMap<>();
				apiMap.put("orderNumber",orderNumber);
				apiMap.put("amount",amount);
				apiMap.put("itemName",itemName);
				apiMap.put("userName",userName);
				apiMap.put("signature",signature);
				apiMap.put("timestamp",timestamp);
				if (Request.isDevice(request, "mobile")) {
					apiMap.put("auth_return","http://210.91.85.54:"
								+ staticProperties.getProperty("common.server.port", "[UNDEFINED]") + "/front/payup/receiveProcess.api");
				}
				else {
					apiMap.put("userAgent",userAgent);
					apiMap.put("returnUrl",returnUrl);
				}
				
				apiMap.put("quota",quota);
				apiMap.put("bypassValue","himediaValue");
				
				returnMap = payupCmpn.JsonApi(url, apiMap);
				
				if ("0000".equals(returnMap.get("responseCode"))) {
					
					if (buyDtlDto != null) {
						
						/** 페이업 거래번호 */
						logger.info("주문정보 = " +  returnMap);
						PayDto payDto = new PayDto();
						payDto.setDeal_num(returnMap.get("ordr_idxx").toString());
						
						// 마스터 설정
						buyMstDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
						buyMstDto.setBuy_info(buyInfo);
						buyMstDto.setBuy_t_price(Integer.parseInt(amount));
						buyMstDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
						buyMstDto.setBuy_t_count(buyCount);
						
						if (!buySrvc.insertForm(buyMstDto, buyDtlListDto, payDto)) {
							// 구매 정보 저장 에러
							returnMap.put("responseCode", "B001");
							returnMap.put("responseMsg", "[Error]Insert Buy Information");
						}
					}
				}
				else {
					logger.error("[" + this.getClass().getName() + ".payKakaoForm().RES.FAILURE] " + returnMap.toString());
				}
			}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".payKakaoForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return returnMap;
	}
	
	// 구매 취소
	@RequestMapping(value = "/front/buy/cancelPay.json", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean cancelPay(HttpServletRequest request, HttpServletResponse response, @RequestBody final BuyMstDto buyMstDto) {
		
		boolean success = false;
		
		try {
			
			String merchantId			= staticProperties.getProperty("front.payup.id", "");
			String key					= staticProperties.getProperty("front.payup.key", "");
			
			PayDto payDto = new PayDto();
			payDto = buySrvc.selectPay(buyMstDto.getSeq_buy_mst());
			
			String card_nm = payDto.getCard_nm();

			String transactionId	= payDto.getDeal_num();
			String signature		= "";	//아래에서 생성
			
			signature = payupCmpn.getSHA256Hash(merchantId + "|" + transactionId + "|" + key);
			
			Map<String,Object> returnMap = new HashMap<>();
			
			if(card_nm.equals("kakaoPay")) {
				
				String url = "https://api.testpayup.co.kr/ep/api/kakao/" + merchantId + "/cancel";

				Map<String,String> apiMap = new HashMap<>();
				apiMap.put("transactionId", transactionId);
				apiMap.put("signature", signature);
				apiMap.put("cancelReason", "테스트");
				
				returnMap = payupCmpn.JsonApi(url, apiMap);
				
			} else if(card_nm.equals("naverPay")){
				
				String url = "https://api.testpayup.co.kr/ep/api/naver/" + merchantId + "/cancel";

				Map<String,String> apiMap = new HashMap<>();
				apiMap.put("transactionId", transactionId);
				apiMap.put("signature", signature);
				apiMap.put("cancelReason", "테스트");
				
				returnMap = payupCmpn.JsonApi(url, apiMap);
				
			} else {
				
				String url = "https://api.testpayup.co.kr/v2/api/payment/" + merchantId + "/cancel2";
				
				Map<String,String> apiMap = new HashMap<>();
				apiMap.put("transactionId", transactionId);
				apiMap.put("signature", signature);
				
				returnMap = payupCmpn.JsonApi(url, apiMap);
				
			}
			
			if ("0000".equals(returnMap.get("responseCode"))) {
				
				buyMstDto.setUpdater(Integer.parseInt(getSession(request, "SEQ_CST")));
				if (buySrvc.cancelPay(buyMstDto)) {
					success = true;
				}

			}
			
			debuggingJSON(success);
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".cancelPay()] " + e.getMessage(), e);
		}
		finally {}
		
		return success;
	}
	
	//상품 상세페이지(구매, 장바구니)
	@RequestMapping(value = "/front/buy/writeForm.web")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response, SaleDto saleDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			String flgMobile	= "N";
			if (Request.isDevice(request, "mobile")) flgMobile = "Y";
			
			ReViewDto reViewDto = new ReViewDto();
			
			
			reViewDto.setSeq_sle(saleDto.getSeq_sle());
			
			SaleDto _saleDto	= saleSrvc.select(saleDto);
			
			List<ReViewDto> _selectRev = saleSrvc.selectRev(reViewDto);
			
			mav.addObject("saleDto"			, _saleDto);
			mav.addObject("selectRev"		, _selectRev);
			mav.addObject("flgMobile"		, flgMobile);
			mav.setViewName("/front/buy/writeForm");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 상품 리스트 페이지 이동
	@RequestMapping(value = "/front/buy/salesList.web")
	public ModelAndView salesList(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (pagingDto.getSearchWord() != null && pagingDto.getSearchWord() != "" ) {
				
				PagingListDto pagingListDto = saleSrvc.searchList(pagingDto);
				
				mav.addObject("paging"	, pagingListDto.getPaging());
				mav.addObject("list"	, pagingListDto.getList());
				mav.addObject("searchWord", pagingDto.getSearchWord());
				mav.addObject("sort", pagingDto.getSort());
				
			} else if (pagingDto.getCd_ctg() != null && pagingDto.getCd_ctg() != "" ) {
				
				PagingListDto pagingListDto = saleSrvc.selectList(pagingDto);
				
				mav.addObject("paging"	, pagingListDto.getPaging());
				mav.addObject("list"	, pagingListDto.getList());
				mav.addObject("cd_ctg", pagingDto.getCd_ctg());
				mav.addObject("sort", pagingDto.getSort());
			}
			
			mav.setViewName("/front/buy/salesList");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".salesList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 결제
	@RequestMapping(value = "/front/buy/payProcess.web")
	public ModelAndView payProcess(@RequestParam Map<String,String> param, HttpServletRequest request) throws NoSuchAlgorithmException {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			logger.info("[" + this.getClass().getName() + ".payProcess().REQ] " + param.toString());
			
			String res_cd		= param.get("res_cd");
			String ordr_idxx	= param.get("ordr_idxx");
			String res_msg		= param.get("res_msg");
			String enc_data		= param.get("enc_data");
			String enc_info		= param.get("enc_info");
			String tran_cd		= param.get("tran_cd");
			String buyr_mail	= param.get("buyr_mail");
			
			String url = "https://api.testpayup.co.kr/ap/api/payment/" + ordr_idxx + "/pay";
			Map<String,String> apiMap = new HashMap<>();		
			apiMap.put("res_cd",res_cd);
			apiMap.put("ordr_idxx",ordr_idxx);
			apiMap.put("res_msg",res_msg);
			apiMap.put("enc_data",enc_data);
			apiMap.put("enc_info",enc_info);
			apiMap.put("tran_cd",tran_cd);
			apiMap.put("buyr_mail",buyr_mail);
			
			Map<String,Object> apiResult = new HashMap<>();
			apiResult = payupCmpn.JsonApi(url, apiMap);
			
			logger.info("[" + this.getClass().getName() + ".payProcess().RES] " + apiResult.toString());
			
			/** 페이업 거래번호 */
			boolean isResult = true;
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
			if ("0000".equals(apiResult.get("responseCode"))) {
				
				PayDto payDto = new PayDto();
				payDto.setDeal_num(ordr_idxx);
				payDto.setFlg_success("Y");
				payDto.setCard_nm(apiResult.get("cardName").toString());
				payDto.setCard_no(apiResult.get("cardNo").toString());
				payDto.setQuota(Integer.parseInt(apiResult.get("quota").toString()));
				payDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
				
				isResult = buySrvc.update(payDto);
				
				request.setAttribute("script"	, "alert('결제에 성공하셨습니다');");
				request.setAttribute("redirect", "/front/cart/main.web");
			}
			else {
				
				PayDto payDto = new PayDto();
				payDto.setDeal_num(ordr_idxx);
				payDto.setFlg_success("N");
				payDto.setCard_nm(apiResult.get("cardName").toString());
				payDto.setCard_no(apiResult.get("cardNo").toString());
				payDto.setQuota(Integer.parseInt(apiResult.get("quota").toString()));
				payDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
				
				logger.error("[" + this.getClass().getName() + ".payProcess().RES.FAILURE] " + apiResult.toString());
				isResult = buySrvc.update(payDto);
				
				request.setAttribute("redirect", "/");
			}
			
			// 결제 결과에 대한 업데이트 실패 시
			if (!isResult) {
				
				request.setAttribute("redirect"	, "/");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".payProcess()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;	
	}
	
	// 장바구니 결제창
	@RequestMapping(value = "/front/buy/payForm.json", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> payForm(@RequestParam Map<String, String> param, HttpServletRequest request, BuyDtlListDto buyDtlListDto) throws NoSuchAlgorithmException {
		
		Map<String,Object> returnMap = new HashMap<>();
		
		try {
			
			if(getSession(request,"SEQ_CST")=="") {
				returnMap.put("login", "login");
			} else if(buyDtlListDto.getBuyList() == null){
				returnMap.put("cart", "cart");
			} else {
				
				BuyMstDto buyMstDto = new BuyMstDto();
				BuyDtlDto buyDtlDto = new BuyDtlDto();
				
				String requests = param.get("requests");
				
				int price = 0;
				
				String buyInfo = "";
				int buyCount = 0;
				for(int i = 0; i < buyDtlListDto.getBuyList().size(); i++) {
					
					int dtlPrice = 0;
					dtlPrice = buyDtlListDto.getBuyList().get(i).getPrice();
					
					int dtlCount = 0;
					dtlCount = buyDtlListDto.getBuyList().get(i).getCount();
					
					price += dtlPrice * dtlCount;
					buyCount += dtlCount;
					
					if(i==0 && buyDtlListDto.getBuyList().size() == 1 ) {
						buyInfo += buyDtlListDto.getBuyList().get(i).getSle_nm();
					} else if(i==0 && buyDtlListDto.getBuyList().size() != 1) {
						buyInfo += buyDtlListDto.getBuyList().get(i).getSle_nm() + "(외 " + (buyDtlListDto.getBuyList().size() - 1) + "개)";
					}
				}
				
				buyMstDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
				buyMstDto.setBuy_info(buyInfo);
				buyMstDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
				buyMstDto.setBuy_t_price(price);
				buyMstDto.setRequest(requests);
				
				
				String yyyyMMddHHmmss = Datetime.getNow("yyyyMMddHHmmss");
				
				String merchantId			= staticProperties.getProperty("front.payup.id", "");
				String key					= staticProperties.getProperty("front.payup.key", "");
				String orderNumber			= "HM-" + yyyyMMddHHmmss;
				String amount				= String.valueOf(price);
				String quota				= "0";
				String itemName 			= buyInfo;
				String userName				= getSession(request, "CST_NM");
				String userAgent			= "WP";
				String returnUrl			= "returnUrl";
				String signature			= "";
				String timestamp			= yyyyMMddHHmmss;	
				
				signature = payupCmpn.getSHA256Hash(merchantId + "|" + orderNumber + "|" + amount + "|" + key + "|" + timestamp);
				
				String url = "https://api.testpayup.co.kr/ap/api/payment/" + merchantId + "/order";
				Map<String,String> apiMap = new HashMap<>();
				apiMap.put("orderNumber",orderNumber);
				apiMap.put("amount",amount);
				apiMap.put("itemName",itemName);
				apiMap.put("userName",userName);
				apiMap.put("signature",signature);
				apiMap.put("timestamp",timestamp);
				if (Request.isDevice(request, "mobile")) {
					apiMap.put("auth_return","http://210.91.85.54:"
								+ staticProperties.getProperty("common.server.port", "[UNDEFINED]") + "/front/payup/receiveProcess.api");
				}
				else {
					apiMap.put("userAgent",userAgent);
					apiMap.put("returnUrl",returnUrl);
				}
				
				apiMap.put("quota",quota);
				apiMap.put("bypassValue","himediaValue");
				
				returnMap = payupCmpn.JsonApi(url, apiMap);
				
				if ("0000".equals(returnMap.get("responseCode"))) {
					
					if (buyDtlDto != null) {
						
						/** 페이업 거래번호 */
						logger.info("주문정보 = " +  returnMap);
						PayDto payDto = new PayDto();
						payDto.setDeal_num(returnMap.get("ordr_idxx").toString());
						
						// 마스터 설정
						buyMstDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
						buyMstDto.setBuy_info(buyInfo);
						buyMstDto.setBuy_t_price(Integer.parseInt(amount));
						buyMstDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
						buyMstDto.setBuy_t_count(buyCount);
						
						if (!buySrvc.insertForm(buyMstDto, buyDtlListDto, payDto)) {
							// 구매 정보 저장 에러
							returnMap.put("responseCode", "B001");
							returnMap.put("responseMsg", "[Error]Insert Buy Information");
						}
					}
				}
				else {
					logger.error("[" + this.getClass().getName() + ".payForm().RES.FAILURE] " + returnMap.toString());
				}
			}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".payForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return returnMap;
	}
	
}
