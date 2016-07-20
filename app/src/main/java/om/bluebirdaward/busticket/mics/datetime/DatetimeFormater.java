package om.bluebirdaward.busticket.mics.datetime;

import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by VuVanThang on 3/17/2016.
 */
public class DatetimeFormater {

    public void datetimecurrent(TextView textView){

            /*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");*/
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        textView.setText(dateFormat.format(date));

    }

    public String datetimecurrent(){

            /*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");*/
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void FormatDateTime(String datetime,TextView textView){
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(datetime);
            textView.setText(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
