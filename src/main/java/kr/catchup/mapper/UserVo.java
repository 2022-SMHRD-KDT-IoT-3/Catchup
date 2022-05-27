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
public class UserVo {
	
	
    @NonNull
	private String user_id; //  회원 아이디
    @NonNull
	private String user_pw; //  회원 비밀번호
    @NonNull
	private String user_name; //  회원 이름
    @NonNull
	private String user_nick; //  회원 닉네임
    @NonNull
	private String user_mail; //  회원 이메일
    @NonNull
	private String user_serial; //  회원 사용기계넘버
    @NonNull
	private Timestamp user_joindate; //  회원 가입일자

	
}
