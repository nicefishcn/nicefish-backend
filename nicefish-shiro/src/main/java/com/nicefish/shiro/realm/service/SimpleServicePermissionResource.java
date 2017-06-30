package com.nicefish.shiro.realm.service;

public class SimpleServicePermissionResource extends SimplePermission {
	
	public static final String SERVICE_PREFIX = "rest:";
	
	public SimpleServicePermissionResource(String permissionString, PermissionResources permissionResources) {
		super(permissionString.replace(SERVICE_PREFIX, ""),permissionResources);
	}

	public boolean isResource(){
		return Boolean.TRUE;
	}
}
