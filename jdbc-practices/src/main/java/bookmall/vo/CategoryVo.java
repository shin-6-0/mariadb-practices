package bookmall.vo;

public class CategoryVo {
	private long no;
	private String name;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + "]";
	}
	
	
}
