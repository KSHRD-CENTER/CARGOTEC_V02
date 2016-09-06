package kh.com.kshrd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import kh.com.kshrd.models.Content;
import kh.com.kshrd.models.Image;
import kh.com.kshrd.models.Model;
import kh.com.kshrd.models.Part;
import kh.com.kshrd.repositories.FileRepository;
import kh.com.kshrd.repositories.ImageRepository;
import kh.com.kshrd.repositories.ModelRepository;
import kh.com.kshrd.repositories.PartRepository;

public class CargotecController {
	
	private static String ROOT = "";
	private static String fileName = "";
	private static String fileNameWithoutExtn ="";
	private static String parentId ="";
	private FileRepository fileRepository;
	private ImageRepository imageRepository;
	private ModelRepository modelRepository;
	private PartRepository partRepository;	
	
	//TODO: TO INITIALIZE THE REPOSITORY.
	public CargotecController(){
		fileRepository = new FileRepository();
		imageRepository = new ImageRepository();
		modelRepository = new ModelRepository();
		partRepository = new PartRepository();
	}
	
	public void execute(String filePath) throws EncryptedDocumentException, InvalidFormatException, IOException{
		
		//TODO: TO GET FILENAME OF THE EXCEL FILE
		fileName = filePath.substring( filePath.lastIndexOf(File.separator)+1, filePath.length() );
		
		//TODO: TO GET FILENAME WITHOUT EXTENSION
		fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
		
		//TODO: TO GET THE USER DIRECTORY FILE
		String path = System.getProperty("user.home");
		
		//TODO: TO ADD THE ROOT DIRECTORY FOR SAVING THE IMAGES
		ROOT = path + File.separator+ "CARGOTEC"+File.separator+ "image"+File.separator+fileNameWithoutExtn;
		
		//TODO: TO CHECK IF THE DIRECTORY IT IS NOT EXIST WE WILL CREATE IT 
		if(!new File(ROOT).exists()){
			new File(ROOT).mkdirs();
		}
		
		//TODO: TO CREATE THE FILE OBJECT 
		kh.com.kshrd.models.File file = new kh.com.kshrd.models.File();
		
		//TODO: TO CREATE THE EXCEL FILE PATH
		String excelFilePath = System.getProperty("user.home") + File.separator + "CARGOTEC" + File.separator + "excel"+ File.separator;
		file.setName(excelFilePath+fileName);
		file.setStatus("1");
		file.setCreatedDate(new Date());
		//TODO: TO CHECK IF THE EXCEL FILE ALREADY EXIST WE DON'T READ IT MORE
		if(fileRepository.checkExistFile(file.getName())){
			System.err.println("THE EXCEL THAT YOU HAVE BEEN CHOOSED TO INSERT HAS BEEN ALREADY INSERTED...");
			return;
		}else{			
			//TODO: IF NOT WE WILL SAVE IT
			file.setId(fileRepository.save(file));
			System.out.println("FILE ID=" + file.getId());
		}
		fileName = fileNameWithoutExtn;
		
		//TODO: TO READ FILE USING FileInputStream
		FileInputStream inputStream = new FileInputStream(new File(filePath));
		
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
				content.setFileId(file.getId());
				map.put(row, content);
			}
		}
		//TODO: TO CREATE THE TreeMap object for ordering the Image in the map
		Map<Integer, Object> treeMap = new TreeMap<Integer, Object>(map);
		
		//TODO: TO SAVE TO THE DATABASE
		saveToDB(firstSheet, treeMap);
		
		System.out.println("YOU HAVE BEEN DONE READING THE EXCEL TO THE DATABASE ALREADY");
		
		//TODO: TO CLOSE THE EXCEL WORKBOOK
		workbook.close();
		
		//TODO: TO CLOSE THE FILE INPUT STREAM
		inputStream.close();
	}
	
	//TODO: TO SAVE TO THE DATABASE
	public void saveToDB(Sheet firstSheet, Map<Integer, Object> map) throws IOException {
		Set s = map.entrySet();
		Iterator it = s.iterator();
		Long i = 1L;
		String descriptionSection = "hiab";
		parentId = firstSheet.getRow(1).getCell(10).getStringCellValue();
		String previousImage = "";
		int k=2;
		Long previousContentId = 0L;
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = entry.getKey() + "";
			
			//TODO: Model(Content)
			Content value = (Content) entry.getValue();
			PictureData picture = (PictureData) value.getHssfPicture().getPictureData();
			String ext = picture.suggestFileExtension(); 
			byte[] data = picture.getData();
			
			int row = value.getRow();
			
			try{
				value.setKoreanTitle(firstSheet.getRow(row).getCell(2).getStringCellValue());
				value.setEnglishTitle(firstSheet.getRow(row+1).getCell(2).getStringCellValue());
				value.setCode(firstSheet.getRow(row).getCell(10).getStringCellValue());
				value.setDate(firstSheet.getRow(row+1).getCell(10).getStringCellValue());
			}catch(Exception ex){
				value.setKoreanTitle("");
				value.setEnglishTitle("");
				value.setCode("");
				ex.printStackTrace();
			}
			
			Long contentId= null;
			String code = firstSheet.getRow(row).getCell(10)+"";
			String[] date = (firstSheet.getRow(row+1).getCell(10)+"").replace(" ", "").split("\\.");
			String title = firstSheet.getRow(row+1).getCell(2)+"";
			Model model = new Model();
			try{
				model.setCode(code);
				model.setEnglishTitle(title);
				model.setFileId(value.getFileId());
				if(date.length>1){
					model.setYear(date[0]);
					model.setMonth(date[1]);
				}else{
					model.setYear("");
					model.setMonth("");
				}
				contentId = modelRepository.findByCodeAndYearAndMonthAndEnglishTitleAndFileId(model).getId();
			}catch(Exception ex){
				
			}
			
			//TODO: TO READ THE DESCRIPTION OF PARTS TO THE DATABASE
			System.out.println("DESCRIPTION ==> " + descriptionSection);
			int j = row+4;
			while(!(firstSheet.getRow(j).getCell(1)+"").equals("")){
				if(!"MAIN  SUBDIVISIONS".equals(firstSheet.getRow(row+1).getCell(2)+"")){
					String no = "";
					no = firstSheet.getRow(j).getCell(0)+"";
					no = no.replace(".0", "");
					no = no.replace("-","0");
					String partNo = firstSheet.getRow(j).getCell(1)+"";
					String koreanTitle = firstSheet.getRow(j).getCell(3)+"";
					String englishTitle = firstSheet.getRow(j+1).getCell(3)+"";
					String quantity = firstSheet.getRow(j).getCell(4)+"";
					String remarks = firstSheet.getRow(j).getCell(5)+"";
					
					//TODO: TO ADD THE PARTS TO THE MODEL(Content)
					value.getDescriptions().add(new Part(no,partNo,koreanTitle,englishTitle,quantity,remarks,code, contentId));
										
					//TODO: TO READ THE VALUE FROM THE SPARE PART...
					if(!(firstSheet.getRow(j).getCell(6)+"").equals("")){
						no = firstSheet.getRow(j).getCell(6)+"";
						no = no.replace(".0", "");
						no = no.replace("-","0");
						partNo = firstSheet.getRow(j).getCell(7)+"";
						koreanTitle = firstSheet.getRow(j).getCell(9)+"";
						englishTitle = firstSheet.getRow(j+1).getCell(9)+"";
						quantity = firstSheet.getRow(j).getCell(10)+"";
						remarks = firstSheet.getRow(j).getCell(11)+"";
						code = firstSheet.getRow(row).getCell(10)+"";
						value.getDescriptions().add(new Part(no,partNo,koreanTitle,englishTitle,quantity,remarks,code, contentId));
					}
					//System.out.println(value.getDescriptions());
				}
				j+=2;
			}
			//TODO: TO SAVE THE PARTS TO THE DATABASE
			partRepository.save(value.getDescriptions());
			
			descriptionSection = (firstSheet.getRow(row+2).getCell(1)+"").trim();
			previousImage = i + "";
			value.setId(i);
			value.setLogoBrand("hiab.jpeg");
			value.setDescription("...");
			
			//TODO: TO SET THE MODEL INFORMATION
			model.setEnglishTitle(value.getEnglishTitle());
			model.setKoreanTitle(value.getKoreanTitle());
			model.setLogoBrand(i+"." + ext);
			
			try{
				//TODO: TO SAVE TO THE MODEL
				Model parentContent = modelRepository.findByCodeAndFileId(parentId, value.getFileId());
				model.setParentId(parentContent.getId());
				model.setLogoBrand(parentContent.getLogoBrand());
			}catch(Exception ex){
				model.setParentId(null);
				model.setLogoBrand(ROOT+File.separator+firstSheet.getSheetName()+"_"+i+"." + ext);
			}
			try{
				if(!"".equals(model.getEnglishTitle()))
				{
					//TODO: TO FIND THE SPARE PART MODEL (spare part content)
					Model spContent = modelRepository.findByCodeAndYearAndMonthAndEnglishTitleAndFileId(model);
					if(null==spContent){
						modelRepository.save(model);						
					}
				}
			}catch(Exception ex){
				System.err.println(value);
				ex.printStackTrace();
			}
			
			//TODO: TO SAVE IMAGE TO THE SERVER AND DATABASE
			FileOutputStream out = null;
			try{
				Image image = new Image();
				image.setFilename(fileNameWithoutExtn+"_"+firstSheet.getSheetName()+"_"+i+"." + ext);
				image.setStatus("1");
				try{
					//TODO: TO FIND THE PREVIOUS CONTENT ID
					previousContentId = modelRepository.findByCodeAndYearAndMonthAndEnglishTitleAndFileId(model).getId();
					image.setContentId(previousContentId);
					if(i==1){
						out  = new FileOutputStream(ROOT+File.separator+fileNameWithoutExtn+"_"+firstSheet.getSheetName()+"_"+i+"." + ext);
						out.write(data);
						out.close();
					}
				}catch(Exception ex){
					image.setContentId(previousContentId);
					//if((i+1)%3==0){
						out  = new FileOutputStream(ROOT+File.separator+fileNameWithoutExtn+"_"+firstSheet.getSheetName()+"_"+i+"." + ext);
						out.write(data);
						out.close(); 
					//}
					image.setUrl(ROOT+File.separator+firstSheet.getSheetName()+"_"+i+"." + ext);
					imageRepository.save(image);
				}
			}catch(Exception ex){
				
			}
			i++;
		}
		System.out.println("=========================== FINISHED ==================================");
	}
	
}
