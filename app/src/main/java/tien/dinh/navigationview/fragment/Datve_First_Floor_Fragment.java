package tien.dinh.navigationview.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tien.dinh.navigationview.Object.Object_Ve;
import tien.dinh.navigationview.R;
import tien.dinh.navigationview.Utils.AppConfig;
import tien.dinh.navigationview.adapter.CustomAdapterOneTrip;
import tien.dinh.navigationview.json.JsonDoiGhe;

/**
 * Created by DinhTien on 15-05-2016.
 */
public class Datve_First_Floor_Fragment extends Fragment {
    @Bind(R.id.A1D)
    ImageView A1D;
    @Bind(R.id.A2D)
    ImageView A2D;
    @Bind(R.id.A3D)
    ImageView A3D;
    @Bind(R.id.A4D)
    ImageView A4D;
    @Bind(R.id.A5D)
    ImageView A5D;

    @Bind(R.id.B1D)
    ImageView B1D;
    @Bind(R.id.B2D)
    ImageView B2D;
    @Bind(R.id.B3D)
    ImageView B3D;
    @Bind(R.id.B4D)
    ImageView B4D;
    @Bind(R.id.B5D)
    ImageView B5D;

    @Bind(R.id.C1D)
    ImageView C1D;
    @Bind(R.id.C2D)
    ImageView C2D;
    @Bind(R.id.C3D)
    ImageView C3D;
    @Bind(R.id.C4D)
    ImageView C4D;
    @Bind(R.id.C5D)
    ImageView C5D;

    @Bind(R.id.D1D)
    ImageView D1D;
    @Bind(R.id.D2D)
    ImageView D2D;
    @Bind(R.id.D3D)
    ImageView D3D;
    @Bind(R.id.D4D)
    ImageView D4D;
    @Bind(R.id.D5D)
    ImageView D5D;

    @Bind(R.id.btnChonGheTang1)
    Button btnChonGheTang1;

    boolean Check_A1D = true;
    boolean Check_A2D = true;
    boolean Check_A3D = true;
    boolean Check_A4D = true;
    boolean Check_A5D = true;

    boolean Check_B1D = true;
    boolean Check_B2D = true;
    boolean Check_B3D = true;
    boolean Check_B4D = true;
    boolean Check_B5D = true;

    boolean Check_C1D = true;
    boolean Check_C2D = true;
    boolean Check_C3D = true;
    boolean Check_C4D = true;
    boolean Check_C5D = true;

    boolean Check_D1D = true;
    boolean Check_D2D = true;
    boolean Check_D3D = true;
    boolean Check_D4D = true;
    boolean Check_D5D = true;


    public static List<String> listGheDaChonTang1;

    ChonGhe chonGhe;
    JsonDoiGhe jsonDoiGhe;



    public static Datve_First_Floor_Fragment newInstance() {

        return new Datve_First_Floor_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.datve_first_floor,container,false);
        ButterKnife.bind(this,rootView);

        List<Object_Ve> list = new ArrayList<>();

        if (AppConfig.KEY_CHECK_FRAGMENT == 1){
            listGheDaChonTang1 = new ArrayList<String>();
            Gson gson =new Gson();
            Type listType = new TypeToken<List<Object_Ve>>(){}.getType();
            list =  gson.fromJson(CustomAdapterOneTrip.sodoghe, listType);
        }else if (AppConfig.KEY_CHECK_FRAGMENT == 0){
            listGheDaChonTang1 = new ArrayList<String>();
            Gson gson =new Gson();
            Type listType = new TypeToken<List<Object_Ve>>(){}.getType();
            list =  gson.fromJson(XemVe.sodogheDoiVe, listType);
        }

        //set color cho ghế đã được người khác chọn
        setGheDachon1(A1D, list);
        setGheDachon1(B1D, list);
        setGheDachon1(C1D, list);
        setGheDachon1(D1D, list);
        setGheDachon2(A2D, list);
        setGheDachon2(B2D, list);
        setGheDachon2(C2D, list);
        setGheDachon2(D2D, list);
        setGheDachon3(A3D, list);
        setGheDachon3(B3D, list);
        setGheDachon3(C3D, list);
        setGheDachon3(D3D, list);
        setGheDachon4(A4D, list);
        setGheDachon4(B4D, list);
        setGheDachon4(C4D, list);
        setGheDachon4(D4D, list);
        setGheDachon5(A5D, list);
        setGheDachon5(B5D, list);
        setGheDachon5(C5D, list);
        setGheDachon5(D5D, list);
        //set color khi click chon ghe
        chongheA1D(A1D);
        chongheB1D(B1D);
        chongheC1D(C1D);
        chongheD1D(D1D);
        chongheA2D(A2D);
        chongheB2D(B2D);
        chongheC2D(C2D);
        chongheD2D(D2D);
        chongheA3D(A3D);
        chongheB3D(B3D);
        chongheC3D(C3D);
        chongheD3D(D3D);
        chongheA4D(A4D);
        chongheB4D(B4D);
        chongheC4D(C4D);
        chongheD4D(D4D);
        chongheA5D(A5D);
        chongheB5D(B5D);
        chongheC5D(C5D);
        chongheD5D(D5D);

        chonGhe = (ChonGhe) getActivity();

        btnChonGheTang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppConfig.KEY_CHECK_FRAGMENT == 1) {
                    chonGhe.clickChonGhe();
                }else if (AppConfig.KEY_CHECK_FRAGMENT == 0){
                    jsonDoiGhe = new JsonDoiGhe();
                }
            }
        });

        return rootView;
    }

    //-------------------------------GOI WEBSERVICE ĐỂ ĐỔI GHẾ -------------------------------------
    public class WebserviceDoiGhe extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    //--------------------------------SET COLOR KHI CLICK CHỌN GHẾ----------------------------------
    private void chongheA1D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_A1D == true) {
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A1D = false;
                    }
                    else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong1);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_A1D = true;
                    return;
                }
            }
        });
    }

    private void chongheA2D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_A2D == true) {
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A2D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong2);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_A2D = true;
                    return;
                }
            }
        });
    }

    private void chongheA3D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_A3D == true) {
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A3D = false;
                    }
                    else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong3);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_A3D = true;
                    return;
                }
            }
        });
    }

    private void chongheA4D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_A4D == true) {
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A4D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong4);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_A4D = true;
                    return;
                }
            }
        });
    }

    private void chongheA5D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_A5D == true) {
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A5D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong5);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_A5D = true;
                    return;
                }
            }
        });
    }

    private void chongheB1D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_B1D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B1D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_B1D = true;
                }
            }
        });
    }

    private void chongheB2D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_B2D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B2D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_B2D = true;
                }
            }
        });
    }

    private void chongheB3D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_B3D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B3D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_B3D = true;
                }
            }
        });
    }

    private void chongheB4D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_B4D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B4D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_B4D = true;
                }
            }
        });
    }

    private void chongheB5D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_B5D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B5D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_B5D = true;
                }
            }
        });
    }

    private void chongheC1D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_C1D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C1D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_C1D = true;
                }
            }
        });
    }

    private void chongheC2D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_C2D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C2D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_C2D = true;
                }
            }
        });
    }

    private void chongheC3D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_C3D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C3D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_C3D = true;
                }
            }
        });
    }

    private void chongheC4D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_C4D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C4D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_C4D = true;
                }
            }
        });
    }

    private void chongheC5D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_C5D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C5D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_C5D = true;
                }
            }
        });
    }

    private void chongheD1D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_D1D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D1D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D1D = true;
                }
            }
        });
    }

    private void chongheD2D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_D2D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D2D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D2D = true;
                }
            }
        });
    }

    private void chongheD3D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_D3D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D3D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D3D = true;
                }
            }
        });
    }

    private void chongheD4D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_D4D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D4D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D4D = true;
                }
            }
        });
    }

    private void chongheD5D(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_D5D == true){
                    if (listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D5D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D5D = true;
                }
            }
        });
    }


    //---------------------------SET COLOR NHỮNG GHẾ ĐÃ ĐƯỢC NGƯỜI KHÁC CHỌN------------------------

    public void setGheDachon1(ImageView imageView, List<Object_Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon1);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon2(ImageView imageView, List<Object_Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon2);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon3(ImageView imageView, List<Object_Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon3);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon4(ImageView imageView, List<Object_Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon4);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon5(ImageView imageView, List<Object_Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon5);
                imageView.setEnabled(false);
            }
        }
    }

    //============== Interface ================//
    public interface ChonGhe{
//        void clickChonGhe(String MaTai,String MaChuyen, String SoGhe);
        void clickChonGhe();
    }
}
