package kr.catchup.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FarmMapper {
	
	FarmVo selectFarmInfo(FarmVo vo);
	
	Void updateFarmInfo(FarmVo vo);


}
