package kr.co.itcen.jblog.repository;

import java.util.List;

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
	public PostVo get(Long postNo) {
		// TODO Auto-generated method stub
		PostVo vo = sqlSession.selectOne("post.select",postNo);
		return vo;
	}
}
