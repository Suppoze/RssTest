package hu.sokizoltan.rsstest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.sokizoltan.rsstest.apitest.ApiTestResponse;

class ApiTestListAdapter extends RecyclerView.Adapter {

    private List<ApiTestResponse> dataSet;

    public ApiTestListAdapter() {
        this.dataSet = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_apitest, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder myHolder = (ViewHolder) holder;
        ApiTestResponse data = dataSet.get(position);

        Glide.with(myHolder.itemView.getContext())
                .load(data.getImageUrl())
                .into(myHolder.imageView);

        myHolder.textView1.setText(data.getCodeName());
        myHolder.textView2.setText(data.getVersionNumber());
        myHolder.textView3.setText(String.valueOf(data.getReleaseDate()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void insertData(ApiTestResponse data) {
        dataSet.add(data);
        notifyItemInserted(dataSet.size());
    }

    public void clearData() {
        int size = dataSet.size();
        dataSet.clear();
        notifyItemRangeRemoved(0, size);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_item_image)
        ImageView imageView;

        @BindView(R.id.list_item_textview_1)
        TextView textView1;

        @BindView(R.id.list_item_textview_2)
        TextView textView2;

        @BindView(R.id.list_item_textview_3)
        TextView textView3;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
