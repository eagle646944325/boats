package cn.gelk;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Excel_reader test= new Excel_reader();
        ArrayList<ArrayList<String>> arr=test.xlsx_reader("E:\\20180327.xlsx",0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);  //后面的参数代表需要输出哪些列，参数个数可以任意
        for(int i=1;i<arr.size();i++){
            ArrayList<String> row=arr.get(i);
//            for(int j=0;j<row.size();j++){
//                System.out.print(row.get(j)+" ");
//            }
//            System.out.println("");

            String sql="insert  into `w_poetry`(`name`,`context`,`introduce`,`wexplain`,`translation`,`appreciation`,`review`,`poetry_order`,`author`,`background_image_id`,`create_time`,`state`,`out_poetry_id`,`out_author_id`,`primary`,`intermediate`,`senior`,`tryout`)" +
                    " values ('"+row.get(1)+"','"+row.get(5)+"','"+row.get(8)+"','"+row.get(9)+"','"+row.get(10)+"','"+row.get(11)+"','"+row.get(12)+"','"+row.get(6)+"',NULL,NULL,NULL,'1','"+row.get(0)+"','"+row.get(4)+"','"+row.get(13)+"','"+row.get(14)+"','"+row.get(15)+"','"+row.get(16)+"');";
            System.out.println(sql);
        }

    }
}
