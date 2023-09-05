package emailist.dao.test;

import java.util.List;

import emailist.dao.EmaillistDao;
import emailist.vo.EmaillistVo;

public class EmaillistDaoTest {

	public static void main(String[] args) {
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("둘");
		vo.setLastName("리001");
		vo.setEmail("dooly001@gmail.com");
		
		testInsert(vo);
		testFindAll();
		testDeleteByEmail("dooly001@gmail.com");
		testFindAll();
		
	}

	private static void testInsert(EmaillistVo vo) {
		new EmaillistDao().insert(vo);
		
	}

	private static void testDeleteByEmail(String email) {
		new EmaillistDao().deleteByEmail(email);
		
	}

	private static void testFindAll() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		System.out.println("*********목록 print********");
		for(EmaillistVo vo : list) {
			System.out.println(vo);
		}
	}

}
