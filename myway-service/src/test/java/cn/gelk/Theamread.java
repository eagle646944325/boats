package cn.gelk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Theamread {
    public static void main(String args[])throws Exception{
        File file = new File("E:\\123.sql");//Text文件
        BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
        String s = null;
        while((s = br.readLine())!=null){//使用readLine方法，一次读一行

            String[] str=replaceNull(s.split("\t"));

        //   String sql=" insert  into `w_theme_poetry`(`theme_id`,`poetry_id`) values ('"+str[0]+"',7);";
            for (String st:str){
                System.out.print(st+",");
            }

        }
        br.close();;
    }

    private static  String[] replaceNull(String[] str){
        //用StringBuffer来存放数组中的非空元素，用“;”分隔
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<str.length; i++) {
            if("".equals(str[i])) {
                continue;
            }
            sb.append(str[i]);
            if(i != str.length - 1) {
                sb.append(";");
            }
        }
        //用String的split方法分割，得到数组
        str = sb.toString().split(";");
        return str;
    }
}
