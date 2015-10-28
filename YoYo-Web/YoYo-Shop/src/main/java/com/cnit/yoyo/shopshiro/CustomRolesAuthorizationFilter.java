package com.cnit.yoyo.shopshiro;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class CustomRolesAuthorizationFilter extends AuthorizationFilter { 

    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException { 

        Subject subject = getSubject(request, response); 
        String[] rolesArray = (String[]) mappedValue; 

        if (rolesArray == null || rolesArray.length == 0) { 
            //no roles specified, so nothing to check - allow access. 
            return true; 
        } 

        for(int i=0;i<rolesArray.length;i++)
        {
            if(subject.hasRole(rolesArray[i]))
            {  
                return true;  
            }  
        }  
        return false; 
    }


} 
