package kr.co.itcen.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;


	//블로그 정보 조회하기
	public BlogVo getBlogInfo(String id) {

		return blogDao.get(id);
	}

	//카테고리 정보 조회하기
	public List<CategoryVo> getList(String id) {
		
		return categoryDao.getList(id);
	}

}
