package om.bluebirdaward.busticket.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.dao.sodoghe.SoDoghe;
import om.bluebirdaward.busticket.dao.Ve;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.request.SoDoGheRepuest;

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

    @Bind(R.id.fragment_sodoghe_tang2_A)
    TextView txtTang2A;
    @Bind(R.id.fragment_sodoghe_tang2_B)
    TextView txtTang2B;
    @Bind(R.id.fragment_sodoghe_tang2_C)
    TextView txtTang2C;

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
    private List<Ve> list;
    ArrayList<String> listGhe;

    //------------------api--------------------
    String TenChuyen;
    String GioDi;
    String NgayDi;
    String code_trip;
    String id_tripdate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_so_do_ghe_tang_2,container,false);
        ButterKnife.bind(this, rootView);
        setTypeFace();
        list = new ArrayList<>();
        listGhe = new ArrayList<>();
        Bundle data = getArguments();
        TenChuyen = data.getString("TenChuyen");
        GioDi = data.getString("GioDi");
        NgayDi = data.getString("NgayDi");
        code_trip = data.getString("code_trip");
        id_tripdate = data.getString("id_tripdate");

        getSoDoGheDaDat(id_tripdate);
        //set color khi click chon ghe
        setColorClickChonGhe();

        return rootView;
    }


    private void setTypeFace(){
        Typeface face1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");
        txtTang2A.setTypeface(face1);
        txtTang2B.setTypeface(face1);
        txtTang2C.setTypeface(face1);
    }

    //==============================================================================================


    private void setColorGheDaChon(ArrayList<String> listGhe){
        setGheDachon1(A1T, listGhe);
        setGheDachon1(B1T, listGhe);
        setGheDachon1(C1T, listGhe);
        setGheDachon1(D1T, listGhe);
        setGheDachon2(A2T, listGhe);
        setGheDachon2(B2T, listGhe);
        setGheDachon2(C2T, listGhe);
        setGheDachon2(D2T, listGhe);
        setGheDachon3(A3T, listGhe);
        setGheDachon3(B3T, listGhe);
        setGheDachon3(C3T, listGhe);
        setGheDachon3(D3T, listGhe);
        setGheDachon4(A4T, listGhe);
        setGheDachon4(B4T, listGhe);
        setGheDachon4(C4T, listGhe);
        setGheDachon4(D4T, listGhe);
        setGheDachon5(A5T, listGhe);
        setGheDachon5(B5T, listGhe);
        setGheDachon5(C5T, listGhe);
        setGheDachon5(D5T, listGhe);
    }

    private void setColorClickChonGhe(){
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
    }


    //--------------------------------SET COLOR KHI CLICK CHỌN GHẾ----------------------------------

    private void chongheA1T(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_A1T == true) {
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A1T = false;
                    }
                    else {
                        Toast.makeText(getActivity(), "Bạn chỉ được chọn nhiều nhất 2 ghế", Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A2T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A3T = false;
                    }
                    else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A4T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A5T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B1T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B2T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B3T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B4T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B5T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C1T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C2T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C3T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C4T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C5T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D1T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D2T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D3T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D4T = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D5T = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D5T = true;
                }
            }
        });
    }

    //---------------------------SET COLOR NHỮNG GHẾ ĐÃ ĐƯỢC NGƯỜI KHÁC CHỌN------------------------

    public void setGheDachon1(ImageView imageView,List<String> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i)))){
                imageView.setImageResource(R.drawable.ghedachon1);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon2(ImageView imageView,List<String> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i)))){
                imageView.setImageResource(R.drawable.ghedachon2);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon3(ImageView imageView,List<String> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i)))){
                imageView.setImageResource(R.drawable.ghedachon3);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon4(ImageView imageView,List<String> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i)))){
                imageView.setImageResource(R.drawable.ghedachon4);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon5(ImageView imageView,List<String> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i)))){
                imageView.setImageResource(R.drawable.ghedachon5);
                imageView.setEnabled(false);
            }
        }
    }

    //----------------------------API---------------------------------------------------------------
    private void getSoDoGheDaDat(String id_tripdate){
        SoDoGheRepuest.getSoDoGhe(id_tripdate, new Response() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int code, String message, Object obj) {
                ArrayList<SoDoghe> soDoghes = (ArrayList<SoDoghe>) obj;
                for (int i = 0; i < soDoghes.size(); i++) {
                    listGhe.add(soDoghes.get(i).seat);
                }

                setColorGheDaChon(listGhe);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
