package bookmall.vo;

public class OrderBookVo {
	private long ordersNo;
	private long quantity;
	private long bookNo;
	public long getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(long ordersNo) {
		this.ordersNo = ordersNo;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}
	@Override
	public String toString() {
		return "OrderBookVo [ordersNum=" + ordersNum + ", quantity=" + quantity + ", bookNo=" + bookNo + "]";
	}
	
}
