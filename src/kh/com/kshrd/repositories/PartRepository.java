package kh.com.kshrd.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kh.com.kshrd.models.Part;

public class PartRepository{

	private Connection con;
	private ResultSet rs;
	
	public PartRepository(){
		con = ConnectionManagement.getConnection();
	}
	
	public boolean save(List<Part> parts){
			
		String sql ="INSERT INTO parts(no, partNo, koreanDescription, englishDescription, quantity, remarks, code, model_id) "
				  + "VALUES(?,?,?,?,?,?,?,?);";
		try(
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			for(Part part : parts){
				pstmt.setString(1, part.getNo());
				pstmt.setString(2, part.getPartNo());
				pstmt.setString(3, part.getKoreanDescription());
				pstmt.setString(4, part.getEnglishDescription());
				pstmt.setString(5, part.getQuantity());
				pstmt.setString(6, part.getRemarks());
				pstmt.setString(7, part.getCode());
				pstmt.setLong(8, part.getContentId());
				pstmt.addBatch();
			}
			int counts[] = pstmt.executeBatch();
			boolean status = false;
			for(int count : counts){
				if(count<=0){
					return false;
				}
			}
			return true;
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return false;
		}
	}
	
	/*public boolean checkExistFile(String fileName){
		String sql ="SELECT COUNT(file_id) FROM parts WHERE name=?";
		try(
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, fileName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt(1)>0){
					return true;
				}
			}
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
			return false;
		}
		return false;
	}*/
}
