package om.bluebirdaward.busticket.request;

import om.bluebirdaward.busticket.dao.nhaxe.ReponseListHangXe;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by VuVanThang on 7/21/2016.
 */
public class ListHangXeRequest {

    public static void getListHangXe(final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ReponseListHangXe> call = client.getListHangXe();
        call.enqueue(new Callback<ReponseListHangXe>() {
            @Override
            public void onResponse(Call<ReponseListHangXe> call, retrofit2.Response<ReponseListHangXe> response) {
                resp.onSuccess(response.body().code, response.body().message, response.body().data);
            }

            @Override
            public void onFailure(Call<ReponseListHangXe> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
