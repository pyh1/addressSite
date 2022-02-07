package com.itwill.address;

import java.util.ArrayList;

/*
AddressService객체
 	- 주소록 비즈니스로직(업무)을 수행하는 클래스
  	- Presentation객체(서블릿,JSP,SWING)에서 직접접근(메쏘드호출)하는 클래스
  	- AddressDao객체를 멤버변수로 가지고있다.
  	- 주소록 업무수행 메쏘드에서 DataBase접근(CRUD)이 필요하면 Dao를 사용한다.
 */
public class AddressService {
	private AddressDao addressDao;
	public AddressService() {
		addressDao=new AddressDao();
	}
	
	public int insert(Address newAddress) throws Exception{
		return addressDao.insert(newAddress);
	}
	public Address selectByNo(int no)throws Exception{
		return addressDao.selectByNo(no);
	}
	public ArrayList<Address> selectAll()throws Exception{
		return addressDao.selectAll();
	}
	public int updateByNo(Address updateAddress)throws Exception{
		return addressDao.updateByNo(updateAddress);
	}
	public int deleteByNo(int no)throws Exception{
		return addressDao.deleteByNo(no);
	}
	
}











