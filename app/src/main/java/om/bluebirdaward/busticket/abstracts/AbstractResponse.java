package om.bluebirdaward.busticket.abstracts;

import om.bluebirdaward.busticket.interfaces.Response;

/**
 * Created by TranTy on 6/6/2016.
 */
public abstract class AbstractResponse implements Response{
    public void onStart(){
    }

    public void onSuccess(int error_code, String message, Object obj){
    }

    public void onFailure(){
    }
}
