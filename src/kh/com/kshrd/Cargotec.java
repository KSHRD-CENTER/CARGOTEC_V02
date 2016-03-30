package kh.com.kshrd;

import java.sql.SQLException;

import kh.com.kshrd.repositories.ConnectionManagement;
import kh.com.kshrd.threads.CopyExcel;
import kh.com.kshrd.threads.ExportExcelToPDF;

public class Cargotec {

	public static void main(String[] args) throws Exception {

		
		new PropertiesManagement();

		CargotecController cargotecController = new CargotecController();
		try {
			ConnectionManagement.getConnection().setAutoCommit(false);
		} catch (SQLException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (args.length > 0) {
				ExportExcelToPDF exportExcelToPDF = new ExportExcelToPDF(args[0]);
				exportExcelToPDF.start();
				CopyExcel copyExcel = new CopyExcel(args[0]);
				copyExcel.start();				
				cargotecController.execute(args[0]);
			} else {
				System.out.println("PLEASE ENTER YOUR EXCEL FILE");
			}
			ConnectionManagement.getConnection().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				ConnectionManagement.getConnection().rollback();
				System.err.println("YOU HAVE BEEN SOME ERRORS...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ConnectionManagement.closeConnection();
		
		

	}
}
