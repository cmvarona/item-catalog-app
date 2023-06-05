package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Movie implements CatalogItem {

    private String id;
    private String name;
    private String director;
    private LocalDate releaseDate;

    public Movie(String name, String director, LocalDate releaseDate) {
        this.name = name;
        this.director = director;
        this.releaseDate = releaseDate;
    }
    public String getName() {
        return name;
    }
    public String getDirector() {
        return director;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public String getId() {
        return id;
    }

    public boolean matchesName(String searchStr) {
        String lowerCaseSearch = searchStr.toLowerCase();
        if (name.toLowerCase().contains(lowerCaseSearch)) {
            return true;
        }
        return false;
    }
    public boolean matchesCreator(String searchStr) {
        String lowerCaseSearch = searchStr.toLowerCase();
        if (director.toLowerCase().contains(lowerCaseSearch)) {
            return true;
        }
        return false;
    }
    public boolean matchesYear(int searchYear) {
        int year = releaseDate.getYear();
        if (searchYear == year) {
            return true;
        }
        return false;
    }
    public void registerItem() {
        id = UUID.randomUUID().toString();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        String contents = "Date & Time: " + format.format(currentTime) + "/n" + this;
        try {
            FileStorageService.writeContentsToFile(contents, "src/main/resources/logs/Movies", true);
        } catch (FileStorageException e) {
            e.printStackTrace();
        }
    }
    public String toString() {
        return "MOVIE \nMovie Title: " + name + "\n" + "Director: " + director + "\n" + "Release Date: " + "\n" + "ID: " + id + "\n";
    }
}
