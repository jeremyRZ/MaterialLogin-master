package cn.fanrunqi.materiallogin;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewFragment extends Fragment {
    public static Fragment newInstance(){return  new RecyclerViewFragment();}
    final List<Object> items = new ArrayList<>();
    static final int ITEMS = 5;
    RecyclerView mRecyclerView;
    private List<String> mData;
    RecyclerViewPagerAdapter recycleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView=view.findViewById(R.id.recyclerView);
        initData();
        recycleAdapter = new RecyclerViewPagerAdapter(Collections.singletonList(mData));
        System.out.println(mData);
        mRecyclerView.setAdapter(recycleAdapter);
        for (int i=0;i<ITEMS;i++){
            items.add(new Object());
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new RecyclerViewPagerAdapter(items));
    }
    private void initData() {
        mData = new ArrayList<>();
        mData.add("HODV-21194"); //0
        mData.add("TEK-080"); //1
        mData.add("IPZ-777"); //2
        mData.add("MIMK-045"); //3
        mData.add("HODV-21193"); //4
        mData.add("MIDE-339"); //5
        mData.add("IPZ-780"); //6
        mData.add("VEC-205"); //7
        mData.add("VEMA-113"); //8
        mData.add("IPZ-776"); //9
        mData.add("MIAD-923"); //10
        mData.add("ARM-513"); //11
    }

}