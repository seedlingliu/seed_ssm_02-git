package com.seed.ssm.dao;

import com.seed.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ISysLogDao {

    @Insert("INSERT INTO sysLog(visitTime,username,ip,url,executionTime,method) " +
            "VALUES(#{visitTime},#{userName},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog) throws Exception;

    @Select("select * from sysLog")
    List<SysLog> findAll() throws Exception;
}
