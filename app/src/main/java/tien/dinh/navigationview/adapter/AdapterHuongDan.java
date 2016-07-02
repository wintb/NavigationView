package tien.dinh.navigationview.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.utils.BitmapLoader;

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

        switch (position) {
            case 0:
                BitmapLoader.LoadImageNotScale(context, imageView, R.drawable.icon_timchuyen);
                BitmapLoader.LoadImageNotScale(context, imageBackground, R.drawable.background_guide_scan);
                break;

            case 1:
                BitmapLoader.LoadImageNotScale(context, imageView, R.drawable.icon_order_guide);
                BitmapLoader.LoadImageNotScale(context, imageBackground, R.drawable.background_guide__all_food_drink);
                break;

            case 2:
                BitmapLoader.LoadImageNotScale(context, imageView, R.drawable.icon_finish_food);
                BitmapLoader.LoadImageNotScale(context, imageBackground, R.drawable.background_guide_payment_bill);
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
