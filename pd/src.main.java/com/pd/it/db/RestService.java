package com.pd.it.db;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.pd.it.common.util.DbUtil;
import com.pd.it.common.vo.KV;
import com.pd.it.common.vo.VO;
import com.pd.it.dao.ICommonDao;

@RestController
@RequestMapping("rest")
public class RestService
{
    @Autowired
    private ICommonDao dao;
    
    @ResponseBody
    @RequestMapping(value = "r/{module}/{demension}/{id}", method = {RequestMethod.GET,
        RequestMethod.POST}, produces = "application/json;charset=utf-8")
    public String r(@PathVariable("") LinkedHashMap<String, String> path,
        @RequestParam LinkedHashMap<String, Object> in)
    {
        path.put("action", "r");
        in.put("id", path.get("id"));
        VO sqlCfg = CommonSqlProvider.cfg(new KV(path), new VO(in));
        VO rsVO = DbUtil.r(dao, sqlCfg);
        return JSON.toJSONString(rsVO);
    }
    
    @ResponseBody
    @RequestMapping(value = "ra/{module}/{demension}", method = {RequestMethod.GET,
        RequestMethod.POST}, produces = "application/json;charset=utf-8")
    public String ra(@PathVariable("") LinkedHashMap<String, String> path,
        @RequestParam LinkedHashMap<String, Object> in)
    {
        path.put("action", "ra");
        VO sqlCfg = CommonSqlProvider.cfg(new KV(path), new VO(in));
        List<VO> rsList = DbUtil.ra(dao, sqlCfg);
        return JSON.toJSONString(rsList);
    }
    
}
