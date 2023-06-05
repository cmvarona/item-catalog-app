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

public class Tool implements CatalogItem {

    private String id;
    private String type;
    private String manufacturer;
    private int count;

    public Tool(String type, String manufacturer, int count) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.count = count;
    }
    public String getType() {
        return type;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public String getId() {
        return id;
    }
    public int getCount() {
        return count;
    }

    public boolean matchesName(String searchStr) {
        String lowerCaseSearch = searchStr.toLowerCase();
        if (type.toLowerCase().contains(lowerCaseSearch)) {
            return true;
        }
        return false;
    }
    public boolean matchesCreator(String searchStr) {
        String lowerCaseSearch = searchStr.toLowerCase();

        if (manufacturer.toLowerCase().contains(lowerCaseSearch)) {
            return true;
        }
        return false;
    }
    public boolean matchesYear(int searchYear) {
        return false;
    }

    public void registerItem() {
        id = UUID.randomUUID().toString();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        String contents = "Date & Time: " + format.format(currentTime) + "/n" + this;
        try {
            FileStorageService.writeContentsToFile(contents, "src/main/resources/logs/Tools", true);
        } catch (FileStorageException e) {
            e.printStackTrace();
        }
    }
    public String toString() {
        return "TOOL \nType: " + type + "\n" + "Manufacturer: " + manufacturer + "\n" + "Count: " + count + "\n" + "ID: " + id +"\n";
    }
}
