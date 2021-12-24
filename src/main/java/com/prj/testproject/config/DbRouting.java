package com.prj.testproject.config;

import com.prj.testproject.enums.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DbRouting extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        Branch threadLocal = DbContext.getThreadLocal();
        return threadLocal;
    }

    public void initDataSource(Environment env) {
        Map<Object, Object> datasourcemap = new HashMap<>();
        String user = env.getProperty("db1.datasource.username");
        String pass = env.getProperty("db1.datasource.password");
        DriverManagerDataSource dataSource =new DriverManagerDataSource(env.getProperty("db1.datasource.url"), user, pass);

        datasourcemap.put(Branch.DB1,dataSource );
        datasourcemap.put(Branch.DB2, new DriverManagerDataSource(env.getProperty("db2.datasource.url"), user, pass));
        this.setTargetDataSources(datasourcemap);
        this.setDefaultTargetDataSource(dataSource);
    }
}
