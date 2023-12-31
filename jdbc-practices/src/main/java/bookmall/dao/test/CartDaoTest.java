package bookmall.dao.test;

import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.OrderBookVo;

import java.util.List;
import java.util.Map;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.MemberDao;

public class CartDaoTest {
	public static void main(String[] args) {
		CartVo vo = new CartVo();

		selectCartPersonal("prodo1234@naver.com", "vmfheh0101");
		
		 updateCartEach("prodo1234@naver.com","vmfheh0101","만델라 자서전",1L);
		 selectCartPersonal("prodo1234@naver.com","vmfheh0101");
		 deleteCartIndividual("prodo1234@naver.com","vmfheh0101","만델라 자서전");
		 selectCartPersonal("prodo1234@naver.com","vmfheh0101");
		 insertCartPersonal("prodo1234@naver.com","vmfheh0101","스티브 잡스",2L);
		 selectCartPersonal("prodo1234@naver.com","vmfheh0101");
		 insertCartPersonal("prodo1234@naver.com","vmfheh0101","어린왕자",1L);
		 selectCartPersonal("prodo1234@naver.com","vmfheh0101");
		 
	}

	public static void insertCartPersonal(String email, String password, String title, long quantity) {
		String memberName = MemberDao.findMemberName(email, password);
		long memberNo = MemberDao.findMemberNo(email, password);
		long bookNo = BookDao.findBookNo(title);
		boolean insertedChk = new CartDao().insertCartEach(memberNo, bookNo, quantity);
		if (insertedChk) {
			System.out.println(">> email ID : " + email + ", password = " + password + "인 회원의 장바구니를 변경합니다.");
			System.out.println(">> "+memberName+" 회원의 장바구니에 제목이 '" + title + "'인 책을 " + quantity + "개 추가합니다.");
		}
	}

	public static void deleteCartIndividual(String email, String password, String title) {
		String memberName = MemberDao.findMemberName(email, password);
		long memberNo = MemberDao.findMemberNo(email, password);
		long bookNo = BookDao.findBookNo(title);
		boolean deletedChk = new CartDao().deleteCartEach(memberNo, bookNo);
		if (deletedChk) {
			System.out.println(">> " + memberName + "회원의 장바구니 중, 제목이 '" + title + "'인 책을 카트에서 삭제합니다.");
		}
	}

	public static void updateCartEach(String email, String password, String title, long quantity) {
		String memberName = MemberDao.findMemberName(email, password);
		long memberNo = MemberDao.findMemberNo(email, password);
		long bookNo = BookDao.findBookNo(title);

		boolean updatedChk = new CartDao().updateCartEach(memberNo, bookNo, quantity);
		if (updatedChk) {
			System.out.println(">> email ID : " + email + ", password = " + password + "인 회원의 장바구니를 변경합니다.");
			System.out
					.println(">> " + memberName + "회원의 장바구니 중, 제목이 '" + title + "'인 책의 수량을 " + quantity + "개 로 수정합니다.");
		}
	}

	public static void selectCartPersonal(String email, String password) {
		System.out.println(">> email ID : " + email + ", password = " + password + "를 입력하여 해당 회원의 장바구니를 확인합니다.");
		String memberName = MemberDao.findMemberName(email, password);// memberDao에서 memberNo가져오기
		long memberNo = MemberDao.findMemberNo(email, password);
		List<Map<String, Long>> list = new CartDao().selectCartPersonal(memberNo);
		System.out.println("****************** 회원명 : " + memberName + "의 카트 리스트입니다. ******************");
		long totalPrice = 0L;
		for (Map<String, Long> m : list) {
			long quantity = m.get("quantity");
			long bookNo = m.get("bookNo");
			long price = BookDao.findBookPrice(bookNo);

			System.out.println("제목 : " + BookDao.findBookTitle(bookNo) + "\t총 수량 : " + Long.valueOf(quantity).intValue()
					+ "\t개당가격 : " + Long.valueOf(price).intValue() + "\t책별 가격 : " + (int) (price * quantity) + "원");
			totalPrice += (int) (price * quantity);
		}
		System.out.println("\t\t\t\t\t\t 총 가격 : " + totalPrice + "원");
		System.out.println("****************************************************************");
		System.out.println();
	}
}