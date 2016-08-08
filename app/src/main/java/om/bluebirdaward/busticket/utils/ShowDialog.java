package om.bluebirdaward.busticket.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.activity.MainActivity;
import om.bluebirdaward.busticket.fragment.FragmentSuaThongTinVe;

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

    public static void alertDialog(Context context, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_dialog_result, null);
        TextView txtMessage = (TextView) view.findViewById(R.id.txtMessage);
        Button btnOK = (Button)view.findViewById(R.id.btnOK);
        txtMessage.setText(message);
        alertDialogBuilder.setView(view);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    public static void alertDialogResult(final Context context, String title, String message, final int code) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_dialog, null);
        TextView VeVuaDat_txtTitle = (TextView) view.findViewById(R.id.VeVuaDat_txtTitle);
        TextView VeVuaDat_message = (TextView) view.findViewById(R.id.VeVuaDat_message);
        Button VeDaDat_btnOK = (Button)view.findViewById(R.id.VeDaDat_btnOK);
        ImageView VeVuaDat_imgStatus = (ImageView)view.findViewById(R.id.VeVuaDat_imgStatus);
        VeVuaDat_txtTitle.setText(title);
        VeVuaDat_message.setText(message);
        alertDialogBuilder.setView(view);
        final AlertDialog alertDialog = alertDialogBuilder.create();

        if (code == 0)
            VeVuaDat_imgStatus.setImageResource(R.drawable.success_new);
        else if (code == -1)
            VeVuaDat_imgStatus.setImageResource(R.drawable.fail);
        else
            VeVuaDat_imgStatus.setImageResource(R.drawable.error);

        alertDialog.show();
        VeDaDat_btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (code == 0){
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }else{
                    alertDialog.dismiss();
                }
            }
        });
    }

    public static void alertDialogCheckInternet(final Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_check_internet, null);
        Button btnExit = (Button) view.findViewById(R.id.btnOk);
        alertDialogBuilder.setView(view);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    public static void alertDialogExit(final Activity context, String question) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_exit, null);
        TextView txtQuestion = (TextView)view.findViewById(R.id.txtQuestion);
        txtQuestion.setText(question);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        Button btnExit = (Button) view.findViewById(R.id.btnOk);
        alertDialogBuilder.setView(view);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                context.finish();
            }
        });

    }
}
