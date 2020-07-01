package com.syntel.diwaligift.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;


public class JSONConverter
{
	
	public static JSONArray convertToJSON(ResultSet resultSet) throws Exception 
	{        
		JSONArray jsonArray = new JSONArray();
		//System.out.println("inside");
		while (resultSet.next()) 
			{
		//	System.out.println("converter ");
				int total_rows = resultSet.getMetaData().getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i = 0; i < total_rows; i++) 
				{
					obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getString(i + 1));
				}
				jsonArray.put(obj);
				
			}  
		return jsonArray;
	} 
	

	public static JSONArray convertToJSONSet(SqlRowSet resultSet) throws Exception 
	{        
		JSONArray jsonArray = new JSONArray();
		//System.out.println("inside");
		while (resultSet.next()) 
			{
		//	System.out.println("converter ");
				int total_rows = resultSet.getMetaData().getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i = 0; i < total_rows; i++) 
				{
					obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getString(i + 1));
				}
				jsonArray.put(obj);
			}  
		return jsonArray;
	} 
	
	
	
	 public static JSONArray convertNew( SqlRowSet rs )
	    throws SQLException, JSONException
	  {
	    JSONArray json = new JSONArray();
	    SqlRowSetMetaData rsmd = rs.getMetaData();

	    while(rs.next()) {
	      int numColumns = rsmd.getColumnCount();
	      JSONObject obj = new JSONObject();

	    
	     
	     
	      
	      for (int i=1; i<numColumns+1; i++) {
	        String column_name = rsmd.getColumnName(i);
	        //System.out.println(rsmd.getColumnType(i)+"%%%%%%%%%%%%%%%% "+column_name);
	        
	        
	        if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
	         obj.put(column_name, ((ResultSet) rs).getArray(column_name));
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
	         obj.put(column_name, rs.getInt(column_name));
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
	         obj.put(column_name, rs.getBoolean(column_name));
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
	         obj.put(column_name, ((ResultSet) rs).getBlob(column_name));
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
	         obj.put(column_name, rs.getDouble(column_name)); 
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
	         obj.put(column_name, rs.getFloat(column_name));
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
	         obj.put(column_name, rs.getInt(column_name));
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
	        	
	         obj.put(column_name, ((ResultSet) rs).getNString(column_name));
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
	        	
	         obj.put(column_name, rs.getString(column_name));
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.CLOB){
	        	
	         Clob clobObject = (Clob)rs.getObject(column_name);                                       
	          final StringBuilder sb = new StringBuilder();
	          try
	              {
	                  final Reader reader = clobObject.getCharacterStream();
	                  final BufferedReader br = new BufferedReader(reader);
	                  int b;
	                  while(-1 != (b = br.read()))
	                  {
	                      sb.append((char)b);
	                  }
	                  br.close();
	                  String theName = sb.toString();
	                  obj.put(column_name,theName);
	                  
	              }
	          catch (Exception ex)
	          {
	        	  System.out.println("Inside catch");
	        	  obj.put(column_name,"");

	          }
	         
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
	         obj.put(column_name, rs.getInt(column_name));
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
	         obj.put(column_name, rs.getInt(column_name));
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
	         obj.put(column_name, rs.getDate(column_name));
	        }
	        else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
	        obj.put(column_name, rs.getTimestamp(column_name));   
	        }
	        else{
	         obj.put(column_name, rs.getObject(column_name));
	        }
	      }

	      json.put(obj);
	    }

	    return json;
	  }
	 
}
	
	
	
	
	