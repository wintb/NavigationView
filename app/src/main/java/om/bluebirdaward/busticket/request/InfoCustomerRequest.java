package om.bluebirdaward.busticket.request;

import java.util.Map;

import om.bluebirdaward.busticket.dao.customer.ResponseInfoCustomer;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Trinh Dinh Tien on 7/23/2016.
 */
public class InfoCustomerRequest {

    public static void getInfoCustomer(Map<String, String> data, final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ResponseInfoCustomer> call = client.getInfoCustomer(data);
        call.enqueue(new Callback<ResponseInfoCustomer>() {
            @Override
            public void onResponse(Call<ResponseInfoCustomer> call, retrofit2.Response<ResponseInfoCustomer> response) {
                if (response.body() != null && response.body().data != null) {
                    resp.onSuccess(response.body().code, response.body().message, response.body().data);
                }else{
                    resp.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ResponseInfoCustomer> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
