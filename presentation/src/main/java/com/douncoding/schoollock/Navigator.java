package com.douncoding.schoollock;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

import com.douncoding.schoollock.ui.bluetooth.BluetoothActivity;
import com.douncoding.schoollock.ui.home.HomeActivity;
import com.douncoding.schoollock.ui.notice.NoticeActivity;
import com.douncoding.schoollock.ui.question.QuestionVoteActivity;
import com.douncoding.schoollock.ui.record.RecordActivity;
import com.douncoding.schoollock.ui.rollbook.RollbookActivity;

import javax.inject.Inject;

public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToHome(Context context) {
        if (context != null) {
            context.startActivity(HomeActivity.getCallingIntent(context));
        }
    }

    public void navigateToRollbook(Context context) {
        if (context != null) {
            context.startActivity(RollbookActivity.getCallingIntent(context));
        }
    }

    public void navigateToNotice(Context context) {
        if (context != null) {
            context.startActivity(NoticeActivity.getCallingIntent(context));
        }
    }

    public void navigateToBluetooth(Context context) {
        if (context != null) {
            context.startActivity(BluetoothActivity.getCallingIntent(context));
        }
    }

    public void navigateToRecord(Context context) {
        if (context != null) {
            context.startActivity(RecordActivity.getCallingIntent(context));
        }
    }

    public void navigateToQuestionVote(Context context) {
        if (context != null) {
            context.startActivity(QuestionVoteActivity.getCallingIntent(context));
        }
    }

    public void navigateToVoiceRecord(Context context) {
        if (context != null) {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            context.startActivity(intent);
        }
    }

    public void navigateToCamera(Context context) {
        if (context != null) {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
            intent.putExtra("android.intent.extra.sizeLimit", 972800L);
            context.startActivity(intent);
        }
    }
}
