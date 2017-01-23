package code;

import java.sql.*;

/**
 * Created by Ferdila Rahmi on 8/23/2016.
 */
public class DBHelper {
    private String ConAddress ="jdbc:mysql://localhost/data_sms?user=root&password="; //koneksi
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    private Connection conn = null;
    public DBHelper() throws Exception, SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(ConAddress);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        }catch(SQLException es){
            throw es;
        }
    }
    public void createQuery(String Query) throws Exception, SQLException{
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
            if(stmt.execute(Query)){
                rs = stmt.getResultSet();
            }
        }catch(SQLException es){
            throw es;
        }
    }
    public void createQueryMetaData(String Query) throws Exception, SQLException{
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
            if(stmt.execute(Query)){
                rs = stmt.getResultSet();
                rsmd = rs.getMetaData();
            }
        }catch(SQLException es){
            throw es;
        }
    }
    public void createUpdate(String Query) throws Exception, SQLException{
        try{
            stmt = conn.createStatement();
            int hasil = stmt.executeUpdate(Query);
        }catch(SQLException es){
            throw es;
        }
    }
    public ResultSet getResult() throws Exception{
        ResultSet Temp = null;
        try{
            return rs;
        }catch(Exception ex){
            throw ex;
        }
    }
    public ResultSetMetaData getResultMetaData() throws Exception{
        ResultSetMetaData Temp = null;
        try{
            return rsmd;
        }catch(Exception ex){
            throw ex;
        }
    }
    public void closeResult() throws Exception, SQLException{
        if(rs != null){
            try{rs.close();
            }catch(SQLException sqlEx){
                rs = null;
                throw sqlEx;
            }
        }
        if(stmt != null){
            try{
                stmt.close();
            }catch(SQLException sqlEx){
                stmt = null;
                throw sqlEx;
            }
        }
    }
    public void closeConnection()throws SQLException, Exception{
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException sqlEx){
                conn = null;
            }
        }
    }
    public void sqlTable(String Query) throws Exception, SQLException{
        try{
            stmt = conn.createStatement();
//            rs = stmt.execute(Query);
            if(stmt.execute(Query)){
                rs = stmt.getResultSet();
            }
        }catch(SQLException es){
            throw es;
        }
    }
}
