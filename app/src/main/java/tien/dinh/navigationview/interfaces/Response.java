package tien.dinh.navigationview.interfaces;

/**
 * Created by Trinh Dinh Tien on 7/10/2016.
 */
public interface Response {

    void onStart();
    void onSuccess(int error_code, String message, Object obj);
    void onFailure();
}
