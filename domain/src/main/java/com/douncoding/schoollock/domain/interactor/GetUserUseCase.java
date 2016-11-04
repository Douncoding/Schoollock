package com.douncoding.schoollock.domain.interactor;


import com.douncoding.schoollock.domain.PostExecutionThread;
import com.douncoding.schoollock.domain.ThreadExecutor;

import javax.inject.Inject;

import rx.Observable;

public class GetUserUseCase extends UseCase {

    @Inject
    public GetUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }
}
