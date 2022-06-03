package kr.catchup.mapper;


import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiaryVo {
		
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
	
}
