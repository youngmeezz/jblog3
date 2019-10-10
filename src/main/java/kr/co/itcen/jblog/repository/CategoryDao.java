package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class CategoryDao {

	@Autowired
	SqlSession sqlSession;

	public Boolean insert(UserVo vo) {

		int cnt = sqlSession.insert("category.insert", vo);

		return cnt == 1;
	}



}
