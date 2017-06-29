package com.nicefish.shiro.realm.service;

import org.apache.shiro.authz.Permission;
import org.springframework.util.StringUtils;

/**
 * @author kimmking (kimmking@163.com)
 * @date 2013-12-5
 */
public class SimplePermission implements Permission {

	private String perm;
	private PermissionResources permissionResources;

	public String getPerm() {
		return perm;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	}

	public SimplePermission(String permissionString, PermissionResources permissionResources) {
		this.perm = permissionString;
		this.permissionResources = permissionResources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.authz.Permission#implies(org.apache.shiro.authz.Permission
	 * )
	 */
	@Override
	public boolean implies(Permission p) {
        SimplePermission simplePermission = (SimplePermission) p;
        if (simplePermission == null | StringUtils.isEmpty(simplePermission.getPerm())) {
            return true;
        }
        if(this.getPerm().equals(simplePermission.getPerm().replace(".","/"))){
            return true;
        }
        return false;
		/*SimplePermission simplePermission = (SimplePermission) p;
		if (simplePermission == null | StringUtils.isEmpty(simplePermission.getPerm()))
			return true;
		if (simplePermission.isResource()) {
			if (permissionResources == null && permissionResources.getResources() == null)
				return false;
            List<String> resources = permissionResources.getResources().get(this.getPerm().split("/")[0]);
            if (resources != null) {
                System.out.println(resources.toString());
                for(String r : resources){
					if(simplePermission.getPerm().equalsIgnoreCase(r)) return true;
				}
			}
			return false;
		} else
			return this.getPerm() != null && this.getPerm().equalsIgnoreCase(simplePermission.getPerm());*/
	}

	@Override
	public String toString() {
		return "SimplePermission[" + this.perm + "]";
	}

	public boolean isResource() {
		return Boolean.FALSE;
	}

}