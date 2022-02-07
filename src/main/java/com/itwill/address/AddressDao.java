package com.itwill.address;
/*
 * Dao(Data[DataBase] Access Object)객체
 * 		- adresss(주소록) 데이타를 저장하고있는 테이블에
 *        CRUD(Create,Read,Update,Delete)작업을 할수있는 
 *        단위메쏘드를 가지고있는 클래스
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class AddressDao {
	/*
	 * Dao 객체는멤버변수로 Connection을 생성하는 DataSource객체를 가짐
	 */
	private DataSource dataSource;
	public AddressDao() {
		dataSource=new DataSource();
	}
	
	
	
	public int insert(String id,String name,String phone,String address) throws Exception{
		Connection con= dataSource.getConnection();
		String insertSql = "insert into address values(address_no_seq.nextval,?||address_no_seq.currval,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(insertSql);
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, phone );
		pstmt.setString(4, address);
		int insertRowCount = pstmt.executeUpdate();
		//System.out.println(">> " + insertRowCount + " 행 insert");
		pstmt.close();
		con.close();
		return insertRowCount;
	}
	public int insert(Address newAddress) throws Exception{
		Connection con= dataSource.getConnection();
		String insertSql = "insert into address values(address_no_seq.nextval,?||address_no_seq.currval,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(insertSql);
		pstmt.setString(1, newAddress.getId());
		pstmt.setString(2, newAddress.getName());
		pstmt.setString(3, newAddress.getPhone() );
		pstmt.setString(4, newAddress.getPhone());
		int insertRowCount = pstmt.executeUpdate();
		//System.out.println(">> " + insertRowCount + " 행 insert");
		pstmt.close();
		con.close();
		return insertRowCount;
	}
	public Address selectByNo(int no) throws Exception {
		Connection con= dataSource.getConnection();
		String selectSql = "select no,id,name,phone,address from address where no = ?";
		Address findAddress=null;
		PreparedStatement pstmt = con.prepareStatement(selectSql);
		pstmt.setInt(1, no);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			int number = rs.getInt("no");
			String id = rs.getString("id");
			String name = rs.getString("name");
			String phone = rs.getString("phone");
			String address = rs.getString("address");
			findAddress=new Address(number, id, name, phone, address);
		} 
		rs.close();
		pstmt.close();
		con.close();
		return findAddress;
	}

	public ArrayList<Address> selectAll() throws Exception{
		Connection con= dataSource.getConnection();
		String selectSql = "select no,id,name,phone,address from address";
		ArrayList<Address> addressList=new ArrayList<Address>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(selectSql);
		while (rs.next()) {
			int no = rs.getInt("no");
			String id = rs.getString("id");
			String name = rs.getString("name");
			String phone = rs.getString("phone");
			String address = rs.getString("address");
			addressList.add(new Address(no, id, name, phone, address));
		}
		rs.close();
		stmt.close();
		con.close();
		return addressList;
	}
	public int updateByNo(Address updateAddress) throws Exception{
		Connection con= dataSource.getConnection();
		String updateSql = "update address set id=?,name=?,phone=?,address=? where no = ?";
		PreparedStatement pstmt = con.prepareStatement(updateSql);
		pstmt.setString(1,updateAddress.getId());
		pstmt.setString(2,updateAddress.getName());
		pstmt.setString(3,updateAddress.getPhone());
		pstmt.setString(4,updateAddress.getAddress());
		pstmt.setInt(5, updateAddress.getNo());
		int updateRowCount = pstmt.executeUpdate();
		//System.out.println(">> " + updateRowCount + " 행이 update...");
		return updateRowCount;
	}
	
	public int updateByNo(int no,String id,String name,String phone,String address) throws Exception{
		Connection con= dataSource.getConnection();
		String updateSql = "update address set id=?,name=?,phone=?,address=? where no = ?";
		PreparedStatement pstmt = con.prepareStatement(updateSql);
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, phone);
		pstmt.setString(4, address);
		pstmt.setInt(5, no);
		int updateRowCount = pstmt.executeUpdate();
		//System.out.println(">> " + updateRowCount + " 행이 update...");
		return updateRowCount;
	}

	public int deleteByNo(int no) throws Exception{
		Connection con= dataSource.getConnection();
		String deleteSql = "delete address where no=?";
		PreparedStatement pstmt = con.prepareStatement(deleteSql);
		pstmt.setInt(1, no);
		int deleteRowCount = pstmt.executeUpdate();
		//System.out.println(">> " + deleteRowCount + " 행 delete..");
		pstmt.close();
		con.close();
		return deleteRowCount;
	}
}


