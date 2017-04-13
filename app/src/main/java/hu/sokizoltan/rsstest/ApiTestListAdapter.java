package hu.sokizoltan.rsstest;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.sokizoltan.rsstest.apitest.ApiTestResponse;

class ApiTestListAdapter extends RecyclerView.Adapter {

    private List<ApiTestResponse> dataSet;
    private SparseBooleanArray selectedItems;

    public ApiTestListAdapter() {
        this.dataSet = new ArrayList<>();
        selectedItems = new SparseBooleanArray();
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

        String formattedDate = new SimpleDateFormat("yyyy / MM / dd", Locale.getDefault()).format(new Date(data.getReleaseDate()));

        myHolder.codename.setText(data.getCodeName());
        myHolder.apiLevel.setText(String.valueOf(data.getApiLevel()));
        myHolder.version.setText(data.getVersionNumber());
        myHolder.date.setText(formattedDate);

        myHolder.itemView.setSelected(selectedItems.get(position));

        myHolder.itemView.setOnClickListener(v -> {
            if (selectedItems.get(position, false)) {
                selectedItems.delete(position);
                v.setSelected(false);
            } else {
                selectedItems.put(position, true);
                v.setSelected(true);
            }
        });
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

        @BindView(R.id.list_item_codename)
        TextView codename;

        @BindView(R.id.list_item_apilevel)
        TextView apiLevel;

        @BindView(R.id.list_item_date)
        TextView date;

        @BindView(R.id.list_item_version)
        TextView version;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
