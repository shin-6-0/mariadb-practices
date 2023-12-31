package bookmall.dao.test;

import java.util.List;
import java.util.Map;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.vo.OrderVo;
import bookmall.vo.OrderBookVo;

public class OrderDaoTest {
	public static void main(String[] args) {
		OrderVo orderVo = new OrderVo();
		OrderBookVo oderBookVo = new OrderBookVo();
		/*
		 * 필요기능 
		 * 멤버별 주문 리스트 확인하기
		 * 도서별 전체 주문리스트 (도서번호, 도서제목, 수량)
		 * 멤버별 카트에 있는 책 주문하기
		 * 주문 취소하기
		 * */
		selectMemOrderList("prodo1234@naver.com","vmfheh0101"); //멤버별 확인
		selectBookOrderList(); //책별 확인
		cancelMemOrderList("prodo1234@naver.com","vmfheh0101",34L); //delete기능 (주문취소)
		selectMemOrderList("prodo1234@naver.com","vmfheh0101"); //멤버별 확인
		selectBookOrderList(); //책별 확인
		//orderPersonal("prodo1234@naver.com","vmfheh0101","어린왕자",2L,"서울시 ㅇㅇ구 ㅁㅁ동 123-45");
		//orderPersonal("prodo1234@naver.com","vmfheh0101","책책책",2L,"서울시 ㅇㅇ구 ㅁㅁ동 123-45");
		selectMemOrderList("prodo1234@naver.com","vmfheh0101"); //멤버별 확인
		selectBookOrderList(); //책별 확인
	}

	public static void selectBookOrderList() {
		System.out.println(">> 책 별로 주문 수량을 확인합니다. (주문 전체)");
		System.out.println("************************* 책 별 주문수량 *************************");
		List<Map<String,Object>> list = new OrderDao().selectBookOrderList();
		for(Map<String,Object> m : list) {
			long orderNo = (long) m.get("orderNo");
			long bookNo = (long) m.get("bookNo");
			long quantity = (long) m.get("quantity");
			String bookTitle = BookDao.findBookTitle(bookNo);
			System.out.println("도서 번호 : "+bookNo+"\t도서 제목 : "+bookTitle+"\t 주문 수량 : "+quantity);
		}
		System.out.println("**************************************************************");
		System.out.println();
	}

	public static void orderPersonal(String email, String password, String bookTitle, long bookQuantity1,String bookTitle2,long bookQuantity2,String address) {
		System.out.println(">> email ID : "+email+", password = "+password+"를 입력하여 해당 회원의 장바구니에 있는 책('"+bookTitle+", "+bookTitle2+"')을 주문합니다.");
		String memberName = MemberDao.findMemberName(email,password);
		long memberNo = MemberDao.findMemberNo(email, password);
		long bookNo1 = BookDao.findBookNo(bookTitle);
		long bookNo2 = BookDao.findBookNo(bookTitle2);
		
		//hasBookQuantity -1 이면 수량부족, 0 이면 장바구니에없음, 기타는 주문수량
		long hasBook1 = CartDao.findCartPersonal(memberNo,bookNo1,bookQuantity1);
		long hasBook2 = CartDao.findCartPersonal(memberNo,bookNo2,bookQuantity1);
		
		if(hasBook1>0&&hasBook2>0) {
			long book1Price = BookDao.findBookPrice(bookNo1);
			long book2Price = BookDao.findBookPrice(bookNo2);
			long totalPrice1 = book1Price*hasBook1;
			long totalPrice2 = book2Price*hasBook2;
			boolean insertOrder = new OrderDao().orderPersonal(memberNo, (totalPrice1+totalPrice2), address);
			long findOrderNum = new OrderDao().findOrderNum(memberNo,(totalPrice1+totalPrice2),address);
			boolean insertOB1 = new OrderDao().insertOrderBook(findOrderNum,bookNo1,bookQuantity1);
			boolean insertOB2 = new OrderDao().insertOrderBook(findOrderNum,bookNo2,bookQuantity2);
			
			System.out.println(">> 회원명 '"+memberName+"'님이 "+bookTitle+"을 "+bookQuantity1+"개, "+bookTitle2+"를 "+bookQuantity2+"개 주문하였습니다.");		

		}else if(hasBook1<0||hasBook2<0){
			System.out.println(">> 원하는 구매수량보다 장바구니의 책 개수가 더 적어 구매가 불가능합니다.");			
		}else {
			System.out.println(">> 주문하신 책이 장바구니에 없습니다.");			
		}
			System.out.println();
	}

	public static void cancelMemOrderList(String email, String password, long orderNo) {
		System.out.println(">> email ID : "+email+", password = "+password+"를 입력하여 해당 회원의 주문내역을 취소합니다.");
		String memberName = MemberDao.findMemberName(email,password);
		long memberNo = MemberDao.findMemberNo(email, password);
		boolean deletedChk = new OrderDao().cancelMemOrderList(memberNo,orderNo);
		if(deletedChk) {
			System.out.println(">> email ID : "+email+", password = "+password+"인 회원의 장바구니를 변경합니다.");
			System.out.println(">> "+memberName+"회원의 장바구니 중, 제목이 '"+orderNo+"'인 책을 카트에서 삭제합니다.");
		}else{
			System.out.println(">> 주문 취소가 정상적으로 이루어지지 않았습니다.");
		}
	}

	public static void selectMemOrderList(String email, String password) {
		System.out.println(">> email ID : "+email+", password = "+password+"를 입력하여 해당 회원의 주문내역을 확인합니다.");
		String memberName = MemberDao.findMemberName(email,password);
		long memberNo = MemberDao.findMemberNo(email, password);
		System.out.println("*********************************** 회원명 : "+memberName+"의 주문내역 리스트입니다. ***********************************");
		List<Map<String,Object>> list = new OrderDao().selectMemOrderList(memberNo);
		for(Map<String,Object> m : list) {
			Object orderNo = m.get("orderNo");
			Object totalPrice = m.get("totalPrice");
			Object address = m.get("address");
			
			System.out.println("주문번호 : "+orderNo+"\t주문자 : "+memberName+"("+email+")\t결제금액 : "
							+((Long)totalPrice).intValue()+"\t배송지 : "+address);
		}
		System.out.println("*****************************************************************************************************");
	}


}