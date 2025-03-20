package modmate.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import modmate.log.Log;

/**
 * Utility class for handling HTTP requests and responses.
 */
public class HttpUtil {

    /**
     * Retrieves data from the specified URL using HttpURLConnection.
     *
     * @param url the URL to retrieve data from
     * @return the response data as a String
     * @throws IOException if an I/O error occurs
     */
    public static String retrieveDataFromURL(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            String responseData = new String(inputStream.readAllBytes());
            inputStream.close();

            if (responseData.isEmpty()) {
                throw new IllegalArgumentException("JSON response is empty or null");
            }

            return responseData;
        }

        Log.saveLog("\n[HttpUtil]   Request failed. Response Code: " + responseCode);
        throw new IOException("Request failed. Response Code: " + responseCode);
    }
}