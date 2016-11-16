package com.douncoding.schoollock.ui.question;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionVoteMenuFragment extends BaseFragment {
    public static QuestionVoteMenuFragment newInstance() {
        Bundle args = new Bundle();
        QuestionVoteMenuFragment fragment = new QuestionVoteMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private OnFragmentListener onFragmentListener;
    public interface OnFragmentListener {
        void onVoteYesClicked();
        void onVoteNoClicked();
        void onDoQuestionClicked();
        void onQuestionListClicked();
    }

    @BindView(R.id.vote_title_txt) TextView titleText;
    @BindView(R.id.vote_content_txt) TextView contentText;

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
        return R.layout.fragment_question_vote_menu;
    }

    @Override
    protected void initContentView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    @OnClick(R.id.vote_yes_panel) public void onVoteYesClick(ViewGroup viewGroup) {
        onFragmentListener.onVoteYesClicked();
    }

    @OnClick(R.id.vote_no_panel) public void onVoteNoClick(ViewGroup viewGroup) {
        onFragmentListener.onVoteNoClicked();
    }

    @OnClick(R.id.do_question_panel) public void onDoQuestionClick(ViewGroup viewGroup) {
        onFragmentListener.onDoQuestionClicked();
    }

    @OnClick(R.id.question_list_panel) public void onClick(ViewGroup viewGroup) {
        onFragmentListener.onQuestionListClicked();
    }
}
