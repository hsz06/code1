package com.qianfeng.dao;

import com.qianfeng.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberDao {
    @Select("select * from member where id = #{id}")
    public Member findById(String id);
}
