package kh.com.kshrd.models;
import java.io.Serializable;

public class Model implements Serializable{

	private Long id;
	private String logoBrand;
	private String koreanTitle;
	private String englishTitle;
	private String year;
	private String month;
	private String code;
	private Long parentId;
	private Long fileId;
	
	public Model() {}
	
	public Model(Long id, String logoBrand, String koreanTitle, String englishTitle, String year, String month,
			String code, Long parentId, Long fileId) {
		super();
		this.id = id;
		this.logoBrand = logoBrand;
		this.koreanTitle = koreanTitle;
		this.englishTitle = englishTitle;
		this.year = year;
		this.month = month;
		this.code = code;
		this.parentId = parentId;
		this.fileId = fileId;
	}
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
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Model [id=" + id + ", logoBrand=" + logoBrand + ", koreanTitle=" + koreanTitle + ", englishTitle="
				+ englishTitle + ", year=" + year + ", month=" + month + ", code=" + code + ", parentId=" + parentId
				+ ", fileId=" + fileId + "]";
	}

}
