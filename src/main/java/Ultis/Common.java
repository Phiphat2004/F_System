package Ultis;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class Common {
    public static Date convertStringtoDate(String value){
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = format.parse(value);
            return new Date(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static List pagination(List list,int pagesize,int pagenum){
        if(list == null || list.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        return list.subList((pagenum - 1) * pagesize,Math.min(list.size(),pagenum * pagesize));
    }
}
