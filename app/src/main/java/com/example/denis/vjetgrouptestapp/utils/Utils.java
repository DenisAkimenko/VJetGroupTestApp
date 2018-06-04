package com.example.denis.vjetgrouptestapp.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment,
                                       int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    public static String formateDate(String dateString) {
        Date date;
        String formattedDate = "";
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(dateString);
            formattedDate = new SimpleDateFormat("dd.MM.yyyy , HH:mm", Locale.getDefault()).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }
}
