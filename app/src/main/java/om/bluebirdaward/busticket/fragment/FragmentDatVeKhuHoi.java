package om.bluebirdaward.busticket.fragment;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import om.bluebirdaward.busticket.mics.datetime.DatetimeFormater;
import om.bluebirdaward.busticket.R;

/**
 * Created by VuVanThang on 3/29/2016.
 */
public class FragmentDatVeKhuHoi extends Fragment {


    TextView txtDateDitab2;
    TextView txtDateVetab2;
    TextView txtChuyenDitab2;
    ImageView imgDateDitab2;
    ImageView imgDateVetab2;
    ImageView imgChuyenDitab2;
    Context context;
    String date,months,years;
    DatetimeFormater datetimeFormaterDi;
    DatetimeFormater datetimeFormaterVe;
    ListView listView;
    ArrayList<String> arrayList;
    Button btnTimChuyentab2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dat_ve_khu_hoi,container,false);

        txtChuyenDitab2 = (TextView) rootView.findViewById(R.id.txtChuyenDiTab2);
        imgChuyenDitab2 = (ImageView) rootView.findViewById(R.id.imgChuyenDiTab2);
        txtDateDitab2 = (TextView) rootView.findViewById(R.id.txtDateDiTab2);
        imgDateDitab2 = (ImageView) rootView.findViewById(R.id.imgDateDiTab2);
        txtDateVetab2 = (TextView) rootView.findViewById(R.id.txtDateVeTab2);
        imgDateVetab2 = (ImageView) rootView.findViewById(R.id.imgDateVeTab2);

        btnTimChuyentab2 = (Button) rootView.findViewById(R.id.btnTimChuyenTab2);

        datetimeFormaterDi = new DatetimeFormater();
        datetimeFormaterDi.datetimecurrent(txtDateDitab2);

        datetimeFormaterVe = new DatetimeFormater();
        datetimeFormaterVe.datetimecurrent(txtDateVetab2);


        chonChuyen();
        ChonNgay();

        return rootView;
    }


    public void chonChuyen(){


        imgChuyenDitab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater1 = (LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout = (LinearLayout) inflater1.inflate(R.layout.dialog_ten_chuyen, null, false);
                listView = (ListView) linearLayout.findViewById(R.id.list);

                /*DocJSON docJSON = new DocJSON();
                docJSON.execute("http://vuvanthang-001-site1.1tempurl.com/json.php");*/

                DocJSON docJSON = new DocJSON();
                docJSON.execute("http://10.0.3.2:8080/xekhach/json.php");

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

                            }
                        }).show();
            }
        });
    }

    public void ChonNgay(){
        //Sự kiện chọn ngày tháng năm vé đi
        imgDateDitab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
                LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.dialog_calendar, null, false);
                CalendarView cv = (CalendarView) ll.getChildAt(0);

                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                        date = Integer.toString(dayOfMonth);
                        months = Integer.toString(month + 1);
                        years = Integer.toString(year);

                    }

                });

                new AlertDialog.Builder(getActivity()).setTitle("Calendar").setMessage("Click to select date")
                        .setView(ll)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String date_month_year = date + "/" + months + "/" + years;
                                txtDateDitab2.setText(date_month_year);

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();


            }
        });


        //su kiện chọn ngày tháng năm vé về
        imgDateVetab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
                LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.dialog_calendar, null, false);
                CalendarView cv = (CalendarView) ll.getChildAt(0);

                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                        date = Integer.toString(dayOfMonth);
                        months = Integer.toString(month + 1);
                        years = Integer.toString(year);

                    }

                });

                new AlertDialog.Builder(getActivity()).setTitle("Calendar").setMessage("Click to select date")
                        .setView(ll)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String date_month_year = date + "/" + months + "/" + years;
                                txtDateVetab2.setText(date_month_year);

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();


            }
        });
    }


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

                ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.custom_dialog_ten_chuyen, arrayList);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        txtChuyenDitab2.setText(arrayList.get(position).toString());

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
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
