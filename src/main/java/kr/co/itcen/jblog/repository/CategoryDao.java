package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	public Boolean newInsert(CategoryVo categoryvo) {

		int cnt = sqlSession.insert("category.newInsert", categoryvo);

		return cnt == 1;
	}

	public Boolean delete(CategoryVo categoryvo) {

		int cnt = sqlSession.delete("category.delete", categoryvo);

		return cnt == 1;
	}

	public List<CategoryVo> getList(String id) {
	
		List<CategoryVo> list = sqlSession.selectList("category.listByID", id);
		return list;
	}
	//카테고리조회하기
	public List<CategoryVo> getList(CategoryVo categoryvo) {
			
		List<CategoryVo> list = sqlSession.selectList("category.getList", categoryvo);
		return list;
	}

}
