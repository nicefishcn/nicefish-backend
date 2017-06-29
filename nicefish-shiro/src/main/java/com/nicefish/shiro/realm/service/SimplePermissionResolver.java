package com.nicefish.shiro.realm.service;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.springframework.beans.factory.annotation.Autowired;

public class SimplePermissionResolver implements PermissionResolver {

	@Autowired
	PermissionResources permissionResources;

	@Override
	public Permission resolvePermission(String perm) {
		if (perm == null)
			return null;
		else if (perm.startsWith(SimpleServicePermissionResource.SERVICE_PREFIX))
			return new SimpleServicePermissionResource(perm,permissionResources);
		else
			return new SimplePermission(perm,permissionResources);
	}

}
