package om.bluebirdaward.busticket.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by TranTy on 6/17/2016.
 */
public class ShowDialog {
    public static void show(Context context, String title, String message){
        ProgressDialog.Builder dialog = new ProgressDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }

    private static ProgressDialog progressDialog;
    public static void showLoading(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public static void dimissLoading(){
        progressDialog.dismiss();
    }

}
