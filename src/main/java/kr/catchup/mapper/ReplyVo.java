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
public class ReplyVo {
		
    @NonNull
	private int rep_seq; //  댓글 순번
    @NonNull
	private int rep_content; //  댓글 내용
    @NonNull
	private String rep_time; //  댓글 작성날짜
    @NonNull
	private String user_id; //  회원 아이디
    @NonNull
	private int board_seq; //  게시글 순번
	
}
