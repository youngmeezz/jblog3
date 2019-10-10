package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class AdminDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean update(BlogVo blogvo) {
		
		int result = sqlSession.update("blog.update",blogvo);
		return result == 1;
	}

	public List<PostVo> write(BlogVo blogvo) {
		
		List<PostVo> result = sqlSession.selectList("post.selectList",blogvo);
		return result;
	}

	//게시글 정보 넣기
//	public Boolean write(BlogVo blogvo) {
//
//		int result = sqlSession.write("post.write",blogvo);
//		return result == 1;
//	}
}
