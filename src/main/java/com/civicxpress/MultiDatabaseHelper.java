package com.civicxpress;

import java.sql.Connection;
import java.sql.SQLException;

import com.tekdog.dbutils.DBQueryParams;
import com.tekdog.dbutils.DBRow;
import com.tekdog.dbutils.DBUtils;

public class MultiDatabaseHelper {
    public static Connection getMunicipalityDbConnection(Connection cx2Conn, Long municipalityId) throws SQLException {
    	String getMuniDbDetailsQuery = "SELECT DbName, DbUser, DbPassword FROM Municipalities WHERE ID=:municipalityId";
    	DBQueryParams muniDbDetailsParams = new DBQueryParams();
        muniDbDetailsParams.addLong("municipalityId", municipalityId);
    	
    	DBRow muniDetails = DBUtils.selectOne(cx2Conn, getMuniDbDetailsQuery, muniDbDetailsParams);
    	
    	String muniUrl = cx2Conn.getMetaData().getURL().replaceAll("databaseName=.+", "databaseName="+muniDetails.getString("DbName"));
    	return DBUtils.getConnection(muniUrl, muniDetails.getString("DbUser"), muniDetails.getString("DbPassword"));
    }
}
