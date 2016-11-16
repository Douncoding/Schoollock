package com.douncoding.schoollock.ui.rollbook;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.model.AAModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

public class RollbookAdapter extends RecyclerView.Adapter<RollbookAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onEnrollClicked(View view, AAModel item);
    }

    private Context context;
    private List<AAModel> dataSet;

    @Inject
    public RollbookAdapter(Context context) {
        this.context = context;
        this.dataSet = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_rollbook, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AAModel item = dataSet.get(position);

        if (position % 2 == 0) {
            holder.mContainer.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.colorGrayTransparent));
        } else {
            holder.mContainer.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.colorWhiteTransparent));
        }

        holder.mDateText.setText(item.getDate().toString());
        holder.mStateText.setText(item.getState());
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }

    public void addDataSet(Collection<AAModel> items) {
        int start = this.dataSet.size();
        int end = start + items.size();

        dataSet.addAll(items);
        this.notifyItemRangeChanged(start, end);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_container) ViewGroup mContainer;
        @BindView(R.id.date_txt) TextView mDateText;
        @BindView(R.id.state_txt) TextView mStateText;
        @BindView(R.id.enroll_btn) FancyButton mEnrollButton;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
