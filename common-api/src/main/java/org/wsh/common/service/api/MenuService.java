package org.wsh.common.service.api;

import java.util.List;
import org.springframework.stereotype.Service;
import org.wsh.common.model.basic.MenuDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

@Service
public interface MenuService {

	/**
	 * 多条件查询栏目信息
	 * @param menuDO
	 * @param pagination
	 * @return
	 */
	public OptionsResponseDO<List<MenuDO>> getMenuListByParams(MenuDO menuDO, Pagination pagination) throws BusinessException;

	/**
	 * 添加栏目信息
	 * @param currentUserId
	 * @param MenuName
	 * @param resId
	 * @param fatherId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doAddMenu(Long currentUserId, String MenuName, Long resId, Long fatherId)throws BusinessException;
	
	/**
	 * 修改栏目信息
	 * @param currentUserId
	 * @param menuId
	 * @param MenuName
	 * @param resId
	 * @param fatherId
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doUpdateMenu(Long currentUserId, Long menuId, String MenuName, Long resId, Long fatherId)throws BusinessException;
	
	/**
	 * 删除栏目信息
	 * @param currentUserId
	 * @param menuId
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doDelMenu(Long currentUserId, Long menuId)throws BusinessException;
	
	/**
	 * 添加栏目信息
	 * @param menuDO
	 * @return
	 */
	public int addMenu(MenuDO menuDO);
	
	/**
	 * 获取所有一级栏目
	 * @return
	 */
	public List<MenuDO> getAllRootMenu();
	
	/**
	 * 获取所有子栏目信息
	 * @return
	 */
	public List<MenuDO> getAllChildrenMenu();
	
	/**
	 * 根据用户名获取用户栏目信息
	 * @param userName
	 * @return
	 */
	public List<MenuDO> getMenuListByUserName(String userName);
	
	/**
	 * 根据当前用户用户名和当前请求URL获取用户栏目信息
	 * @param userName
	 * @param currentURL
	 * @return
	 */
	public List<MenuDO> getMenuList(String currentUserName, String currentRequestURL);

	/**
	 * 验证当前用户是否拥有此地址权限
	 * @param currentUserName
	 * @param currentRequestURL
	 * @return
	 */
	public boolean validatePermission(String currentUserName, String currentRequestURL);

	/**
	 * 根据栏目Id获取栏目信息
	 * @param menuId
	 * @return
	 * @throws BusinessException
	 */
	public MenuDO getMenuById(Long menuId) throws BusinessException;
}

