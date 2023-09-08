package bookmall.main;

import bookmall.dao.MemberDao;
import bookmall.dao.test.BookDaoTest;
import bookmall.dao.test.CartDaoTest;
import bookmall.dao.test.CategoryDaoTest;
import bookmall.dao.test.MemberDaoTest;
import bookmall.dao.test.OrderDaoTest;
import bookmall.vo.BookVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;


public class BookMall {
	public static void main(String[] args) {
		/*
		 * MemberDao memberDao = new MemberDao();
		 * 
		 * memberDao.insert(membervoMem1); memberDao.insert(membervoMem2);
		 */
		
		System.out.println("## 회원리스트");
		MemberDaoTest.selectMember();//조회

		//Member 2명 넣기
		MemberVo voMem = new MemberVo();
		voMem.setName("춘식이");
		voMem.setEmail("chun123@naver.com");
		voMem.setPassword("cnstlr123");
		voMem.setPhone("010-1234-4321");
		MemberDaoTest.insertMember(voMem);
		voMem.setName("프로도");
		voMem.setEmail("prodo1234@naver.com");
		voMem.setPassword("vmfheh0101");
		voMem.setPhone("010-9999-5555");
		MemberDaoTest.insertMember(voMem);
		voMem.setName("어피치");
		voMem.setEmail("apeach@gmail.com");
		voMem.setPassword("apeach444");
		voMem.setPhone("010-4444-4444");
		MemberDaoTest.insertMember(voMem);
		MemberDaoTest.selectMember();//조회	
		MemberDaoTest.updatePW("010-1234-4321","chun123@naver.com","cnstlr123","chunchun2");
		MemberDaoTest.selectMember();//조회	
		MemberDaoTest.deleteMember("어피치","apeach@gmail.com");
		MemberDaoTest.selectMember();//조회	
		System.out.println();
		
		
		System.out.println("## 카테고리"); //카테고리 3개 나와야함
		CategoryDaoTest.selectCategoryAll();
		CategoryVo voCategory = new CategoryVo();
		voCategory.setName("IT서적");
		CategoryDaoTest.insertCategory(voCategory);
		voCategory.setName("소설");
		CategoryDaoTest.insertCategory(voCategory);
		voCategory.setName("자서전");
		CategoryDaoTest.insertCategory(voCategory);
		CategoryDaoTest.selectCategoryAll();
		System.out.println();
	
		
		System.out.println("## 상품");
		BookDaoTest.selectBookAll();
		BookVo voBook = new BookVo();
		voBook.setTitle("JAVA의 정석");
		voBook.setPrice(55000L);
		voBook.setCategoryName("IT서적");
		BookDaoTest.insertBook(voBook);
		voBook.setTitle("만델라자서전");
		voBook.setPrice(42000L);
		voBook.setCategoryName("자서전");
		BookDaoTest.insertBook(voBook);
		voBook.setTitle("토지");
		voBook.setPrice(22000L);
		voBook.setCategoryName("소설");
		BookDaoTest.insertBook(voBook);
		System.out.println();
		BookDaoTest.selectBookAll();
		BookDaoTest.updateBookPrice("JAVA의 정석",49500L);
		BookDaoTest.selectBookAll();
	
		
		
		System.out.println("## 카트");
		CartDaoTest.selectCartPersonal("prodo1234@naver.com", "vmfheh0101");
		CartDaoTest.insertCartPersonal("prodo1234@naver.com","vmfheh0101","만델라자서전",2L);
		CartDaoTest.insertCartPersonal("prodo1234@naver.com","vmfheh0101","토지",2L);
		CartDaoTest.insertCartPersonal("prodo1234@naver.com","vmfheh0101","JAVA의 정석",2L);
		CartDaoTest.selectCartPersonal("prodo1234@naver.com", "vmfheh0101");
		CartDaoTest.updateCartEach("prodo1234@naver.com","vmfheh0101","만델라자서전",1L);
		CartDaoTest.deleteCartIndividual("prodo1234@naver.com","vmfheh0101","만델라자서전");
		CartDaoTest.selectCartPersonal("prodo1234@naver.com", "vmfheh0101");
		System.out.println();
		
		
		
		System.out.println("## 주문 리스트");
		OrderDaoTest.selectMemOrderList("prodo1234@naver.com","vmfheh0101"); //멤버별 확인
		OrderDaoTest.selectBookOrderList();
		//OrderDaoTest.orderPersonal("prodo1234@naver.com","vmfheh0101","JAVA의 정석",1L,"토지",1L,"서울시 ㅇㅇ구 ㅁㅁ동 123-45");
		
		
		
	}
}
