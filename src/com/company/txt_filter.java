package com.company;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class txt_filter extends FileFilter {
    private String txt = "txt";
    private char dot = '.';

    public txt_filter() {
    }

    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        } else {
            return this.extension(file).equalsIgnoreCase(this.txt);
        }
    }

    public String getDescription() {
        return ".txt format";
    }

    public String extension(File file) {
        String filename = file.getName();
        int indexFile = filename.lastIndexOf(this.dot);
        return indexFile > 0 && indexFile < filename.length() - 1 ? filename.substring(indexFile + 1) : "";
    }
}