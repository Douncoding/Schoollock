package com.douncoding.schoollock.domain.interactor;

import com.douncoding.schoollock.domain.PostExecutionThread;
import com.douncoding.schoollock.domain.ThreadExecutor;
import com.sun.istack.internal.NotNull;
import com.sun.media.sound.InvalidDataException;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * 사용자 로그인
 */
public class LoginUseCase extends UseCase {

    private String username;
    private String password;

    @Inject
    public LoginUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    public LoginUseCase init(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;

        return this;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        // Observable.concat(validate(), this.userRepository.user(username, password);
        return validate();
    }

    private Observable validate() {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                if (LoginUseCase.this.username.equals("") || LoginUseCase.this.password.equals("")) {
                    subscriber.onError(new InvalidDataException());
                } else {
                    subscriber.onCompleted();
                }
            }
        });
    }
}
