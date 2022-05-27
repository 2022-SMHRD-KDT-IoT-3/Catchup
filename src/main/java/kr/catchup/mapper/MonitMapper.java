package kr.catchup.mapper;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonitMapper {

	MonitVo monitInsert(MonitVo vo);
	
	MonitVo monitInsert(MonitVo vo, String monit_area, Timestamp monit_time, String monit_done, String monit_infected, String user_id );


	
	

	



}
