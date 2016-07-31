package om.bluebirdaward.busticket.interfaces;

import java.util.Map;

import om.bluebirdaward.busticket.dao.ChuyenDi.ResponseChuyenDi2Params;
import om.bluebirdaward.busticket.dao.DanhSachChuyen.ReponseDanhSachChuyen;
import om.bluebirdaward.busticket.dao.NhaXe.ReponseListChuyenDi;
import om.bluebirdaward.busticket.dao.NhaXe.ReponseListHangXe;
import om.bluebirdaward.busticket.dao.NhaXe.ResponseListNhaXe;
import om.bluebirdaward.busticket.dao.NhaXeDetail.ResponseNhaXeDetail;
import om.bluebirdaward.busticket.dao.SoDoghe.ReponseSoDoGhe;
import om.bluebirdaward.busticket.dao.ThongTinVeVuaDat.ReponseThongTinVeVuaDat;
import om.bluebirdaward.busticket.dao.customer.ResponseInfoCustomer;
import om.bluebirdaward.busticket.dao.parent.ResponseVO;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST("getInfoCustomer")
    Call<ResponseInfoCustomer> getInfoCustomer(@FieldMap Map<String,String> field);

    @FormUrlEncoded
    @POST("deleteCustomer")
    Call<ResponseVO> deleteTicket(@FieldMap Map<String,String> field);

    @FormUrlEncoded
    @POST("getListTripDateByCarmaker")
    Call<ReponseDanhSachChuyen> getDanhSachChuyen( @Field("id_carmaker") String id_carmaker, @Field("route") String route, @Field("date") String date);

    @FormUrlEncoded
    @POST("getListSeat")
    Call<ReponseSoDoGhe> getSoDoGhe(@Field("id_tripdate") String id_tripdate);

    @FormUrlEncoded
    @POST("insertCustomerAndTicket")
    Call<ReponseThongTinVeVuaDat> getThongTinVeVuaDat(@Field("seat")String seat,
                                                      @Field("fullname") String fullname,
                                                      @Field("identity_number") String identity_number,
                                                      @Field("phone") String phone,
                                                      @Field("qrcode") String qrcode,
                                                      @Field("quantity") String quantity,
                                                      @Field("note") String note,
                                                      @Field("id_tripdate") String id_tripdate,
                                                      @Field("code_trip") String code_trip);

    @FormUrlEncoded
    @POST("changeInfoCustomer")
    Call<ResponseInfoCustomer> editTicket(@FieldMap Map<String,String> field);

    @GET("getListTrip")
    Call<ResponseChuyenDi2Params> getTenChuyenDi2Params();

    @FormUrlEncoded
    @POST("getListTripDate")
    Call<ReponseDanhSachChuyen> getDanhSachChuyen2Params(@Field("route") String route, @Field("date") String date);

}
