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
public class ResVO {
	
    @NonNull
	private int res_seq; //  방제 순번
    @NonNull
	private String res_pesticide; //  방제 사용약품
    @NonNull
	private Timestamp res_rsvtime; //  방제 예약 시작시간
    @NonNull
	private String user_id; //  회원 아이디

	
}
