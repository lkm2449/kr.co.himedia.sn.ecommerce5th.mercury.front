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
 * File			: MainWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231219111626][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.front.board.dto.BoardDto;
import kr.co.himedia.ecommerce.front.board.service.BoardSrvc;
import kr.co.himedia.ecommerce.front.reply.dto.ReplyDto;
import kr.co.himedia.ecommerce.front.reply.service.ReplySrvc;
import kr.co.himedia.ecommerce.util.Files;



/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-19
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.board.controller.BoardWeb")
public class BoardWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BoardWeb.class);
	
	@Autowired
	Properties staticProperties;
	@Inject 
	private BoardSrvc boardSrvc;
	@Inject 
	ReplySrvc replySrvc;
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2023-12-19
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	
	@RequestMapping(value = "/front/board/inquiryForm.web")
	public ModelAndView inquiryForm (HttpServletRequest request, HttpServletResponse response, String cd_bbs_type) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if(getSession(request,"SEQ_CST")=="") {
				
				request.setAttribute("script"	, "alert('로그인이 필요한 서비스입니다!');");
				request.setAttribute("redirect"	, "/front/login/loginForm.web");
				mav.setViewName("forward:/servlet/result.web");
				
			} else {
				
				mav.addObject("cd_bbs_type"	, cd_bbs_type);

				mav.setViewName("/front/board/inquiryForm");
			}		
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".inquiryForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	} 
	
	@RequestMapping(value = "/front/board/faqView.web")
	public ModelAndView faqView(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			BoardDto _boardDto = boardSrvc.select(boardDto);
			
			mav.addObject("boardDto", _boardDto);
			mav.setViewName("/front/board/faqView");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".faqView()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/front/board/faqList.web")
	public ModelAndView faqList (HttpServletRequest request, HttpServletResponse response, String cd_bbs_type, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (pagingDto.getCd_bbs_type() == null || pagingDto.getCd_bbs_type() == 0) {
				pagingDto.setCd_bbs_type(2);
			}
			
			PagingListDto pagingListDto = boardSrvc.list(pagingDto);
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.addObject("cd_bbs_type"	, cd_bbs_type);
			mav.setViewName("/front/board/faqList");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".faqList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/board/myInquiryList.web")
	public ModelAndView myInquiryList (HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto, String cd_bbs_type) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if(getSession(request,"SEQ_CST")=="") {
				
				request.setAttribute("script"	, "alert('로그인이 필요한 서비스입니다!');");
				request.setAttribute("redirect"	, "/front/login/loginForm.web");
				mav.setViewName("forward:/servlet/result.web");
				
			} else {
				
				mav.addObject("cd_bbs_type"	, cd_bbs_type);
				
				if (pagingDto.getCd_bbs_type() == null || pagingDto.getCd_bbs_type() == 0) {
					pagingDto.setCd_bbs_type(3);
				}
				pagingDto.setRegister(Integer.parseInt(getSession(request,"SEQ_CST")));
				PagingListDto pagingListDto = boardSrvc.myList(pagingDto);
				
				mav.addObject("paging"	, pagingListDto.getPaging());
				mav.addObject("list"	, pagingListDto.getList());
				mav.setViewName("/front/board/myInquiryList");
			}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".myInquiryList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	

	@RequestMapping(value = "/front/board/myInquiryView.web")
	public ModelAndView myInquiryView(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			BoardDto _boardDto = boardSrvc.select(boardDto);
			ReplyDto _replyDto = replySrvc.select(boardDto);
			
			mav.addObject("replyDto", _replyDto);
			mav.addObject("boardDto", _boardDto);
			mav.setViewName("/front/board/myInquiryView");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".myInquiryView()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/board/noticeList.web")
	public ModelAndView noticeList (HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (pagingDto.getCd_bbs_type() == null || pagingDto.getCd_bbs_type() == 0) {
				pagingDto.setCd_bbs_type(1);
			}
			
			PagingListDto pagingListDto = boardSrvc.list(pagingDto);
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/front/board/noticeList");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".noticeList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	

	@RequestMapping(value = "/front/board/noticeView.web")
	public ModelAndView noticeView(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			BoardDto _boardDto = boardSrvc.select(boardDto);
			
			mav.addObject("boardDto", _boardDto);
			mav.setViewName("/front/board/noticeView");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".noticeView()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	

	@RequestMapping(value = "/front/board/inquiryProc.web", method = RequestMethod.POST)
	public ModelAndView inquiryProc(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto, String cd_bbs_type) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			Map<String, String> boardMap = upload(request, response);
			
			String fileNameSave = boardMap.get("fileNameSave");
			String fileNameOrig = boardMap.get("fileNameOrig");
			
			
			// 파일이 있을 경우에만
			if (fileNameOrig != null && fileNameOrig.length() != 0) {
						
				String dirUpload	= staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");
				String newFolder	= "board";
						
				File srcFile = new File(dirUpload + File.separator + "temp" + File.separator + fileNameSave);
				File destDir = new File(dirUpload + "/" + newFolder);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				
				String dirUpload2	= staticProperties.getProperty("common.dirUpload2", "[UNDEFINED]");
				
				File srcFile2 = new File(dirUpload2 + File.separator + "temp" + File.separator + fileNameSave);
				File destDir2 = new File(dirUpload2 + "/" + newFolder);
				FileUtils.moveFileToDirectory(srcFile2, destDir2, true);
						
				boardDto.setFile_save("/" + newFolder + "/" + fileNameSave);
				boardDto.setFile_orig(fileNameOrig);
			}
				
			boardDto.setCd_bbs_type(Integer.parseInt(boardMap.get("cd_bbs_type")));		
			boardDto.setCd_bbs_tab(boardMap.get("cd_bbs_tab"));
			boardDto.setTitle(boardMap.get("title"));
			boardDto.setContents(boardMap.get("contents"));
			boardDto.setFlg_top("N");
			boardDto.setFlg_delete("N");
			boardDto.setRegister(Integer.parseInt(getSession(request,"SEQ_CST")));
			
			
			if (boardSrvc.insert(boardDto)) {
				request.setAttribute("script"	, "alert('등록 성공');");
				request.setAttribute("redirect"	, "/front/board/myInquiryList.web");
			}
			else {
				request.setAttribute("script"	, "alert('등록 실패;')");
				request.setAttribute("redirect"	, "/front/board/myInquiryList.web");
			}
			
			mav.addObject("cd_bbs_type"	, cd_bbs_type);
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".inquiryProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@SuppressWarnings("rawtypes")
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String dirUpload = staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");
		String dirUpload2 = staticProperties.getProperty("common.dirUpload2", "[UNDEFINED]");
		
		Map<String, String> boardMap = new HashMap<String, String>();
		
		String encoding = "utf-8";
		File currentDirPath = new File(dirUpload);
		File currentDirPath2 = new File(dirUpload2);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(request);
			
			for (int loop = 0; loop < items.size(); loop++) {
				
				FileItem fileItem = (FileItem) items.get(loop);
				
				// INPUT일 경우
				if (fileItem.isFormField()) {					
					boardMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}
				// FILE일 경우
				else {
					
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						String fileNameOrig = fileItem.getName().substring(idx + 1);
						String fileNameSave = Files.getFileSaveName(fileNameOrig);
						boardMap.put("fileNameOrig", fileNameOrig);
						boardMap.put("fileNameSave", fileNameSave);
						
						File uploadFile = new File(currentDirPath + File.separator + "temp" + File.separator + fileNameSave);
						fileItem.write(uploadFile);
						
						File uploadFile2 = new File(currentDirPath2 + File.separator + "temp" + File.separator + fileNameSave);
						fileItem.write(uploadFile2);
					}
				}
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".upload()] " + e.getMessage(), e);
		}
		return boardMap;
	}
	
}
