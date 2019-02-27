package com.lingting.travel.dao;

import com.lingting.travel.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao {

    /** 查询所有 */
    @Select("select * from member")
    public List<Member> findAll();

    /** 根据id 查询*/
    @Select("select * from member where id = #{id}")
    public Member findById(String id);
}
