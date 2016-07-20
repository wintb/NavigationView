package om.bluebirdaward.busticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import om.bluebirdaward.busticket.R;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread background = new Thread() {
            public void run() {

                try {
                    sleep(3*1000);
                    Intent i=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
