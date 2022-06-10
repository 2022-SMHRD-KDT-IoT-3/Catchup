package kr.catchup.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.catchup.mapper.UserMapper;
import kr.catchup.mapper.UserVo;

@Controller
public class UserContloller {

	@Inject
	private UserMapper mapper;

	// app/login.do를 요청했을 때 실행되는 메소드
	@RequestMapping("/login.do")
	public void login(UserVo vo, HttpServletResponse response) {
		System.out.println("-login.do");
		// Responsebody 대신 gson + out객체 사용

		// System.out.println("vo : "+vo);
		UserVo info = mapper.loginSelect(vo);
		System.out.println("[login] + " + info);
		Gson gson = new Gson();
		String value = gson.toJson(info);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(value);

	}

	// app/loginSelect.do를 요청했을 때 실행되는 메소드
	@RequestMapping("/loginSelect.do")
	public UserVo loginSelect(UserVo vo) {

		System.out.println("-loginSelect.do");
		System.out.println(vo.toString());
		UserVo info = mapper.loginSelect(vo);

		System.out.println("loginSelect " + info); // info 세션정보가 출력된다

		return info;
	}

	// app/join.do를 요청했을 때 실행되는 메소드
	@RequestMapping("/join.do")
	public void join() {
		System.out.println("-join.do");

	}

	// app/joinInsert.do를 요청했을 때 실행되는 메소드
	@RequestMapping("/joinInsert.do")
	public String joinInsert(UserVo vo) {
		mapper.joinInsert(vo);
		System.out.println("회원가입기능 요청");

		return "redirect:/login.do";
	}

	// app/idCheck.do를 요청했을때 실행되는 메소드
	@RequestMapping("/idCheck.do")
	public void idCheck(String user_id, HttpServletResponse response) {
		System.out.println("[중복확인 진입]" + user_id);
		UserVo vo = mapper.idCheck(user_id);

		Gson gson = new Gson();
		String value = gson.toJson(vo);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(value);

		System.out.println("[중복확인 이탈]" + vo);

	}

	// app/findId.do를 요청했을때 실행되는 메소드
	@RequestMapping("/findId.do")
	public void findId(UserVo vo, HttpServletResponse response) {
		System.out.println("[findId 진입]" + vo);
		UserVo data = mapper.findId(vo);

		System.out.println("data : " + data);

		Gson gson = new Gson();
		String value = gson.toJson(data);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(value);
	}

	// app/findPw.do를 요청했을때 실행되는 메소드
	@RequestMapping("/findPw.do")
	public void findPw(UserVo vo, HttpServletResponse response) {
		System.out.println("[findPw 진입]" + vo);
		UserVo data = mapper.findPw(vo);

		System.out.println("data : " + data);

		Gson gson = new Gson();
		String value = gson.toJson(data);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(value);
	}

	// app/setPw.do를 요청했을때 실행되는 메소드
	@RequestMapping("/setPw.do")
	public void setPw(UserVo vo, HttpServletResponse response) {
		System.out.println("[setPw 진입]" + vo);
		
		mapper.setPw(vo);
		//System.out.println("[setPw 이탈]" + vo);

	}

	// app/updateInfo.do를 요청했을때 실행되는 메소드
	@RequestMapping("/updateInfo.do")
	public void updateInfo(UserVo vo, HttpServletResponse response) {
		System.out.println("[updateInfo 진입]" + vo);

		mapper.updateInfo(vo);
		
		//System.out.println("[updateInfo 이탈]" + vo);

	}

}
