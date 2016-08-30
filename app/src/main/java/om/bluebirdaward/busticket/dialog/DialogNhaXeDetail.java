package om.bluebirdaward.busticket.dialog;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.dao.NhaXeDetail.NhaXeDetail;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.mics.Constant;
import om.bluebirdaward.busticket.request.NhaXeDetailRequest;
import om.bluebirdaward.busticket.utils.ShowDialog;

public class DialogNhaXeDetail extends Activity {

    private ImageView imgPhone, imgNhaXe;
    private TextView txtPhone, txtAbout, txtRating, txtCarmaker,txtEmailNhaXeDetail;
    private int carmaker_id;
    private NhaXeDetail nhaXeDetail;
    private RatingBar ratingNhaXeDetail;
    private RelativeLayout layout_dialog_nhaxe;
    private LinearLayout layoutcallphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_nha_xe_detail);
        setFinishOnTouchOutside(false);

        addWidget();
        layoutcallphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + txtPhone.getText()));
                if (ActivityCompat.checkSelfPermission(DialogNhaXeDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("my_id");
        carmaker_id = bundle.getInt("carmaker_id");
        getNhaXeDetail(carmaker_id);
    }


    private void addWidget() {
        imgPhone = (ImageView) findViewById(R.id.imgPhone);
        txtPhone = (TextView) findViewById(R.id.txtPhoneNhaXeDetail);
        imgNhaXe = (ImageView) findViewById(R.id.imgNhaXeDetail);
        txtAbout = (TextView) findViewById(R.id.txtAboutNhaXeDetail);
        txtRating = (TextView) findViewById(R.id.txtRating);
        ratingNhaXeDetail = (RatingBar)findViewById(R.id.ratingNhaXeDetail);
        layout_dialog_nhaxe = (RelativeLayout)findViewById(R.id.layout_dialog_nhaxe);
        txtCarmaker = (TextView) findViewById(R.id.txtCarmaker);
        txtEmailNhaXeDetail = (TextView) findViewById(R.id.txtEmailNhaXeDetail);
        layoutcallphone = (LinearLayout)findViewById(R.id.layoutcallphone);
    }

    private void addData(final int id) {
        Picasso.with(getBaseContext())
                .load(nhaXeDetail.image)
                .into(imgNhaXe);
        txtAbout.setText(nhaXeDetail.intro);
        txtPhone.setText(nhaXeDetail.phone);
        txtCarmaker.setText(nhaXeDetail.carmaker);
        txtEmailNhaXeDetail.setText(nhaXeDetail.email);
        txtRating.setText(nhaXeDetail.ratingAverage + "/5");
//        ratingNhaXeDetail.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("id_carmaker", id);
//                    bundle.putFloat("rating", ratingNhaXeDetail.getRating());
//                    Intent intent = new Intent(DialogNhaXeDetail.this, DialogRating.class);
//                    intent.putExtra("my_rating_bundle", bundle);
//                    startActivity(intent);
//                }
//                return true;
//            }
//        });
        ratingNhaXeDetail.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Bundle bundle = new Bundle();
                bundle.putInt("id_carmaker", id);
                bundle.putFloat("rating", ratingNhaXeDetail.getRating());
                Intent intent = new Intent(DialogNhaXeDetail.this, DialogRating.class);
                intent.putExtra("my_rating_bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void getNhaXeDetail(final int id) {
        Log.d("DiaLogNhaXeDetail", "Id la: " + id);

        NhaXeDetailRequest.getNhaXeDetail(id, new Response() {
            @Override
            public void onStart() {
                ShowDialog.showLoading(DialogNhaXeDetail.this);
            }

            @Override
            public void onSuccess(int code, String message, Object obj) {

                if (code == 0) {
                    ShowDialog.dimissLoading();
                    nhaXeDetail = (NhaXeDetail) obj;
                    addData(id);
                    layout_dialog_nhaxe.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure() {
                Toast.makeText(DialogNhaXeDetail.this, "fail", Toast.LENGTH_SHORT).show();
                ShowDialog.dimissLoading();
            }
        });
    }

    public static void alertCall(final Context context,String phone) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_check_internet, null);
        Button btnCall = (Button) view.findViewById(R.id.btnCall);
        btnCall.setText(" Call "+phone);
        alertDialogBuilder.setView(view);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.CALL_EVENT = 1;
                alertDialog.dismiss();
            }
        });

    }
}
