package kr.catchup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

@Mapper
public interface UserMapper {

	void joinInsert(UserVo vo);

	UserVo loginSelect(UserVo vo);

	UserVo logout(UserVo vo);

	void updateService(UserVo vo);

	List<UserVo> userList();

	UserVo idCheck(String user_id);
	
	//public List<BoardVO> boardList();

	//public void boardInsert(BoardVO vo);

	//public BoardVO boardContents(int idx);




}
