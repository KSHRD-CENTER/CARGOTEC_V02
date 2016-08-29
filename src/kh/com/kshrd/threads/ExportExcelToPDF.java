package kh.com.kshrd.threads;

import java.io.File;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

//TODO: THREAD FOR EXPORT EXCEL TO THE PDF
public class ExportExcelToPDF extends Thread{
	
	private String filePath;
	
	public ExportExcelToPDF(String filePath){
		this.filePath = filePath;
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {
		System.out.println("STARTING CONVERTING TO PDF");
		Workbook workbook;
		try {
			workbook = new Workbook(this.filePath);
			String path = System.getProperty("user.home")+File.separator+ "CARGOTEC"+File.separator+ "pdf"+File.separator;
			if(!new File(path).exists()){
				new File(path).mkdirs();
			}
			String fileName = filePath.substring( filePath.lastIndexOf(File.separator)+1, filePath.length() );
			String fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
			workbook.save(path+fileNameWithoutExtn+".pdf", SaveFormat.PDF);
			System.out.println("ENDING THE CONVERTING THE FILE SUCCESSFULLY....");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ENDING THE CONVERTING THE FILE FAILURE....");
		}
	}
	
	public static void main(String[] args) {
		String filePath = "C:\\Users\\HRD\\git\\HRD_SHORTCOURSE\\dest.xls";
		Workbook workbook;
		try {
			workbook = new Workbook(filePath);
			String path = "";
			if(!new File(path).exists()){
				new File(path).mkdirs();
			}
			String fileName = filePath.substring( filePath.lastIndexOf(File.separator)+1, filePath.length() );
			String fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
			workbook.save(path+fileNameWithoutExtn+".pdf", SaveFormat.PDF);
			System.out.println("ENDING THE CONVERTING THE FILE SUCCESSFULLY....");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ENDING THE CONVERTING THE FILE FAILURE....");
		}
	}

}
