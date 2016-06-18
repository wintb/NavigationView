package tien.dinh.navigationview.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DinhTien on 18-06-2016.
 */
public class CheckNumber {

    public static boolean checkNumber(String data){
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }


}
