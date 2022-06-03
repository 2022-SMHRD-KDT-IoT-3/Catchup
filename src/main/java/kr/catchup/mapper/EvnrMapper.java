package kr.catchup.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvnrMapper {

	EvnrVo selectEnv(EvnrVo vo);	

	

}
