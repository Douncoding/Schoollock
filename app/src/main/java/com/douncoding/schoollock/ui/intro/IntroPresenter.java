package com.douncoding.schoollock.ui.intro;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.douncoding.schoollock.Constants;
import com.douncoding.schoollock.FirebaseUtils;
import com.douncoding.schoollock.Navigator;
import com.douncoding.schoollock.internal.di.PerActivity;
import com.douncoding.schoollock.model.Student;
import com.douncoding.schoollock.ui.BaseContractView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

@PerActivity
public class IntroPresenter implements IntroContract.Presenter {
    private IntroContract.View mView;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    @Inject Navigator navigator;

    @Inject
    public IntroPresenter(FirebaseAuth auth, GoogleApiClient googleApiClient) {
        this.mAuth = auth;
        this.mGoogleApiClient = googleApiClient;
    }

    @Override
    public void attachView(BaseContractView view) {
        this.mView = (IntroContract.View)view;
    }

    @Override
    public void resume() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Student student = new Student("test", "test@test.com", null);
        mView.showSignedInUI(student);
//        if (currentUser != null && !currentUser.isAnonymous()) {
//            FirebaseUtils.getStudentRef().child(currentUser.getUid())
//            .addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Student student = dataSnapshot.getValue(Student.class);
//                    if (student != null)
//                        mView.showSignedInUI(student);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                }
//            });
//        } else {
//            mView.showSignInFormUI();
//        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onSignIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        if (mView.context() instanceof FragmentActivity) {
            ((FragmentActivity)mView.context()).startActivityForResult(
                    intent, Constants.RC_GOOGLE_SIGN_IN);
        }
    }

    @Override
    public void onSignOut() {
//        mAuth.signOut();
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
//        mView.showSignInFormUI();
    }

    @Override
    public void onGoogleSignInActivityResult(Intent intent) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
        if (result.isSuccess() && result.getSignInAccount() != null) {
            GoogleSignInAccount acct = result.getSignInAccount();
            AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

            mView.showProgressDialog("회원가입 처리 중...");

            mAuth.signInWithCredential(credential)
                    .addOnSuccessListener(authResult -> handleFirebaseAuthResult(authResult))
                    .addOnFailureListener(e -> {
                        FirebaseCrash.logcat(Log.ERROR, "Auth:onFailure", e.getMessage());
                        Logger.e("Auth:onFailure:" + e.getMessage());
                        handleFirebaseAuthResult(null);
                    });
        } else {
            Logger.d("Google Sign-In failed.");
        }
    }

    /**
     * .Successful authenticate with Firebase, store student data, and show signed-in UI
     * .Unsuccessful authenticate with Firebase, show signed-Out UI and error message.
     */
    private void handleFirebaseAuthResult(AuthResult result) {
        mView.dismissProgressDialog();

        if (result != null) {
            FirebaseUser firebaseUser = result.getUser();
            String[] params = new String[3];

            for (UserInfo userInfo : firebaseUser.getProviderData()) {
                if (userInfo.getDisplayName() != null) {
                    params[0] = userInfo.getDisplayName();
                }
                if (userInfo.getEmail() != null) {
                    params[1] = userInfo.getEmail();
                }
                if (userInfo.getPhotoUrl() != null) {
                    params[2] = userInfo.getPhotoUrl().toString();
                }
            }

            Student student = new Student(params[0], params[1], params[2]);
            FirebaseUtils.getStudentRef().child(firebaseUser.getUid()).updateChildren(
                    student.toMap(),
                    ((databaseError, databaseReference) -> {
                        if (databaseError != null) {
                            FirebaseCrash.logcat(Log.ERROR, "", databaseError.getMessage());
                            mView.showErrorMessage("회원가입 실패: 관리자에게 문의하세요.");
                        } else  {
                            mView.showSignedInUI(student);
                        }
                    }));
        } else {
            mView.showErrorMessage("회원가입 실패: 관리자에게 문의하세요.");
        }
    }
}
