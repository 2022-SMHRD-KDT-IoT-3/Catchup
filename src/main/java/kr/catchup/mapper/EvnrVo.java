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
public class EvnrVo {
	
    @NonNull
	private int env_seq; //  환경 순번
    @NonNull
	private int env_temprt; //  환경 온도
    @NonNull
	private int env_humid; //  환경 습도
    @NonNull
	private String env_time; //  환경 시간
    @NonNull
	private String user_id; //  회원 아이디
	
}
