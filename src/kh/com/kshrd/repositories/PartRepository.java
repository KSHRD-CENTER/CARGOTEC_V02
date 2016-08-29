package kh.com.kshrd.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import kh.com.kshrd.models.Part;

public class PartRepository{

	private Connection con;
	
	public PartRepository(){
		con = ConnectionManagement.getConnection();
	}
	
	//TODO: TO SAVE BATCH PARTS INFORMATION TO THE DATABASE
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
}
