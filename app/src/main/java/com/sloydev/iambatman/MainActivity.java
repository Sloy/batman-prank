package com.sloydev.iambatman;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    for (int i = 0; i < 5; i++) {
      audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
          AudioManager.ADJUST_RAISE, 0);
    }

    MediaPlayer player = MediaPlayer.create(this, R.raw.batman);
    player.setVolume(100, 100);
    player.start();

    finish();
  }
}
