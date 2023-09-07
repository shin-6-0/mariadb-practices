package bookmall.vo;

public class OrderVo {
	private long no;
	private long memNo;
	private long totalPrice;
	private String address;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getMemNo() {
		return memNo;
	}
	public void setMemNo(long memNo) {
		this.memNo = memNo;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", memNo=" + memNo + ", totalPrice=" + totalPrice + ", address=" + address + "]";
	}

	
	

}
