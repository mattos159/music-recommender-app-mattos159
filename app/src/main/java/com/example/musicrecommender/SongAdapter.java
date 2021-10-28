package com.example.musicrecommender;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> implements Filterable {

    // https://www.youtube.com/watch?v=bhhs4bwYyhc - tutorial

    //set up the constructor for the adapter class
    private List<Song> songList;

    //creating copy of list for filter
    private List<Song> songListFilter;

    public SongAdapter(List<Song> songs) {
        this.songList = songs;
        // assign filter list
        songListFilter = new ArrayList<>(songList);
    }

    private OnItemClickListener myListener;

    //interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        myListener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //all the attributes I want to display on the recycler list item.
        private TextView name;
        private RatingBar rating;
        private TextView genre;
        private TextView artist;
        private ImageView image;


        public MyViewHolder(View view, OnItemClickListener listener) {
            super(view);
            name = view.findViewById(R.id.nameTxt) ;
            rating = view.findViewById(R.id.ratingBar);
            genre = view.findViewById(R.id.genreTxt);
            artist = view.findViewById(R.id.artistTxt);
            image = view.findViewById(R.id.albumCoverImageView);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }

                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView, myListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Song songs = songList.get(position); //gets the position and returns it
        holder.name.setText(songs.getSongName());
        holder.genre.setText(songs.getGenre());
        holder.artist.setText(songs.getArtist());
        holder.rating.setRating((float) songs.getRating());
        holder.image.setImageResource(songs.getImage()); //getImage

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    @Override
    public Filter getFilter() {
        return songFilter;
    }

    private final Filter songFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Song> filteredList = new ArrayList<>();

            // when no constraint given, filtered list will not remove any items
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(songListFilter);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                // search through all songs to identify if contains constraint and add to filtered list
                for (Song song : songListFilter) {
                    if (song.getSongName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(song);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            // clear songs
            songList.clear();

            // add all items in results
            songList.addAll((ArrayList)filterResults.values);
            notifyDataSetChanged();
        }
    };
}
