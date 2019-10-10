package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.AdminDao;
import kr.co.itcen.jblog.vo.BlogVo;

@Service
public class AdminService {

	@Autowired
	AdminDao adminDao;
	public void update(BlogVo blogvo) {
		adminDao.update(blogvo);
	}
	
	public void write(BlogVo blogvo) {
		adminDao.write(blogvo);
	}
}
