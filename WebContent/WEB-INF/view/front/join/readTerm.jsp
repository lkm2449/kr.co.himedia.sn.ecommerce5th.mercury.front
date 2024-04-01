<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="java.io.*" %>
<%
	BufferedReader reader = null;
	String url = request.getParameter("url");
	try{
		String filePath = application.getRealPath(url);
		File file = new File(filePath);
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		
		while(true){
			String str = reader.readLine();
			if(str==null){
				break;
			}
			out.println(str + "<br />");
		}
	}catch(FileNotFoundException e){
		out.println(url + "파일이 존재하지 않습니다.");
	}catch(IOException e){
		out.println(url + "파일을 읽을 수 없습니다.");
	}finally{
		try{
			reader.close();
		}catch(Exception e){
		}
	}
%>
