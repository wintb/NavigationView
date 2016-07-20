package om.bluebirdaward.busticket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.adapter.AdapterHuongDan;

/**
 * Created by DinhTien on 19-06-2016.
 */
public class FragmentHuongDan extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener{

    @Bind(R.id.fragment_huongdan_viewpager)
    ViewPager guide_viewpager;
    @Bind(R.id.viewPagerCountDots)
    LinearLayout pager_indicator;

    private int dotsCount;
    private ImageView[] dots;
    private AdapterHuongDan viewPagerApdapter;

    private String[] title = {
            "Chọn chuyến đi và ngày đi \n Nhấn Tìm chuyến để hiển thị danh sách các chuyến",
            "Chọn ghế cho chuyến đi đã chọn",
            "Điền thông tin khách hàng \n Yêu cầu xe trung chuyển ở phần ghi chú",
            "Điền SDT và CMND để xem vé\n Sau đó thao tác xóa, sửa hoặc đổi vé"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_huongdan, container, false);
        ButterKnife.bind(this,rootView);
        getActivity().setTitle("Hướng dẫn");
        viewPagerApdapter = new AdapterHuongDan(getActivity(), title);
        guide_viewpager.setAdapter(viewPagerApdapter);
        guide_viewpager.setCurrentItem(0);
        guide_viewpager.setOnPageChangeListener(this);
        setUiPageViewController();
        return rootView;
    }


    @SuppressWarnings("deprecation")
    private void setUiPageViewController(){
        dotsCount = viewPagerApdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.guide_nonselectitem_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(7, 0, 7, 0);

            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.guide_selectitem_dot));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.guide_nonselectitem_dot));
        }
        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.guide_selectitem_dot));
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
