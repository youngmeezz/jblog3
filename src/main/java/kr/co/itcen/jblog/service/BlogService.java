package kr.co.itcen.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
//	@Autowired
//	private PostDao postDao;

	@Autowired
	private CategoryDao categoryDao;
	
	public BlogVo getAll(String id, Long categoryNo, Long postNo ) {
		
		BlogVo blogVo = getBlogInfo( id );
		PostVo postVo = viewPost( postNo );
		List<PostVo> listPostVo = getPostList( categoryNo );
		List<CategoryVo> listCategoryVo = getCategoryList( id );
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put( "blogId", id );
		map.put( "blog", blogVo );
		map.put( "post", postVo );
		map.put( "postList", listPostVo );
		map.put( "categoryList", listCategoryVo );
		map.put( "currentPost", postNo );
		map.put( "currentCategory", categoryNo );

		return (BlogVo) map;
	}

	private List<CategoryVo> getCategoryList(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<PostVo> getPostList(Long categoryNo) {
		// TODO Auto-generated method stub
		return null;
	}

	private PostVo viewPost(Long postNo) {
		// TODO Auto-generated method stub
		return null;
	}

	//블로그 정보 조회하기
	public BlogVo getBlogInfo(String id) {

		return blogDao.get(id);
	}

	//카테고리 정보 조회하기
	public List<CategoryVo> getList(String id) {
		
		return blogDao.getList(id);
	}

}
