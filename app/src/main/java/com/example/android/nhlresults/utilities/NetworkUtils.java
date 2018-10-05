package com.example.android.nhlresults.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {

    final static String NHL_BASE_URL = "https://statsapi.web.nhl.com";
    final static String NHL_SCHEDULE_DATE_SUFFIX = "/api/v1/schedule";


    /**
     * Builds the URL used to query nhl-score-api.
     */
    public static URL buildUrl(String date) {
        Uri builtUri = Uri.parse(NHL_BASE_URL+NHL_SCHEDULE_DATE_SUFFIX).buildUpon()
                .appendQueryParameter("date", date)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the result from the HTTP response.
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            if(urlConnection.getResponseCode() != HttpsURLConnection.HTTP_OK){
                throw new IOException(urlConnection.getResponseMessage() + ": with " + url);
            }

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
