package bookmall.dao.test;

public class CategoryDaoTest {
	public static void main(String[] args) {
		insertTest();
		findAllTest();
	}

	private static void insertTest() {
		BookDao dao = new BookDao()
		
		dao.insert(bookVo);
		dao.insert(bookVo);
	}
}