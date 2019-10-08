package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//회원 가입 정보 insert
	public void join(UserVo vo) {
			userDao.insert(vo);
	}
	
	//회원 가입한 유저 정보 가져오기 이걸로 로그인 확인
	public UserVo getUser(UserVo vo) {
		return userDao.get(vo);
	}

	public Boolean existUser(String id) {
		return userDao.get(id) != null;
	}
	

}
