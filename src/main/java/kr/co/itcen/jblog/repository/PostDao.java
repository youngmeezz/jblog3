package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	SqlSession sqlSession;
	
	public Boolean insert(PostVo postvo) {
		
		int cnt = sqlSession.insert("post.insert", postvo);
		
		return cnt==1; 
	}
}
