package tien.dinh.navigationview.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
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
import java.util.concurrent.ExecutionException;

import tien.dinh.navigationview.Object.Object_Chuyen;
import tien.dinh.navigationview.Object.Object_TaiXe;
import tien.dinh.navigationview.R;
import tien.dinh.navigationview.Utils.AppConfig;
import tien.dinh.navigationview.json.JsonSoDoghe;
import tien.dinh.navigationview.json.ReadJson;
import tien.dinh.navigationview.tabhost.oneway.OnWay_ListTrip;

/**
 * Created by VuVanThang on 5/11/2016.
 */
public class CustomAdapterOneTrip extends BaseAdapter {
    private List<Object_Chuyen> object_chuyenList;
    private Context context;
    private LayoutInflater layoutInflater;
    private ReadJson readJsonTaiXe;
    private JsonSoDoghe jsonSoDoghe;
    SoDoGhe interfaceSoDoGhe;
    public static String sodoghe;


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

        final Object_Chuyen object_chuyen = (Object_Chuyen) getItem(position);
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
        //Gửi dữ liệu lên server để kiểm tra ghế đã đặt và hiện sơ đồ ghế
         txtChon.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //lấy chuỗi json các ghế đã chọn để đưa vào list
                 AppConfig.KEY_CHECK_FRAGMENT = 1;
                 try {
                     jsonSoDoghe = new JsonSoDoghe(object_chuyen.getMaChuyen());
                     sodoghe = new GoiWbServiceSoDoGhe().execute(AppConfig.URL_SODOGHE).get();
                     Log.d("JSON_SODOGHE:",sodoghe);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } catch (ExecutionException e) {
                     e.printStackTrace();
                 }
                 interfaceSoDoGhe.setSoDoGhe(OnWay_ListTrip.ChuyenDi,txtGioDi.getText().toString(),
                         OnWay_ListTrip.NgayDi,object_chuyen.getMaChuyen(),object_chuyen.getMaTai());
             }
         });
        return view;
    }


    public interface SoDoGhe{
        void setSoDoGhe(String TenChuyen, String GioDi, String NgayDi,String MaChuyen, String MaTai);
    }

    //-------------------------SELECT SOGHE TỪ SERVER ĐỂ KIỂM TRA GHẾ ĐÃ CHỌN-----------------------

    private class GoiWbServiceSoDoGhe extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return jsonSoDoghe.makePostRequestSoDoGhe(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    // --------------------------------SEND MA TAI TO SERVER AND GET DATA FROM SERVER --------------
    public void xemChiTietTaiXe(final TextView tai){
        tai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readJsonTaiXe = new ReadJson(tai.getText().toString());
                new GoiWebServiceTaiXe().execute(AppConfig.URL_TAIXE);
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
