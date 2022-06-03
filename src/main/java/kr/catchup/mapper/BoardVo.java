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
public class BoardVo {
    
    @NonNull
	private int board_seq; //  게시글 순번
    @NonNull
	private char board_type; //  게시글 유형
    @NonNull
	private String board_title; //  게시글 제목
    @NonNull
	private String board_time; //  게시글 작성시간
    @NonNull
	private String board_content; //  게시글 내용
    @NonNull
	private int board_cnt; //  게시글 조회수
    @NonNull
	private int board_commcnt; //  게시글 댓글수
    @NonNull
	private String user_id; //  회원 아이디

	
}
