package com.douncoding.schoollock.ui.intro;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.douncoding.schoollock.FirebaseUtils;
import com.douncoding.schoollock.R;
import com.douncoding.schoollock.internal.di.PerActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;

@Module
public class IntroModule {
    private FragmentActivity context;

    public IntroModule(FragmentActivity context) {
        this.context = context;
    }

    @PerActivity
    @Provides
    GoogleApiClient provideGoogleApiClient() {
        return new GoogleApiClient.Builder(context)
                .enableAutoManage(context, null)
                .addApi(Auth.GOOGLE_SIGN_IN_API,
                        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestEmail()
                                .requestIdToken(context.getString(R.string.default_web_client_id))
                                .build())
                .build();
    }

    @PerActivity
    @Provides
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }
}
