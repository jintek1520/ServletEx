package kr.co.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import kr.co.domain.MemberDTO;

public class MemberDAO {

	private DataSource dataFactory;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MemberDTO read(String id) {
		MemberDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT *FROM tbl_member WHERE ID=? ";
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String name =rs.getString("name");
				int age = rs.getInt("age");
				dto = new MemberDTO(id, name, age);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}

		return dto;
	}

	private void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO tbl_member (id, name, age) VALUES (?,?,?)";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getAge());
			
		
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
	}

	public List<MemberDTO> list() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM tbl_member";
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");

				MemberDTO dto = new MemberDTO(id, name, age);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return list;
	}

	public MemberDTO updateui(String id) {
		// TODO Auto-generated method stub
		return read(id);
	}

	public void update(MemberDTO dto) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql = "UPDATE tbl_member SET name = ?, age = ? WHERE id = ?";
		
		
		try {
			conn = dataFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getAge());
			pstmt.setString(3, dto.getId());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeAll(null, pstmt, conn);
		}
		
		
	}

	public void delete(String id) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		String sql ="DELETE FROM tbl_member WHERE id=?";
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);		
			
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		
	}

	 public Map<String, String> menuMap(){
	      Map<String, String> map = new HashMap<String, String>();
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql ="SELECT *FROM menu";
	      ResultSet rs = null;
	      
	      try {
			conn = dataFactory.getConnection();
			pstmt =conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while( rs.next()) {
				String sp = rs.getString(1);
				String fullname = rs.getString(2);
				
				((HashMap<String, String>) map).put(sp,fullname);
				
		}
			} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			closeAll(rs, pstmt, conn);
		}
	      
	     return map;
	   } 

}
