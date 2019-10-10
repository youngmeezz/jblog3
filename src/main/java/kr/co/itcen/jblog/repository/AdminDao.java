package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;

@Repository
public class AdminDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean update(BlogVo blogvo) {
		
		int result = sqlSession.update("blog.update",blogvo);
		return result == 1;
	}
}
