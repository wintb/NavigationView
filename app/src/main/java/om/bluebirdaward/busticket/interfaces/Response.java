package om.bluebirdaward.busticket.interfaces;

/**
 * Created by Trinh Dinh Tien on 7/10/2016.
 */
public interface Response {

    void onStart();
    void onSuccess(int code, String message, Object obj);
    void onFailure();
}
