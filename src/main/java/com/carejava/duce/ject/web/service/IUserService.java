package com.carejava.duce.ject.web.service;

import com.carejava.duce.ject.web.entity.CmPrivEntity;
import com.carejava.duce.ject.web.entity.UserEntity;
import com.carejava.duce.ject.web.in.vo.UserVO;

import java.util.List;

public interface IUserService {
    boolean add(UserEntity userEntity);

    boolean update(UserEntity userEntity);

    int selectCount(UserVO userVO);

    List<UserEntity> selectPageList(UserVO userVO);

    boolean delete(UserEntity userEntity);

    UserEntity queryById(Long id);
    
    List<UserEntity> queryAll();

    List<CmPrivEntity> getAllAPI(Long id);

    List<CmPrivEntity> getAllMenu(Long id);

    UserEntity login(UserVO parames);
}
