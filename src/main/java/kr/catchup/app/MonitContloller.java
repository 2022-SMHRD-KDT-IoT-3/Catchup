package kr.catchup.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
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
	
	// 전염도 알림
	@RequestMapping("/Infectivity.do")
	public void Infectivity(MonitVo vo, HttpServletResponse response) {
		System.out.println(vo);
		System.out.println("전염도 보기");
//		
		List<MonitVo> list = mapper.Infectivity(vo);
		System.out.println(list);
		int sum1 = 0, sum2 = 0, sum3 = 0, sum4= 0, sum5 = 0, sum6 = 0, sum7 = 0, sum8 = 0 , sum9 = 0;
		int num = 0;
		System.out.println(list.get(0).getMonit_area());
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMonit_area().equals("1")) {
				sum1 += Character.getNumericValue(list.get(i).getMonit_infected());
				num++;
			} else if (list.get(i).getMonit_area().equals("2")) {
				sum2 += Character.getNumericValue(list.get(i).getMonit_infected());
			} else if (list.get(i).getMonit_area().equals("3")) {
				sum3 += Character.getNumericValue(list.get(i).getMonit_infected());
			} else if (list.get(i).getMonit_area().equals("4")) {
				sum4 += Character.getNumericValue(list.get(i).getMonit_infected());
			} else if (list.get(i).getMonit_area().equals("5")) {
				sum5 += Character.getNumericValue(list.get(i).getMonit_infected());
			} else if (list.get(i).getMonit_area().equals("6")) {
				sum6 += Character.getNumericValue(list.get(i).getMonit_infected());
			} else if (list.get(i).getMonit_area().equals("7")) {
				sum7 += Character.getNumericValue(list.get(i).getMonit_infected());
			} else if (list.get(i).getMonit_area().equals("8")) {
				sum8 += Character.getNumericValue(list.get(i).getMonit_infected());
			} else if (list.get(i).getMonit_area().equals("9")) {
				sum9 += Character.getNumericValue(list.get(i).getMonit_infected());
			}

		}
		System.out.println("num : "+ num);
		if(num == 0) {
			num = 1;
		}
		
		System.out.println(sum1);
		System.out.println(sum2);
		System.out.println(sum3);
		System.out.println(sum4);
		System.out.println(sum5);
		System.out.println(sum6);
		System.out.println(sum7);
		System.out.println(sum8);
		System.out.println(sum9);
		
		
		double avg1 = (double)sum1 / (double)num;
		double avg2 = (double)sum2 / (double)num;
		double avg3 = (double)sum3 / (double)num;
		double avg4 = (double)sum4 / (double)num;
		double avg5 = (double)sum5 / (double)num;
		double avg6 = (double)sum6 / (double)num;
		double avg7 = (double)sum7 / (double)num;
		double avg8 = (double)sum8 / (double)num;
		double avg9 = (double)sum9 / (double)num;
		
		ArrayList<MonitVo> monitorList = new ArrayList<MonitVo>();
		double[] avg = {avg1, avg2, avg3, avg4, avg5, avg6, avg7, avg8, avg9};
		System.out.println(avg1);
		System.out.println(avg2);
		System.out.println(avg3);
		System.out.println(avg4);
		System.out.println(avg5);
		System.out.println(avg6);
		System.out.println(avg7);
		System.out.println(avg8);
		System.out.println(avg9);
		String [] area = {"1","2","3","4","5","6","7","8","9"};
		
		for(int i = 0; i < area.length; i++) {
			
		vo.setMonit_area(area[i]);
		System.out.println(area[i]);
		char infected = '0';
		if(avg[i]==0) {
			infected = '0';
		}else if(avg[i] < 1) {
			infected = '1';
		}else if(avg[i] == 1) {
			infected = '2';
		}
		vo.setMonit_infected(infected);
		System.out.println(vo);
		
		monitorList.add(vo);
		System.out.println(monitorList);
		}
//		System.out.println(monitorList);
		
		
		Gson gson = new Gson();
		String value = gson.toJson(monitorList);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(value);
		out.print(value);

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
