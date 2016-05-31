package tien.dinh.navigationview.fragment;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
public class Datve_First_Floor_Fragment extends Fragment {

    ImageView A1D;
    ImageView A2D;
    ImageView A3D;
    ImageView A4D;
    ImageView A5D;

    ImageView B1D;
    ImageView B2D;
    ImageView B3D;
    ImageView B4D;
    ImageView B5D;

    ImageView C1D;
    ImageView C2D;
    ImageView C3D;
    ImageView C4D;
    ImageView C5D;

    ImageView D1D;
    ImageView D2D;
    ImageView D3D;
    ImageView D4D;
    ImageView D5D;

    Button btnChonGheTang1;

    boolean check = true;
    public static List<String> listGheDaChonTang1;

    ChonGhe chonGhe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.datve_first_floor,container,false);

        A1D = (ImageView) rootView.findViewById(R.id.A1D);
        A2D = (ImageView) rootView.findViewById(R.id.A2D);
        A3D = (ImageView) rootView.findViewById(R.id.A3D);
        A4D = (ImageView) rootView.findViewById(R.id.A4D);
        A5D = (ImageView) rootView.findViewById(R.id.A5D);
        B1D = (ImageView) rootView.findViewById(R.id.B1D);
        B2D = (ImageView) rootView.findViewById(R.id.B2D);
        B3D = (ImageView) rootView.findViewById(R.id.B3D);
        B4D = (ImageView) rootView.findViewById(R.id.B4D);
        B5D = (ImageView) rootView.findViewById(R.id.B5D);
        C1D = (ImageView) rootView.findViewById(R.id.C1D);
        C2D = (ImageView) rootView.findViewById(R.id.C2D);
        C3D = (ImageView) rootView.findViewById(R.id.C3D);
        C4D = (ImageView) rootView.findViewById(R.id.C4D);
        C5D = (ImageView) rootView.findViewById(R.id.C5D);
        D1D = (ImageView) rootView.findViewById(R.id.D1D);
        D2D = (ImageView) rootView.findViewById(R.id.D2D);
        D3D = (ImageView) rootView.findViewById(R.id.D3D);
        D4D = (ImageView) rootView.findViewById(R.id.D4D);
        D5D = (ImageView) rootView.findViewById(R.id.D5D);
        btnChonGheTang1 = (Button) rootView.findViewById(R.id.btnChonGheTang1);

        listGheDaChonTang1 = new ArrayList<String>();
        //lấy danh sách các ghế đã chọn
        Gson gson =new Gson();
        Type listType = new TypeToken<List<Object_Ve>>(){}.getType();
        final List<Object_Ve> list =  gson.fromJson(CustomAdapterOneTrip.sodoghe, listType);

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
        chonghe1(A1D);
        chonghe1(B1D);
        chonghe1(C1D);
        chonghe1(D1D);
        chonghe2(A2D);
        chonghe2(B2D);
        chonghe2(C2D);
        chonghe2(D2D);
        chonghe3(A3D);
        chonghe3(B3D);
        chonghe3(C3D);
        chonghe3(D3D);
        chonghe4(A4D);
        chonghe4(B4D);
        chonghe4(C4D);
        chonghe4(D4D);
        chonghe5(A5D);
        chonghe5(B5D);
        chonghe5(C5D);
        chonghe5(D5D);

        chonGhe = (ChonGhe) getActivity();

        btnChonGheTang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGhe.clickChonGhe();
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
                    listGheDaChonTang1.add(imageView.getTag().toString());
                    Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                    check = false;
                    return;
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    check = true;
                    return;
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
                    listGheDaChonTang1.add(imageView.getTag().toString());
                    Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                    check = false;
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
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
                    listGheDaChonTang1.add(imageView.getTag().toString());
                    Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                    check = false;
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
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
                    listGheDaChonTang1.add(imageView.getTag().toString());
                    Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                    check = false;
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
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
                    listGheDaChonTang1.add(imageView.getTag().toString());
                    Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                    check = false;
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    listGheDaChonTang1.remove(listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    check = true;
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
