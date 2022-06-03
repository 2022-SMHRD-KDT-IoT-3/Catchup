package kr.catchup.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import kr.catchup.mapper.EvnrMapper;
import kr.catchup.mapper.EvnrVo;

@Controller
public class EvnrController {

	@Inject
	private EvnrMapper mapper;

	// app/selectEnv.do를 요청했을 때 실행되는 메소드
	@RequestMapping("/selectEnv.do")
	public void selectEnv(EvnrVo vo, HttpServletResponse response) {
		System.out.println("-selectEnv.do");
		// Responsebody 대신 gson + out객체 사용

		// System.out.println("vo : "+vo);

		/*EvnrVo data = mapper.selectEnv(vo);
		System.out.println("[selectEnv] + " + data);
		Gson gson = new Gson();
		String value = gson.toJson(data);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(value);
*/
	}

}
