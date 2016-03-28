package kh.com.kshrd.models;

import java.io.Serializable;

public class Part implements Serializable{

	private Long id;
	private String no;
	private String partNo;
	private String koreanDescription;
	private String englishDescription;
	private String quantity;
	private String remarks;
	private String code;
	private Long contentId;
	
	public Part(String no, String partNo, String koreanDescription, String englishDescription, String quantity,
			String remarks, String code, Long contentId) {
		super();
		this.no = no;
		this.partNo = partNo;
		this.koreanDescription = koreanDescription;
		this.englishDescription = englishDescription;
		this.quantity = quantity;
		this.remarks = remarks;
		this.code = code;
		this.contentId = contentId;
	}
	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * @return the partNo
	 */
	public String getPartNo() {
		return partNo;
	}
	/**
	 * @param partNo the partNo to set
	 */
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	/**
	 * @return the koreanDescription
	 */
	public String getKoreanDescription() {
		return koreanDescription;
	}
	/**
	 * @param koreanDescription the koreanDescription to set
	 */
	public void setKoreanDescription(String koreanDescription) {
		this.koreanDescription = koreanDescription;
	}
	/**
	 * @return the englishDescription
	 */
	public String getEnglishDescription() {
		return englishDescription;
	}
	/**
	 * @param englishDescription the englishDescription to set
	 */
	public void setEnglishDescription(String englishDescription) {
		this.englishDescription = englishDescription;
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
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the contentId
	 */
	public Long getContentId() {
		return contentId;
	}
	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Description [no=");
		builder.append(no);
		builder.append(", partNo=");
		builder.append(partNo);
		builder.append(", koreanDescription=");
		builder.append(koreanDescription);
		builder.append(", englishDescription=");
		builder.append(englishDescription);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", remarks=");
		builder.append(remarks);
		builder.append(", code=");
		builder.append(code);
		builder.append(", contentId=");
		builder.append(contentId);
		builder.append("]");
		return builder.toString();
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
	
}
