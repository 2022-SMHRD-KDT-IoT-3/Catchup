package kr.catchup.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiaryVo2{

	public DiaryVo2(String title, String content, String date, String id, int temp, int humid, int percent,
			String pesti, int cnt) {
		// TODO Auto-generated constructor stub
	}
	@NonNull
	private int diary_seq; //  다이어리 순번
    @NonNull
	private String diary_title; //  다이어리 제목
    @NonNull
	private String diary_content; //  다이어리 내용
    @NonNull
	private String diary_dt; //  다이어리 날짜
    @NonNull
	private String diary_id; //  작성자 아이디
    private int diary_temp;
    private int diary_humid;
    private int diary_percent;
    private String diary_pesti;
    private int diary_cnt;
}
