package com.pd.it.common.itf;

import java.util.List;

import com.pd.it.common.vo.PageList;
import com.pd.it.common.vo.PageVO;
import com.pd.it.common.vo.VO;

public interface IDbDao
{
    // @SelectProvider(method = "c", type = AiSqlProvider.class)
    int c(VO vo);
    
    int cs(List<VO> vo);
    
    VO r(VO fo);
    
    int u(VO vo);
    
    // @SelectProvider(method = "d", type = AiSqlProvider.class)
    int d(VO vo);
    
    int ds(List<VO> vo);
    
    List<VO> ra();
    
    List<VO> rs(VO fo);
    
    int pCount(VO fo, PageVO page);
    
    PageList<VO> p(VO fo);
    
}
