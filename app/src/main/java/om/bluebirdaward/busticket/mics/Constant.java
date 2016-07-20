package om.bluebirdaward.busticket.mics;

/**
 * Created by VuVanThang on 6/4/2016.
 */
public class Constant {

    /**
     * KEY_CHECK_FRAGMENT = 0 : sơ đồ ghế cho xem vé
     * KEY_CHECK_FRAGMENT = 1 :sơ đồ ghế cho đặt vé
     */
    public static final String ROOT_URL = "http://doanchuyennganh.96.lt/";
    public static final String host = "http://doanchuyennganh.96.lt/index.php/api/";

    public static int KEY_CHECK_FRAGMENT = 2;
    public static final String URL_TAIXE = ROOT_URL + "json_tai_xe.php";
    public static final String URL_SODOGHE = ROOT_URL + "Json_so_do_ghe.php";
    public static final String URL_TIMCHUYEN_MOTCHIEU = ROOT_URL + "danhsachchuyendimotchieu.php";
    public static final String URL_TENCACCHUYEN = ROOT_URL + "jsontencacchuyen.php";
    public static final String INSERT_URL = ROOT_URL + "insertkhachhang.php";
    public static final String URL_DOI_GHE = ROOT_URL + "updatedoighe.php";
    public static final String URL_HUY_VE = ROOT_URL + "delete_ve.php";
    public static final String URL_SUA_VE = ROOT_URL + "update_thongtinve.php";
    public static final String URL_TONG_SO_GHE = ROOT_URL + "select_soghe.php";
    public static final String URL_THONGTINVE = ROOT_URL + "jsonxemve.php";
}
