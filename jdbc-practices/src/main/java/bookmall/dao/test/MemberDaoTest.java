package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.MemberDao;
import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		MemberVo vo = new MemberVo();
		vo.setName("신유경");
		vo.setEmail("cheerup313@naver.com");
		vo.setPassword("shinyukyung313");
		vo.setPhone("010-1234-4321");

		selectMember();
		insertMember(vo);
		selectMember();
		deleteMember("신유경","cheerup313@naver.com");
		selectMember();
		updatePW("010-1111-2222","choonsik@gmail.com","chunchun2","cnstlr4321");
		selectMember();
		/*
		 * insertMember(vo); selectMember(); deleteMember("춘식이","choonsik@gmail.com");
		 * selectMember();
		 */
	}

	private static void updatePW(String phone, String email,String pwBefore, String pwAfter) {
		System.out.println(">> 회원비밀번호 변경(Update) 실행 : email이 '" + email + "인 회원의 비밀번호를 '" + pwBefore + "'에서 "+pwAfter+"로 변경");
		new MemberDao().updatePW(phone,email,pwBefore,pwAfter);
	}

	private static void deleteMember(String MemberName, String email) {
		System.out.println(">> 회원탈퇴(Delete) 실행 : name = '" + MemberName + ", email = '" + email + "'인 멤버");
		new MemberDao().delete(MemberName,email);
	}

	private static void selectMember() {
		List<MemberVo> list = new MemberDao().select();
		System.out.println("********************Member list*******************");
		for (MemberVo vo : list) {
			System.out.println("No : " + vo.getNo() + "\t 이름 : " + vo.getName() + "\t 연락처 : " + vo.getPhone()
					+ "\t 이메일 : " + vo.getEmail() + "\t 비밀번호 : " + vo.getPassword());
		}
		System.out.println("**************************************************");
	}

	private static void insertMember(MemberVo vo) {
		new MemberDao().insert(vo);
		System.out.println(">> 회원가입(Insert) 실행 : 이름 = " + vo.getName() + ", 연락처 : " + vo.getPhone()
			+ ", 이메일 : " + vo.getEmail() + ", 비밀번호 : " + vo.getPassword()+ "인 멤버 추가");
	}
	
	public static String findMemberName(String email,String password) {
		String memerName =new MemberDao().findMemberName(email,password);
		return memerName;
	}
	
	public static long findMemberNo(String email,String password) {
		long memberNo =new MemberDao().findMemberNo(email,password);
		return memberNo;
	}
}