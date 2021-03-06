package om.bluebirdaward.busticket.request;

import om.bluebirdaward.busticket.dao.NhaXe.ResponseListNhaXe;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Trinh Dinh Tien on 7/20/2016.
 */
public class ListNhaXeRequest {

    public static void getListNhaXe(final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ResponseListNhaXe> call = client.getListNhaXe();
        call.enqueue(new Callback<ResponseListNhaXe>() {
            @Override
            public void onResponse(Call<ResponseListNhaXe> call, retrofit2.Response<ResponseListNhaXe> response) {
                if (response.body() != null) {
                    resp.onSuccess(response.body().code, response.body().message, response.body().data);
                }else {
                    resp.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ResponseListNhaXe> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
