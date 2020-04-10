package common;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpUtil {
    CloseableHttpClient httpclient;

    public HttpUtil(){
        httpclient = HttpClients.createDefault();
    }

    public ResponseWrapper get(String url){
        HttpGet httpget = new HttpGet(url);
        ResponseWrapper responseWrapper = new ResponseWrapper();
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            responseWrapper.setStatus(response.getStatusLine().getStatusCode());
            responseWrapper.setStatusMessage(response.getStatusLine().getReasonPhrase());
            responseWrapper.setSuccess(true);
            responseWrapper.setContent(inputStreamToString(response.getEntity().getContent()));

            response.close();
        } catch (IOException e) {
            e.printStackTrace();
            responseWrapper.setSuccess(false);
        } finally {
            httpget.releaseConnection();
        }

        return responseWrapper;
    }

    private String inputStreamToString(InputStream is) throws IOException {
        String line = "";
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        // Read response until the end
        while ((line = rd.readLine()) != null) {
            total.append(line);
        }
        // Return full string
        return total.toString();
    }
}
