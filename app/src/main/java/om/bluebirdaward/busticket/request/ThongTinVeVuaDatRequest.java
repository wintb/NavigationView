package om.bluebirdaward.busticket.request;

import om.bluebirdaward.busticket.dao.ThongTinVeVuaDat.ReponseThongTinVeVuaDat;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by VuVanThang on 7/24/2016.
 */
public class ThongTinVeVuaDatRequest {
    public static void getThongTinVeVuaDat(String seat,
                                           String fullname,
                                           String identity_number,
                                           String phone,
                                           String qrcode,
                                           String quantity,
                                           String note,
                                           String id_tripdate,
                                           String code_trip,
                                           final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ReponseThongTinVeVuaDat> call = client.getThongTinVeVuaDat(seat, fullname, identity_number, phone,
                qrcode, quantity, note, id_tripdate, code_trip);

        call.enqueue(new Callback<ReponseThongTinVeVuaDat>() {
            @Override
            public void onResponse(Call<ReponseThongTinVeVuaDat> call, retrofit2.Response<ReponseThongTinVeVuaDat> response) {
                if (response.body() != null) {
                    resp.onSuccess(response.body().code, response.body().message, response.body().data);
                }else{
                    resp.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ReponseThongTinVeVuaDat> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
