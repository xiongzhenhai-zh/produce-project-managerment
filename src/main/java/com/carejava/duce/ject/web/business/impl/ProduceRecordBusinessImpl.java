package com.carejava.duce.ject.web.business.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.carejava.duce.ject.web.business.IProduceRecordBusiness;
import com.carejava.duce.ject.web.core.PageResultBeanList;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.ProduceRecordEntity;
import com.carejava.duce.ject.web.in.vo.ProduceRecordVO;
import com.carejava.duce.ject.web.enums.ErrorCode;
import com.carejava.duce.ject.web.service.IProduceRecordService;
import com.carejava.duce.ject.web.utils.UserSessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目生成管理平台<br>
 * ProduceRecordbusiness 层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2018年01月31日]
 */
@Component("produceRecordBusiness")
public class ProduceRecordBusinessImpl extends BaseBusiness implements IProduceRecordBusiness {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProduceRecordBusinessImpl.class);

    @Autowired
    private IProduceRecordService produceRecordService;

    @Override
    public ResultBean add(ProduceRecordEntity produceRecordEntity) {

        if (!checkParam(produceRecordEntity)) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        boolean result = produceRecordService.add(produceRecordEntity);
        if (result) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    private boolean checkParam(ProduceRecordEntity produceRecordEntity) {

        if (produceRecordEntity == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ResultBean update(ProduceRecordEntity produceRecordEntity) {

        if (produceRecordEntity == null) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        boolean success = produceRecordService.update(produceRecordEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    @Override
    public ResultBean selectPageList(HttpServletRequest request, ProduceRecordVO produceRecordVO) {

        if (produceRecordVO == null) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        produceRecordVO.setUserId(UserSessionUtil.getUserId(request));
        if (StringUtils.isNotBlank(produceRecordVO.getFilePath())) {
            produceRecordVO.setFilePath("%" + produceRecordVO.getFilePath() + "%");
        }
        PageResultBeanList<ProduceRecordEntity> result = new PageResultBeanList<>();

        int totalNum = produceRecordService.selectCount(produceRecordVO);

        if (totalNum > 0) {
            result.setTotalNum(totalNum);
            int totalPage = (totalNum - 1) / produceRecordVO.getPageNum() + 1;
            result.setTotalPage(totalPage);

            produceRecordVO.setOffset((produceRecordVO.getCurrentPage() - 1) * produceRecordVO.getPageNum());

            List<ProduceRecordEntity> list = produceRecordService.selectPageList(produceRecordVO);
            for (ProduceRecordEntity en : list) {
                en.setFilePath(en.getFilePath().substring(en.getFilePath().lastIndexOf("/") + 1));
            }
            result.setList(list);
        }
        return getResultBean(ErrorCode.ERR_SYSTEM_ERR, result);
    }

    @Override
    public ResultBean delete(HttpServletRequest request, ProduceRecordEntity produceRecordEntity) {

        if (produceRecordEntity == null) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }

        ProduceRecordEntity produceRecordEntityTemp = produceRecordService.queryById(produceRecordEntity.getId());
        if (produceRecordEntityTemp == null) {
            return getResultBean(ErrorCode.NOT_EXISTS);
        }
        Long userId = UserSessionUtil.getUserId(request);
        if (!produceRecordEntityTemp.getUserId().equals(userId)) {
            return getResultBean(ErrorCode.NO_AUTHORITY_ERR);
        }
        try{
            File file = new File(produceRecordEntityTemp.getFilePath());
            file.delete();
        }catch (Exception e){
            LOGGER.error("文件删除错误");
        }
        boolean success = produceRecordService.delete(produceRecordEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }

    }

    @Override
    public ResultBean queryById(Long id) {

        if (id == null) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        ProduceRecordEntity produceRecordEntity = produceRecordService.queryById(id);
        if (produceRecordEntity == null) {
            return getResultBean(ErrorCode.NOT_EXISTS);
        }
        return getResultBean(ErrorCode.SUCCESS, (Serializable) produceRecordEntity);
    }
}
