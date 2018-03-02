package com.carejava.duce.ject.web.service;

import com.carejava.duce.ject.web.entity.ProjectEntity;
import com.carejava.duce.ject.web.in.vo.ProjectVO;
import com.carejava.duce.ject.web.outVO.ModelOutVO;

import java.util.List;

public interface IProjectService {
    boolean add(ProjectEntity projectEntity);

    boolean update(ProjectEntity projectEntity);

    int selectCount(ProjectVO projectVO);

    List<ProjectEntity> selectPageList(ProjectVO projectVO);

    boolean delete(ProjectEntity projectEntity);

    ProjectEntity queryById(Long id);
    
    List<ProjectEntity> queryAll();

    List<ModelOutVO> queryModelsByProjectId(String ids);
}
