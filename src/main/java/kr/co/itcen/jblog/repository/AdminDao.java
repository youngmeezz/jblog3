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
	
	//블로그 title, logo 변경하기
	public Boolean update(BlogVo blogvo) {
		
		int result = sqlSession.update("blog.update",blogvo);
		return result == 1;
	}
	
	//파일 업로드 하기
	

	//게시글 정보읽기
	public List<PostVo> getList(Long categoryNo) {
		
	
		List<PostVo> result = sqlSession.selectList("post.selectList",categoryNo);
		return result;
	}

	//게시글 정보 넣기
	public Boolean write(PostVo	postvo) {

		int result = sqlSession.insert("post.insert",postvo);
		return result == 1;
	}
}
