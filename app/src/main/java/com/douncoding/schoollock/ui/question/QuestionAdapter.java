package com.douncoding.schoollock.ui.question;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.model.QuestionModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClicked(View view, QuestionModel item);
    }

    private Context context;
    private List<QuestionModel> dataSet;

    @Inject
    public QuestionAdapter(Context context) {
        this.context = context;
        this.dataSet = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QuestionModel item = dataSet.get(position);

        holder.contentText.setText(item.getContent());
        holder.writerText.setText(item.getWriter());
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }

    public void addAll(Collection<QuestionModel> items) {
        int start = this.dataSet.size();
        int end = start + items.size();

        dataSet.addAll(items);
        this.notifyItemRangeChanged(start, end);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.writer_txt) TextView writerText;
        @BindView(R.id.content_txt) TextView contentText;

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
