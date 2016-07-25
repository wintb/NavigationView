package om.bluebirdaward.busticket.request;

import om.bluebirdaward.busticket.dao.nhaxedetail.ResponseNhaXeDetail;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Trinh Dinh Tien on 7/21/2016.
 */
public class NhaXeDetailResponse {

    public static void getNhaXeDetail(int id, final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ResponseNhaXeDetail> call = client.getNhaXeDetail(id);
        call.enqueue(new Callback<ResponseNhaXeDetail>() {
            @Override
            public void onResponse(Call<ResponseNhaXeDetail> call, retrofit2.Response<ResponseNhaXeDetail> response) {
                resp.onSuccess(response.body().code, response.body().message, response.body().data);
            }

            @Override
            public void onFailure(Call<ResponseNhaXeDetail> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
