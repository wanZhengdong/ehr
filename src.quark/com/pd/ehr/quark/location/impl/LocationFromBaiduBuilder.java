package com.pd.ehr.quark.location.impl;

import com.pd.base.EhrApi.Builder.IBuilder;
import com.pd.ehr.ip.util.IpUtil;
import com.pd.ehr.quark.location.vo.LocationFo;
import com.pd.ehr.quark.location.vo.LocationVo;
import com.pd.ehr.util.Json;
import com.pd.ehr.util.Json.JsonUtil;
import com.pd.ehr.util.Unicode;

import net.sf.json.JSONObject;

class LocationFromBaiduBuilder implements IBuilder<LocationFo, LocationVo>
{
    
    @Override
    public LocationVo build(LocationFo _in)
    {
        // 初始化Json条件
        String curUrl = "http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip=" + IpUtil.getCurW3Ip();
        Json.Dto.Fo fo = new Json.Dto.Fo().setUrl(curUrl);
        
        // Json查询
        String jsonResult = JsonUtil.html(fo);
        
        // Json解析
        String cnStr = new Unicode.Builder.ToCn().build(jsonResult);
        JSONObject jsonObject = JSONObject.fromObject(cnStr);
        String[] addressArr = jsonObject.getString("address").split("\\|");
        LocationVo locationVo = new LocationVo();
        locationVo.setName(addressArr[2]);
        return locationVo;
    }
    
}