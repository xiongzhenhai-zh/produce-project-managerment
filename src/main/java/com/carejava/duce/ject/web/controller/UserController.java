package com.carejava.duce.ject.web.controller;

import com.carejava.duce.ject.web.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import com.carejava.duce.ject.web.entity.UserEntity;
import com.carejava.duce.ject.web.in.vo.UserVO;
import com.carejava.duce.ject.web.core.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.carejava.duce.ject.web.business.IUserBusiness;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * 项目生成管理平台<br>
 * User控制层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月09日]
 */
@Controller
@RequestMapping(value = "/user", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
public class UserController extends BaseController{

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserBusiness userBusiness;

    @RequestMapping(value= "add" )
    @ResponseBody
    public ResultBean add(HttpServletRequest request, @RequestBody UserEntity userEntity) {
        return userBusiness.add(userEntity);
    }

    @RequestMapping(value= "update")
    @ResponseBody
    public ResultBean update(HttpServletRequest request, @RequestBody UserEntity userEntity) {
        return userBusiness.update(userEntity);
    }

    @RequestMapping(value= "selectPageList")
    @ResponseBody
    public ResultBean selectPageList(HttpServletRequest request, @RequestBody UserVO userVO) {
        return userBusiness.selectPageList(userVO);
    }

    @RequestMapping(value= "delete")
    @ResponseBody
    public ResultBean delete(HttpServletRequest request, @RequestBody UserEntity userEntity) {
        return userBusiness.delete(userEntity);
    }

    @RequestMapping(value= "queryById")
    @ResponseBody
    public ResultBean queryById(HttpServletRequest request, @RequestBody UserEntity userEntity) {
        return userBusiness.queryById(userEntity.getId());
    }

    /**
     *
     * 功能描述: <br>
     * 登陆接口
     *
     * @version [V1.0, 2017年9月20日]
     * @param request
     * @param parames
     * @return
     */
    @RequestMapping(value = "/common/toLogin")
    @ResponseBody
    public ResultBean login(HttpServletRequest request, @RequestBody UserVO parames) {
        return userBusiness.login(request, parames);
    }

    /**
     * 获取背景图
     * @return
     */
    @RequestMapping(value = "/common/getLoginImg")
    @ResponseBody
    public ResultBean getLoginImg() {
        return userBusiness.getLoginImg();
    }


    /**
     *
     * 功能描述: <br>
     * 登出接口
     *
     * @version [V1.0, 2017年9月20日]
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public ResultBean logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //把用户数据保存在session域对象中
        session.invalidate();
        return getResultBean(ErrorCode.SUCCESS);
    }

    /**
     *
     * 功能描述: <br>
     * 获取所有菜单
     *
     * @version [V1.0, 2017年9月20日]
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAllMenu")
    @ResponseBody
    public ResultBean getAllMenu(HttpServletRequest request) {
        //把用户数据保存在session域对象中
        return userBusiness.getAllMenu(request);
    }
}
