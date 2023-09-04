package bookshop.vo;

public class BookVo {
	private int no;
	private String title;
	private String rent;
	private int author_no;
	private String name;
	public BookVo () {}
	public BookVo(int no, String title, String rent, int author_no, String name) {
		super();
		this.no = no;
		this.title = title;
		this.rent = rent;
		this.author_no = author_no;
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public int getAuthor_no() {
		return author_no;
	}
	public void setAuthor_no(int author_no) {
		this.author_no = author_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
