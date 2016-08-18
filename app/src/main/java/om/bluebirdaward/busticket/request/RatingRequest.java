package om.bluebirdaward.busticket.request;

import om.bluebirdaward.busticket.dao.DanhSachChuyen.ReponseDanhSachChuyen;
import om.bluebirdaward.busticket.dao.parent.ResponseVO;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Trinh Dinh Tien on 8/19/2016.
 */
public class RatingRequest {

    public static void ratingCarmaker(String rating, int id, final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ResponseVO> call = client.ratingForCarmaker(rating, id);
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
