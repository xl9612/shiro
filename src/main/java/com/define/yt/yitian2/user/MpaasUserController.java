package com.define.yt.yitian2.user;

import com.define.yt.yitian2.common.util.FileUtils;
import com.define.yt.yitian2.properties.DragonProperties;
import com.define.yt.yitian2.properties.FileProperties;
import com.definesys.mpaas.common.exception.MpaasBusinessException;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQuery;
import com.definesys.mpaas.query.MpaasQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: xulei
 * @since: 2018-11-14
 * @history: 1.2018-11-14 created by xulei
 */
@RestController
@RequestMapping(value = "/user")
public class MpaasUserController {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    @Autowired
    private DragonProperties dragonProperties;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Response queryMpaasUser() {
        MpaasQuery query = sw.buildQuery();
//        List<MpaasUser> mpaasUsers = sw.buildQuery()
//                .sql("select * from mpaas_user")
//                .doQuery(MpaasUser.class);
//        return Response.ok().table(mpaasUsers);
        List<MpaasUser> mpaasUsers = query.select("name,email")
                .doQuery(MpaasUser.class);
        return Response.ok().table(mpaasUsers);
    }


    @RequestMapping(value = "/pageQuery", method = RequestMethod.GET)
    public Response pageQueryMpaasUser(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "pageSize") Integer pageSize) {
        return sw.buildQuery()
                .doPageQuery(page, pageSize, MpaasUser.class)
                .httpResponse();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response addMpaasUser(@RequestBody MpaasUser item) {
        Object key = sw.buildQuery()
                .bind(item)
                .doInsert();
        return Response.ok().data(key);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Response deleteMpaasUser(@RequestParam(value = "rowId") String rowId) {
        sw.buildQuery()
                .bind(MpaasUser.class)
                .addRowIdClause("id", "=", rowId)
                .doDelete();
        return Response.ok();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response updateMpaasUser(@RequestBody MpaasUser item) {
        sw.buildQuery()
                .addRowIdClause("id", "=", item.getRowId())
                .doUpdate(item);
        return Response.ok();
    }

    /**
     * 导出excel
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletResponse response) {
        sw.buildQuery("mpaasuser_v")
                .fileName("user.xlsx")
                .doExport(response, MpaasUser.class);
    }

    @PostMapping(value = "/upload")
    public Response upload(@RequestParam(value = "file") MultipartFile uploadFile) {
        if (uploadFile.isEmpty())
            throw new MpaasBusinessException("上传文件不能为空！");
        File file = new File("d:/upload/user");
        if (!file.exists())
            file.mkdirs();
        String fileName = uploadFile.getOriginalFilename();
        try {
            uploadFile.transferTo(new File(file, fileName));
        } catch (IOException e) {
            throw new MpaasBusinessException("文件上传失败！");
        }
        return Response.ok().setMessage("文件上传成功！");
    }

    @PostMapping(value = "/uploadMany")
    public Response upload02(@RequestParam(value = "fileList") List<MultipartFile> fileList) {
        long maxFileSize = 0;
        for (MultipartFile mfile : fileList
        ) {

            maxFileSize += mfile.getSize();
            if (maxFileSize > 40000000)
                throw new MpaasBusinessException("");
        }
        if (fileList == null || fileList.isEmpty())
            throw new MpaasBusinessException("文件不能为空！");
        File file = new File("d:/uploadMany/user");
        if (!file.exists())
            file.mkdirs();
        for (MultipartFile mfile : fileList
        ) {
            try {
                System.out.println(mfile.getSize());
                if (mfile.getSize() > 1000000)
                    throw new MpaasBusinessException("文件大小不能超过。。。。。。");
                mfile.transferTo(new File(file, mfile.getOriginalFilename()));
            } catch (IOException e) {
                throw new MpaasBusinessException("文件" + mfile.getOriginalFilename() + "上传失败！");
            }
        }
        return Response.ok().setMessage("文件上传成功！");
    }

    @PostMapping(value = "/test")
    public Response test(@RequestParam(value = "fileList") List<MultipartFile> fileList) {
        FileProperties fileProperties = dragonProperties.getUpload();
        fileProperties.init();
        System.out.println(fileProperties.getFileSizeNum() + ":" + fileProperties.getReqSizeNum());
        //fileProperties.setDestPath("");手动设置文件的保存路径
        fileProperties.setDestPath(fileProperties.getDestPath() + "img");
        FileUtils.fileUpload(fileList, fileProperties);
        return Response.ok().setMessage("文件上传成功！");
    }
}