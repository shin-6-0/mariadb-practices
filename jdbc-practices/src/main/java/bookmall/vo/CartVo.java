package bookmall.vo;

public class CartVo {
	private long no;
	private long bookNo;
	private long memNo;
	private long quantity;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}
	public long getMemNo() {
		return memNo;
	}
	public void setMemNo(long memNo) {
		this.memNo = memNo;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartVo [no=" + no + ", bookNo=" + bookNo + ", memNo=" + memNo + ", quantity=" + quantity + "]";
	}
	
	
}
