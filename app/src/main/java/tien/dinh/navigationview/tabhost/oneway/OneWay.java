package tien.dinh.navigationview.tabhost.oneway;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.json.ReadJson;
import tien.dinh.navigationview.tabhost.datetime.CompareDateTime;
import tien.dinh.navigationview.tabhost.datetime.DatetimeFormater;

/**
 * Created by VuVanThang on 3/29/2016.
 */
public class OneWay extends Fragment{

    TextView txtDate;
    TextView txtChuyenDi;
    ImageView imgDate;
    ImageView imgChuyenDi;
    Context context;
    String date,months,years;
    DatetimeFormater datetimeFormater;
    CompareDateTime compareDateTime;
    ListView listView;
    ArrayList<String> arrayList;
    Button btnTimChuyen;
    String url_TimChuyen_MotChieu = "http://10.0.3.2:8080/xekhach/danhsachchuyendimotchieu.php";
    String Json_DanhSach_Chuyen ;
    OnNameSetListener onNameSetListener;
    ReadJson readJsonChuyenDi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabhost_oneway_acivity,container,false);

        txtDate = (TextView) rootView.findViewById(R.id.txtDate);
        txtChuyenDi = (TextView) rootView.findViewById(R.id.txtChuyenDi);
        imgDate = (ImageView) rootView.findViewById(R.id.imgDate);
        imgChuyenDi = (ImageView) rootView.findViewById(R.id.imgChuyenDi);
        btnTimChuyen = (Button) rootView.findViewById(R.id.btnTimChuyenTab1);
        //Lấy ngày hiện tại lên textview
        datetimeFormater = new DatetimeFormater();
        datetimeFormater.datetimecurrent(txtDate);
        // So sánh ngày hiện tại với ngày đã chọn
        compareDateTime = new CompareDateTime(getActivity());
        //chọn chuyến đi
        chonChuyen();

        //chon ngay thang di
        ChonNgay();

        //Sự kiệm click vào button Tìm chuyến sẽ hiển thị ra danh sách các chuyến
        btnTimChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //post data to server and get json string
                readJsonChuyenDi = new ReadJson(txtChuyenDi.getText().toString(),txtDate.getText().toString());
                try {
                    Json_DanhSach_Chuyen = new GoiWebService().execute(url_TimChuyen_MotChieu).get();
                    Log.d("TEST_JSON_JSON_JSON:",Json_DanhSach_Chuyen);
                    if (Json_DanhSach_Chuyen == "[]"){
                        Toast.makeText(getActivity(), "No trip on day", Toast.LENGTH_LONG).show();
                    }else {
                        Log.d("JSON_TEST","TEST_TEST_TEST");
                        // send data to fragment OnWay_ListTrip class
                        String tenchuyendi = txtChuyenDi.getText().toString();
                        String ngaydi = txtDate.getText().toString();
                        onNameSetListener.setChuyenDi_NgayDi(tenchuyendi, ngaydi, Json_DanhSach_Chuyen);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });

        return rootView;
    }


    //--------------------------POST DATA TO SERVER AND GET DATA FROM SERVER------------------------------

    private class GoiWebService extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return readJsonChuyenDi.makePostRequestChuyenDi(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            //Json_DanhSach_Chuyen = s;
            Log.d("JSON DANH SACH CHUYEN: ", s);
            super.onPostExecute(s);
        }
    }

    //------------------------------------INTERFACE FOR SEND DATA TO FRAGMENT LIST TRIP -------------------------------

    public interface OnNameSetListener{
         public void setChuyenDi_NgayDi(String ChuyenDi, String NgayDi, String json);
    }


   @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onNameSetListener = (OnNameSetListener)activity;
        } catch (Exception e){throw new ClassCastException(activity.toString() + " must implement OnNameSetListener");}

    }


    //--------------------------------------------CHOOSE TRIP-------------------------------------------------------------

    public void chonChuyen(){
        imgChuyenDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater1 = (LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout = (LinearLayout) inflater1.inflate(R.layout.listview_dialog_activity, null, false);
                listView = (ListView) linearLayout.findViewById(R.id.list);
                DocJSON docJSON = new DocJSON();
                docJSON.execute("http://10.0.3.2:8080/xekhach/jsontencacchuyen.php");

                new AlertDialog.Builder(getActivity()).setTitle("List Trip").setMessage("Click to select Trip")
                        .setView(linearLayout)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtChuyenDi.setText("");
                            }
                        }).show();
            }
        });
    }

    //---------------------------------------CHOOSE DATE TIME FOR TRIP-----------------------------------------------------

    public void ChonNgay(){

        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
                LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.calendar, null, false);
                CalendarView cv = (CalendarView) ll.getChildAt(0);
                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                        date = Integer.toString(dayOfMonth);
                        months = Integer.toString(month+1);
                        years = Integer.toString(year);
                        compareDateTime.comparedatetime(dayOfMonth,month+1,year);


                    }

                });

                new AlertDialog.Builder(getActivity()).setTitle("Calendar").setMessage("Chọn ngày đi")
                        .setView(ll)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //String date_month_year = years + "-" + months + "-" + date;
                                String date_month_year = date + "/" + months + "/" + years;
                                datetimeFormater.FormatDateTime(date_month_year, txtDate);
                                //txtDate.setText(date_month_year);

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

            }
        });
    }


    //----------------------------------READ DATE FROM JSON FILE ------------------------------------------------

    private class DocJSON extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);

        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            arrayList = new ArrayList<String>();
            try {
                JSONArray MangDanhSachChuyen = new JSONArray(s);
                for (int i = 0; i < MangDanhSachChuyen.length(); i++) {
                    JSONObject ChuyenDi = MangDanhSachChuyen.getJSONObject(i);
                    arrayList.add(ChuyenDi.getString("TenChuyen"));

                    Log.d("JSON_OBJECT", ChuyenDi.getString("TenChuyen").toString());
                }

                ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.list_item, arrayList);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        txtChuyenDi.setText(arrayList.get(position).toString());

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    //đọc nội dung từ URL
    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();
        try
        {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }


}
