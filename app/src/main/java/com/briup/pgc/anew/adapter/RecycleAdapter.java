package com.briup.pgc.anew.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.briup.pgc.anew.R;
import com.briup.pgc.anew.bean.NewBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewBean.NewsBean> data;
    private Handler mHandler= new Handler();

    public RecycleAdapter(List<NewBean.NewsBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载不同的viewholder

            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
            ItemViewHolder holder = new ItemViewHolder(view);
            return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            final ItemViewHolder itemHolder= (ItemViewHolder) holder;
            NewBean.NewsBean newsBean = data.get(position);

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("http://10.0.3.2:8888/upload/" + newsBean.getNewicon()).build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                public void onFailure(Call call, IOException e) {

                }
                public void onResponse(Call call, Response response) throws IOException {
                    byte[] data=response.body().bytes();
                    final Bitmap bitmap= BitmapFactory.decodeByteArray(data, 0, data.length);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            itemHolder.icon.setImageBitmap(bitmap);
                        }
                    });
                }
            });

            itemHolder.title.setText(newsBean.getTitle());
            itemHolder.desc.setText(newsBean.getNewdesc());
            //如果设置回调
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = itemHolder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(itemHolder.itemView, position);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position1 = holder.getLayoutPosition();
                        mOnItemClickListener.onItemLongClick(holder.itemView, position1);
                        return false;
                    }
                });
            }
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView desc;
        public ItemViewHolder(View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.item_img);
            title=itemView.findViewById(R.id.item_title);
            desc=itemView.findViewById(R.id.item_desc);
        }
    }

    /**
     * 定义接口回调
     * */
    public interface onItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    private onItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(onItemClickListener listener){
        this.mOnItemClickListener=listener;
    }
    //下拉刷新增加数据
    public void addHeaderItem(List<NewBean.NewsBean> items){
        data.addAll(0,items);
        notifyDataSetChanged();
    }

}
