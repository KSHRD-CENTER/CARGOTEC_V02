package kh.com.kshrd.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFPicture;

public class Content implements Serializable{

	private Long id;
	private String logoBrand;
	private String koreanTitle;
	private String englishTitle;
	private String date;
	private String code;
	private HSSFPicture hssfPicture;
	private int pictureIndex;
	private String filename;
	private int row;
	private int column;
	private String description;
	private Long fileId;
	private List<Part> descriptions = new ArrayList<Part>();
	private List<Image> images = new ArrayList<Image>();
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the logoBrand
	 */
	public String getLogoBrand() {
		return logoBrand;
	}
	/**
	 * @param logoBrand the logoBrand to set
	 */
	public void setLogoBrand(String logoBrand) {
		this.logoBrand = logoBrand;
	}
	/**
	 * @return the koreanTitle
	 */
	public String getKoreanTitle() {
		return koreanTitle;
	}
	/**
	 * @param koreanTitle the koreanTitle to set
	 */
	public void setKoreanTitle(String koreanTitle) {
		this.koreanTitle = koreanTitle;
	}
	/**
	 * @return the englishTitle
	 */
	public String getEnglishTitle() {
		return englishTitle;
	}
	/**
	 * @param englishTitle the englishTitle to set
	 */
	public void setEnglishTitle(String englishTitle) {
		this.englishTitle = englishTitle;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the hssfPicture
	 */
	public HSSFPicture getHssfPicture() {
		return hssfPicture;
	}
	/**
	 * @param hssfPicture the hssfPicture to set
	 */
	public void setHssfPicture(HSSFPicture hssfPicture) {
		this.hssfPicture = hssfPicture;
	}
	/**
	 * @return the pictureIndex
	 */
	public int getPictureIndex() {
		return pictureIndex;
	}
	/**
	 * @param pictureIndex the pictureIndex to set
	 */
	public void setPictureIndex(int pictureIndex) {
		this.pictureIndex = pictureIndex;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the descriptions
	 */
	public List<Part> getDescriptions() {
		return descriptions;
	}
	/**
	 * @param descriptions the descriptions to set
	 */
	public void setDescriptions(List<Part> descriptions) {
		this.descriptions = descriptions;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
}
