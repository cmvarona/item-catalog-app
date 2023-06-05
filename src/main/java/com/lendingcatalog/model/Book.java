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
import java.util.Locale;
import java.util.UUID;

public class Book implements CatalogItem {

    private String id;
    private String title;
    private String author;
    private LocalDate publishDate;

    public Book(String title, String author, LocalDate publishDate) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }
    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public String getId() {
        return id;
    }
    public LocalDate getPublishDate() {
        return publishDate;
    }

    public boolean matchesName(String searchStr) {
        String lowerCaseSearch = searchStr.toLowerCase();
        if (title.toLowerCase().contains(lowerCaseSearch)) {
            return true;
        }
        return false;
    }
    public boolean matchesCreator(String searchStr) {
        String lowerCaseSearch = searchStr.toLowerCase();
        if (author.toLowerCase().contains(lowerCaseSearch)) {
            return true;
        }
        return false;
    }
    public boolean matchesYear(int searchYear) {
        int year = publishDate.getYear();
        if (searchYear == year) {
            return true;
        }
        return false;
    }

    /**
     *
     */
    public void registerItem() {
        id = UUID.randomUUID().toString();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        String contents = "Date & Time: " + format.format(currentTime) + "/n" + this;
        try {
            FileStorageService.writeContentsToFile(contents, "src/main/resources/logs/Books", true);
        } catch (FileStorageException e) {
            e.printStackTrace();
        }
    }
    public String toString() {
        return "BOOK \nTitle: " + title + "\n" + "Author: " + author + "\n" + "Publish Date: " + publishDate + "\n" + "ID: " + id + "\n";
    }

}
