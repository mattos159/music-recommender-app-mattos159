package com.example.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SongAdapter myAdapter;
    private List<Song> songList = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Build recycler view
        //set up recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Used this to put a return button on my action bar. I set the MainActivity as the parent class to return to.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //populate the recycler view
        prepareData();

        // on click listener for when user presses a song card -> sending them to detail activity
        // for more detailed information regarding that song
        myAdapter.setOnClickListener(new SongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // launching detail activity through intent
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("Song Id", songList.get(position).getSongId());
                startActivity(intent);
            }
        });
    }

    public void prepareData() {
        // preparing and populating data for recyclerview
        for (Song songs : Song.getSongs()) {
            Song songsList = new Song(songs.getSongId(), songs.getSongName(), songs.getArtist(), songs.getGenre(), songs.getRating(), songs.getImage(), songs.getSpotifyLink(), songs.getDescription(), songs.getSongReview(), songs.getGeniusLink());
            songList.add(songsList);
            System.out.println("TESTED Before change");
            //myAdapter.notifyDataSetChanged(); //refreshes the data, let's the adapter know of change.
            System.out.println("TESTED after change");
        }
        myAdapter = new SongAdapter(songList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged(); // refreshing data. Letting adapter know of change
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.song_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);


        SearchView searchV = (SearchView) searchItem.getActionView();
        View actionView = searchItem.getActionView();
        searchV.setMaxWidth(Integer.MAX_VALUE);
        searchV.setQueryHint("Search for song");

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionsItemSelected(searchItem);
            }
        });


        //text listener to respond to search filter
        searchV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // calling filter method from adapter class
                myAdapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ratingLowHigh:
                // sort arraylist by calling comparator method
                Collections.sort(songList, Song.ratingAscending);
                // toast message to be displayed after filter selected to provide user feedback
                Toast.makeText(this, "Rating sorted (lowest to highest)", Toast.LENGTH_SHORT).show();
                //update adapter
                myAdapter.notifyDataSetChanged();
                return true;
            case R.id.ratingHighLow:
                //sort arraylist by calling comparator method
                Collections.sort(songList, Song.ratingDescending);
                // toast message to be displayed after filter selected to provide user feedback
                Toast.makeText(this, "Rating sorted (highest to lowest)", Toast.LENGTH_SHORT).show();
                //update adapter
                myAdapter.notifyDataSetChanged();
                return true;
            case android.R.id.home:
                // allow for back press
                onBackPressed();
                return true;
        }

        return true;
    }
}