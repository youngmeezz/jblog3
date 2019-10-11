package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.itcen.jblog.service.AdminService;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;


@Controller
@RequestMapping("/{id:(?!assets)(?!images).*}")

public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private AdminService adminService;
	
	//1./id 
	//2./id/categoryNo 
	//3./id/categoryNo/postNo

	@RequestMapping( {"", "/{pathNo1}", "/{pathNo1}/{pathNo2}" } )
	public String index( 
		@ModelAttribute @PathVariable String id,
		@PathVariable Optional<Long> pathNo1,
		@PathVariable Optional<Long> pathNo2,
		Model model ) {

		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if( pathNo2.isPresent() ) {
			
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		
		} else if( pathNo1.isPresent() ){
			
			categoryNo = pathNo1.get();
	
		}
		
		//카테고리 정보 조회하기
		List<CategoryVo> list = blogService.getList(id);
		//System.out.println(list.size());
		model.addAttribute("list",list);
		
		//블로그 업데이트 한 거 조회하기(title, logo)
		BlogVo blogvo = blogService.getBlogInfo(id);
		model.addAttribute("blogvo",blogvo);
		
		//게시글 리스트 조회하기
		List<PostVo> p_list = adminService.getList(categoryNo);
		model.addAttribute("p_list",p_list);


		return "blog/blog-main";
	}
	

}
