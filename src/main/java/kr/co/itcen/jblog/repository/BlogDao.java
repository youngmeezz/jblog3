package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class BlogDao {

	
	@Autowired
	SqlSession sqlSession;
	
	public Boolean insert(UserVo vo) {
		
		int cnt = sqlSession.insert("blog.insert", vo);
		
		return cnt==1; 
	}

	public List<CategoryVo> getList(String id) {
		
		List<CategoryVo> result = sqlSession.selectList("category.getList",id);
		System.out.println(id);
		return result;
	}
	
}
