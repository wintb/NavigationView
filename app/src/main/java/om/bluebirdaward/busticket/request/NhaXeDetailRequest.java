package om.bluebirdaward.busticket.request;

import om.bluebirdaward.busticket.dao.NhaXeDetail.ResponseDetailNhaXe;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Trinh Dinh Tien on 8/18/2016.
 */
public class NhaXeDetailRequest {

    public static void getNhaXeDetail( int id, final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ResponseDetailNhaXe> call = client.getNhaXeDetail(id);
        call.enqueue(new Callback<ResponseDetailNhaXe>() {
            @Override
            public void onResponse(Call<ResponseDetailNhaXe> call, retrofit2.Response<ResponseDetailNhaXe> response) {
                if (response.body() != null) {
                    resp.onSuccess(response.body().code, response.body().message, response.body().data);
                }else {
                    resp.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailNhaXe> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
