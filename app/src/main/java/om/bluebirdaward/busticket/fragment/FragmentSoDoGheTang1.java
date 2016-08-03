package om.bluebirdaward.busticket.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.activity.MainActivity;
import om.bluebirdaward.busticket.dao.SoDoGhe.SoDoghe;
import om.bluebirdaward.busticket.dao.Ve;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.json.JsonDoiGhe;
import om.bluebirdaward.busticket.mics.Constant;
import om.bluebirdaward.busticket.request.SoDoGheRepuest;
import om.bluebirdaward.busticket.utils.CheckInternet;
import om.bluebirdaward.busticket.utils.ShowDialog;

/**
 * Created by DinhTien on 15-05-2016.
 */
public class FragmentSoDoGheTang1 extends Fragment {
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

    @Bind(R.id.fragment_sodoghe_tang1_A)
    TextView txtTang1A;
    @Bind(R.id.fragment_sodoghe_tang1_B)
    TextView txtTang1B;
    @Bind(R.id.fragment_sodoghe_tang1_C)
    TextView txtTang1C;
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

    private List<Ve> list;
    ArrayList<String> listGhe;

    ChonGhe chonGhe;
    JsonDoiGhe jsonDoiGhe;


    //------------------api--------------------
    String TenChuyen;
    String GioDi;
    String NgayDi;
    String code_trip;
    String id_tripdate;
    String code_driver;

    public static FragmentSoDoGheTang1 newInstance() {

        return new FragmentSoDoGheTang1();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_so_do_ghe_tang_1,container,false);
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
        code_driver = data.getString("code_driver");

        getSoDoGheDaDat(id_tripdate);
        //set color khi click chon ghe
        setColorClickChonGhe();

        chonGhe = (ChonGhe) getActivity();

        btnChonGheTang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInternet.isConnected(getActivity())){
                    setOnClickChonGhe();
                }else{

                    String title = "Warning";
                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.show(getActivity(), title, message);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Check_A1D = true;
        Check_A2D = true;
        Check_A3D = true;
        Check_A4D = true;
        Check_A5D = true;

        Check_B1D = true;
        Check_B2D = true;
        Check_B3D = true;
        Check_B4D = true;
        Check_B5D = true;

        Check_C1D = true;
        Check_C2D = true;
        Check_C3D = true;
        Check_C4D = true;
        Check_C5D = true;

        Check_D1D = true;
        Check_D2D = true;
        Check_D3D = true;
        Check_D4D = true;
        Check_D5D = true;
    }

    //==============================================================================================


    private void setColorGheDaChon(ArrayList<String> listGhe){
        setGheDachon1(A1D, listGhe);
        setGheDachon1(B1D, listGhe);
        setGheDachon1(C1D, listGhe);
        setGheDachon1(D1D, listGhe);
        setGheDachon2(A2D, listGhe);
        setGheDachon2(B2D, listGhe);
        setGheDachon2(C2D, listGhe);
        setGheDachon2(D2D, listGhe);
        setGheDachon3(A3D, listGhe);
        setGheDachon3(B3D, listGhe);
        setGheDachon3(C3D, listGhe);
        setGheDachon3(D3D, listGhe);
        setGheDachon4(A4D, listGhe);
        setGheDachon4(B4D, listGhe);
        setGheDachon4(C4D, listGhe);
        setGheDachon4(D4D, listGhe);
        setGheDachon5(A5D, listGhe);
        setGheDachon5(B5D, listGhe);
        setGheDachon5(C5D, listGhe);
        setGheDachon5(D5D, listGhe);
    }

    private void setColorClickChonGhe(){
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
    }

    private void setTypeFace(){
        Typeface face1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");
        txtTang1A.setTypeface(face1);
        txtTang1B.setTypeface(face1);
        txtTang1C.setTypeface(face1);
        btnChonGheTang1.setTypeface(face1);

    }


    private void setOnClickChonGhe(){
        if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size() == 0){
            Toast.makeText(getActivity(), "bạn chưa chọn ghế, vui lòng chọn.", Toast.LENGTH_SHORT).show();
        }else{

            if (Constant.KEY_CHECK_FRAGMENT == 1) {
                chonGhe.clickChonGhe(TenChuyen, GioDi, NgayDi);
            }else if (Constant.KEY_CHECK_FRAGMENT == 0){
                Bundle data = getArguments();
                String MaChuyen = data.getString("MaChuyen");
                String MaVe = data.getString("MaVe");
                String SDTKhach = data.getString("SDTKhach");
                String SDT_3SoCuoi = SDTKhach.substring(SDTKhach.length() - 3);
                String MaVeThayDoi = FragmentTabhostSoDoGhe.listGheDaChonTang1.get(0) + SDT_3SoCuoi;

                jsonDoiGhe = new JsonDoiGhe(MaChuyen, MaVe,FragmentTabhostSoDoGhe.listGheDaChonTang1.get(0),MaVeThayDoi);
                String result = null;
                try {
                    result = new WebserviceDoiGhe().execute(Constant.URL_DOI_GHE).get();

                    new AlertDialog.Builder(getActivity()).setTitle("Doi Ghe").setMessage(result)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //getFragmentManager().beginTransaction().replace(R.id.fragmentholder,new FragmentXemVe()).commit();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //-------------------------------GOI WEBSERVICE ĐỂ ĐỔI GHẾ -------------------------------------
    public class WebserviceDoiGhe extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return jsonDoiGhe.makePostRequest_DoiGhe(params[0]);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A1D = false;
                    }
                    else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A2D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A3D = false;
                    }
                    else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A4D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_A5D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B1D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B2D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B3D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B4D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_B5D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C1D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C2D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C3D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C4D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_C5D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon1);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D1D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong1);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon2);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D2D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong2);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon3);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D3D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong3);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon4);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D4D = false;
                    }else{
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong4);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
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
                    if (FragmentTabhostSoDoGhe.listGheDaChonTang1.size()<2) {
                        imageView.setImageResource(R.drawable.ghedangchon5);
                        FragmentTabhostSoDoGhe.listGheDaChonTang1.add(imageView.getTag().toString());
                        Log.d("JSON_DATVE_TANG1:", "Da dat ghe " + imageView.getTag().toString());
                        Check_D5D = false;
                    }else {
                        Toast.makeText(getActivity(),"Bạn chỉ được chọn nhiều nhất 2 ghế",Toast.LENGTH_LONG).show();
                    }
                }else{
                    imageView.setImageResource(R.drawable.ghetrong5);
                    FragmentTabhostSoDoGhe.listGheDaChonTang1.remove(FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1);
                    Log.d("JSON_DATVE_TANG1:", "Da huy ghe " + imageView.getTag().toString());
                    Check_D5D = true;
                }
            }
        });
    }


    //---------------------------SET COLOR NHỮNG GHẾ ĐÃ ĐƯỢC NGƯỜI KHÁC CHỌN------------------------

    public void setGheDachon1(ImageView imageView, List<String> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i)))){
                imageView.setImageResource(R.drawable.ghedachon1);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon2(ImageView imageView, List<String> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i)))){
                imageView.setImageResource(R.drawable.ghedachon2);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon3(ImageView imageView, List<String> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i)))){
                imageView.setImageResource(R.drawable.ghedachon3);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon4(ImageView imageView, List<String> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i)))){
                imageView.setImageResource(R.drawable.ghedachon4);
                imageView.setEnabled(false);
            }
        }
    }

    public void setGheDachon5(ImageView imageView, List<String> list){
        for (int i = 0; i<list.size(); i++){
            if ((imageView.getTag().toString().equalsIgnoreCase(list.get(i)))){
                imageView.setImageResource(R.drawable.ghedachon5);
                imageView.setEnabled(false);
            }
        }
    }

    //============== Interface ================//
    public interface ChonGhe{
//        void clickChonGhe(String MaTai,String MaChuyen, String SoGhe);
        void clickChonGhe(String TenChuyen, String GioDi, String NgayDi);
    }


    //----------------------------API---------------------------------------------------------------
    private void getSoDoGheDaDat(String id_tripdate){
        ShowDialog.showLoading(getActivity());
        SoDoGheRepuest.getSoDoGhe(id_tripdate, new Response() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int code, String message, Object obj) {

                ShowDialog.dimissLoading();
                if (code == 0) {
                    ArrayList<SoDoghe> soDoghes = (ArrayList<SoDoghe>) obj;
                    for (int i = 0; i < soDoghes.size(); i++) {
                        listGhe.add(soDoghes.get(i).seat);
                    }
                    setColorGheDaChon(listGhe);
                }
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
