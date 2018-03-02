package com.carejava.duce.ject.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.carejava.duce.ject.web.dao.ProjectMapper;
import com.carejava.duce.ject.web.entity.ProjectEntity;
import com.carejava.duce.ject.web.in.vo.ProjectVO;
import com.carejava.duce.ject.web.outVO.ModelOutVO;
import com.carejava.duce.ject.web.service.IProjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Project Service层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Service("projectService")
public class ProjectServiceImpl implements IProjectService{

    @Autowired
    private ProjectMapper projectMapper;
    
    @Override
    public boolean add(ProjectEntity projectEntity) {
        int num = projectMapper.insert(projectEntity);
        return num == 1;
    }

    @Override
    public boolean update(ProjectEntity projectEntity) {
        int num = projectMapper.update(projectEntity);
        return num == 1;
    }

    @Override
    public int selectCount(ProjectVO projectVO) {
        return projectMapper.selectCount(projectVO);
    }

    @Override
    public List<ProjectEntity> selectPageList(ProjectVO projectVO) {
        return projectMapper.selectPageList(projectVO);
    }

    @Override
    public boolean delete(ProjectEntity projectEntity) {
        int num = projectMapper.delete(projectEntity);
        return num == 1;
    }

    @Override
    public ProjectEntity queryById(Long id) {
        return projectMapper.queryById(id);
    }

    @Override
    public List<ProjectEntity> queryAll() {
        return projectMapper.queryAll();
    }

    @Override
    public List<ModelOutVO> queryModelsByProjectId(String idStr) {
        if (StringUtils.isBlank(idStr)){
            return new ArrayList<>();
        }
        List<String> ids = Arrays.asList(idStr.split(","));
        if (ids.size()<0){
            return new ArrayList<>();
        }
        return projectMapper.queryModelsByProjectId(ids);
    }

}
