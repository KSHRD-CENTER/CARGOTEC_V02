package kh.com.kshrd.threads;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyExcel extends Thread {

	private String filePath;

	public CopyExcel(String filePath) {
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
		System.out.println("STARTING COPYING THE EXCEL FILE");
		InputStream inStream = null;
		OutputStream outStream = null;
		try {
			String path = System.getProperty("user.home") + File.separator + "CARGOTEC" + File.separator + "excel"+ File.separator;
			if (!new File(path).exists()) {
				new File(path).mkdirs();
			}
			File afile = new File(filePath);
			File bfile = new File(path+afile.getName());

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
			inStream.close();
			outStream.close();
			System.out.println("ENDING THE COPYING THE EXCEL FILE SUCCESSFULLY....");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ENDING THE COPYING THE EXCEL FILE FAILURE....");
		}

/*		try {
			File file = new File(filePath);
			String path = System.getProperty("user.home") + File.separator + "CARGOTEC" + File.separator + "excel"
					+ File.separator;
			if (!new File(path).exists()) {
				new File(path).mkdirs();
			}
			if (file.renameTo(new File(path + file.getName()))) {
				System.out.println("FILE HAS BEEN COPIED ALREADY");
			} else {
				System.out.println("FILE HAS BEEN FAILED WHEN COPY.");
			}
			System.out.println("ENDING THE COPYING THE EXCEL FILE SUCCESSFULLY....");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ENDING THE COPYING THE EXCEL FILE FAILURE....");
		}*/
	}

}
