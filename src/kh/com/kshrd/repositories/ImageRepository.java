package kh.com.kshrd.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kh.com.kshrd.models.Image;

public class ImageRepository {

	private Connection con;
	private ResultSet rs;
	
	public ImageRepository(){
		con = ConnectionManagement.getConnection();
	}
	
	public boolean save(Image image){
		
		String sql ="INSERT INTO images(url, filename, model_id, status) "
				  + "VALUES(?,?,?,?)";
		try(
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, image.getUrl());
			pstmt.setString(2, image.getFilename());
			pstmt.setLong(3, image.getContentId());			
			pstmt.setString(4, "1");
			int count = pstmt.executeUpdate();
			if(count>0){
				return true;
			}
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
			return false;
		}
		return false;
	}
}
