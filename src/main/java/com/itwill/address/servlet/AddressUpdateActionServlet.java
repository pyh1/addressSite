package com.itwill.address.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.address.Address;
import com.itwill.address.AddressService;

/**
 * Servlet implementation class AddressUpdateActionServlet
 */
@WebServlet("/address_update_action.do")
public class AddressUpdateActionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("address_main.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			/**
			 * 0.요청객체 encoding설정 
			 * 1.파라메타 바끼(no,id,name,phone,address) 
			 * 2.파라메타데이타로 Address객체생성
			 * 3.AddressService객체생성 
			 * 4.AddressService.updateByNo()메쏘드실행 
			 * 5.adress_detail.do 로
			 * redirection
			 */
			request.setCharacterEncoding("UTF-8");
			String noStr = request.getParameter("no");
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("address");
			Address address = new Address(Integer.parseInt(noStr), id, name, phone, addr);
			AddressService addressService = new AddressService();
			int updateRowCount = addressService.updateByNo(address);
			response.sendRedirect("address_detail.do?no="+noStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
