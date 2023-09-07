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
		  
		selectMemOrderList("prodo1234@naver.com","vmfheh0101"); //멤버별 확인
		//cancelMemOrderList("prodo1234@naver.com","vmfheh0101",1L); //delete기능 (주문취소)
		//orderPersonal("prodo1234@naver.com")
	}

	private static void cancelMemOrderList(String email, String password, long orderNo) {
		System.out.println(">> email ID : "+email+", password = "+password+"를 입력하여 해당 회원의 주문내역을 취소합니다.");
		String memberName = MemberDao.findMemberName(email,password);//memberDao에서 memberNo가져오기
		long memberNo = MemberDao.findMemberNo(email, password);
		boolean deletedChk = new OrderDao().cancelMemOrderList(memberNo,orderNo);
		if(deletedChk) {
			System.out.println(">> email ID : "+email+", password = "+password+"인 회원의 장바구니를 변경합니다.");
			System.out.println(">> "+memberName+"회원의 장바구니 중, 제목이 '"+orderNo+"'인 책을 카트에서 삭제합니다.");
		}
	}

	private static void selectMemOrderList(String email, String password) {
		System.out.println(">> email ID : "+email+", password = "+password+"를 입력하여 해당 회원의 주문내역을 확인합니다.");
		String memberName = MemberDao.findMemberName(email,password);//memberDao에서 memberNo가져오기
		long memberNo = MemberDao.findMemberNo(email, password);
		System.out.println("*************************** 회원명 : "+memberName+"의 주문내역 리스트입니다. ***************************");
		List<Map<String,Object>> list = new OrderDao().selectMemOrderList(memberNo);
		for(Map<String,Object> m : list) {
			Object orderNo = m.get("orderNo");
			Object totalPrice = m.get("totalPrice");
			Object address = m.get("address");
			
			System.out.println("주문자 : "+memberName+"\t주문번호 : "+orderNo+"\t결제금액 : "
							+((Long)totalPrice).intValue()+"\t배송지 : "+address);
		}
		System.out.println("*************************************************************************************");
	}


}