package om.bluebirdaward.busticket.fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.request.ThongTinVeVuaDatRequest;
import om.bluebirdaward.busticket.utils.BitmapLoader;
import om.bluebirdaward.busticket.utils.ShowDialog;

/**
 * Created by DinhTien on 31-05-2016.
 */
public class FragmentThongTinVeVuaDat extends Fragment {

    private TextView txtHoTenVuaDat;
    private TextView txtSDTVuaDat;
    private TextView txtCMNDVuaDat;
    private TextView txtGhiChuVuaDat;
    private TextView txtMaVeVuaDat;
    private TextView txtSoGheVuaDat;
    private TextView txtMaTaiVuaDat;
    private TextView txtTenChuyenDiVuaDat;
    private TextView txtNgayDiVuaDat;
    private TextView txtGioDiVuaDat;
    private TextView txtSLVeVuaDat ;
    private TextView txtXacNhan;
    private TextView txtSua;

    private String SDTKhach;
    private String HoTen;
    private String CMND;
    private String GhiChu;
    private String MaVe;
    private String MaChuyen;
    private int SoLuong;
    private String MaTai;
    private String SoGhe = "";
    private String TenChuyen;
    private String GioDi;
    private String NgayDi;
    private String code_trip;


    backDatve backDatveFragment;
    String ThongBao;
    protected String StringForEncode;
    protected String md5Qrcode;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thong_tin_ve_vua_dat,container,false);

        txtCMNDVuaDat = (TextView)view.findViewById(R.id.txtCMNDVuaDat);
        txtHoTenVuaDat = (TextView)view.findViewById(R.id.txtHoTenVuaDat);
        txtSDTVuaDat = (TextView)view.findViewById(R.id.txtSDTVuaDat);
        txtGhiChuVuaDat = (TextView)view.findViewById(R.id.txtGhiChuVuaDat);
        txtSoGheVuaDat = (TextView)view.findViewById(R.id.txtSoGheVuaDat);
        txtMaTaiVuaDat = (TextView)view.findViewById(R.id.txtMaTaiVuaDat);
        txtTenChuyenDiVuaDat = (TextView)view.findViewById(R.id.txtTenChuyenDiVuaDat);
        txtNgayDiVuaDat = (TextView)view.findViewById(R.id.txtNgayDiVuaDat);
        txtGioDiVuaDat = (TextView)view.findViewById(R.id.txtGioDiVuaDat);
        txtMaVeVuaDat = (TextView)view.findViewById(R.id.txtMaVeVuaDat);
        txtSLVeVuaDat = (TextView)view.findViewById(R.id.txtSLVeVuaDat);
        txtXacNhan = (TextView)view.findViewById(R.id.txtXacNhan);
        txtSua = (TextView)view.findViewById(R.id.txtSua);
        backDatveFragment = (backDatve)getActivity();

        Bundle data = getArguments();

        SDTKhach = data.getString("SDT");
        HoTen = data.getString("HoTen");
        CMND = data.getString("CMND");
        GhiChu = data.getString("GhiChu");
        MaTai = data.getString("MaTai");
        MaVe = data.getString("MaVe");
        //SoGhe = data.getString("SoGhe");
        SoLuong = data.getInt("SoLuong");
        MaChuyen = data.getString("MaChuyen");
        code_trip = data.getString("code_trip");
        TenChuyen = data.getString("TenChuyen");
        GioDi = data.getString("GioDi");
        NgayDi = data.getString("NgayDi");

        for (int i = 0; i < FragmentTabhostSoDoGhe.listGheDaChonTang1.size(); i++){

            SoGhe += data.getString("SoGhe" + (i+1));
            if (i < FragmentTabhostSoDoGhe.listGheDaChonTang1.size() - 1)
                SoGhe += " - ";
        }

        txtCMNDVuaDat.setText(CMND);
        txtHoTenVuaDat.setText(HoTen);
        txtSDTVuaDat.setText(SDTKhach);
        txtGhiChuVuaDat.setText(GhiChu);
        txtMaTaiVuaDat.setText(MaTai);
        txtMaVeVuaDat.setText(MaVe);
        txtSoGheVuaDat.setText(SoGhe);
        txtSLVeVuaDat.setText(String.valueOf(SoLuong));
        txtTenChuyenDiVuaDat.setText(TenChuyen);
        txtGioDiVuaDat.setText(GioDi);
        txtNgayDiVuaDat.setText(NgayDi);

        StringForEncode = txtCMNDVuaDat.getText().toString() + txtSDTVuaDat.getText().toString() + System.currentTimeMillis();
        md5Qrcode = md5(StringForEncode);

        txtXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacnhan_datve(new Gson().toJson(FragmentTabhostSoDoGhe.listGheDaChonTang1),
                        txtHoTenVuaDat.getText().toString(),
                        txtCMNDVuaDat.getText().toString(),
                        txtSDTVuaDat.getText().toString(),
                        md5Qrcode,
                        txtSLVeVuaDat.getText().toString(),
                        txtGhiChuVuaDat.getText().toString(),
                        MaChuyen,
                        code_trip);
            }
        });

        txtSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    public interface backDatve{
        void setBackDatVe();
    }


    protected String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    //-----------------------------------------API--------------------------------------------------


    private void xacnhan_datve(final String seat, String fullname, String identity_number, String phone,
                               String qrcode, String quantity, String note, String id_tripdate, String code_trip){

        ShowDialog.showLoading(getActivity());
        ThongTinVeVuaDatRequest.getThongTinVeVuaDat(seat, fullname, identity_number, phone, qrcode,
                quantity, note, id_tripdate, code_trip, new Response() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(int code, String message, Object obj) {
                        ShowDialog.dimissLoading();
                        if (code == 1){
                            showDialogSuccess_error("Đặt vé không thành công !", R.drawable.error, "Vui lòng thử lại.",true);

                        } else if(code == 0){
                            showDialogSuccess_error("Đặt vé thành công !", R.drawable.success_new, "Nhấn OK để tiếp tục",false);
                        }
                    }

                    @Override
                    public void onFailure() {
                        showDialogSuccess_error("Đặt vé không thành công !", R.drawable.error, "Vui lòng thử lại.",true);
                    }
                });

    }

    //--------------------------DIALOG--------------------------------------------------------------

    private Dialog dialog;

    public void showDialogSuccess_error(String title, int image, String message, boolean checkError){
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_layout_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        TextView txtTitle = (TextView) dialog.findViewById(R.id.VeVuaDat_txtTitle);
        ImageView imgStatus = (ImageView) dialog.findViewById(R.id.VeVuaDat_imgStatus);
        TextView txtMessage = (TextView) dialog.findViewById(R.id.VeVuaDat_message);
        Button btnOK = (Button) dialog.findViewById(R.id.VeDaDat_btnOK);

        txtTitle.setText(title);
        //imgStatus.setImageResource(image);
        BitmapLoader.LoadImageNotScale(getActivity(), imgStatus, image);
        txtMessage.setText(message);

        if (checkError == true){
            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dimissDialog();
                }
            });
        }else {
            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dimissDialog();
                    showDialogQrcode(md5Qrcode);
                }
            });
        }



    }

    public void showDialogQrcode( String qrcode){
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_layout_dialog_qrcode);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        ImageView imgQrcode = (ImageView) dialog.findViewById(R.id.imgQrCode);
        Button btnQrcodeOK = (Button) dialog.findViewById(R.id.QRcode_btnOK);
        createQrCode(qrcode, imgQrcode);
        //addImageToGallery();
        SaveImage(bmp);

        btnQrcodeOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimissDialog();
                backDatveFragment.setBackDatVe();
            }
        });

    }

    public void dimissDialog(){
        dialog.dismiss();
    }


    private Bitmap bmp;
    public void createQrCode(String qrcode, ImageView imageView){
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(qrcode, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            imageView.setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


    public void addImageToGallery(){
        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bmp, "ImageQRCODE", "ImageQRCODE");
    }

    private void SaveImage(Bitmap finalBitmap) {

        File myDir = new File("/sdcard/BusTicket/");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image"+".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
