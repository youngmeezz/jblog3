package kr.co.itcen.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.service.AdminService;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}/admin")
public class AdminController {

		@Autowired
		private AdminService adminService;
		@Autowired
		private BlogService blogService;
		
		
		//블로그 관리 창으로 들어가기
		@RequestMapping(value = "/basic", method = RequestMethod.GET)
		public String basic(Model model,@PathVariable String id) {
			BlogVo vo = blogService.getBlogInfo(id);
			model.addAttribute("blogvo",vo);
			return "blog/blog-admin-basic";
		}
		
		//블로그 관리창에서 제목이랑 사진 수정하기
		@RequestMapping(value = "/modify", method = RequestMethod.POST)
		public String blogmanage(@RequestParam(value = "logo-file",required = false) MultipartFile multipartFile,
				@PathVariable String id,
				BlogVo vo) {
			vo.setId(id);
			adminService.restore(multipartFile,vo);
			return "redirect:/"+id;
		}
		
		//카테고리 창에서 카테고리 조회하기
		@RequestMapping(value = "/categoryform", method = RequestMethod.GET)
		public String categorySelect(@PathVariable String id, Model model) {
				CategoryVo categoryvo = new CategoryVo();
				categoryvo.setId(id);
				
				//카테고리 관리창에서 정보 조회하기
				List<CategoryVo> categorylist = adminService.select(categoryvo);
				model.addAttribute("categorylist", categorylist);
			
			return "/blog/blog-admin-category";
		} 
		
		
		//블로그 관리 창에서 글작성 창으로 들어가기
		@RequestMapping(value = "/writeform", method = RequestMethod.GET)
		public String writeform(@PathVariable String id, Model model) {
			
			List<CategoryVo> categoryList = blogService.getList(id);
			model.addAttribute("list", categoryList);
			
			return "blog/blog-admin-write";
		}
		
		//글작성 창에서 글 쓰기
		@RequestMapping(value = "/write", method = RequestMethod.GET)
		public String write(@ModelAttribute PostVo postvo,BindingResult result, Model model,HttpSession session) {
						//@RequestParam(value=jsp단에서 name="")
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			if( result.hasErrors() ) {
				model.addAllAttributes(result.getModel());
				return "blog/write";
			}
			
			if (authUser != null) {
//				postvo.setCategoryNo(postvo.getCategoryNo());
				adminService.write(postvo);
			}
			
			return "redirect:/" + authUser.getId();
		}

}
