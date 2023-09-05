package emailist.main;

import java.util.List;
import java.util.Scanner;

import emailist.dao.EmaillistDao;
import emailist.vo.EmaillistVo;

public class EmaillistApp {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while(true) {
			System.out.print("((l)ist (i)nsert (d)elete (q)uit) >");
			String command = sc.nextLine();
			if("l".equals(command)) {
				doList();
			}else if("i".equals(command)) {
				doInsert();
			}else if("d".equals(command)) {
				doDelete();
			}else if("q".equals(command)) {
				System.out.println("종료합니다.");
				break;
			}
		}
	}

	private static void doDelete() {
		System.out.print("삭제할 이메일을 입력하세요 > ");
		String email=sc.nextLine();
		new EmaillistDao().deleteByEmail(email);
		
		doList();
	}

	private static void doInsert() {
		System.out.print("성 입력 >");
		String firstName=sc.nextLine();
		System.out.print("이름 입력 >");
		String lastName=sc.nextLine();
		System.out.print("이메일 입력 >");
		String email=sc.nextLine();
		
		EmaillistVo vo=new EmaillistVo();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);
		
		new EmaillistDao().insert(vo);
		doList();
	}

	private static void doList() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		System.out.println("**************목록 print*************");
		for(EmaillistVo vo : list) {
			System.out.println("이름 : "+vo.getFirstName()+vo.getLastName()+", 이메일 : "+vo.getEmail());
		}
		System.out.println("************************************");

	}

}
