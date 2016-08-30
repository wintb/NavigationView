
package om.bluebirdaward.busticket.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.mics.Constant;
import om.bluebirdaward.busticket.request.RatingRequest;
import om.bluebirdaward.busticket.utils.ShowDialog;

public class DialogRating extends Activity {

    private RatingBar ratingReview;
    private Button btnReview;
    private TextView txtReview;
    private int id_carmaker;
    private float rating_first;
    private float rating_end;
    private LinearLayout layoutRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_rating);
        addWidget();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("my_rating_bundle");
        id_carmaker = bundle.getInt("id_carmaker");
        rating_first = bundle.getFloat("rating");
        ratingReview.setRating(rating_first);
        textRating(rating_first);
        ratingEvent();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void addWidget(){
        ratingReview = (RatingBar)findViewById(R.id.ratingReview);
        btnReview = (Button)findViewById(R.id.btnReview);
        txtReview = (TextView)findViewById(R.id.txtReview);
        layoutRating = (LinearLayout)findViewById(R.id.layoutRating);
    }


    public void ratingEvent(){

        ratingReview.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Constant.RATING_EVENT = 2;
                textRating(rating);
            }
        });

        if (Constant.RATING_EVENT == 1)
            ratingEvent(String.valueOf(rating_first * 10), id_carmaker);
        else
            ratingEvent(String.valueOf(ratingReview.getRating() * 10), id_carmaker);
    }

    public void ratingEvent(final String rating, final int id){
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingRequest.ratingCarmaker(rating, id, new Response() {
                    @Override
                    public void onStart() {
                        ShowDialog.showLoading(DialogRating.this);
                    }

                    @Override
                    public void onSuccess(int code, String message, Object obj) {

                        if (code == 0) {
                            ShowDialog.dimissLoading();
                            Toast.makeText(DialogRating.this, "Cảm ơn đánh giá của bạn", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            ShowDialog.dimissLoading();
                            Toast.makeText(DialogRating.this, "Đánh giá chưa thành công, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure() {
                        ShowDialog.dimissLoading();
                        Toast.makeText(DialogRating.this, "Đánh giá chưa thành công, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void textRating(float rating){
        if (rating <= 1)
            txtReview.setText("Rất không hài lòng");
        else if (rating > 1 && rating <=2)
            txtReview.setText("Không hài lòng");
        else if (rating > 2 && rating <=3)
            txtReview.setText("Hài lòng");
        else if (rating > 3 && rating <=4)
            txtReview.setText("Thích");
        else if (rating > 4 && rating <=5)
            txtReview.setText("Chất lượng rất tốt");
    }
}
