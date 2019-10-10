package kr.co.itcen.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.jblog.service.AdminService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}/admin")
public class AdminController {

		@Autowired
		private AdminService adminService;
		
		//블로그 관리 창으로 들어가기
		@RequestMapping(value = "basic", method = RequestMethod.GET)
		public String basic(@ModelAttribute UserVo vo) {
			return "blog/blog-admin-basic";
		}
		
		//블로그 관리창에서 제목이랑 사진 수정하기 (보류중)
		@RequestMapping(value= "modify", method = RequestMethod.POST)
		public String modify(@ModelAttribute BlogVo blogvo, BindingResult result, Model model, HttpSession session) {
			
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			if( result.hasErrors() ) {
				model.addAllAttributes(result.getModel());
				return "blog/modify";
			}
			
			if (authUser != null) {
				blogvo.setId(authUser.getId());
				adminService.update(blogvo);
			}
			
			return "redirect:/" + authUser.getId();
		}
		
		//블로그 관리 창에서 카테고리 관리 창으로 들어가기
		@RequestMapping(value = "category", method = RequestMethod.GET)
		public String category(@ModelAttribute UserVo vo) {
			return "blog/blog-admin-category";
		}
		
		
		//블로그 관리 창에서 글작성 창으로 들어가기
		@RequestMapping(value = "writeform", method = RequestMethod.GET)
		public String writeform(@ModelAttribute UserVo vo) {
			return "blog/blog-admin-write";
		}
		
		//글작성 창에서 글 쓰기
		@RequestMapping(value = "write", method = RequestMethod.POST)
		public String write(@ModelAttribute BlogVo blogvo,BindingResult result, Model model,HttpSession session) {
			
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			if( result.hasErrors() ) {
				model.addAllAttributes(result.getModel());
				return "blog/write";
			}
			
			if (authUser != null) {
				blogvo.setId(authUser.getId());
				adminService.write(blogvo);
			}
			
			return "redirect:/" + authUser.getId();
		}
		
		
}
