package com.carejava.duce.ject.web.service.impl;

import java.util.List;

import com.carejava.duce.ject.web.dao.CmPrivMapper;
import com.carejava.duce.ject.web.dao.UserMapper;
import com.carejava.duce.ject.web.entity.CmPrivEntity;
import com.carejava.duce.ject.web.entity.UserEntity;
import com.carejava.duce.ject.web.in.vo.UserVO;
import com.carejava.duce.ject.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * User Service层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月09日]
 */
@Service("userService")
public class UserServiceImpl implements IUserService{

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CmPrivMapper privMapper;
    
    @Override
    public boolean add(UserEntity userEntity) {
        int num = userMapper.insert(userEntity);
        return num == 1;
    }

    @Override
    public boolean update(UserEntity userEntity) {
        int num = userMapper.update(userEntity);
        return num == 1;
    }

    @Override
    public int selectCount(UserVO userVO) {
        return userMapper.selectCount(userVO);
    }

    @Override
    public List<UserEntity> selectPageList(UserVO userVO) {
        return userMapper.selectPageList(userVO);
    }

    @Override
    public boolean delete(UserEntity userEntity) {
        int num = userMapper.delete(userEntity);
        return num == 1;
    }

    @Override
    public UserEntity queryById(Long id) {
        return userMapper.queryById(id);
    }

    @Override
    public List<UserEntity> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public List<CmPrivEntity> getAllAPI(Long id) {
        return privMapper.getAllAPI(id);
    }

    @Override
    public List<CmPrivEntity> getAllMenu(Long id) {
        return privMapper.getAllMenu(id);
    }

    @Override
    public UserEntity login(UserVO parames) {
        return userMapper.login(parames);
    }

}
