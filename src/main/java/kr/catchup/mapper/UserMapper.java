package kr.catchup.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	void joinInsert(UserVo vo);

	UserVo loginSelect(UserVo vo);

	UserVo idCheck(String user_id);

	UserVo findId(UserVo vo);

	UserVo findPw(UserVo vo);

	void setPw(UserVo vo);

	void updateInfo(UserVo vo);

	
}
