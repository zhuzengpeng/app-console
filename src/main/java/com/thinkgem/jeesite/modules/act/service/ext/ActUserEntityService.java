/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.ext;

import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * Activiti User Entity Service
 * @author ThinkGem
 * @version 2013-11-03
 */
@Service
public class ActUserEntityService extends UserEntityManager {

	private SystemService systemService;

	public SystemService getSystemService() {
		if (systemService == null){
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}

	public User createNewUser(String userId) {
		return new UserEntity(userId);
	}

	public void insertUser(User user) {
		throw new RuntimeException("not implement method.");
	}

	public void updateUser(UserEntity updatedUser) {
		throw new RuntimeException("not implement method.");
	}

	public UserEntity findUserById(String userId) {
		return ActUtils.toActivitiUser(getSystemService().getUserByLoginName(userId));
	}

	public void deleteUser(String userId) {
		User user = findUserById(userId);
		if (user != null) {
			getSystemService().deleteUser(new com.thinkgem.jeesite.modules.sys.entity.User(user.getId()));
		}
	}

	public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
		throw new RuntimeException("not implement method.");
	}

	public long findUserCountByQueryCriteria(UserQueryImpl query) {
		throw new RuntimeException("not implement method.");
	}

	public List<Group> findGroupsByUser(String userId) {
		List<Group> list = Lists.newArrayList();
		for (Role role : getSystemService().findRole(new Role(new com.thinkgem.jeesite.modules.sys.entity.User(null, userId)))){
			list.add(ActUtils.toActivitiGroup(role));
		}
		return list;
	}

	public UserQuery createNewUserQuery() {
		throw new RuntimeException("not implement method.");
	}

	public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
		throw new RuntimeException("not implement method.");
	}

	public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
		throw new RuntimeException("not implement method.");
	}

	public Boolean checkPassword(String userId, String password) {
		throw new RuntimeException("not implement method.");
	}

	public List<User> findPotentialStarterUsers(String proceDefId) {
		throw new RuntimeException("not implement method.");

	}

	public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
		throw new RuntimeException("not implement method.");
	}

	public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
		throw new RuntimeException("not implement method.");
	}
	
}
