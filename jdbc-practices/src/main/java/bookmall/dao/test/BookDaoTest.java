package bookmall.dao.test;

import bookmall.dao.BookDao;

public class BookDaoTest {
	public static void main(String[] args) {
		insertTest();
		findAllTest();
	}

	private static void insertTest() {
		BookDao dao = new BookDao();
		
		dao.insert(bookVo);
		dao.insert(bookVo);
	}
}