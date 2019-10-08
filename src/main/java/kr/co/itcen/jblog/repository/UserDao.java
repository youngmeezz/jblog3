package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//회원 정보 디비 삽입하기
	public Boolean insert(UserVo vo) {
	
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;

	}

	//회원 정보 아이디로 get 꺼내오기 이게 로그인 하는 건 줄 알았는데 그게 아니고, id중복 체크할 떄 사용
	public UserVo get(String id) {
		UserVo result = sqlSession.selectOne("user.getById",id);
		return result;
	}

	//회원 정보 로그인
	public UserVo get(UserVo vo) {
		UserVo result = sqlSession.selectOne("user.getIdAndPassword",vo);
		return result;
	}


}
