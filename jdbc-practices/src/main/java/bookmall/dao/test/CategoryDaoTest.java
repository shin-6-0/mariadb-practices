package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {
	public static void main(String[] args) {
		CategoryVo vo = new CategoryVo();
		vo.setName("자서전");
		
		selectCategoryAll();
		//insertCategory(vo);
		selectCategoryAll();
		//deleteCategory("자서전");
		//selectCategory();
		//selectCategoryOne(1L);
	}

	private static void deleteCategory(String categoryName) {
		System.out.println(">> Delete 실행 : name = '"+categoryName+"'인 카테고리");
		new CategoryDao().delete(categoryName);
	}

	private static void selectCategoryAll() {
		List<CategoryVo> list = new CategoryDao().selectCategoryAll();
		System.out.println("*********Category list********");
		for(CategoryVo vo : list) {
			System.out.println("No : "+vo.getNo()+"\t 이름 : "+vo.getName());
		}
		System.out.println("******************************");
	}

	private static void insertCategory(CategoryVo vo) {
		new CategoryDao().insert(vo);
		System.out.println(">> Insert 실행 : name = '"+vo.getName()+"'인 카테고리");
	}
}