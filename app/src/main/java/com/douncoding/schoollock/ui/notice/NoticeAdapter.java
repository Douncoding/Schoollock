package com.douncoding.schoollock.ui.notice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.model.NoticeModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClicked(View view, NoticeModel item);
    }

    private Context context;
    private List<NoticeModel> dataSet;

    @Inject
    public NoticeAdapter(Context context) {
        this.context = context;
        this.dataSet = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_notice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoticeModel item = dataSet.get(position);

        holder.date.setText(item.getDate());
        holder.title.setText(item.getTitle());
        holder.content.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }

    public void addAll(Collection<NoticeModel> items) {
        int start = this.dataSet.size();
        int end = start + items.size();

        dataSet.addAll(items);
        this.notifyItemRangeChanged(start, end);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.date) TextView date;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.content) TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClicked(v, dataSet.get(getAdapterPosition()));
            }
        }
    }
}
