package om.bluebirdaward.busticket.request;

import om.bluebirdaward.busticket.dao.parent.ResponseVO;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Trinh Dinh Tien on 8/7/2016.
 */
public class ChangeSeatRequest {
    public static void changeSeat(String seat,int id,  final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ResponseVO> call = client.changeSeat(seat, id);

        call.enqueue(new Callback<ResponseVO>() {
            @Override
            public void onResponse(Call<ResponseVO> call, retrofit2.Response<ResponseVO> response) {
                if (response.body() != null) {
                    resp.onSuccess(response.body().code, response.body().message, response.body());
                }else{
                    resp.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ResponseVO> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
