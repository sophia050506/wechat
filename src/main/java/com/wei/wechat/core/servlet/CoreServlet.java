package com.wei.wechat.core.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wei.wechat.core.service.CoreService;
import com.wei.wechat.core.util.SignUtil;

/**
 * Servlet implementation class CoreServlet
 */
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Logger logger = Logger.getLogger(CoreServlet.class);  

	/**
	 * 请求校验
	 * 确认请求来自微信服务器
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		logger.info("signature="+signature+";timestamp="+timestamp+";nonce="+nonce+";echostr="+echostr);
		
		
		PrintWriter out = response.getWriter();
		//if(SignUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		//}
		out.close();
		out=null;
		
	}

	/**
	 * 处理微信服务器发过来的消息
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("进入doPost方法");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		logger.info("request内容:"+request+"---"+request.toString());
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		
		PrintWriter out = response.getWriter();
		//if(SignUtil.checkSignature(signature, timestamp, nonce)){
			String respXml = CoreService.processRequest(request);
			out.print(respXml);
		//}
		logger.info("respXml内容:"+respXml);
		out.close();
		out=null;
	}

}
