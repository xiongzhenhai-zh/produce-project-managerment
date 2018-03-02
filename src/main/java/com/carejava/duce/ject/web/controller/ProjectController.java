package com.carejava.duce.ject.web.controller;

import com.carejava.duce.ject.web.business.IProduceRecordBusiness;
import com.carejava.duce.ject.web.entity.ProduceRecordEntity;
import com.carejava.duce.ject.web.enums.ErrorCode;
import com.carejava.duce.ject.web.service.IProduceRecordService;
import com.carejava.duce.ject.web.utils.UserSessionUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.carejava.duce.ject.web.entity.ProjectEntity;
import com.carejava.duce.ject.web.in.vo.ProjectVO;
import com.carejava.duce.ject.web.core.ResultBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carejava.duce.ject.web.business.IProjectBusiness;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Objects;

/**
 * 项目生成管理平台<br>
 * Project控制层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Controller
@RequestMapping(value = "/project", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
public class ProjectController extends BaseController {

    @Autowired
    private IProjectBusiness projectBusiness;

    @Autowired
    private IProduceRecordService produceRecordService;

    @RequestMapping(value = "add")
    @ResponseBody
    public ResultBean add(HttpServletRequest request, @RequestBody ProjectEntity projectEntity) {
        return projectBusiness.add(request, projectEntity);
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public ResultBean update(HttpServletRequest request, @RequestBody ProjectEntity projectEntity) {
        return projectBusiness.update(request, projectEntity);
    }

    @RequestMapping(value = "selectPageList")
    @ResponseBody
    public ResultBean selectPageList(HttpServletRequest request, @RequestBody ProjectVO projectVO) {
        return projectBusiness.selectPageList(request, projectVO);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public ResultBean delete(HttpServletRequest request, @RequestBody ProjectEntity projectEntity) {
        return projectBusiness.delete(request, projectEntity);
    }

    @RequestMapping(value = "queryById")
    @ResponseBody
    public ResultBean queryById(HttpServletRequest request, @RequestBody ProjectEntity projectEntity) {
        return projectBusiness.queryById(request, projectEntity.getId());
    }

    /**
     * 生成项目
     *
     * @param request
     * @param projectEntity
     * @return
     */
    @RequestMapping(value = "produceById")
    @ResponseBody
    public ResultBean produceById(HttpServletRequest request, @RequestBody ProjectEntity projectEntity) {
        return projectBusiness.produceById(request, projectEntity);
    }

    /**
     * 查询项目表以及字段
     * @param request
     * @param projectEntity
     * @return
     */
    @RequestMapping(value = "queryModelsByProjectId")
    @ResponseBody
    public ResultBean queryModelsByProjectId(HttpServletRequest request, @RequestBody ProjectEntity projectEntity) {
        return projectBusiness.queryModelsByProjectId(request, projectEntity.getId());
    }

    /**
     * 查询支持的系统类型
     * @param request
     * @return
     */
    @RequestMapping(value = "queryOsType")
    @ResponseBody
    public ResultBean queryOsType(HttpServletRequest request) {
        return projectBusiness.queryOsType(request);
    }

    /**
     * 下载
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping(value = "download", method = RequestMethod.GET)
    @ResponseBody
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "filePath", required = false) Long id) {
        ProduceRecordEntity produceRecordEntity = produceRecordService.queryById(id);
        if (produceRecordEntity == null){
            return;
        }
        if (!Objects.equals(produceRecordEntity.getUserId(), UserSessionUtil.getUserId(request))){
            return;
        }
        String filePath = produceRecordEntity.getFilePath();
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        File file = new File(filePath);

        if (file.exists()) {
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.addHeader("Content-Type", "application/binary");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
