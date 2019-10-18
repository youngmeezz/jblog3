package kr.co.itcen.jblog.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	//회원 가입 만들기( join / joinsuccess )
	@Autowired
	private UserService userService;
	
	
	//회원 가입 했을때 GET해서 뿌려줄 데이터 값 url 매핑 (회원 가입 실행하기)
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		
		return "user/join";
	}
	
	//회원 가입 할때 POST해서 데이터 입력해줄 url 매핑 (회원 가입 폼 만들기)
	@RequestMapping(value = "/join" , method = RequestMethod.POST)
	public String join(@ModelAttribute("userVo") @Valid UserVo vo,
			BindingResult result,
			Model model) {
		
		if( result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	//회원가입 성공
	@RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	//로그인 폼 들어가기
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
	
		return "user/login";
	}
	
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserVo vo, HttpSession session, Model model) {

		UserVo userVo = userService.getUser(vo);
		if (userVo == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}
		// 로그인 처리
		session.setAttribute("authUser", userVo);
		return "redirect:/";
	}

	
	// 로그아웃 -> interceptor 처리하기
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// 접근 제어(ACL)
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser != null) {
			session.removeAttribute("authUser");
			session.invalidate();
		}
		return "redirect:/";
	}	

	
}