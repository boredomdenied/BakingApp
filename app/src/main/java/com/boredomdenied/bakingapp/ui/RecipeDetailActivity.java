package com.boredomdenied.bakingapp.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.model.Recipe;
import com.boredomdenied.bakingapp.model.Step;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

import java.util.List;


public class RecipeDetailActivity extends AppCompatActivity {
    private TextView step;

    private static final String KEY_PLAY_WHEN_READY = "play_when_ready";
    private static final String KEY_WINDOW = "window";
    private static final String KEY_POSITION = "position";

    private AppBarLayout appBarLayout;
    private PlayerView playerView;
    private SimpleExoPlayer player;

    private Timeline.Window window;
    private DataSource.Factory mediaDataSourceFactory;
    private DefaultTrackSelector trackSelector;
    private boolean shouldAutoPlay;
    private BandwidthMeter bandwidthMeter;

    private ProgressBar progressBar;
    private boolean playWhenReady;
    private int currentWindow;
    private long playbackPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final List<Step> stepList = getIntent().getParcelableArrayListExtra("recipeSteps");
        final int index = getIntent().getIntExtra("stepIndex", 0);

            Log.d("exoPlayer: ", String.valueOf(stepList.get(index).getVideoURL()));


        if (savedInstanceState == null) {
            playWhenReady = true;
            currentWindow = 0;
            playbackPosition = 0;
        } else {
            playWhenReady = savedInstanceState.getBoolean(KEY_PLAY_WHEN_READY);
            currentWindow = savedInstanceState.getInt(KEY_WINDOW);
            playbackPosition = savedInstanceState.getLong(KEY_POSITION);
        }

        shouldAutoPlay = true;
        bandwidthMeter = new DefaultBandwidthMeter();
        mediaDataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, null), (TransferListener<? super DataSource>) bandwidthMeter);
        window = new Timeline.Window();
        progressBar = findViewById(R.id.progress_bar);


        step = findViewById(R.id.step);
        step.setText(stepList.get(index).getDescription());


    }

    private void initializePlayer() {

        final List<Step> stepList = getIntent().getParcelableArrayListExtra("recipeSteps");
        final int index = getIntent().getIntExtra("stepIndex", 0);

        playerView = findViewById(R.id.player_view);
        playerView.requestFocus();

        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);

        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        playerView.setPlayer(player);

//        if (this.getResources().getConfiguration().orientation ==
//                Configuration.ORIENTATION_LANDSCAPE) {
//            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
//                    playerView.getLayoutParams();
//            params.width = params.MATCH_PARENT;
//            params.height = params.MATCH_PARENT;
//            playerView.setLayoutParams(params);
//        }

        player.addListener(new PlayerEventListener());
        player.setPlayWhenReady(shouldAutoPlay);



            MediaSource mediaSource = new ExtractorMediaSource.Factory(mediaDataSourceFactory)
                    .createMediaSource(Uri.parse(stepList.get(index).getVideoURL()));


            boolean haveStartPosition = currentWindow != C.INDEX_UNSET;
            if (haveStartPosition) {
                player.seekTo(currentWindow, playbackPosition);
            }

            player.prepare(mediaSource, !haveStartPosition, false);
        }




        private void releasePlayer () {
        if (player != null) {
            updateStartPosition();
            shouldAutoPlay = player.getPlayWhenReady();
            player.release();
            player = null;
            trackSelector = null;
        }
    }


        @Override
        public void onStart () {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

        @Override
        public void onResume () {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

        @Override
        public void onPause () {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

        @Override
        public void onStop () {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

        @Override
        protected void onSaveInstanceState (Bundle outState){
        updateStartPosition();

        outState.putBoolean(KEY_PLAY_WHEN_READY, playWhenReady);
        outState.putInt(KEY_WINDOW, currentWindow);
        outState.putLong(KEY_POSITION, playbackPosition);
        super.onSaveInstanceState(outState);
    }

        private void updateStartPosition () {
        playbackPosition = player.getCurrentPosition();
        currentWindow = player.getCurrentWindowIndex();
        playWhenReady = player.getPlayWhenReady();
    }



        private class PlayerEventListener extends Player.DefaultEventListener {

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                switch (playbackState) {
                    case Player.STATE_IDLE:       // The player does not have any media to play yet.
                        progressBar.setVisibility(View.GONE);
                        break;
                    case Player.STATE_BUFFERING:  // The player is buffering (loading the content)
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case Player.STATE_READY:      // The player is able to immediately play
                        progressBar.setVisibility(View.GONE);
                        break;
                    case Player.STATE_ENDED:      // The player has finished playing the media
                        progressBar.setVisibility(View.GONE);
                        break;
                }
            }
        }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        // Checking the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            //First Hide other objects (listview or recyclerview), better hide them using Gone.
//            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)appBarLayout.getLayoutParams();
//
//            lp.height = 0;
//
//            appBarLayout.setLayoutParams(lp);
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            navigateUpTo(new Intent(this, RecipeListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
