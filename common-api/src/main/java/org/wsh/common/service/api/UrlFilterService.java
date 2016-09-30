package org.wsh.common.service.api;

import org.wsh.common.support.beans.UrlFilter;
import java.util.List;


/**
 * Url权限拦截
 * File Name: <UrlFilterService.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-6-12 上午9:48:27
 */
public interface UrlFilterService {
	
	/**
	 * 创建urlFilter
	 * @param urlFilter
	 * @return
	 */
    public UrlFilter createUrlFilter(UrlFilter urlFilter);
    
    /**
     * 更新
     * @param urlFilter
     * @return
     */
    public UrlFilter updateUrlFilter(UrlFilter urlFilter);
    
    /**
     * 删除
     * @param urlFilterId
     */
    public void deleteUrlFilter(Long urlFilterId);

    /**
     * 获取一个
     * @param urlFilterId
     * @return
     */
    public UrlFilter findOne(Long urlFilterId);
    
    /**
     * 获取所有
     * @return
     */
    public List<UrlFilter> findAll();
}
