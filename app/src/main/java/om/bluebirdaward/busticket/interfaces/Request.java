package om.bluebirdaward.busticket.interfaces;

import om.bluebirdaward.busticket.dao.NhaXe.ResponseListNhaXe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Trinh Dinh Tien on 7/10/2016.
 */
public interface Request {

    @GET("listCarmaker")
    Call<ResponseListNhaXe> getListNhaXe();
}
