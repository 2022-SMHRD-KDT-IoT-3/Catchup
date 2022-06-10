package kr.catchup.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonitMapper {

	void monitInsert(MonitVo vo);

	List<MonitVo> selectMonit(MonitVo vo);

	String getInfected(MonitVo vo);

	//String getInfected(MonitVo vo);

}
