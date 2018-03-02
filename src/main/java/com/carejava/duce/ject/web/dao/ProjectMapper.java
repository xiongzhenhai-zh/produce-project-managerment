package com.carejava.duce.ject.web.dao;

import java.util.List;

import com.carejava.duce.ject.web.entity.ProjectEntity;
import com.carejava.duce.ject.web.in.vo.ProjectVO;
import com.carejava.duce.ject.web.outVO.ModelOutVO;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public interface ProjectMapper {
    
    int insert(ProjectEntity projectEntity);
    
    int update(ProjectEntity projectEntity);
    
    int selectCount(ProjectVO projectVO);
    
    List<ProjectEntity> selectPageList(ProjectVO projectVO);
    
    int delete(ProjectEntity projectEntity);

    ProjectEntity queryById(Long id);
    
    List<ProjectEntity> queryAll();

    List<ModelOutVO> queryModelsByProjectId(List<String> ids);
}
