package com.carejava.duce.ject.web.dao;

import java.util.List;

import com.carejava.duce.ject.web.entity.UserEntity;
import com.carejava.duce.ject.web.in.vo.UserVO;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月09日]
 */
public interface UserMapper {
    
    int insert(UserEntity userEntity);
    
    int update(UserEntity userEntity);
    
    int selectCount(UserVO userVO);
    
    List<UserEntity> selectPageList(UserVO userVO);
    
    int delete(UserEntity userEntity);

    UserEntity queryById(Long id);
    
    List<UserEntity> queryAll();

    UserEntity login(UserVO parames);
}
