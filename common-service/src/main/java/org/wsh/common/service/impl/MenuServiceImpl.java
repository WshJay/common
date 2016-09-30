package org.wsh.common.service.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.service.api.UserService;
import org.wsh.common.dao.MenuDAO;
import org.wsh.common.dao.PermissionDAO;
import org.wsh.common.model.basic.MenuDO;
import org.wsh.common.model.basic.PermissionDO;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticOptionsResponseDO;


@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDAO menuDAO;
	
	@Autowired
	private PermissionDAO permissionDAO;
	
	@Autowired
	private UserService userService;
	
	private final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public OptionsResponseDO<List<MenuDO>> getMenuListByParams(
			MenuDO menuDO, Pagination pagination) throws BusinessException {
		try {
			int totalCount = menuDAO.queryCountByParams(menuDO);
			if (totalCount <= 0) {
				return newStaticOptionsResponseDO();
			}
			pagination.setTotalCount(totalCount);
			List<MenuDO> menuList = menuDAO.queryByParams(menuDO, pagination.getRowBounds());
			return newOptionsResponseDO(totalCount, menuList, pagination.getPageSize(),pagination.getPP());
		} catch (Exception e) {
			throw new BusinessException("多条件查询栏目信息异常",e);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doAddMenu(Long currentUserId, String MenuName,
								Long resId, Long fatherId) throws BusinessException {
		try {
			// TODO validata
			MenuDO menuDO = new MenuDO();
//			menuDO.setCreateUserId(currentUserId);
			menuDO.setName(MenuName);
			menuDO.setResId(resId);
			menuDO.setFatherId(fatherId);
			menuDAO.insertMenu(menuDO);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("添加栏目信息异常，参数currentUserId:[%s],MenuName:[%s],resId:[%s],fatherId:[%s]", currentUserId,MenuName,
				resId,fatherId));
		}
	}
	
	@Override
	public int addMenu(MenuDO menuDO) {
		return menuDAO.insertMenu(menuDO);
	}
	
	@Override
	public List<MenuDO> getMenuListByUserName(String userName) {
		
			// 获取所有一级栏目信息
			List<MenuDO> rootList = menuDAO.queryAllFatherMenu();
			// 获取所有非一级栏目信息
			List<MenuDO> childrenList = menuDAO.queryAllChildrenMenu();
			if (CollectionUtils.isEmpty(rootList)) {
				return Collections.emptyList();
			}
			UserBasicDO user = userService.getUserRoleByUserName(userName);
			List<Long> permIdList = new ArrayList<Long>();
			List<PermissionDO> pList = new ArrayList<PermissionDO>(user.getPermissionSet());
			for (PermissionDO permissionDO : pList) {
				permIdList.add(permissionDO.getId());
			}
			// 最终需要的栏目信息
			List<MenuDO> menuList = new ArrayList<MenuDO>();
			for (MenuDO root : rootList) {
				List<MenuDO> cList = new ArrayList<MenuDO>();
				for (MenuDO children : childrenList) {
					if (root.getId() == children.getFatherId() && isHavePermission(permIdList, children.getResId(), children.getAllow())) {
						cList.add(children);
					}
				}
				if (!CollectionUtils.isEmpty(cList)) {
					root.setChildren(cList);
					menuList.add(root);
				}
			}
		return menuList;
	}
	
	public boolean isHavePermission(List<Long> permIdList, Long permissionId, int allow){
		if (allow == 1 || permIdList.contains(permissionId)) {
			return true;
		}
		return false;
	}

	@Override
	public List<MenuDO> getMenuList(String currentUserName,
			String currentRequestURL) {
		// 获取所有一级栏目信息
		List<MenuDO> rootList = menuDAO.queryAllFatherMenu();
		if (CollectionUtils.isEmpty(rootList)) {
			return Collections.emptyList();
		}
		// 获取该用户所有栏目权限ID
		UserBasicDO user = userService.getUserRoleByUserName(currentUserName);
		List<Long> permIdList = new ArrayList<Long>();
		List<PermissionDO> pList = new ArrayList<PermissionDO>(user.getPermissionSet());
		for (PermissionDO permissionDO : pList) {
			// 权限类型为1表示为栏目权限
			if (permissionDO.getType() == 1) {
				permIdList.add(permissionDO.getId());
			}
		}
		// 获取所有非一级栏目信息
		List<MenuDO> childrenList = menuDAO.queryAllChildrenMenu();
		// 最终需要的栏目信息
		List<MenuDO> menuList = new ArrayList<MenuDO>();
		for (MenuDO root : rootList) {
			List<MenuDO> cList = new ArrayList<MenuDO>();
			for (MenuDO children : childrenList) {
				if (root.getId().equals(children.getFatherId()) && isHavePermission(permIdList, children.getResId(), children.getAllow())) {
					if (children.getTarget().equals(currentRequestURL)) {
						children.setCurrent(true);
						root.setCurrent(true);
					}
					cList.add(children);
				}
			}
			if (!CollectionUtils.isEmpty(cList)) {
				root.setChildren(cList);
				menuList.add(root);
			}
		}
		return menuList;
	}

	@Override
	public List<MenuDO> getAllRootMenu() {
		return menuDAO.queryAllFatherMenu();
	}

	@Override
	public boolean validatePermission(String currentUserName,
			String currentRequestURL) {
		
		//查询地址所属权限
		PermissionDO perm = permissionDAO.queryByTarget(currentRequestURL);
		// 未查询到权限
		if (perm == null) {
			return true;
		}
		// 获取该用户所有栏目权限ID
		UserBasicDO user = userService.getUserRoleByUserName(currentUserName);
		List<Long> permIdList = new ArrayList<Long>();
		List<PermissionDO> pList = new ArrayList<PermissionDO>(user.getPermissionSet());
		for (PermissionDO permissionDO : pList) {
				permIdList.add(permissionDO.getId());
		}
		return isHavePermission(permIdList, perm.getId(), perm.getAllow());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doUpdateMenu(Long currentUserId, Long menuId,
			String MenuName, Long resId, Long fatherId)
			throws BusinessException {
		try {
			// TODO validata
			MenuDO menuDO = new MenuDO();
			menuDO.setId(menuId);
			menuDO.setName(MenuName);
			menuDO.setResId(resId);
			menuDO.setFatherId(fatherId);
			menuDAO.updateMenu(menuDO);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("修改栏目信息异常，参数currentUserId:[%s],menuId:[%s],MenuName:[%s],resId:[%s],fatherId:[%s]", currentUserId, menuId, MenuName, resId, fatherId));
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doDelMenu(Long currentUserId, Long menuId)throws BusinessException {
		try {
			// TODO validata
			menuDAO.deleteById(menuId);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("删除栏目信息异常，参数currentUserId:[%s],menuId:[%s]", currentUserId, menuId));
		}
	}

	@Override
	public MenuDO getMenuById(Long menuId) throws BusinessException {
		try {
			MenuDO menuDO = menuDAO.queryById(menuId);
			return menuDO;
		} catch (Exception e) {
			throw new BusinessException(String.format("根据栏目ID:[%s],获取栏目信息", menuId));
		}
	}

	@Override
	public List<MenuDO> getAllChildrenMenu() {
		return menuDAO.queryAllChildrenMenu();
	}

}

