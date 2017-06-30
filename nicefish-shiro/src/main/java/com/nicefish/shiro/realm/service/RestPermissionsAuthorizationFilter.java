package com.nicefish.shiro.realm.service;


import com.alibaba.fastjson.JSON;
import com.nicefish.common.constant.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by newroc on 13-11-17.
 */
public class RestPermissionsAuthorizationFilter extends AuthorizationFilter {
	Log logger = LogFactory.getLog(RestPermissionsAuthorizationFilter.class);

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

		Subject subject = getSubject(request, response);
		String[] resources = this.buildPermissions(request);
        Session session = subject.getSession(true);

		boolean isPermitted = true;
        if(session.getAttribute("_USERINFO")==null){
            isPermitted = false;
        }else{
            if (resources != null && resources.length > 0) {
                if (resources.length == 1) {
                    if (!subject.isPermitted(resources[0])) {
                        isPermitted = false;
                    }
                } else {
                    if (!subject.isPermittedAll(resources)) {
                        isPermitted = false;
                    }
                }
            }
            /*if (isPermitted&&subject.isRemembered()) {
                HttpServletResponse res = (HttpServletResponse) response;
                res.addCookie(new Cookie("XSRF-TOKEN", "User1"));
            }*/
        }

		return isPermitted;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		Subject subject = getSubject(request, response);
        String url="";
        Session session = subject.getSession(true);
        if(request instanceof HttpServletRequest){
            url= ((HttpServletRequest)request).getRequestURI();
        }
		// If the subject isn't identified, redirect to login URL
		if (subject.getPrincipal() == null||session.getAttribute("_USERINFO")==null) {
			genResponse(response, "用户未登录或登录超时,请重新登录.url="+url, "NOT_LOGIN", 401);
		} else {
			// If subject is known but not authorized, redirect to the
			// unauthorized URL if there is one
			// If no unauthorized URL is specified, just return an unauthorized
			// HTTP status code
			genResponse(response, "用户没有权限访问该服务.url="+url, "UNAUTHORIZED", 403);

		}
		return false;
	}

	private void genResponse(ServletResponse response, String errormsg, String errorCode, int headerStatus) throws IOException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.setStatus(headerStatus);
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSON.toJSONString(new Result(false,errormsg, headerStatus)));
        response.getWriter().flush();
	}

	protected String[] buildPermissions(ServletRequest request) {
		String[] resources = new String[1];
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI();
		if (path == null)
			return null;
		String[] tmp = path.split("/");
		resources[0] = SimpleServicePermissionResource.SERVICE_PREFIX + ((tmp.length > 2) ? (tmp[1] + "." + tmp[2]) : (tmp[1] + ".list"));
		System.out.println("  ========>  buildPerm: " + resources[0]);
		return resources;
	}

}
