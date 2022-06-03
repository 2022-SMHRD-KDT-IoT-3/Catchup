package kr.catchup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

@Mapper
public interface UserMapper {

	void joinInsert(UserVo vo);

	UserVo loginSelect(UserVo vo);

	UserVo idCheck(String user_id);

	UserVo findId(UserVo vo);

	UserVo findPw(UserVo vo);

	UserVo setPw(UserVo vo);

	
	//UserVo setPw(UserVo vo);
	
	//void updateService(UserVo vo);

	//List<UserVo> userList();
	
	//UserVo logout(UserVo vo);

	//UserVo idCheck(String user_id);
	
	//public List<BoardVO> boardList();

	//public void boardInsert(BoardVO vo);

	//public BoardVO boardContents(int idx);




}
