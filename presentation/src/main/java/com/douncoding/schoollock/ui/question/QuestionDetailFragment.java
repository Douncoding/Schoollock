package com.douncoding.schoollock.ui.question;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.model.QuestionModel;
import com.douncoding.schoollock.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionDetailFragment extends BaseFragment {
    public static final String TAG = QuestionDetailFragment.class.getSimpleName();
    public static QuestionDetailFragment newInstance() {
        Bundle args = new Bundle();
        QuestionDetailFragment fragment = new QuestionDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private OnFragmentListener onFragmentListener;
    public interface OnFragmentListener {
    }

    @BindView(R.id.title_txt) TextView titleText;
    @BindView(R.id.content_txt) TextView contentText;
    @BindView(R.id.writer_txt) TextView writerText;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            this.onFragmentListener = (OnFragmentListener)context;
        } else {
            throw new IllegalArgumentException("must be implements OnFragmentListener.");
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_question_detail;
    }

    @Override
    protected void initContentView(View view) {
        ButterKnife.bind(this, view);
        titleText.setText("제목이 표시됩니다.");
        contentText.setText("질문내용이 표시됩니다. 강사의 답변은 어떻게 확인이 되나요?");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }
}
