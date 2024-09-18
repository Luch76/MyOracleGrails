package com.oci

import groovy.sql.Sql

import javax.sql.DataSource
import java.sql.Timestamp

import static org.springframework.http.HttpStatus.OK

class IndexController {

    DataSource dataSource;

    def index() {
        Sql sql = new Sql(dataSource);
        Map retVal = [:];
        Timestamp timestamp;

        sql.eachRow("SELECT SYSDATE FROM DUAL") {it ->
            retVal.databaseTime = it."SYSDATE";
        }
        sql.close();

        respond ([retVal: retVal]);

    }

}
