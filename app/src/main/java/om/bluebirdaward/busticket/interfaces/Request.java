package om.bluebirdaward.busticket.interfaces;

import om.bluebirdaward.busticket.dao.danhsachchuyen.ReponseDanhSachChuyen;
import java.util.Map;

import om.bluebirdaward.busticket.dao.nhaxe.ReponseListChuyenDi;
import om.bluebirdaward.busticket.dao.nhaxe.ReponseListHangXe;
import om.bluebirdaward.busticket.dao.nhaxe.ResponseListNhaXe;
import om.bluebirdaward.busticket.dao.nhaxedetail.ResponseNhaXeDetail;
import om.bluebirdaward.busticket.dao.sodoghe.ReponseSoDoGhe;
import om.bluebirdaward.busticket.dao.thongtinvevuadat.ReponseThongTinVeVuaDat;
import om.bluebirdaward.busticket.dao.customer.ResponseInfoCustomer;
import om.bluebirdaward.busticket.dao.parent.ResponseVO;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.FieldMap;
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

}
