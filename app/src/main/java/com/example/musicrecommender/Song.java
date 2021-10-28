package com.example.musicrecommender;

import java.util.ArrayList;
import java.util.Comparator;

public class Song {
    private int songId;
    private String songName;
    private String artist;
    private String genre;
    private double rating;
    private int image;
    private String spotifyLink;
    private String description;
    private String songReview;
    private String geniusLink;

    public Song(int songId, String songName, String artist, String genre, double rating, int image, String spotifyLink, String description, String songReview, String geniusLink) {
        this.songId = songId;
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.rating = rating;
        this.image = image;
        this.spotifyLink = spotifyLink;
        this.description = description;
        this.songReview = songReview;
        this.geniusLink = geniusLink;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSpotifyLink() {
        return spotifyLink;
    }

    public void setSpotifyLink(String spotifyLink) {
        this.spotifyLink = spotifyLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
    public String getGeniusLink() {
        return geniusLink;
    }

    public void setGeniusLink(String geniusLink) {
        this.geniusLink = geniusLink;
    }
    public String getSongReview() {

        return songReview;
    }

    public void setSongReview(String songReview) {

        this.songReview = songReview;
    }

    // comparator to allow for sorting
    //comparator for ascending
    public static Comparator<Song> ratingAscending = new Comparator<Song>() {
        @Override
        public int compare(Song song, Song t1) {
            double delta = song.getRating() - t1.getRating();
            if (delta > 0.00001) return 1;
            if (delta < -0.00001) return -1;
            return 0;
        }
    };


    // comparator for descending
    public static Comparator<Song> ratingDescending = new Comparator<Song>() {
        @Override
        public int compare(Song song, Song t1) {
            double delta = t1.getRating() - song.getRating();
            if (delta > 0.0001) return 1;
            if (delta < -0.00001) return -1;
            return 0;
        }
    };



    //array list to return songs to be accessed from other activities
    public static ArrayList<Song> getSongs() {

        ArrayList<Song> songList = new ArrayList<>();

        songList.add(new Song(0,"Cartier", "Bazzi", "Pop", 4.5,
                R.drawable.bazzi_cosmic,  "https://www.youtube.com/watch?v=_bpXfwPWGrU",
                "Song by youtuber-turned-singer, Bazzi, about the passion between two people", "Great song with great melody",
                "https://genius.com/Bazzi-cartier-lyrics"));

        songList.add(new Song(1, "Someone You Loved", "Lewis Capaldi",
                "Pop", 5.0, R.drawable.someoneyouloved,
                "https://www.youtube.com/watch?v=zABLecsR5UE",
                "Someone You Loved is a song recorded by Scottish singer " +
                        "Lewis Capaldi. This is his first hit single",
                "Lewis shows his incredible vocal range in the song and embodies his " +
                        "emotions with every second",
                "https://genius.com/Lewis-capaldi-someone-you-loved-lyrics"));
        songList.add(new Song(2, "Ghost", "Justin Beiber", "Pop",
                4.0, R.drawable.ghost_jb, "https://www.youtube.com/watch?v=Fp8msa5uYsc",
                "Ghost is a song released by Justin Bieber which is currently in the " +
                        "Billboard top 100 chart", "Catchy song that will be a great " +
                "club hit", "https://genius.com/Justin-bieber-ghost-lyrics" ));
        songList.add(new Song(3, "Girls Want Girls", "Drake",
                "Hip Hop", 4.0, R.drawable.girlswantgirls,
                "https://www.youtube.com/watch?v=b8M6N0FTpNc", "Girls Want " +
                "Girls is the third track on Drake's  album, Certified Lover Boy, " +
                "released on September 3, 2021", "Hard hitting beats smoothed out by a " +
                "catchy flow and tune make this song perfect to dance or sing to",
                "https://genius.com/Drake-girls-want-girls-lyrics"));

        songList.add(new Song(4, "Fair Trade", "Drake", "Hip Hop",
                3.5, R.drawable.fairtrade, "https://www.youtube.com/watch?v=THVbtGqEO1o",
                "Fair Trade is a song by " +
                "rapper Drake featuring fellow rapper Travis Scott. It was released in " +
                "2021 as the sixth track on Drake's sixth album, Certified " +
                "Lover Boy", "Future shines on this track and provides a " +
                "pleasing change up in flow from Drake",
                "https://genius.com/Drake-fair-trade-lyrics"));

        songList.add(new Song(5, "Hurricane", "Kanye West", "Hip Hop",
                4.5, R.drawable.hurricane, "https://www.youtube.com/watch?v=bPjZmQAvk_8",
                "Hurricane is the fifth song in Kanye's new album - Donda",
                "The chorus lifts the song to another level",
                "https://genius.com/Kanye-west-and-the-weeknd-hurricane-lyrics"));

        songList.add(new Song(6, "Closer", "The Chainsmokers",
                "EDM", 3.0, R.drawable.closer,
                "https://www.youtube.com/watch?v=PT2_F-1esPk",
                "Closer is a song by American DJ duo the Chainsmokers featuring " +
                        "American singer Halsey", "Catchy beat and nice vocal blend " +
                "make this perfect song to listen in summer",
                "https://genius.com/The-chainsmokers-closer-lyrics"));

        songList.add(new Song(7, "Better", "Khalid", "R&B", 5.0,
                R.drawable.better, "https://www.youtube.com/watch?v=x3bfa3DZ8JM",
                "Better is a song by American singer Khalid for his first EP Suncity (2018) " +
                        "and was the lead single",
                "Khalid compels with his vocals. His tone remains beautiful and " +
                        "distinct", "https://genius.com/Khalid-better-lyrics"));

        songList.add(new Song(8, "Slow Dancing in the Dark", "Joji",
                "R&B", 4.5, R.drawable.slowdancinginthedark, "https://www.youtube.com/watch?v=K3" +
                "Qzzggn--s", "Slow Dancing in the Dark is Joji's second single",
                "Slow Dancing in the Dark is a powerful ballad with a melancholic " +
                        "tone that fully displays Joji's raw talent and potential",
                "https://genius.com/Joji-slow-dancing-in-the-dark-lyrics"));
        songList.add(new Song(9, "XO", "EDEN", "Indie Pop", 4.5,
                R.drawable.xo, "https://www.youtube.com/watch?v=tWHBaJHo5og",
                "XO is Eden's sixth track on his 2016 album I think you think too " +
                        "much of me", "A unique and mellow feel-good tune",
                "https://genius.com/Eden-xo-lyrics"));

        return songList;
    }
}

