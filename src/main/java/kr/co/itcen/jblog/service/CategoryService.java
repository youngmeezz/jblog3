package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	//카테고리 삽입
	public void newInsert(CategoryVo categoryvo) {

			categoryDao.newInsert(categoryvo);
		}
	
	
	//카테고리 삭제
	public void delete(CategoryVo categoryvo) {

			categoryDao.delete(categoryvo);
		}
	
}
