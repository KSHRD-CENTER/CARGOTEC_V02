import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import kh.com.kshrd.models.Content;
import kh.com.kshrd.models.Image;
import kh.com.kshrd.models.Model;
import kh.com.kshrd.models.Part;

public class Main {

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		//TODO: TO READ FILE USING FileInputStream
		FileInputStream inputStream = new FileInputStream(new File("excel/HIAB10000XG-201111 ALL rev.1.xls"));
		
		//TODO: TO CREATE NEW WORKBOOK with fileInputStream
		Workbook workbook = WorkbookFactory.create(inputStream);
		
		//TODO: TO READ THE SHEET INDEX 0
		Sheet firstSheet = workbook.getSheetAt(0);
		
		//TODO: TO READ THE IMAGES IN THE EXCEL FILE
		HSSFPatriarch dravingPatriarch = (HSSFPatriarch) firstSheet.getDrawingPatriarch();
		
		//TODO: TO READ ALL SHAPES AS THE IMAGE FILE
		java.util.List<HSSFShape> shapes = dravingPatriarch.getChildren();
		
		//TODO: TO CREATE MAP OBJECT TO STORE THE DATA FOR EACH IMAGES
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		
		Iterator<Row> it = firstSheet.rowIterator();
        while(it.hasNext()){
            HSSFRow r = (HSSFRow) it.next();
            Iterator<Cell> it1=r.cellIterator();
            System.out.print("\nROW==> " + r.getRowNum() + "  " );
            while(it1.hasNext()){
                HSSFCell cell = (HSSFCell)it1.next();
                System.out.print(" "+ cell + " -- ");
            }
        }
		
		//TODO: TO LOOP THROUGH ALL THE IMAGES
		for (HSSFShape shape : shapes) {
			//TODO: TO CHECK IF THAT SHAPE IS THE IMAGE
			if (shape instanceof HSSFPicture) {
				//TODO: TO CREATE THE HSSFPicture Object
				HSSFPicture hssfPicture = (HSSFPicture) shape;
				int picIndex = hssfPicture.getPictureIndex();
				String filename = hssfPicture.getFileName();
				int row = hssfPicture.getClientAnchor().getRow1();
				int col = hssfPicture.getClientAnchor().getCol1();
				
				//TODO: CONTENT IS THE IMAGE INFORMATION
				Content content = new Content();
				content.setFilename(filename);
				content.setPictureIndex(picIndex);
				content.setColumn(col);
				content.setHssfPicture(hssfPicture);
				content.setRow(row);
				//content.setFileId(file.getId());
				map.put(row, content);
				
				System.out.println("Picture " + picIndex + " with Filename: " + filename + " is located row: " + row +	 ", col: " + col);
				System.out.println("INDEX=="+ picIndex + " ROW=="+ row +" FILENAME==> " + filename);
			}
		}
		//TODO: TO CREATE THE TreeMap object for ordering the Image in the map
		Map<Integer, Object> treeMap = new TreeMap<Integer, Object>(map);
		Set s = treeMap.entrySet();
		Iterator it2 = s.iterator();
		while (it2.hasNext()) {
			Map.Entry entry = (Map.Entry) it2.next();
			String key = entry.getKey() + "";
			Content value = (Content) entry.getValue();
			
			PictureData picture = (PictureData) value.getHssfPicture().getPictureData();
			String ext = picture.suggestFileExtension(); 
			byte[] data = picture.getData();
			
			FileOutputStream out = null;
			out  = new FileOutputStream("images/"+ "ROW_"+ value.getRow()+"_"+value.getFilename()+"."+ext);
			out.write(data);
			out.close();
			
			System.out.println("IMAGE==> " + value);
		}
		
		System.out.println("YOU HAVE BEEN DONE READING THE EXCEL TO THE DATABASE ALREADY");
		workbook.close();
		inputStream.close();
		
	}
	
}
