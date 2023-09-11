package com.example.dashboard.Mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.dashboard.Entity.Token;
import com.example.dashboard.Entity.User;

@Mapper
public interface TokenMapper {
    @Select("SELECT * FROM token WHERE token = #{tokenValue} ")
    Token getTokenByTokenValue(String tokenValue);

     @Select("SELECT * FROM _user WHERE id = #{userid} ")
    User getUserByUserID(String userid);
}