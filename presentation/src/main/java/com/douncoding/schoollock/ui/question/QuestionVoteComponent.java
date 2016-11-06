package com.douncoding.schoollock.ui.question;

import com.douncoding.schoollock.internal.di.ActivityModule;
import com.douncoding.schoollock.internal.di.ApplicationComponent;
import com.douncoding.schoollock.internal.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, QuestionVoteModule.class})
public interface QuestionVoteComponent {
    void inject(QuestionVoteActivity questionVoteActivity);
    void inject(QuestionListFragment questionListFragment);
}
