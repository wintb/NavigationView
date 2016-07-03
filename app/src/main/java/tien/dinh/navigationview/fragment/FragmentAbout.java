package tien.dinh.navigationview.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.adapter.AdapterAbout;

/**
 * Created by DinhTien on 19-06-2016.
 */
public class FragmentAbout extends Fragment {

    private Context context;
    private RecyclerView recyclerAbout;
    private AdapterAbout adapterAbout;
    private ArrayList<Items> listItemses;

    public FragmentAbout(){}

   /* public FragmentAbout(Context context) {
        this.context = context;
        listItemses = new ArrayList<>() ;
        this.setItems();
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about,container,false);
        recyclerAbout = (RecyclerView)view.findViewById(R.id.recyclerAbout);
        listItemses = new ArrayList<>();
        setRecyclerAbout(recyclerAbout);
        setItems();
        return view;
    }

    public void setRecyclerAbout(RecyclerView recv){
        adapterAbout = new AdapterAbout(context,listItemses);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView.LayoutManager mLayoutManager = manager;
        recv.setLayoutManager(mLayoutManager);
        recv.setAdapter(adapterAbout);
    }



    public class Items{
        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    private void setItems(){
        Items items1 = new Items();
        items1.setTitle(getActivity().getResources().getString(R.string.title1));
        items1.setContent(getActivity().getResources().getString(R.string.content1));
        listItemses.add(items1);

        Items items2 = new Items();
        items2.setTitle(getActivity().getResources().getString(R.string.title2));
        items2.setContent(getActivity().getResources().getString(R.string.content2));
        listItemses.add(items2);

        Items items3 = new Items();
        items3.setTitle(getActivity().getResources().getString(R.string.title3));
        items3.setContent(getActivity().getResources().getString(R.string.content3));
        listItemses.add(items3);

        Items items4 = new Items();
        items4.setTitle(getActivity().getResources().getString(R.string.title4));
        items4.setContent(getActivity().getResources().getString(R.string.content4));
        listItemses.add(items4);

        Items items5 = new Items();
        items5.setTitle(getActivity().getResources().getString(R.string.title5));
        items5.setContent(getActivity().getResources().getString(R.string.content5));
        listItemses.add(items5);
    }



}
