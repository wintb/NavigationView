package om.bluebirdaward.busticket.request;

import android.util.Log;


import om.bluebirdaward.busticket.dao.SoDoGhe.ReponseSoDoGhe;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by VuVanThang on 7/23/2016.
 */
public class SoDoGheRepuest {

    public static void getSoDoGhe(String id_tripdate, final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ReponseSoDoGhe> call = client.getSoDoGhe(id_tripdate);
        call.enqueue(new Callback<ReponseSoDoGhe>() {
            @Override
            public void onResponse(Call<ReponseSoDoGhe> call, retrofit2.Response<ReponseSoDoGhe> response) {
                if (response.body()!= null) {
                    resp.onSuccess(response.body().code, response.body().message, response.body().data);
                }else{
                    Log.d("SoDoGheTang1", "Tất cả các ghế chưa được chọn");
                }
            }

            @Override
            public void onFailure(Call<ReponseSoDoGhe> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
