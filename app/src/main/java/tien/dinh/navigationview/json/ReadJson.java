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
 * Created by VuVanThang on 5/14/2016.
 */
public class ReadJson {

    private String MaTai;
    private String TenChuyen;
    private String NgayDi;
    private String MaVe;
    private String CMND;
    private String SDTKhach;


    public ReadJson(String TenChuyen, String NgayDi){
        this.TenChuyen = TenChuyen;
        this.NgayDi = NgayDi;
    }

    public ReadJson(String MaTai){
        this.MaTai = MaTai;
    }

    public ReadJson(String MaVe, String CMND, String SDTKhach){
        this.MaVe = MaVe;
        this.CMND = CMND;
        this.SDTKhach = SDTKhach;
    }

    /**
     * Post MaVe, CMND, SDTKhach to server for see json Thong Tin Ve
     */

    public String makePostRequestThongTinVe(String url){
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("MaVe", MaVe);
            jsonObject.put("CMND", CMND);
            jsonObject.put("SDTKhach", SDTKhach);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("ThongTinVe", jsonObject.toString()));
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
     * Post TenChuyen, NgayDi to server for see list Trip
     * @param url
     * @return
     */
    public String makePostRequestChuyenDi(String url){
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("TenChuyen", TenChuyen);
            jsonObject.put("NgayDi", NgayDi);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("ChuyenDi", jsonObject.toString()));
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
     * Post MaTai to server for see detail TaiXe
     * @param url
     * @return file json
     */

    public String makePostRequestTaiXe(String url){
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("MaTai", MaTai);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("TaiXe", jsonObject.toString()));
            Log.d("JSON POST DATA", "mainToPost: " + nameValuePairs.toString());
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            //execute HttpPost request
            HttpResponse httpResponse = httpClient.execute(httpPost);
            InputStream inputStream = httpResponse.getEntity().getContent();
            InputStreamToString str = new InputStreamToString();
            result = str.getStringFromInputStream(inputStream);
            Log.d("JSON_TAIXE: ",result);
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
