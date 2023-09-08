package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.vo.BookVo;
import bookmall.vo.MemberVo;

public class BookDaoTest {
	public static void main(String[] args) {
		BookVo vo = new BookVo();
		vo.setTitle("JAVA의 정석");
		vo.setPrice(55000L);
		vo.setCategoryName("IT서적");

		selectBookAll();
		insertBook(vo);
		selectBookAll();
		updateBookPrice("JAVA의 정석",49500L);
		selectBookAll();

		/*selectBook();
		deleteBook("신유경","cheerup313@naver.com");
		selectBook();
		updatePW("010-1111-2222","choonsik@gmail.com","chunchun2","cnstlr4321");
		selectBook();*/
	}

	public static void updateBookPrice(String title, long price) {
		boolean updatePrice = new BookDao().updateBookPrice(title,price);
		if(updatePrice) {
			System.out.println(">> 가격 변경 (Update) 실행 : 제목 = '"+title+"'인 책의 가격을 "
					+ price +"원으로 수정");
		}
	}

	public static void selectBookAll() {
		List<BookVo> list = new BookDao().selectBookAll();
		System.out.println("****************Book list***************");
		for (BookVo vo : list) {
			System.out.println("제목 : " + vo.getTitle() 
							+ "\t 가격 : " + vo.getPrice());
		}
		System.out.println("****************************************");
		System.out.println();
	}

	public static void insertBook(BookVo vo) {
		boolean insertTrue =new BookDao().insert(vo);
		if(insertTrue) {
			System.out.println(">> Insert 실행 : 제목 = '" + vo.getTitle() 
			+ "', 가격 = " + vo.getPrice()+"원, 카테고리 = '"+vo.getCategoryName()+"'인 책 추가");
		}
	}
	public static long findBookNo(String title) {
		new BookDao();
		long bookNo = BookDao.findBookNo(title);
		return bookNo;
	}

}