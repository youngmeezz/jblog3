package kr.co.itcen.jblog.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;


@Controller
@RequestMapping("/{id:(?!assets).*}")

public class BlogController {

	@Autowired
	private BlogService blogService;
	
	//1./id 
	//2./id/categoryNo 
	//3./id/categoryNo/postNo

	@RequestMapping( {"", "/{pathNo1}", "/{pathNo1}/{pathNo2}" } )
	public String index( 
		@PathVariable String id,
		@PathVariable Optional<Long> pathNo1,
		@PathVariable Optional<Long> pathNo2,
		Model model ) {

		Long categoryNo = 0L;
		Long postNo = 0L;
		
		//카테고리 정보 조회하기
		List<CategoryVo> list = blogService.getList(id);
		//System.out.println(list.size());
		model.addAttribute("list",list);
		
		//블로그 업데이트 한 거 조회하기
		BlogVo blogvo = blogService.getBlogInfo(id);
		model.addAttribute("blogvo",blogvo);
		
		
		
		if( pathNo2.isPresent() ) {
			
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		
		} else if( pathNo1.isPresent() ){
			
			categoryNo = pathNo1.get();
	
		} else {
			
			
		}

		//modelMap.putAll( blogService.getAll( id, categoryNo, postNo ) );
		return "blog/blog-main";
	}
	
//	@ResponseBody
//	@RequestMapping( "/admin/basic" )
//	public String adminBasic( @PathVariable String id ) {
//		return "id:" + id;
//	}
}
