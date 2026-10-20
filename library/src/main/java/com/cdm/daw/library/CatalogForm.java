package com.cdm.daw.library;

import java.util.Date;

public class CatalogForm {
    private String title;
    private String author;
    private String date;
    private int copies;
    private String genres;

    //public form {String title, String author, String date, int copies, String genres}


    public CatalogForm(String title, String author, String date, int copies, String genres) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.copies = copies;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitulo(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres){
        this.genres = genres;
    }
}