package om.bluebirdaward.busticket.request;

import om.bluebirdaward.busticket.dao.ChuyenDi.ResponseChuyenDi2Params;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by VuVanThang on 7/26/2016.
 */
public class ChuyenDi2ParamsRequest {
    public static void getTenChuyenDi2Params(final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ResponseChuyenDi2Params> call = client.getTenChuyenDi2Params();
        call.enqueue(new Callback<ResponseChuyenDi2Params>() {
            @Override
            public void onResponse(Call<ResponseChuyenDi2Params> call, retrofit2.Response<ResponseChuyenDi2Params> response) {
                if (response.body() != null) {
                    resp.onSuccess(response.body().code, response.body().message, response.body().data);
                }else{
                    resp.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ResponseChuyenDi2Params> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
