package org.wsh.common.basic.service.security.chain;

import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wsh.common.model.basic.PermissionDO;
import org.wsh.common.model.basic.RoleDO;
import org.wsh.common.model.basic.RolePermissionDO;
import org.wsh.common.service.api.PermissionService;
import org.wsh.common.service.api.RoleService;
import org.wsh.common.support.exception.BusinessException;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

@Service("shiroFilerChainManager")
public class ShiroFilerChainManager {

	@Autowired
    private DefaultFilterChainManager filterChainManager;
	
	@Autowired
    private RoleService roleService;
	
	@Autowired
    private PermissionService permissionService;
	
    private LinkedHashMap<String, NamedFilterList> defaultFilterChains;
    
    private final Logger log = LoggerFactory.getLogger(ShiroFilerChainManager.class);

    @PostConstruct
    public void init() throws Exception {
		try {
			log.info("初始化加载权限信息......");
			defaultFilterChains = new LinkedHashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
			List<RoleDO> roleList = roleService.getAllRole();
			List<RolePermissionDO> rolePermissionList = permissionService.getRolePermissionList();
			List<PermissionDO> permissionList = permissionService.getAllPermission();
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
		} catch (BusinessException e) {
			log.error("初始化权限异常:",e);
			throw e;
		}
    }

    private void initFilterChains(List<RoleDO> roleList, List<PermissionDO> permissionList) {
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
