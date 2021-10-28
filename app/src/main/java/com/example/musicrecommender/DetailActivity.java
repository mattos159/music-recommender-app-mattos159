package com.example.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //initialise widgets
        ImageView albumImage = findViewById(R.id.albumImage);
        TextView nameText = findViewById(R.id.nameText);
        TextView artistText = findViewById(R.id.artistText);
        TextView genreText = findViewById(R.id.genreText);
        TextView descriptionText = findViewById(R.id.descriptionText);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        // sourced from https://www.google.com/search?q=youtube+logo+png&rlz=1C5CHFA_enAU838AU838&sxsrf=AOaemvK5j0I3CHG4ryD4S_JTF-XVlyX7XQ:1635425067529&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiBkNORke3zAhWy9nMBHUjYBuIQ_AUoAXoECAEQAw&biw=1440&bih=709&dpr=2#imgrc=uwuCyP4IVRyT1M
        ImageView youtubeLogo = findViewById(R.id.youtubeLogo);
        // sourced from https://www.google.com/search?q=genius+logo+png&rlz=1C5CHFA_enAU838AU838&sxsrf=AOaemvJHwr5sX4_meSeMagb2RfMDNk-ZYg:1635425109454&source=lnms&tbm=isch&sa=X&sqi=2&ved=2ahUKEwjP9tGlke3zAhVzqZUCHXuwB_0Q_AUoAXoECAEQAw&biw=1440&bih=709&dpr=2#imgrc=STUKQy5b5EeIaM
        ImageView geniusLogo = findViewById(R.id.geniusLogo);
        TextView reviewText = findViewById(R.id.reviewText);

        //Used this to put a return button on my action bar. I set the ListActivity as the parent class to return to.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get intent from list_activity
        String songId = "SongId not received";
        // receiving intent from list activity
        int intSongId = getIntent().getExtras().getInt("Song Id");


            // setting widgets on create
            nameText.setText(Song.getSongs().get(intSongId).getSongName());
            artistText.setText(Song.getSongs().get(intSongId).getArtist());
            genreText.setText(Song.getSongs().get(intSongId).getGenre());
            descriptionText.setText(Song.getSongs().get(intSongId).getDescription());
            ratingBar.setRating((float) Song.getSongs().get(intSongId).getRating());
            reviewText.setText(Song.getSongs().get(intSongId).getSongReview());


            // youtube link
            String youtubeLink = Song.getSongs().get(intSongId).getSpotifyLink();
            // genius link
            String geniusLink = Song.getSongs().get(intSongId).getGeniusLink();

            albumImage.setImageResource(Song.getSongs().get(intSongId).getImage());

            //on click listener for button
        youtubeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl(youtubeLink);
            }

            private void goToUrl(String youtubeLink) {
                Uri link = Uri.parse(youtubeLink);
                startActivity(new Intent(Intent.ACTION_VIEW, link));
            }
        });

        // on click listener for geniusLogo - send user to lyrics
        geniusLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri geniusLinkWeb = Uri.parse(geniusLink);
                startActivity(new Intent(Intent.ACTION_VIEW, geniusLinkWeb));
            }
        });

    }
}