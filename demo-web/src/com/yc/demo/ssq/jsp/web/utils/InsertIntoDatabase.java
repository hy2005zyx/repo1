package com.yc.demo.ssq.jsp.web.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ly.util.DBHelper;

public class InsertIntoDatabase {
	
	public int insert(String str) throws SQLException{
		String[] dataStrs = str.split("\\n"); 
		StringBuffer sb=new StringBuffer( "insert into lottery(opendate,num,redone,redtwo,redthree,redfour,redfive,redsix,blue,sale,firstprize,secondeprize) values");
		List<Object> params=new ArrayList<Object>();
		for(   String s: dataStrs){
			String [] ss=s.split("\\t");
			
			String [] strs=ss[0].split(" ");
			String opendate=strs[0];
			String num=strs[1];
			
			strs=ss[1].split("\\s");
			String redone=strs[1];
			String redtwo=strs[2];
			String redthree=strs[3];
			String redfour=strs[4];
			String redfive=strs[5];
			String redsix=strs[6];
			String blue=strs[8];
			
			String sale=ss[2].trim();
			
			strs=ss[3].split("\\s");
			String firstprize=strs[1];
			String secondeprize=strs[2];
			
			/**
			 * sqlite 数据库的sql语句不能使用超过999个参数，因此改为直接将参数写到sql语句中
			 */
			String s1 = "('?','?','?','?','?','?','?','?','?','?','?','?'),";
			s1 = Utils.replaceAll(s1, "\\?", opendate,num,redone,redtwo,redthree,redfour,redfive,
					redsix,blue,sale,firstprize,secondeprize);
			sb.append(s1);
			
			/*params.add( opendate  );
			params.add( num  );
			params.add( redone  );
			params.add( redtwo  );
			params.add( redthree  );
			params.add( redfour  );
			params.add( redfive  );
			params.add( redsix  );
			params.add( blue  );
			params.add( sale  );
			params.add( firstprize  );
			params.add( secondeprize  );*/
		}
		String s=sb.substring(0, sb.length()-1);
		//System.out.println("sql语句为:"+s);
		
		return DBHelper.doUpdate(s, params.toArray());
	}
}
