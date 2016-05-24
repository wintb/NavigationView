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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import tien.dinh.navigationview.Object.Object_Ve;
import tien.dinh.navigationview.R;
import tien.dinh.navigationview.adapter.CustomAdapterOneTrip;

/**
 * Created by DinhTien on 15-05-2016.
 */
public class Datve_Second_Floor_Fragment extends Fragment {

    ImageView A1T;
    ImageView A2T;
    ImageView A3T;
    ImageView A4T;
    ImageView A5T;

    ImageView B1T;
    ImageView B2T;
    ImageView B3T;
    ImageView B4T;
    ImageView B5T;

    ImageView C1T;
    ImageView C2T;
    ImageView C3T;
    ImageView C4T;
    ImageView C5T;

    ImageView D1T;
    ImageView D2T;
    ImageView D3T;
    ImageView D4T;
    ImageView D5T;

    Button btnChongheTang2;

    boolean check = true;
    public static List<String> listGheDaChonTang2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.datve_second_floor,container,false);

        A1T = (ImageView) rootView.findViewById(R.id.A1T);
        A2T = (ImageView) rootView.findViewById(R.id.A2T);
        A3T = (ImageView) rootView.findViewById(R.id.A3T);
        A4T = (ImageView) rootView.findViewById(R.id.A4T);
        A5T = (ImageView) rootView.findViewById(R.id.A5T);
        B1T = (ImageView) rootView.findViewById(R.id.B1T);
        B2T = (ImageView) rootView.findViewById(R.id.B2T);
        B3T = (ImageView) rootView.findViewById(R.id.B3T);
        B4T = (ImageView) rootView.findViewById(R.id.B4T);
        B5T = (ImageView) rootView.findViewById(R.id.B5T);
        C1T = (ImageView) rootView.findViewById(R.id.C1T);
        C2T = (ImageView) rootView.findViewById(R.id.C2T);
        C3T = (ImageView) rootView.findViewById(R.id.C3T);
        C4T = (ImageView) rootView.findViewById(R.id.C4T);
        C5T = (ImageView) rootView.findViewById(R.id.C5T);
        D1T = (ImageView) rootView.findViewById(R.id.D1T);
        D2T = (ImageView) rootView.findViewById(R.id.D2T);
        D3T = (ImageView) rootView.findViewById(R.id.D3T);
        D4T = (ImageView) rootView.findViewById(R.id.D4T);
        D5T = (ImageView) rootView.findViewById(R.id.D5T);
        btnChongheTang2 = (Button) rootView.findViewById(R.id.btnChonGheTang2);

        listGheDaChonTang2 = new ArrayList<String>();
        //lấy danh sách các ghế đã chọn
        Gson gson =new Gson();
        Type listType = new TypeToken<List<Object_Ve>>(){}.getType();
        List<Object_Ve> list =  gson.fromJson(CustomAdapterOneTrip.sodoghe, listType);

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
        chonghe1(A1T);
        chonghe1(B1T);
        chonghe1(C1T);
        chonghe1(D1T);
        chonghe2(A2T);
        chonghe2(B2T);
        chonghe2(C2T);
        chonghe2(D2T);
        chonghe3(A3T);
        chonghe3(B3T);
        chonghe3(C3T);
        chonghe3(D3T);
        chonghe4(A4T);
        chonghe4(B4T);
        chonghe4(C4T);
        chonghe4(D4T);
        chonghe5(A5T);
        chonghe5(B5T);
        chonghe5(C5T);
        chonghe5(D5T);

        btnChongheTang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }

    //--------------------------------SET COLOR KHI CLICK CHỌN GHẾ----------------------------------

    private void chonghe1(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == true){
                    imageView.setImageResource(R.drawable.ghedangchon1);
                    listGheDaChonTang2.add(imageView.getTag().toString());
                    Log.d("JSON_DATVE_TANG2:", "Da dat ghe " + imageView.getTag().toString());
                    check = false;
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    listGheDaChonTang2.remove(listGheDaChonTang2.size() - 1);
                    Log.d("JSON_DATVE_TANG2:", "Da huy ghe " + imageView.getTag().toString());
                    check = true;
                }
            }
        });
    }

    private void chonghe2(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == true){
                    imageView.setImageResource(R.drawable.ghedangchon2);
                    listGheDaChonTang2.add(imageView.getTag().toString());
                    Log.d("JSON_DATVE_TANG2:", "Da dat ghe " + imageView.getTag().toString());
                    check = false;
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    listGheDaChonTang2.remove(listGheDaChonTang2.size() - 1);
                    Log.d("JSON_DATVE_TANG2:", "Da huy ghe " + imageView.getTag().toString());
                    check = true;
                }
            }
        });
    }

    private void chonghe3(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == true){
                    imageView.setImageResource(R.drawable.ghedangchon3);
                    listGheDaChonTang2.add(imageView.getTag().toString());
                    Log.d("JSON_DATVE_TANG2:", "Da dat ghe " + imageView.getTag().toString());
                    check = false;
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    listGheDaChonTang2.remove(listGheDaChonTang2.size() - 1);
                    Log.d("JSON_DATVE_TANG2:", "Da huy ghe " + imageView.getTag().toString());
                    check = true;
                }
            }
        });
    }

    private void chonghe4(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == true){
                    imageView.setImageResource(R.drawable.ghedangchon4);
                    listGheDaChonTang2.add(imageView.getTag().toString());
                    Log.d("JSON_DATVE_TANG2:", "Da dat ghe " + imageView.getTag().toString());
                    check = false;
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    listGheDaChonTang2.remove(listGheDaChonTang2.size() - 1);
                    Log.d("JSON_DATVE_TANG2:", "Da huy ghe " + imageView.getTag().toString());
                    check = true;
                }
            }
        });
    }

    private void chonghe5(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == true){
                    imageView.setImageResource(R.drawable.ghedangchon5);
                    listGheDaChonTang2.add(imageView.getTag().toString());
                    Log.d("JSON_DATVE_TANG2:", "Da dat ghe " + imageView.getTag().toString());
                    check = false;
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    listGheDaChonTang2.remove(listGheDaChonTang2.size() - 1);
                    Log.d("JSON_DATVE_TANG2:", "Da huy ghe " + imageView.getTag().toString());
                    check = true;
                }
            }
        });
    }

    //---------------------------SET COLOR NHỮNG GHẾ ĐÃ ĐƯỢC NGƯỜI KHÁC CHỌN------------------------

    public void setGheDachon1(ImageView imageView,List<Object_Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon1);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon2(ImageView imageView,List<Object_Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon2);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon3(ImageView imageView,List<Object_Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon3);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon4(ImageView imageView,List<Object_Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon4);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon5(ImageView imageView,List<Object_Ve> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i).getSoGhe()))){
                imageView.setImageResource(R.drawable.ghedachon5);
                imageView.setEnabled(false);
            }
        }
    }
}
