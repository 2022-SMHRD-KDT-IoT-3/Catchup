package kr.catchup.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.catchup.mapper.DiaryVo;
import kr.catchup.mapper.DiaryVo2;
import kr.catchup.mapper.ResVo;
import kr.catchup.mapper.ReservationMapper;
import kr.catchup.mapper.UserVo;

@Controller
public class ReservationController {
	
	@Inject
	private ReservationMapper mapper;
	
	@RequestMapping("/Reservation_list.do")
	public void Reservation_list(ResVo vo, HttpServletResponse response){
		System.out.println(vo);
		System.out.println("예약목록 보기");
//		
		List<ResVo> list = mapper.Reservation_list(vo);
		System.out.println(list);
		Gson gson = new Gson();
		String value = gson.toJson(list);
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(value);
		out.print(value);
		
	}
	@RequestMapping("/Reservation_add.do")
	public void Reservation_add(ResVo vo){
		System.out.println(vo);
		System.out.println("예약추가");
		mapper.Reservation_add(vo);
	}
	@RequestMapping("/Reservation_remove.do")
	public void Reservation_remove(ResVo vo){
		System.out.println(vo);
		System.out.println("예약제거");
		mapper.Reservation_remove(vo);
	}
	
	// Diary_write.do -> 다이어리 작성 기능
	@RequestMapping("/diaryWrite.do")
	public void diaryWrite(DiaryVo vo, HttpServletResponse response) {
		System.out.println("다이어리 작성 기능 실행");
		System.out.println(vo);
		
		String title = vo.getDiary_title();
		String content = vo.getDiary_content();
		String date = vo.getDiary_dt();
		String id = vo.getDiary_id();
		
		int temp = Integer.parseInt(vo.getDiary_temp());
		int humid = Integer.parseInt(vo.getDiary_humid());
		int percent = Integer.parseInt(vo.getDiary_percent());
		String pesti = vo.getDiary_pesti();
		int cnt = Integer.parseInt(vo.getDiary_cnt());
		
		//DiaryVo2 vo2 = new DiaryVo2("title", "content", "date", "id", 22, 32, 33, "pesti", 5);
		DiaryVo2 vo2 = new DiaryVo2();
		vo2.setDiary_title(title);
		vo2.setDiary_content(content);
		vo2.setDiary_dt(date);
		vo2.setDiary_id(id);
		vo2.setDiary_temp(temp);
		vo2.setDiary_humid(humid);
		vo2.setDiary_percent(percent);
		vo2.setDiary_pesti(pesti);
		vo2.setDiary_cnt(cnt);
		
		System.out.println(vo2);
		
		mapper.diary_write(vo2);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("Hello");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}