package com.define.yt.yitian2.testGenerator;

import com.define.yt.yitian2.user.MpaasUser;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQuery;
import com.definesys.mpaas.query.MpaasQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/demo/query")
public class QueryController {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    /**
     * 查询表所有的记录
     *
     * @return
     */
    @RequestMapping(value = "/test01", method = RequestMethod.GET)
    public Response test01() {
        MpaasQuery query = sw.buildQuery();
        List<MpaasUser> mpaasUsers = query.doQuery(MpaasUser.class);
        return Response.ok().table(mpaasUsers);
    }

    @GetMapping(value = "/test02")
    public Response test02() {
        List<Map<String, Object>> maps = sw.buildQuery()
                .select("name,salary,birthday")
                .table("mpaas_user")
                .doQuery();
        return Response.ok().table(maps);
    }

    @GetMapping(value = "/test03")
    public Response test03() {
        List<MpaasUser> mpaasUsers = sw.buildQuery()
                .select("name,salary,birthday")//select控制sql语句中查询的字段
                .doQuery(MpaasUser.class);//doQuery控制存储检索结果的容器（对象或其他容器），这也代表了最终json数据格式。
        return Response.ok().table(mpaasUsers);
    }

    @GetMapping(value = "/test04")
    public Response test04() {
        List<Map<String, Object>> maps = sw.buildQuery()
                .eq("name", "朱七")
                .select("code,age,email")
                .table("mpaas_user")
                .addClause("age", "=", 40)
                .doQuery();
        //如果返回的json格式为pojo类，则可以调用doQuery(pojo.class)
        //反之需要指定表名（sql方法中，table方法中）
        return Response.ok().table(maps);
    }

    @GetMapping(value = "/test05")
    public Response test05() {
        List<Map<String, Object>> maps = sw.buildQuery()
                .select("code,u.name,email,d.name")
                .table("mpaas_user u,mpaas_dept d")
                .addClause("dept_id", "=", 1)
                //.eq("dept_id", 1)
                .doQuery();
        return Response.ok().table(maps);
    }

    @GetMapping(value = "/test06")
    public Response test06() {
        List<Map<String, Object>> maps = sw.buildQuery()
                //表关联加条件查询：1、先做表关联，并作为一个子查询。2、外面再嵌套一个查询，并且子查询必须给出别名
                .sql("select * from(select u.id,u.name uname,age,salary,u.email,u.dept_id,d.name dname,d.manager from mpaas_user u,mpaas_dept d where dept_id=d.id) temp")
                .gt("age", 30)
                .gt("id", 6)
                .gt("salary", 7000)
                .doQuery();
        return Response.ok().table(maps);
    }

    @GetMapping(value = "/test07")
    public Response test07() {
        List<Map<String, Object>> maps = sw.buildQuery()
                .sql("select * from(select u.id,u.name uname,age,salary,u.email,u.dept_id,d.name dname,d.manager from mpaas_user u,mpaas_dept d where dept_id=d.id) temp")
                .or()
                //与或非等运算符，作用在所包含的条件之前（避免条件一多，需要逐一连接）
                .gt("age", 30)
                .gt("salary", 4000)
                .eq("email", "lei.xu@definesys.com")
                .conjuctionAnd()
                .gt("id", 3)
                .doQuery();
        return Response.ok().table(maps);
    }


}
