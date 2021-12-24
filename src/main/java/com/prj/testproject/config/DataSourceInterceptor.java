package com.prj.testproject.config;

import com.prj.testproject.enums.Branch;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DataSourceInterceptor extends  HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String branch = request.getHeader("db");
        if (Branch.DB1.toString().equalsIgnoreCase(branch)) {
            DbContext.setThreadLocal(Branch.DB1);
        } else {
            DbContext.setThreadLocal(Branch.DB2);
        }
        return super.preHandle(request, response, handler);
    }
}
