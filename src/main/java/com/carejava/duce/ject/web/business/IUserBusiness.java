package com.carejava.duce.ject.web.business;

import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.UserEntity;
import com.carejava.duce.ject.web.in.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

public interface IUserBusiness {
    ResultBean add(UserEntity userEntity);

    ResultBean update(UserEntity userEntity);

    PageResultBean selectPageList(UserVO userVO);

    ResultBean delete(UserEntity userEntity);

    ResultBean queryById(Long id);

    ResultBean login(HttpServletRequest request, UserVO parames);

    ResultBean getAllMenu(HttpServletRequest request);

    ResultBean getLoginImg();
}
