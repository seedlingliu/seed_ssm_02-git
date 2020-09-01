package com.seed.ssm.dao;

import com.seed.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberDao {
    @Select("select * from member where id = #{id} ")
    Member findOneById(int id);
}
