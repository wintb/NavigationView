package tien.dinh.navigationview.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import tien.dinh.navigationview.Object.Object_Chuyen;
import tien.dinh.navigationview.Object.Object_TaiXe;
import tien.dinh.navigationview.R;
import tien.dinh.navigationview.json.ReadJson;

/**
 * Created by VuVanThang on 5/11/2016.
 */
public class CustomAdapterOneTrip extends BaseAdapter {
    private List<Object_Chuyen> object_chuyenList;
    private Context context;
    private LayoutInflater layoutInflater;
    private ReadJson readJsonTaiXe;
    SoDoGhe interfaceSoDoGhe;


    public CustomAdapterOneTrip(Context context, List<Object_Chuyen> object_chuyenList) {
        this.context = context;
        this.object_chuyenList = object_chuyenList;
        interfaceSoDoGhe = (SoDoGhe) context;
    }

    @Override
    public int getCount() {
        return object_chuyenList.size();
    }

    @Override
    public Object getItem(int position) {
        return object_chuyenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.tabhost_oneway_listtrip_listitem,parent,false);

        Object_Chuyen object_chuyen = (Object_Chuyen) getItem(position);
        final TextView txtTai = (TextView) view.findViewById(R.id.txtTai);
        final TextView txtGioDi = (TextView) view.findViewById(R.id.txtGioDi);
        TextView txtGioDen = (TextView) view.findViewById(R.id.txtGioDen);
        TextView txtGiaVe = (TextView) view.findViewById(R.id.txtGiaVe);
        TextView txtChon = (TextView) view.findViewById(R.id.txtChon);

        txtTai.setText(object_chuyen.getMaTai());
        txtGioDi.setText(object_chuyen.getGioDi());
        txtGioDen.setText(object_chuyen.getGioDen());
        txtGiaVe.setText(object_chuyen.getGiaVe());
        txtChon.setText(">");
        //xem chi tiet tai xe
        xemChiTietTaiXe(txtTai);
        //Show so do ghe
         txtChon.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 interfaceSoDoGhe.setSoDoGhe("ChuyenDi",txtGioDi.getText().toString(),"NgayDi");
             }
         });
        return view;
    }

    //-----------------------------------DIAGRAMS FOR TRIP CHOOSED----------------------------------
    /*public void sodoghe(TextView chon){
        chon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceSoDoGhe.setSoDoGhe();
            }
        });
    }*/

    public interface SoDoGhe{
        public void setSoDoGhe(String TenChuyen, String GioDi, String NgayDi);
    }


    // --------------------------------SEND MA TAI TO SERVER AND GET DATA FROM SERVER --------------

    public void xemChiTietTaiXe(final TextView tai){
        tai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readJsonTaiXe = new ReadJson(tai.getText().toString());
                new GoiWebServiceTaiXe().execute("http://10.0.3.2:8080/xekhach/json_tai_xe.php");
            }
        });
    }

    //---------------------------------ĐỌC THÔNG TIN TÀI XẾ-----------------------------------------

    private class GoiWebServiceTaiXe extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return readJsonTaiXe.makePostRequestTaiXe(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = (LinearLayout) inflater1.inflate(R.layout.listview_dialog_activity, null, false);
            ListView listView = (ListView) linearLayout.findViewById(R.id.list);
            Gson gson =new Gson();
            Type listType = new TypeToken<List<Object_TaiXe>>(){}.getType();
            List<Object_TaiXe> taixe = gson.fromJson(s, listType);
            CustomAdapterTaiXe customAdapterTaiXe = new CustomAdapterTaiXe(context,taixe);
            listView.setAdapter(customAdapterTaiXe);

            new AlertDialog.Builder(context).setTitle("Thông tin tài xế "+taixe.get(0).getMaTai())/*.setMessage("")*/
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
    }

}
