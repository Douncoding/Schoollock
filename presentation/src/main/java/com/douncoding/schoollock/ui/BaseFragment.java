package com.douncoding.schoollock.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.douncoding.schoollock.internal.di.HasComponent;

/**
 * 프라그먼트는 데이터를 처리하는 역할을 수행하지 않으며, View 의 대한 로직만
 * 구현되야 한다.
 * onAttach() > onCreate() > onViewCreated() > onActivityCreated() > onStart() > onResume()
 */
public abstract class BaseFragment extends Fragment {
    public abstract int getContentViewId();
    /**
     * View 를 초기화하는 과정에서 Activity 의 Context 를 사용하는 로직을 구현하는 것에 주의해야 한다.
     * {@link BaseFragment#initData()} 호출 지점에서 Context 의 의존적인 초기화로직을 수행하길 바란다.
     */
    protected abstract void initContentView(View view);
    protected abstract void initData();
    protected abstract void initArguments(Bundle bundle);

//    protected T onListener;
    protected interface OnBaseListener {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnBaseListener) {
//            this.onListener = (T)context;
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initArguments(getArguments());
        return inflater.inflate(getContentViewId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initContentView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initData();
    }

    /**
     * inject() 는 공통으로 수행할 수 없다는 Dagger2 의 조건에 따라 각 각의 프라그먼트에서 직접 수행할 수 있도록 한다.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>)getActivity()).getComponent());
    }
}
