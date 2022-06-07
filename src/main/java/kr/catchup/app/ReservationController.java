package kr.catchup.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

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
}