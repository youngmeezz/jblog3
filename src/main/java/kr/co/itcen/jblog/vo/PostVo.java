package kr.co.itcen.jblog.vo;

public class PostVo {

	private Long postNo;
	private String title;
	private String content;
	private String regDate;
	private Long categoryNo;
	
	
	public Long getPostNo() {
		return postNo;
	}
	public void setPostNo(Long postNo) {
		this.postNo = postNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", title=" + title + ", content=" + content + ", regDate=" + regDate
				+ ", categoryNo=" + categoryNo + "]";
	}
	
}
