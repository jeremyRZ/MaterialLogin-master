package cn.fanrunqi.materiallogin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewPagerAdapter extends RecyclerView.Adapter<RecyclerViewPagerAdapter.MyViewHolder>{
    List<Object> contents;
    public RecyclerViewPagerAdapter(List<Object> contents) {
        this.contents = contents;
        System.out.println("@@@@@@@@@");
        System.out.println(contents);
    }
    public int getItemCount() {
        return contents.size();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_cardview, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        holder.tv.setText(contents.get(position));
//        holder.tv.setTag(contents.get(position));
    }

//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        super.onViewRecycled(holder);
//
//    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.recyclerView);
        }
    }
}
