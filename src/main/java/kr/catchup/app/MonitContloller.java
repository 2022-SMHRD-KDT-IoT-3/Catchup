package kr.catchup.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.catchup.mapper.MonitMapper;
import kr.catchup.mapper.MonitVo;

@Controller
public class MonitContloller {

	// String monit_area = "", user_id = "", monit_infected = "", monit_done = "";
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
