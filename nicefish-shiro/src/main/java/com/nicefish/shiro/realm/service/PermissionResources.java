/**
 * 
 */
package com.nicefish.shiro.realm.service;

import java.util.List;
import java.util.Map;

/**
 * @author kk
 * @date 2013-12-4 16:43:10
 * @version 0.0.1
 */
public class PermissionResources {
	
	private Map<String,List<String>> resources;

	public Map<String, List<String>> getResources() {
		return resources;
	}

	public void setResources(Map<String, List<String>> resources) {
		this.resources = resources;
	}
	
}
