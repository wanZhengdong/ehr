package com.pd.common.service;

import com.pd.common.dao.EnvPropertyDao;
import com.pd.common.dao.IPropertyDao;

public class EnvPropertyService implements IPropertyService
{
    private IPropertyDao dao;
    public EnvPropertyService(){
        dao=new EnvPropertyDao();
    }
    @Override
    public String value(String key)
    {
        return dao.getString(key);
    }
    
}
