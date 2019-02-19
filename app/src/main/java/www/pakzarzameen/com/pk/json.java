package www.pakzarzameen.com.pk;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class json extends AsyncTask<Void, Void, Void> {
    JSONObject jsonParam = new JSONObject();
    String response = "";
    int code;

    @Override
    protected Void doInBackground(Void... voids) {
        URL url;
        try {
            //url = new URL("https://api.agromonitoring.com/agro/1.0/polygons?appid=ee4ee7c319e3219ed804e03b5ce6a991");
            url = new URL("https://api.spatial.farm/field");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Accept-Language", "en-GB");
            conn.setRequestProperty("Accept-Encoding", "identity");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            jsonParam = makeJsonRequest("AUF");
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(jsonParam.toString());
            os.flush();
            os.close();
            response = conn.getResponseMessage().toString();
            code = conn.getResponseCode();
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //MainActivity.Data.setText(Integer.toString(code));
        MainActivity.Data.setText("Code: " + code + "\n" + "Response: " + response);
    }

    private JSONObject makeJsonRequest(String landName) {
        JSONObject jsonObject = new JSONObject();
        JSONObject geo_json = new JSONObject();
        JSONObject label = new JSONObject();
        try {
            geo_json.put("type", "Feature");
            label.put("label", "test02");
            geo_json.put("properties", label);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject geometry = new JSONObject();
        try {
            geometry.put("type", "Polygon");
            JSONArray coordinates = new JSONArray(); // `coordinates`
            geometry.put("coordinates", coordinates);
            JSONArray innerArray = new JSONArray();
            coordinates.put(innerArray);
            JSONArray entry = new JSONArray();
            entry.put(73.069711);
            entry.put(31.450094);
            innerArray.put(entry);
            entry = new JSONArray();
            entry.put(73.075565);
            entry.put(31.455392);
            innerArray.put(entry);
            entry = new JSONArray();
            entry.put(73.078660);
            entry.put(31.449542);
            innerArray.put(entry);
            entry = new JSONArray();
            entry.put(73.074919);
            entry.put(31.446025);
            innerArray.put(entry);
            entry = new JSONArray();
            entry.put(73.069711);
            entry.put(31.450094);
            innerArray.put(entry);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            geo_json.put("geometry", geometry);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        try {
//            //jsonObject.put("data", landName);
//            //jsonObject.put("geo_json", geo_json);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        try {
            jsonObject.put("data", geo_json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
