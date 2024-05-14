package com.example.StudyWithMe.utils;

import com.example.StudyWithMe.models.FileType;

import javax.annotation.Nullable;

public class Utils {

    public static FileType getFileType(@Nullable String contentType) {
        if (contentType == null) return FileType.UNKNOWN;

        if (contentType.startsWith("image/")) {
            return FileType.IMAGE;
        } else if (contentType.startsWith("video/")) {
            return FileType.VIDEO;
        } else {
            return FileType.UNKNOWN;
        }
    }
}
