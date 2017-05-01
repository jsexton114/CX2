package com.civicxpress.letters;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DatabaseConnectionInfo {

    String dbUrl = null;
    String dbUser = null;
    String dbPassword = null;
    String dbName = null;
    int dbPortNumber;

    public DatabaseConnectionInfo() {
    	
    }

    public DatabaseConnectionInfo(String url, String user, String password, String name, int portNumber) {
    	if (url != null) this.dbUrl = url;
    	if (user != null) this.dbUser = user;
    	if (password != null) this.dbPassword = password;
    	if (name != null) this.dbName = name;
    	this.dbPortNumber = portNumber;
    }
    
    public void readFromWebContext() {
        Context ctx = null;
        try {
            ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            this.dbUrl = (String) env.lookup("dbUrl");
            this.dbUser = (String) env.lookup("dbUser");
            this.dbPassword = (String) env.lookup("dbPassword");
            this.dbName = (String) env.lookup("dbName");
            this.dbPortNumber = (int) env.lookup("dbPortNumber");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public int getDbPortNumber() {
		return dbPortNumber;
	}

	public void setDbPortNumber(int dbPortNumber) {
		this.dbPortNumber = dbPortNumber;
	}
    
    
}
