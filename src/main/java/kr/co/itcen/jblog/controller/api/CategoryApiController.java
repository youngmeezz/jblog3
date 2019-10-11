package kr.co.itcen.jblog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@RestController
@RequestMapping("/api/{id:(?!assets).*}/admin")
public class CategoryApiController {

	@Autowired
	CategoryService categoryService;
	
	//카테고리 창에서 카테고리 추가하기
	@RequestMapping(value = "/categoryInsert", method = RequestMethod.POST)
	public CategoryVo categoryInsert(@ModelAttribute CategoryVo categoryvo,BindingResult result, Model model,HttpSession session) {

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser != null) {
			categoryvo.setId(authUser.getId());
			categoryService.newInsert(categoryvo);			
		}
		
		return categoryvo;
	}
	
	//카테고리 창에서 카테고리 삭제하기
	@RequestMapping(value = "categoryDelete", method = RequestMethod.POST)
	public CategoryVo categoryDelete(@ModelAttribute CategoryVo categoryvo,BindingResult result, Model model,HttpSession session) {
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser != null) {
			categoryvo.setId(authUser.getId());
			categoryService.delete(categoryvo);			
		}
		
		return categoryvo;
	}
	
	
		
}
