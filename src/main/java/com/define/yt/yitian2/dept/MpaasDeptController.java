package com.define.yt.yitian2.dept;

import com.definesys.mpaas.common.exception.MpaasRuntimeException;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: xulei
 * @since: 2018-11-13
 * @history: 1.2018-11-13 created by xulei
 */
@RestController
@RequestMapping(value = "/dept")
public class MpaasDeptController {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Response queryMpaasDept() {
        List<MpaasDept> table = sw.buildQuery()
                .doQuery(MpaasDept.class);
        return Response.ok().table(table);
    }


    @RequestMapping(value = "/pageQuery", method = RequestMethod.GET)
    public Response pageQueryMpaasDept(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "pageSize") Integer pageSize) {
        return sw.buildQuery()
                .sql("select * from mpaas_dept")
                .doPageQuery(page, pageSize, MpaasDept.class)
                .httpResponse();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response addMpaasDept(@RequestBody MpaasDept item) {
        Object key = sw.buildQuery()
                .bind(item)
                .doInsert();
        return Response.ok().data(key);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Response deleteMpaasDept(@RequestParam(value = "rowId") String rowId) {
        sw.buildQuery()
                .bind(MpaasDept.class)
                .addRowIdClause("id", "=", rowId)
                .doDelete();
        return Response.ok();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response updateMpaasDept(@RequestBody MpaasDept item) {
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
        sw.buildQuery()
                .fileName("dept")
                .doExport(response, MpaasDept.class);
    }

}