package om.bluebirdaward.busticket.request;

import om.bluebirdaward.busticket.dao.DanhSachChuyen.ReponseDanhSachChuyen;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by VuVanThang on 7/26/2016.
 */
public class DanhSachChuyen2paramsRequest {

    public static void getDanhSachChuyen2Params(String route, String date, final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ReponseDanhSachChuyen> call = client.getDanhSachChuyen2Params(route, date);
        call.enqueue(new Callback<ReponseDanhSachChuyen>() {
            @Override
            public void onResponse(Call<ReponseDanhSachChuyen> call, retrofit2.Response<ReponseDanhSachChuyen> response) {
                if (response.body() != null) {
                    resp.onSuccess(response.body().code, response.body().message, response.body().data);
                }else{
                    resp.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ReponseDanhSachChuyen> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
