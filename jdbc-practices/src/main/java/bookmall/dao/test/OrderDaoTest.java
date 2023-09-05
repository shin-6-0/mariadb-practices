package bookmall.dao.test;

import bookmall.dao.BookDao;
import bookmall.vo.OrderVo;
import bookmall.vo.OrderBookVo;

public class OrderDaoTest {
	public static void main(String[] args) {
		OrderVo orderVo = new OrderVo();
		OrderBookVo oderBookVo = new OrderBookVo();
		  
		selectOrderList("email","password");

	}

	private static void insertBook() {
		BookDao dao = new BookDao();
		
		dao.insert(bookVo);
		dao.insert(bookVo);
	}
}