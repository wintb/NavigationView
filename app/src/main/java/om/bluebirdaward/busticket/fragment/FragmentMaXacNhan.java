package om.bluebirdaward.busticket.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

import om.bluebirdaward.busticket.R;

/**
 * Created by VuVanThang on 7/31/2016.
 */
public class FragmentMaXacNhan extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ma_xac_nhan,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Mã xác nhận");
        ImageView imgMaXacNhan = (ImageView) view.findViewById(R.id.imgMaXacNhan);
        getMaXacNhan(imgMaXacNhan);
    }

    private void getMaXacNhan( ImageView imageView){
        File path = new File("/sdcard/BusTicket/");
        if(path.exists())
        {
            //Bitmap mBitmap = Bitmap.decodeFile(path.getPath() + "" + "Image" + ".jpg");
            Bitmap bitmap = BitmapFactory.decodeFile(path.getPath() + "/" + "Image" + ".jpg");
            imageView.setImageBitmap(bitmap);
        }




    }


}
