package kr.catchup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
	public List<BoardVo> boardList();

	public void boardInsert(BoardVo vo);

	public BoardVo boardContents(int idx);

	public void boardUpdate(BoardVo vo);

	public void boardDelete(int idx);

	public void count(int idx);

}
