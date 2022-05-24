package kr.catchup.app;



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
public class MonitVO {
		
    @NonNull
	private int monit_seq; //  모니터링 순번
    @NonNull
	private String monit_area; //  모니터링 구역
    @NonNull
	private Timestamp monit_time; //  모니터링 시간
    @NonNull
	private char monit_done; //  모니터링 완료여부
    @NonNull
	private char monit_infected; //  모니터링 전염여부
    @NonNull
	private String user_id; //  회원 아이디
    @NonNull
	private int res_seq; //  방제 순번
	
}
