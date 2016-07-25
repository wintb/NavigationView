package om.bluebirdaward.busticket.dialog;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.dao.nhaxedetail.NhaXeDetail;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.request.NhaXeDetailResponse;

public class DialogNhaXeDetail extends Activity {

    private ImageView imgPhone, imgNhaXe;
    private TextView txtPhone, txtAbout;
    private int carmaker_id;
    private NhaXeDetail nhaXeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_nha_xe_detail);

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
        carmaker_id = bundle.getInt("carmaker_id");
        getNhaXeDetail(carmaker_id);
    }

    public void addWidget(){
        imgPhone = (ImageView) findViewById(R.id.imgPhone);
        txtPhone = (TextView) findViewById(R.id.txtPhoneNhaXeDetail);
        imgNhaXe = (ImageView) findViewById(R.id.imgNhaXeDetail);
        txtAbout = (TextView) findViewById(R.id.txtAboutNhaXeDetail);
    }

    public void getNhaXeDetail(int id){

        NhaXeDetailResponse.getNhaXeDetail(id, new Response() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int code, String message, Object obj) {

                if (code == 0){
                    nhaXeDetail = (NhaXeDetail)obj;
                    Picasso.with(getBaseContext())
                            .load(nhaXeDetail.image)
                            .into(imgNhaXe);
                    txtAbout.setText(nhaXeDetail.intro);
                    txtPhone.setText(nhaXeDetail.phone);

                }
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
