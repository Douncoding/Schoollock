package com.douncoding.schoollock;

import com.douncoding.schoollock.model.Author;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by douncoding on 2016. 11. 10..
 */

public class FirebaseUtils {
    private static final String STUDENT = "student";
    private static final String TEACHER = "teacher";
    private static final String LECTURE = "lecture";

    public static DatabaseReference getBaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public static String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        }
        return null;
    }

    public static Author getAuthor() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) return null;

        String name = user.getDisplayName();
        String picture = user.getPhotoUrl() != null ? user.getPhotoUrl().toString():null;

        for (UserInfo userInfo : user.getProviderData()) {
            if (name == null && userInfo.getDisplayName() != null) {
                name = userInfo.getDisplayName();
            }

            if (picture == null && userInfo.getPhotoUrl() != null) {
                picture = userInfo.getPhotoUrl().toString();
            }
        }
        return new Author(name, picture, user.getUid());
    }

    public static DatabaseReference getCurrentStudentRef() {
        String uid = getCurrentUserId();
        if (uid != null) {
            return getBaseRef().child(STUDENT).child(getCurrentUserId());
        }
        return null;
    }

    public static DatabaseReference getStudentRef() {
        return getBaseRef().child(STUDENT);
    }

    public static DatabaseReference getTeacherRef() {
        return getBaseRef().child(TEACHER);
    }

    public static DatabaseReference getLectureRef() {
        return getBaseRef().child(LECTURE);
    }
}
