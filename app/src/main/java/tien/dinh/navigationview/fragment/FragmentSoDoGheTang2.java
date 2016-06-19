package tien.dinh.navigationview.fragment;

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
import tien.dinh.navigationview.dao.Ve;
import tien.dinh.navigationview.R;
import tien.dinh.navigationview.mics.Constant;
import tien.dinh.navigationview.adapter.AdapterDanhSachChuyen;

/**
 * Created by DinhTien on 15-05-2016.
 */
public class FragmentSoDoGheTang2 extends Fragment {

    @Bind(R.id.A1T)
    ImageView A1T;
    @Bind(R.id.A2T)
    ImageView A2T;
    @Bind(R.id.A3T)
    ImageView A3T;
    @Bind(R.id.A4T)
    ImageView A4T;
    @Bind(R.id.A5T)
    ImageView A5T;

    @Bind(R.id.B1T)
    ImageView B1T;
    @Bind(R.id.B2T)
    ImageView B2T;
    @Bind(R.id.B3T)
    ImageView B3T;
    @Bind(R.id.B4T)
    ImageView B4T;
    @Bind(R.id.B5T)
    ImageView B5T;

    @Bind(R.id.C1T)
    ImageView C1T;
    @Bind(R.id.C2T)
    ImageView C2T;
    @Bind(R.id.C3T)
    ImageView C3T;
    @Bind(R.id.C4T)
    ImageView C4T;
    @Bind(R.id.C5T)
    ImageView C5T;

    @Bind(R.id.D1T)
    ImageView D1T;
    @Bind(R.id.D2T)
    ImageView D2T;
    @Bind(R.id.D3T)
    ImageView D3T;
    @Bind(R.id.D4T)
    ImageView D4T;
    @Bind(R.id.D5T)
    ImageView D5T;


    boolean Check_A1T = true;
    boolean Check_A2T = true;
    boolean Check_A3T = true;
    boolean Check_A4T = true;
    boolean Check_A5T = true;

    boolean Check_B1T = true;
    boolean Check_B2T = true;
    boolean Check_B3T = true;
    boolean Check_B4T = true;
    boolean Check_B5T = true;

    boolean Check_C1T = true;
    boolean Check_C2T = true;
    boolean Check_C3T = true;
    boolean Check_C4T = true;
    boolean Check_C5T = true;

    boolean Check_D1T = true;
    boolean Check_D2T = true;
    boolean Check_D3T = true;
    boolean Check_D4T = true;
    boolean Check_D5T = true;

    public static List<String> listGheDaChonTang2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_so_do_ghe_tang_2,container,false);
        ButterKnife.bind(this,rootView);

        List<Ve> list = new ArrayList<>();
        // KEY_CHECK_FRAGMENT == 1 Fragment
        if (Constant.KEY_CHECK_FRAGMENT == 1){
            listGheDaChonTang2 = new ArrayList<String>();
            Gson gson =new Gson();
            Type listType = new TypeToken<List<Ve>>(){}.getType();
            list =  gson.fromJson(AdapterDanhSachChuyen.sodoghe, listType);
        }else if (Constant.KEY_CHECK_FRAGMENT == 0){
            listGheDaChonTang2 = new ArrayList<String>();
            Gson gson =new Gson();
            Type listType = new TypeToken<List<Ve>>(){}.getType();
            list =  gson.fromJson(FragmentThongTinVeDaDat.sodogheDoiVe, listType);
        }

        //set color cho ghế đã được người khác chọn
        setGheDachon1(A1T, list);
        setGheDachon1(B1T,list);
        setGheDachon1(C1T,list);
        setGheDachon1(D1T,list);
        setGheDachon2(A2T,list);
        setGheDachon2(B2T,list);
        setGheDachon2(C2T,list);
        setGheDachon2(D2T,list);
        setGheDachon3(A3T,list);
        setGheDachon3(B3T,list);
        setGheDachon3(C3T,list);
        setGheDachon3(D3T,list);
        setGheDachon4(A4T,list);
        setGheDachon4(B4T,list);
        setGheDachon4(C4T,list);
        setGheDachon4(D4T,list);
        setGheDachon5(A5T,list);
        setGheDachon5(B5T,list);
        setGheDachon5(C5T,list);
        setGheDachon5(D5T,list);
        //set color khi click chon ghe
        chongheA1T(A1T);
        chongheB1T(B1T);
        chongheC1T(C1T);
        chongheD1T(D1T);
        chongheA2T(A2T);
        chongheB2T(B2T);
        chongheC2T(C2T);
        chongheD2T(D2T);
        chongheA3T(A3T);
        chongheB3T(B3T);
        chongheC3T(C3T);
        chongheD3T(D3T);
        chongheA4T(A4T);
        chongheB4T(B4T);
        chongheC4T(C4T);
        chongheD4T(D4T);
        chongheA5T(A5T);
        chongheB5T(B5T);
        chongheC5T(C5T);
        chongheD5T(D5T);


        return rootView;
    }


    //--------------------------------SET COLOR KHI CLICK CHỌN GHẾ----------------------------------

    private void chongheA1T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_A1T == true) {
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A1T = false;
                    }
                    else {
                        Toast.makeText(getActivity(), "Bạn chỉ được chọn nhiều nhất 2 ghế", Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_A1T = true;
                    return;
                }
            }
        });
    }

    private void chongheA2T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_A2T == true) {
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A2T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_A2T = true;
                    return;
                }
            }
        });
    }

    private void chongheA3T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_A3T == true) {
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A3T = false;
                    }
                    else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_A3T = true;
                    return;
                }
            }
        });
    }

    private void chongheA4T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_A4T == true) {
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A4T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_A4T = true;
                    return;
                }
            }
        });
    }

    private void chongheA5T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_A5T == true) {
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A5T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_A5T = true;
                    return;
                }
            }
        });
    }

    private void chongheB1T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_B1T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B1T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_B1T = true;
                }
            }
        });
    }

    private void chongheB2T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_B2T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B2T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_B2T = true;
                }
            }
        });
    }

    private void chongheB3T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_B3T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B3T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_B3T = true;
                }
            }
        });
    }

    private void chongheB4T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_B4T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B4T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_B4T = true;
                }
            }
        });
    }

    private void chongheB5T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_B5T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B5T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_B5T = true;
                }
            }
        });
    }

    private void chongheC1T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_C1T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C1T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_C1T = true;
                }
            }
        });
    }

    private void chongheC2T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_C2T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C2T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_C2T = true;
                }
            }
        });
    }

    private void chongheC3T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_C3T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C3T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_C3T = true;
                }
            }
        });
    }

    private void chongheC4T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_C4T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C4T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_C4T = true;
                }
            }
        });
    }

    private void chongheC5T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_C5T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C5T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_C5T = true;
                }
            }
        });
    }

    private void chongheD1T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_D1T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D1T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D1T = true;
                }
            }
        });
    }

    private void chongheD2T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_D2T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D2T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D2T = true;
                }
            }
        });
    }

    private void chongheD3T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_D3T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D3T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D3T = true;
                }
            }
        });
    }

    private void chongheD4T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_D4T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D4T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D4T = true;
                }
            }
        });
    }

    private void chongheD5T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_D5T == true){
                    if (FragmentSoDoGheTang1.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentSoDoGheTang1.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D5T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentSoDoGheTang1.listGheDaChonTang1.remove(FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D5T = true;
                }
            }
        });
    }

    //---------------------------SET COLOR NHỮNG GHẾ ĐÃ ĐƯỢC NGƯỜI KHÁC CHỌN------------------------

    public void setGheDachon1(ImageView imageView,List<Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon1);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon2(ImageView imageView,List<Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon2);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon3(ImageView imageView,List<Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon3);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon4(ImageView imageView,List<Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon4);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon5(ImageView imageView,List<Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon5);
                imageView.setEnabled(false);
            }
        }
    }
}
