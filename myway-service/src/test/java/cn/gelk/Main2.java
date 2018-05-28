package cn.gelk;

import java.io.IOException;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) throws IOException {
        Excel_reader test= new Excel_reader();
        ArrayList<ArrayList<String>> arr=test.xlsx_reader("E:\\20180327-1060.xlsx",0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76);  //后面的参数代表需要输出哪些列，参数个数可以任意
        int[] theamId={6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97};
        for(int i=1;i<arr.size();i++){
            ArrayList<String> row=arr.get(i);
            String proId=row.get(0);
            for(int j=1;j<row.size();j++) {
//                System.out.print(row.get(j) + " ");
              if("1".equals(row.get(j))){
                  String sql=" insert  into `w_theme_poetry`(`poetry_id`,`theme_id`) values ('"+proId+"',"+theamId[j-1]+");";
                  System.out.println(sql);
              }

            }



//            String sql="insert  into `w_poetry`(`name`,`context`,`introduce`,`wexplain`,`translation`,`appreciation`,`review`,`poetry_order`,`author`,`background_image_id`,`create_time`,`state`,`out_poetry_id`,`out_author_id`,`primary`,`intermediate`,`senior`,`tryout`)" +
//                    " values ('"+row.get(1)+"','"+row.get(5)+"','"+row.get(8)+"','"+row.get(9)+"','"+row.get(10)+"','"+row.get(11)+"','"+row.get(12)+"','"+row.get(6)+"',NULL,NULL,NULL,'1','"+row.get(0)+"','"+row.get(4)+"','"+row.get(13)+"','"+row.get(14)+"','"+row.get(15)+"','"+row.get(16)+"');";
//            System.out.println(sql);
        }

    }
}
