package com.carejava.duce.ject.web.business;

import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.ProjectEntity;
import com.carejava.duce.ject.web.in.vo.ProjectVO;

import javax.servlet.http.HttpServletRequest;

public interface IProjectBusiness {
    ResultBean add(HttpServletRequest request, ProjectEntity projectEntity);

    ResultBean update(HttpServletRequest request, ProjectEntity projectEntity);

    PageResultBean selectPageList(HttpServletRequest request, ProjectVO projectVO);

    ResultBean delete(HttpServletRequest request, ProjectEntity projectEntity);

    ResultBean queryById(HttpServletRequest request, Long id);

    ResultBean produceById(HttpServletRequest request, ProjectEntity id);

    ResultBean queryModelsByProjectId(HttpServletRequest request, Long id);

    ResultBean queryOsType(HttpServletRequest request);
}
