package com.xxx.common.web.security;

import com.xxx.common.repository.dao.PermissionDAO;
import com.xxx.common.repository.dao.RoleDAO;
import com.xxx.common.repository.dao.RolePermissionDAO;
import com.xxx.common.repository.entity.PermissionDO;
import com.xxx.common.repository.entity.RoleDO;
import com.xxx.common.repository.entity.RolePermissionDO;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

@Service("shiroFilerChainManager")
public class ShiroFilerChainManager {

	@Autowired
    private DefaultFilterChainManager filterChainManager;
	
	@Autowired
    private RoleDAO roleDAO;
	
	@Autowired
    private PermissionDAO permissionDAO;
	
	@Autowired
    private RolePermissionDAO rolePermissionDAO;

    private LinkedHashMap<String, NamedFilterList> defaultFilterChains;
    
    private final Logger log = LoggerFactory.getLogger(ShiroFilerChainManager.class);

    @PostConstruct
    public void init() {
    	
    	log.info("初始化执行...");
        defaultFilterChains = new LinkedHashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
//        List<UrlFilter> urlFilters = new ArrayList<UrlFilter>();
//        UrlFilter uf = new UrlFilter();
//        uf.setId(1L);
//        uf.setName("角色列表");
//        uf.setPermissions("role:*");
//        uf.setRoles("aa");
//        uf.setUrl("/role/*");
//        urlFilters.add(uf);
//        initFilterChains(urlFilters);
        List<RoleDO> roleList = roleDAO.queryAll();
        List<RolePermissionDO> rolePermissionList = rolePermissionDAO.getRolePermissionDOList();
        List<PermissionDO> permissionList = permissionDAO.queryAll();
        for (RoleDO roleDO : roleList) {
        	for (RolePermissionDO rolePermissionDO : rolePermissionList) {
    			if (roleDO.getId().equals(rolePermissionDO.getRoleId())) {
					for (PermissionDO permissionDO : permissionList) {
						if (rolePermissionDO.getPermissionId().equals(permissionDO.getId())) {
							if (CollectionUtils.isEmpty(roleDO.getPermissionSet())) {
								roleDO.setPermissionSet(new HashSet<PermissionDO>());
							}else{
								roleDO.getPermissionSet().add(permissionDO);
							}
						}
					}
				}
    		}
		}
        initFilterChains(roleList, permissionList);
    	log.info("filterChainManager",filterChainManager);
    }

    public void initFilterChains(List<RoleDO> roleList, List<PermissionDO> permissionList) {
        //1、首先删除以前老的filter chain并注册默认的
        filterChainManager.getFilterChains().clear();
        if(defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }
        //2、注册filter chain
        for (RoleDO roleDO : roleList) {
        	if (!CollectionUtils.isEmpty(roleDO.getPermissionSet())) {
        		for (PermissionDO permissionDO : roleDO.getPermissionSet()) {
        			filterChainManager.addToChain(permissionDO.getTarget(), "roles", roleDO.getRoleCode());
				}
			}
		}
        for (PermissionDO permissionDO : permissionList) {
        	filterChainManager.addToChain(permissionDO.getTarget(), "perms", permissionDO.getCode());
		}
    }

}
