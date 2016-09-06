package kh.com.kshrd.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kh.com.kshrd.models.Model;

public class ModelRepository{

	private Connection con;
	private ResultSet rs;
	
	public ModelRepository(){
		con = ConnectionManagement.getConnection();
	}
	
	//TODO: TO FIND MODEL AND FIND BY (CODE AND YEAR AND MONTH AND ENGLISH_TITLE AND FILE_ID) 
	public Model findByCodeAndYearAndMonthAndEnglishTitleAndFileId(Model model){
		String sql ="SELECT model_id, logoBrand, koreanTitle, englishTitle, year, month, code, parentId, fileId "
				  + "FROM models "
				  + "WHERE code=? "
				  + "AND year=? " 
				  + "AND month=? "
				  + "AND englishTitle=? "
				  + "AND fileId=? ";
		System.out.println("Code="+ model.getCode());
		System.out.println("Year="+ model.getYear());
		System.out.println("Month="+ model.getMonth());
		System.out.println("EnglishTitle="+ model.getEnglishTitle());
		System.out.println("FileId="+ model.getFileId());
		
		try(
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, model.getCode());
			pstmt.setString(2, model.getYear());
			pstmt.setString(3, model.getMonth());
			pstmt.setString(4, model.getEnglishTitle());
			pstmt.setLong(5, model.getFileId());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("RETURN FROM MODEL NOT NULL");
				Model returnModel = new Model(rs.getLong("model_id"),
								 rs.getString("logoBrand"),
								 rs.getString("koreanTitle"),
								 rs.getString("englishTitle"),
								 rs.getString("year"),
								 rs.getString("month"),
								 rs.getString("code"),
								 rs.getLong("parentId"),
								 rs.getLong("fileId"));
				System.out.println(returnModel);
				return returnModel;
			}else{
				System.out.println("RETURN FROM MODEL NULL");
				return null;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("ERROR CATCH ON THE MODEL findByCodeAndYearAndMonthAndEnglishTitleAndFileId");
			System.err.println(ex.getMessage());
			return null;
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
	}
	
	//TODO: TO FIND THE MODEL BY (CODE AND FILE_ID)
	public Model findByCodeAndFileId(String code, Long fileId){
		String sql ="SELECT model_id, logoBrand, koreanTitle, englishTitle, year, month, code, parentId, fileId "
				  + "FROM models "
				  + "WHERE code=? "
				  + "AND fileId=?";
		try(
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, code);
			pstmt.setLong(2, fileId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return new Model(rs.getLong("model_id"),
								 rs.getString("logoBrand"),
								 rs.getString("koreanTitle"),
								 rs.getString("englishTitle"),
								 rs.getString("year"),
								 rs.getString("month"),
								 rs.getString("code"),
								 rs.getLong("parentId"),
								 rs.getLong("fileId"));
			}else{
				return null;
			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return null;
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
	}
	
	
	//TODO: TO SAVE THE MODEL TO THE DATABASE
	public boolean save(Model model){
		System.out.println("STARTING SAVING THE MODEL...");
		if(model.getCode().trim().equals("")){
			return false;
		}
		String sql ="INSERT INTO models(logoBrand, koreanTitle, englishTitle, year, month, code, parentId, fileId) "
				  + "VALUES(?, ? ,?, ?, ?, ?, ?, ?);";
		try(
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, model.getLogoBrand());
			pstmt.setString(2, model.getKoreanTitle());
			pstmt.setString(3, model.getEnglishTitle());
			pstmt.setString(4, model.getYear());
			pstmt.setString(5, model.getMonth());
			pstmt.setString(6, model.getCode());
			if(null==model.getParentId()){
				pstmt.setNull(7, java.sql.Types.BIGINT);
			}else{
				pstmt.setLong(7, model.getParentId());
			}
			pstmt.setLong(8, model.getFileId());
			int count = pstmt.executeUpdate();
			if(count>0){
				System.out.println("SAVING THE MODEL HAS BEEN SUCCESSFULLY...");
				return true;
			}
			System.out.println("SAVING THE MODEL HAS BEEN FAILURE...");
		}catch(Exception ex){
			ex.printStackTrace();
			System.err.println(ex.getMessage());
			return false;
		}
		return false;
	}
}
