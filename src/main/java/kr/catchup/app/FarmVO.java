package kr.catchup.app;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FarmVO {
	
    @NonNull
	private int farm_seq; //  농장 번호
    @NonNull
	private int farm_plant; //  농장 주수
    @NonNull
	private int farm_linenum; //  농장 라인수
    @NonNull
	private int farm_areanum; //  농장 구역수
    @NonNull
	private String user_id; //  회원 아이디
	
}
