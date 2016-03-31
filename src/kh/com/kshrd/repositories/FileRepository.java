package kh.com.kshrd.repositories;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kh.com.kshrd.models.File;

public class FileRepository{
	
	private Connection con;
	private ResultSet rs;
	
	public FileRepository(){
		con = ConnectionManagement.getConnection();
	}
	
	public Long save(File file){
		String sql ="INSERT INTO files(name, status, createdDate) VALUES(?,?,GETDATE());";
		try(
			PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		){
			pstmt.setString(1, file.getName());
			pstmt.setString(2, file.getStatus());
			int count = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
			    return rs.getLong(1);
			}
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
			return 0L;
		}
		return 0L;
	}
	
	public boolean checkExistFile(String fileName){
		String sql ="SELECT COUNT(file_id) FROM files WHERE name=?";
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
	}

}
