package om.bluebirdaward.busticket.request;

import java.util.Map;

import om.bluebirdaward.busticket.dao.customer.ResponseInfoCustomer;
import om.bluebirdaward.busticket.interfaces.Request;
import om.bluebirdaward.busticket.interfaces.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Trinh Dinh Tien on 7/25/2016.
 */
public class EditCustomerRequest {

    public static void editCustomer(Map<String, String> data, final Response resp) {
        resp.onStart();
        Request client = HandlerRequest.createService(Request.class);
        Call<ResponseInfoCustomer> call = client.editTicket(data);
        call.enqueue(new Callback<ResponseInfoCustomer>() {
            @Override
            public void onResponse(Call<ResponseInfoCustomer> call, retrofit2.Response<ResponseInfoCustomer> response) {
                resp.onSuccess(response.body().code, response.body().message, response.body().data);
            }

            @Override
            public void onFailure(Call<ResponseInfoCustomer> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
