package kr.catchup.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.catchup.mapper.EvnrVo;
import kr.catchup.mapper.MonitMapper;
import kr.catchup.mapper.MonitVo;

@Controller
public class MonitContloller {

	@Inject
	private MonitMapper mapper;

	// app/monitInsert.do? 를 요청했을 때 실행되는 메소드
	// /monitInsert.do 자체에서는 500오류발생. 요청할때 항상 쿼리스트링을 써야한다
	@RequestMapping("/monitInsert.do")
	public void monitInsert(MonitVo vo) {

		System.out.println("-monitInsert.do");

		System.out.println(vo);
		mapper.monitInsert(vo);

	}

	// app/selectMonit.do를 요청했을 때 실행되는 메소드
	@RequestMapping("/selectMonit.do")
	public void selectMonit(MonitVo vo, HttpServletResponse response) {
		System.out.println("-selectMonit.do");

		System.out.println("vo : " + vo);

		List<MonitVo> list = mapper.selectMonit(vo);

		System.out.println(list.size());

		System.out.println("[selectEnv] + " + list);
		Gson gson = new Gson();
		String value = gson.toJson(list);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(value);

	}

	// app/getInfected.do를 요청했을 때 실행되는 메소드
	@RequestMapping("/getInfected.do")
	public void getInfected(MonitVo vo, HttpServletResponse response) {
		System.out.println("-getInfected.do");

		// System.out.println(vo.toString());

		String data = mapper.getInfected(vo);
		System.out.println("data : " + data);

		double temp = Double.parseDouble(data);
		int temp2 = (int) Math.round(temp);

		String value = String.valueOf(temp2);

		Gson gson = new Gson();
		String result = gson.toJson(value);
		result=result.replaceAll("[^0-9]",""); //숫자를 제외한 모든 값을 없앤다
		System.out.println("result : " + result);
		PrintWriter out = null;
		try {
			out = response.getWriter();

		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(result);
	}

	/*
	 * // app/monitInsert.do를 요청했을 때 실행되는 메소드
	 * 
	 * @RequestMapping("/monitInsert2.do") public void monitInsert2(MonitVo vo,
	 * HttpServletResponse response) { System.out.println("-monitInsert.do");
	 * 
	 * // System.out.println("구역 : "+monit_area+" 완료? : "+monit_done+" 감염? : //
	 * "+monit_infected+" 아이디 : "+user_id);
	 * 
	 * // Gson gson = new Gson(); // String value = gson.toJson(data);
	 * 
	 * String value = ("구역 : " + monit_area + " 완료? : " + monit_done + " 감염? : " +
	 * monit_infected + " 아이디 : " + user_id); PrintWriter out = null; try { out =
	 * response.getWriter(); } catch (IOException e) { e.printStackTrace(); }
	 * out.print(value);
	 * 
	 * // Responsebody 대신 gson + out객체 사용
	 * 
	 * 
	 * MonitVo data = new MonitVo(); data = mapper.monitInsert(vo);
	 * 
	 * System.out.println("[monitInsert] + "+ data );
	 * 
	 * Gson gson = new Gson(); String value = gson.toJson(data); PrintWriter out =
	 * null; try { out = response.getWriter(); } catch (IOException e) {
	 * e.printStackTrace(); } out.print(value);
	 * 
	 * }
	 */
}
