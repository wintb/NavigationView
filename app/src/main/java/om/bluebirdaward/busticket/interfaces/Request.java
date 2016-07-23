package om.bluebirdaward.busticket.interfaces;

import om.bluebirdaward.busticket.dao.NhaXe.ReponseListChuyenDi;
import om.bluebirdaward.busticket.dao.NhaXe.ReponseListHangXe;
import om.bluebirdaward.busticket.dao.NhaXe.ResponseListNhaXe;
import om.bluebirdaward.busticket.dao.NhaXeDetail.ResponseNhaXeDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Trinh Dinh Tien on 7/10/2016.
 */
public interface Request {

    @GET("listCarmaker")
    Call<ResponseListNhaXe> getListNhaXe();

    @GET("listNameCarmaker")
    Call<ReponseListHangXe> getListHangXe();

    @GET("getTripCarmaker/{id}")
    Call<ReponseListChuyenDi> getListChuyenDi(@Path("id") int id);


    @GET("detailCarmaker/{id}")
    Call<ResponseNhaXeDetail> getNhaXeDetail(@Path("id") int id);
}
