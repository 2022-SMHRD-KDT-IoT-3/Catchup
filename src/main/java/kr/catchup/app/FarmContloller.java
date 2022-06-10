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

import kr.catchup.mapper.FarmMapper;
import kr.catchup.mapper.FarmVo;
import kr.catchup.mapper.UserVo;

@Controller
public class FarmContloller {

	@Inject
	private FarmMapper mapper;

	// app/selectFarmInfo.do를 요청했을 때 실행되는 메소드
	@RequestMapping("/selectFarmInfo.do")
	public void selectFarmInfo(FarmVo vo,HttpServletResponse response) {

		System.out.println("[selectFarmInfo] in");
		System.out.println(vo.toString());
		FarmVo data = mapper.selectFarmInfo(vo);

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

	// app/updateFarmInfo.do를 요청했을 때 실행되는 메소드
	@RequestMapping("/updateFarmInfo.do")
	public void updateFarmInfo(FarmVo vo, HttpServletResponse response) {
		System.out.println("[updateFarmInfo in]");
		// Responsebody 대신 gson + out객체 사용

		// System.out.println("vo : "+vo);
		mapper.updateFarmInfo(vo);

		System.out.println("[updateFarmInfo out] " + vo);

	}
}
