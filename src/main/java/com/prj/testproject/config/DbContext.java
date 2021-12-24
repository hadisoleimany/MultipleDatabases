package com.prj.testproject.config;

import com.prj.testproject.enums.Branch;

public class DbContext {
    private static ThreadLocal<Branch> threadLocal=new ThreadLocal<>();

    public static Branch getThreadLocal() {
        return threadLocal.get();
    }

    public static void setThreadLocal(Branch branch) {
        threadLocal.set(branch);
    }
    public static void clearThreadLocal(){
        threadLocal.remove();
    }
}
