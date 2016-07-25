package om.bluebirdaward.busticket.request;

import java.util.Map;

import om.bluebirdaward.busticket.dao.customer.ResponseInfoCustomer;
import om.bluebirdaward.busticket.dao.parent.ResponseVO;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Trinh Dinh Tien on 7/24/2016.
 */
public class DeleteCustomerRequest {

    public static void deleteCustomer(Map<String, String> data, final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ResponseVO> call = client.deleteTicket(data);
        call.enqueue(new Callback<ResponseVO>() {
            @Override
            public void onResponse(Call<ResponseVO> call, retrofit2.Response<ResponseVO> response) {
                resp.onSuccess(response.body().code, response.body().message, response.body());
            }

            @Override
            public void onFailure(Call<ResponseVO> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
