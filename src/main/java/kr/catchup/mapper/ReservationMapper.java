package kr.catchup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

@Mapper
public interface ReservationMapper {

	public List<ResVo> Reservation_list(ResVo vo);

	public void Reservation_add(ResVo vo);

	public void Reservation_remove(ResVo vo);
	
	public void diary_write(DiaryVo2 vo2);

	//public int reservation_count(String res_rsvtime, String user_id);

	public int reservation_count(ResVo res_vo);

	public List<ResVo> resPesticide(ResVo vo);

	public List<DiaryVo> select_diarylist(DiaryVo vo);

	public List<DiaryVo> all_diarylist(DiaryVo vo);

}
