package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.Taex_cataltdasDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
@Transactional
public class Taex_cataltdasDaoImpl extends JdbcDaoSupport implements Taex_cataltdasDao {


    @Autowired
    private DataSource dataSource2;
    private JdbcTemplate jdbcTemplate;

    public Taex_cataltdasDaoImpl(DataSource dataSource2) {
        this.setDataSource(dataSource2);
        this.jdbcTemplate = new JdbcTemplate(dataSource2);
    }


    public String getIpSucursal (Number fisucursal){

        String sql = "SELECT fcip FROM XTRACT.TAEX_CATALTDAS WHERE FISUCURSAL = ?";

        String ip = (String)getJdbcTemplate().queryForObject(
                sql, new Object[] { fisucursal }, String.class);
       // logger.info(ip);
        return ip;
    }
}
