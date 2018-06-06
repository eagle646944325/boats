package cn.gelk;

import java.io.IOException;
import java.util.ArrayList;

public class problem {
    public static void main(String[] args) throws IOException {
        Excel_reader test= new Excel_reader();
        ArrayList<ArrayList<String>> arr=test.xlsx_reader("E:\\20180329.xlsx",0,1,2,3,4,5,6,7,8,9,10,11,12,13,14);  //后面的参数代表需要输出哪些列，参数个数可以任意
        for(int i=1;i<arr.size();i++){
            ArrayList<String> row=arr.get(i);
//            for(int j=0;j<row.size();j++){
//                System.out.print(row.get(j)+" ");
//            }
            String sql="insert  into `w_poetry_problem`(`poetry_id`,`title`,`problem`,`select1`,`select2`,`select3`,`select4`,`answer`,`problem_type`,`poetry_name`,`poetry_author`,`difficulty`,`tryout`) values " +
                    "('"+row.get(0)+"','"+row.get(6)+"','"+row.get(7)+"','"+row.get(8)+"','"+row.get(9)+"','"+row.get(10)+"','"+row.get(11)+"','"+row.get(12)+"','"+row.get(1)+"','"+row.get(2)+"','"+row.get(3)+"','"+row.get(13)+"','"+row.get(14)+"');";
            System.out.println(sql);

        }

    }
}
