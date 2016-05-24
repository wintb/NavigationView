package tien.dinh.navigationview.json;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VuVanThang on 5/22/2016.
 */
public class JsonSoDoghe {
    private String MaChuyen;

    public JsonSoDoghe(){}
    public JsonSoDoghe(String MaChuyen){
        this.MaChuyen = MaChuyen;
    }

    /**
     * Post MaTai to server for see detail TaiXe
     * @param url
     * @return file json
     */

    public String makePostRequestSoDoGhe(String url){
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("MaChuyen", MaChuyen);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("Chuyen", jsonObject.toString()));
            Log.d("JSON POST DATA", "mainToPost: " + nameValuePairs.toString());
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            //execute HttpPost request
            HttpResponse httpResponse = httpClient.execute(httpPost);
            InputStream inputStream = httpResponse.getEntity().getContent();
            InputStreamToString str = new InputStreamToString();
            result = str.getStringFromInputStream(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Read input stream to string
     */
    private static class InputStreamToString{

        public static void main(String[] args) throws IOException {
            InputStream is = new ByteArrayInputStream("file content".getBytes());
            getStringFromInputStream(is);
        }

        public static String getStringFromInputStream(InputStream is){
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }
    }
}
