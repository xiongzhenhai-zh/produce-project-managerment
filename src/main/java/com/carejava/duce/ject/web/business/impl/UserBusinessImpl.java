package com.carejava.duce.ject.web.business.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carejava.duce.ject.web.business.IUserBusiness;
import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.CmPrivEntity;
import com.carejava.duce.ject.web.entity.CmRoleEntity;
import com.carejava.duce.ject.web.entity.UserEntity;
import com.carejava.duce.ject.web.in.vo.UserVO;
import com.carejava.duce.ject.web.enums.ErrorCode;
import com.carejava.duce.ject.web.service.ICmRoleService;
import com.carejava.duce.ject.web.service.IUserService;
import com.carejava.duce.ject.web.utils.HttpUtil;
import com.carejava.duce.ject.web.utils.UserSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 项目生成管理平台<br>
 * Userbusiness 层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月09日]
 */
@Component("userBusiness")
public class UserBusinessImpl extends BaseBusiness implements IUserBusiness{

    private final static Logger LOGGER = LoggerFactory.getLogger(UserBusinessImpl.class);

    /**
     * 首页背景图获取地址
     */
    private final static String BING_IMG_URL = "http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";

    /**
     * 首页背景图地址
     */
    private static String BING_IMG_CACHE = null;

    /**
     * 首页背景图缓存时间，单位分钟
     */
    private final static int BING_IMG_CACHE_TIME = 30*60*1000;

    /**
     * 上次缓存时间
     */
    private static long BING_IMG_CACHE_LAST_TIME = 0L;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICmRoleService roleService;

    @Override
    public ResultBean add(UserEntity userEntity) {

        if (!checkParam(userEntity)) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        userEntity.setCreateTime(new Date());
        boolean result = userService.add(userEntity);
        if (result) {
            return getResultBean(ErrorCode.SUCCESS);
        }else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    private boolean checkParam(UserEntity userEntity) {

        if (userEntity== null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ResultBean update(UserEntity userEntity) {

        if (userEntity == null ){
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        boolean success = userService.update(userEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    @Override
    public PageResultBean selectPageList(UserVO userVO) {

        if (userVO == null ) {
            return new PageResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        PageResultBean result = new PageResultBean(ErrorCode.SUCCESS);

        int totalNum = userService.selectCount(userVO);

        if (totalNum > 0) {
            result.setTotalNum(totalNum);
            int totalPage = (totalNum - 1) / userVO.getPageNum() + 1;
            result.setTotalPage(totalPage);

            userVO.setOffset((userVO.getCurrentPage() - 1) * userVO.getPageNum());

            List<UserEntity> list = userService.selectPageList(userVO);
            result.setContent((Serializable) list);
        }
        return result;
    }

    @Override
    public ResultBean delete(UserEntity userEntity) {

        if (userEntity==null) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        boolean success = userService.delete(userEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        }else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }

    }

    @Override
    public ResultBean queryById(Long id) {

        if (id==null) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        UserEntity userEntity = userService.queryById(id);
        if (userEntity==null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        return getResultBean(ErrorCode.SUCCESS,(Serializable) userEntity);
    }

    @Override
    public ResultBean login(HttpServletRequest request, UserVO parames) {
        if (parames == null || parames.getUserName() == null || parames.getPassword() == null) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        UserEntity entity = userService.login(parames);
        if (entity == null) {
            return getResultBean(ErrorCode.FAIL_LOGIN);
        }
        List<CmRoleEntity> roleEntities = roleService.queryByUserId(entity.getId());

        List<CmPrivEntity> privEntities = userService.getAllAPI(entity.getId());

        //把用户数据保存在session域对象中
        UserSessionUtil.setSession(request, entity, roleEntities,privEntities);

        return getResultBean(ErrorCode.SUCCESS, covertOutVoByEntity(entity, roleEntities));

    }

    private UserEntity covertOutVoByEntity(UserEntity entity, List<CmRoleEntity> roleEntities) {
        UserEntity outVO = new UserEntity();
        outVO.setId(entity.getId());
        outVO.setUserName(entity.getUserName());
        return outVO;
    }

    @Override
    public ResultBean getAllMenu(HttpServletRequest request) {
        List<CmPrivEntity> privs = userService.getAllMenu(UserSessionUtil.getUserId(request));
        List<CmPrivEntity> privsTree = buildToTree(privs);
        return getResultBean(ErrorCode.SUCCESS, (Serializable) privsTree);
    }

    @Override
    public ResultBean getLoginImg() {
        if (BING_IMG_CACHE == null || System.currentTimeMillis() - BING_IMG_CACHE_LAST_TIME > BING_IMG_CACHE_TIME) {
            try {
                String result = HttpUtil.get(BING_IMG_URL, null);
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("images");
                JSONObject obj = (JSONObject) jsonArray.get(0);
                BING_IMG_CACHE_LAST_TIME = System.currentTimeMillis();
                BING_IMG_CACHE = "http://www.bing.com" + obj.getString("url");
            } catch (Exception e) {
                LOGGER.error("错误e={}", e);
                return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
            }
        }
        return getResultBean(ErrorCode.SUCCESS, (Serializable) BING_IMG_CACHE);
    }

    private List<CmPrivEntity> buildToTree(List<CmPrivEntity> privs) {
        List<CmPrivEntity> privsTree = new ArrayList<CmPrivEntity>();
        for (CmPrivEntity priv : privs) {
            if(priv.getPid()!=null){
                //获得父节点，并加入的父节点
                CmPrivEntity parent = getParentNode(priv.getPid(), privs);
                List<CmPrivEntity> subPrivs = parent.getSubPrivs();
                if(subPrivs == null){
                    subPrivs = new ArrayList<CmPrivEntity>();
                }
                subPrivs.add(priv);
                parent.setSubPrivs(subPrivs);
            }else{
                privsTree.add(priv);
            }
        }
        return privsTree;

    }

    private CmPrivEntity getParentNode(Long pid, List<CmPrivEntity> nodes) {
        for(CmPrivEntity node: nodes){
            if(pid.equals(node.getId())){
                return node;
            }
        }
        return null;

    }


}
