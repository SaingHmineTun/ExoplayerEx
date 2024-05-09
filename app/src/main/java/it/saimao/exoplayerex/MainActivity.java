package it.saimao.exoplayerex;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.RawResourceDataSource;
import androidx.media3.exoplayer.ExoPlayer;

import it.saimao.exoplayerex.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        player = new ExoPlayer.Builder(this).build();
        initPlayer();
    }

    @OptIn(markerClass = UnstableApi.class) private void initPlayer() {
        binding.playerView.setPlayer(player);
        var onlineVideo = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4";
        // DEPRECATED : var localVideo = RawResourceDataSource.buildRawResourceUri(R.raw.java_basic_1);
        var localVideo = new Uri.Builder().scheme(ContentResolver.SCHEME_ANDROID_RESOURCE).path(Integer.toString(R.raw.java_basic_1)).build();
        MediaItem mediaItem = MediaItem.fromUri(onlineVideo);
        MediaItem mediaItem2 = MediaItem.fromUri(localVideo);
        // Set the media item to be played.
        player.addMediaItem(mediaItem);
        player.addMediaItem(mediaItem2);
        // Prepare the player.
        player.prepare();
        // Start the playback.
        player.play();
    }
}