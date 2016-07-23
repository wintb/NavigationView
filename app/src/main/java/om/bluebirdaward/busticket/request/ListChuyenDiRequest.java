package om.bluebirdaward.busticket.request;

import om.bluebirdaward.busticket.dao.NhaXe.ReponseListChuyenDi;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by VuVanThang on 7/23/2016.
 */
public class ListChuyenDiRequest {

    public static void getListChuyenDi(int id, final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ReponseListChuyenDi> call = client.getListChuyenDi(id);
        call.enqueue(new Callback<ReponseListChuyenDi>() {
            @Override
            public void onResponse(Call<ReponseListChuyenDi> call, retrofit2.Response<ReponseListChuyenDi> response) {
                resp.onSuccess(response.body().code, response.body().message, response.body().data);
            }

            @Override
            public void onFailure(Call<ReponseListChuyenDi> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
