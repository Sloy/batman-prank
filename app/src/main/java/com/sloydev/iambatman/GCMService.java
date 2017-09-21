package com.sloydev.iambatman;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.TaskParams;
import java.util.concurrent.TimeUnit;

public class GCMService extends GcmTaskService {

  @Override
  public void onInitializeTasks() {
    super.onInitializeTasks();
    Log.d("Batman", "onInitializeTasks");
    PeriodicTask syncTask = new PeriodicTask.Builder()
        .setTag("batman")
        .setService(GCMService.class)
        .setPeriod(TimeUnit.HOURS.toSeconds(2))
        .setFlex(TimeUnit.HOURS.toSeconds(1))
        .setPersisted(true)
        .setUpdateCurrent(true)
        .build();

    GcmNetworkManager.getInstance(getApplicationContext()).schedule(syncTask);

    //say();
  }

  @Override
  public int onRunTask(TaskParams taskParams) {
    say();
    return GcmNetworkManager.RESULT_SUCCESS;
  }

  private void say() {
    AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    for (int i = 0; i < 5; i++) {
      audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
          AudioManager.ADJUST_RAISE, 0);
    }

    MediaPlayer player = MediaPlayer.create(this, R.raw.batman);
    player.setVolume(100, 100);
    player.start();
  }
}
