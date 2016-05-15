package tien.dinh.navigationview.tabhost.datetime;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by VuVanThang on 3/17/2016.
 */
public class CompareDateTime {

    Context context;
    public CompareDateTime(Context context){
        this.context = context;
    }

    public void comparedatetime(int dates, int months, int years){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //String datetimdselected = years+"-"+months+"-"+dates;
        try {
            String datetimdselected = dates+"/"+months+"/"+years;
            Date NgayDaChon = dateFormat.parse(datetimdselected);

            DatetimeFormater datetimeFormater = new DatetimeFormater();
            Date NgayHienTai = dateFormat.parse(datetimeFormater.datetimecurrent());

            if (NgayDaChon.compareTo(NgayHienTai)<0){
                Toast.makeText(context,"Ngày đăng kí phải lớn hơn hoặc bằng ngày hiện tại!",Toast.LENGTH_LONG).show();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
