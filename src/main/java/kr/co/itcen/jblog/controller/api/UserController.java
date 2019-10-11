package kr.co.itcen.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.dto.JSONResult;
import kr.co.itcen.jblog.service.UserService;

@Controller("userApiController") //id가 필요 bean설정때 id가 필요해가지고 controller이름이 겹치지 않게 해주기 api라는 패키지가 구분해주지만 복잡하게 만들지 말고 이름 같이 쓰고 bean앞에 id 쓰기
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//아이디 중복체크 ajax
	@ResponseBody
	@RequestMapping("/checkid")
	public JSONResult checkid(@RequestParam(value="id", required=true, defaultValue="")String id) {
		Boolean exist = userService.existUser(id);
		return JSONResult.success(exist);

	}
}
