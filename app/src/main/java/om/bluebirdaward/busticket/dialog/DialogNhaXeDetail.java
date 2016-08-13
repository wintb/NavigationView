package om.bluebirdaward.busticket.dialog;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.dao.NhaXeDetail.NhaXeDetail;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.request.NhaXeDetailResponse;
import om.bluebirdaward.busticket.utils.ShowDialog;

public class DialogNhaXeDetail extends Activity {

    private ImageView imgPhone, imgNhaXe;
    private TextView txtPhone, txtAbout, txtRating;
    private String carmaker_id;
    private NhaXeDetail nhaXeDetail;
    private RatingBar ratingNhaXeDetail;
    private RelativeLayout layout_dialog_nhaxe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_nha_xe_detail);
        setFinishOnTouchOutside(false);

        addWidget();
        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + txtPhone.getText()));
                if (ActivityCompat.checkSelfPermission(DialogNhaXeDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("my_id");
        carmaker_id = bundle.getString("carmaker_id");
        getNhaXeDetail(carmaker_id);
    }

    public void addWidget() {
        imgPhone = (ImageView) findViewById(R.id.imgPhone);
        txtPhone = (TextView) findViewById(R.id.txtPhoneNhaXeDetail);
        imgNhaXe = (ImageView) findViewById(R.id.imgNhaXeDetail);
        txtAbout = (TextView) findViewById(R.id.txtAboutNhaXeDetail);
        txtRating = (TextView) findViewById(R.id.txtRating);
        ratingNhaXeDetail = (RatingBar)findViewById(R.id.ratingNhaXeDetail);
        layout_dialog_nhaxe = (RelativeLayout)findViewById(R.id.layout_dialog_nhaxe);
    }

    public void addData() {
        Picasso.with(getBaseContext())
                .load(nhaXeDetail.image)
                .into(imgNhaXe);
        txtAbout.setText(nhaXeDetail.intro);
        txtPhone.setText(nhaXeDetail.phone);
        txtRating.setText(nhaXeDetail.ratingAverage + "/5");
        ratingNhaXeDetail.setRating(nhaXeDetail.ratingAverage);
    }

    public void getNhaXeDetail(String id) {
        Log.d("DiaLogNhaXeDetail", "Id la: " + id);

        NhaXeDetailResponse.getNhaXeDetail(id, new Response() {
            @Override
            public void onStart() {
                ShowDialog.showLoading(DialogNhaXeDetail.this);
            }

            @Override
            public void onSuccess(int code, String message, Object obj) {

                if (code == 0) {
                    ShowDialog.dimissLoading();
                    nhaXeDetail = (NhaXeDetail) obj;
                    addData();
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
}
