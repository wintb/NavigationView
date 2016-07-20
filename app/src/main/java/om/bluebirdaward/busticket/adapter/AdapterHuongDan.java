package om.bluebirdaward.busticket.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.utils.BitmapLoader;

/**
 * Created by VuVanThang on 6/29/2016.
 */
public class AdapterHuongDan extends PagerAdapter {

    private Context context;
    private String[] title;

    public AdapterHuongDan(Context context, String[] title){
        this.context = context;
        this.title = title;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view  == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_item_huongdan, container,false);
        ImageView imageBackground = (ImageView) itemView.findViewById(R.id.fragment_huongdan_bacground);
        LinearLayout layoutContent = (LinearLayout) itemView.findViewById(R.id.fragment_huongdan_layoutContent);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.fragment_huongdan_icon);
        TextView textView = (TextView) itemView.findViewById(R.id.fragment_huongdan_title);

        Typeface face1 = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface face2 = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
        textView.setTypeface(face1);

        switch (position) {
            case 0:
                BitmapLoader.LoadImageNotScale(context, imageView, R.drawable.icon_huongdan_timchuyen);
                BitmapLoader.LoadImageNotScale(context, imageBackground, R.drawable.background_huongdan);
                break;

            case 1:
                BitmapLoader.LoadImageNotScale(context, imageView, R.drawable.iocn_b);
                BitmapLoader.LoadImageNotScale(context, imageBackground, R.drawable.background_huongdan);
                break;

            case 2:
                BitmapLoader.LoadImageNotScale(context, imageView, R.drawable.icon_c);
                BitmapLoader.LoadImageNotScale(context, imageBackground, R.drawable.background_huongdan);
                break;
            case 3:
                BitmapLoader.LoadImageNotScale(context, imageView, R.drawable.icon_d);
                BitmapLoader.LoadImageNotScale(context, imageBackground, R.drawable.background_huongdan);
                break;
        }

        textView.setText(title[position]);

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
