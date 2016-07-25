package om.bluebirdaward.busticket.request;

import om.bluebirdaward.busticket.dao.danhsachchuyen.ReponseDanhSachChuyen;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by VuVanThang on 7/23/2016.
 */
public class DanhSachChuyenRequest {

    public static void getDanhSachChuyen(String id_carmaker, String route, String date, final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ReponseDanhSachChuyen> call = client.getDanhSachChuyen(id_carmaker, route, date);
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
