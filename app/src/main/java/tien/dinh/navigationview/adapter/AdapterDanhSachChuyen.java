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

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.dao.Chuyen;
import tien.dinh.navigationview.dao.TaiXe;
import tien.dinh.navigationview.fragment.FragmentDanhSachChuyen;
import tien.dinh.navigationview.json.JsonSoDoghe;
import tien.dinh.navigationview.json.ReadJson;
import tien.dinh.navigationview.json.Select_TongSoGhe;
import tien.dinh.navigationview.mics.Constant;

/**
 * Created by VuVanThang on 5/11/2016.
 */
public class AdapterDanhSachChuyen extends BaseAdapter {
    private List<Chuyen> _chuyenList;
    private Context context;
    private LayoutInflater layoutInflater;
    private ReadJson readJsonTaiXe;
    private JsonSoDoghe jsonSoDoghe;
    private Select_TongSoGhe select_tongSoGhe;

    SoDoGhe interfaceSoDoGhe;
    public static String sodoghe;
    private int tongghe;


    public AdapterDanhSachChuyen(Context context, List<Chuyen> _chuyenList) {
        this.context = context;
        this._chuyenList = _chuyenList;
        interfaceSoDoGhe = (SoDoGhe) context;
    }

    @Override
    public int getCount() {
        return _chuyenList.size();
    }

    @Override
    public Object getItem(int position) {
        return _chuyenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_fragment_danh_sach_chuyen,parent,false);

        final Chuyen _chuyen = (Chuyen) getItem(position);
        final TextView txtTai = (TextView) view.findViewById(R.id.txtTai);
        final TextView txtGioDi = (TextView) view.findViewById(R.id.txtGioDi);
        TextView txtGioDen = (TextView) view.findViewById(R.id.txtGioDen);
        TextView txtGiaVe = (TextView) view.findViewById(R.id.txtGiaVe);
        TextView txtChon = (TextView) view.findViewById(R.id.txtChon);
        LinearLayout itemDanhSachChuyen = (LinearLayout) view.findViewById(R.id.layout_custom_danhsachchuyen);

        txtTai.setText(_chuyen.getMaTai());
        txtGioDi.setText(_chuyen.getGioDi());
        txtGioDen.setText(_chuyen.getGioDen());
        txtGiaVe.setText(_chuyen.getGiaVe());
        txtChon.setText(">");

        //Tô đậm những item đã full ghế
        /*select_tongSoGhe = new Select_TongSoGhe(_chuyen.getMaChuyen());

        try {
            String TongSoGhe = new GoiWebserviceTongSoGhe().execute(Constant.URL_TONG_SO_GHE).get();
            Log.d("TongSoGhe","-----"+TongSoGhe);
            tongghe = Integer.parseInt(TongSoGhe);

            if (tongghe >= 6){
                itemDanhSachChuyen.setBackgroundColor(Color.parseColor("#1abc9c"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch (NumberFormatException e){
            e.printStackTrace();
        }*/

        //xem chi tiet tai xe
        xemChiTietTaiXe(txtTai);
        //Gửi dữ liệu lên server để kiểm tra ghế đã đặt và hiện sơ đồ ghế
         txtChon.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //lấy chuỗi json các ghế đã chọn để đưa vào list
                 Constant.KEY_CHECK_FRAGMENT = 1;
                 try {
                     jsonSoDoghe = new JsonSoDoghe(_chuyen.getMaChuyen());
                     sodoghe = new GoiWbServiceSoDoGhe().execute(Constant.URL_SODOGHE).get();
                     Log.d("JSON_SODOGHE:",sodoghe);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } catch (ExecutionException e) {
                     e.printStackTrace();
                 }
                 interfaceSoDoGhe.setSoDoGhe(FragmentDanhSachChuyen.ChuyenDi,txtGioDi.getText().toString(),
                         FragmentDanhSachChuyen.NgayDi, _chuyen.getMaChuyen(), _chuyen.getMaTai());
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

    //------------------GỌI WEBSERVICE ĐỂ ĐẾM TẤT CẢ CÁC GHẾ ĐÃ ĐƯỢC CHỌN TRONG CHUYẾN--------------
    private class GoiWebserviceTongSoGhe extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return select_tongSoGhe.makePostRequestTongSoGhe(params[0]);
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
                new GoiWebServiceTaiXe().execute(Constant.URL_TAIXE);
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
            LinearLayout linearLayout = (LinearLayout) inflater1.inflate(R.layout.dialog_ten_chuyen, null, false);
            ListView listView = (ListView) linearLayout.findViewById(R.id.list);
            Gson gson =new Gson();
            Type listType = new TypeToken<List<TaiXe>>(){}.getType();
            List<TaiXe> taixe = gson.fromJson(s, listType);
            AdapterThongTinTaiXe customAdapterTaiXe = new AdapterThongTinTaiXe(context,taixe);
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
