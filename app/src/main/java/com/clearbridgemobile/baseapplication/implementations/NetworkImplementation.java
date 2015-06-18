package com.clearbridgemobile.baseapplication.implementations;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import com.clearbridgemobile.core.interfaces.NetworkInterface;
import com.clearbridgemobile.core.managers.NetworkManager;

import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Map;

public class NetworkImplementation implements NetworkInterface {

    private NetworkManager _networkManager;
    private RequestQueue _requestQueue;
    private Context _context;
    private boolean connected;

    public NetworkImplementation (Context context)
    {
        _context = context;
//        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        //connected = activeNetwork != null && activeNetwork.isAvailable() && activeNetwork.isConnected();
        connected = true;
    }

    public void setNetworkManager (NetworkManager networkManager)
    {
        _networkManager = networkManager;
//        ConnectionManager.NetworkStatusChangedListener listener = ConnectionManager.getInstance().getNetworkStatusChangedListener();
//        if(listener != null) {
//            listener.onStatusChanged(connected);
//        }
    }

    protected RequestQueue getRequestQueue ()
    {
        if (_requestQueue == null)
            _requestQueue = Volley.newRequestQueue(_context);

        return _requestQueue;
    }

    protected <T> void addToRequestQueue (Request<T> request, String tag)
    {
        request.setTag(tag);

        getRequestQueue().add(request);
    }

    protected void cancelPendingRequests (Object tag)
    {
        if (_requestQueue != null)
            _requestQueue.cancelAll(tag);

    }

    protected void sendRequest (String url, final Map<String, String> headers, Map<String, Object> params, final String requestId, int method)
    {
        if (_networkManager == null)
            return;

        JSONObject jsonParams = null;
        String paramsString = "";
        if (params != null) {
            if(method == Request.Method.GET)
            {
                StringBuilder sb = new StringBuilder();
                sb.append(url);

                if(params.size() > 0)
                {
                    sb.append("?");
                }

                for(Hashtable.Entry<String, Object> e : params.entrySet()){
                    if(sb.length() > url.length() + 1){
                        sb.append('&');
                    }
                    try {
                        String key = e.getKey();
                        Object value = e.getValue();
                        if(value.getClass() == String.class) {
                            sb.append(URLEncoder.encode(key, "UTF-8")).append('=').append(URLEncoder.encode((String)value, "UTF-8"));
                        }
                        else if(value instanceof String[]) {
                            String[] values = (String[]) value;

                            for(String v : values)
                            {
                                sb.append('&');
                                sb.append(URLEncoder.encode(key, "UTF-8")).append('=').append(URLEncoder.encode(v, "UTF-8"));
                            }
                        }
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                }
                url = sb.toString();
            }
            else {
                jsonParams = new JSONObject(params);
                paramsString = jsonParams.toString();
            }
        }

        JsonRequest<String> request = new JsonRequest<String>(method, url, paramsString, new Response.Listener<String>() {

            @Override
            public void onResponse(String data) {
                onSuccessCallback(data, requestId);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                onErrorCallback(error, requestId);
            }
        }) {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String dataString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                }
                catch (UnsupportedEncodingException e)
                {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            public Map<String, String> getHeaders() {
                if(headers == null)
                {
                    return new Hashtable<>();
                }
                return headers;
            }
        };

        addToRequestQueue(request, requestId);
    }

    protected void onSuccessCallback (String data, String requestId)
    {
        if (_networkManager == null)
            return;
        _networkManager.onSuccess(data, requestId);
    }

    protected void onErrorCallback (VolleyError error, String requestId)
    {
        if (_networkManager == null)
            return;

        int statusCode = -1;
        if(error.networkResponse != null)
            statusCode = error.networkResponse.statusCode;
        String error_string = error.getMessage();
        error_string = (error_string == null) ? "" : error_string;

        _networkManager.onError(statusCode, error_string, requestId);
    }

    @Override
    public void makeGetRequest(String url, Hashtable<String, String> headers, Hashtable<String, Object> params, String requestID) {
        sendRequest(url, headers, params, requestID, Request.Method.GET);
    }

    @Override
    public void makePostRequest(String url, Hashtable<String, String> headers, Hashtable<String, Object> params, String requestID) {
        sendRequest(url, headers, params, requestID, Request.Method.POST);
    }

    @Override
    public void makePutRequest(String url, Hashtable<String, String> headers, Hashtable<String, Object> params, String requestID) {
        sendRequest(url, headers, params, requestID, Request.Method.PUT);
    }

    @Override
    public void makeDeleteRequest(String url, Hashtable<String, String> headers, Hashtable<String, Object> params, String requestID) {
        sendRequest(url, headers, params, requestID, Request.Method.DELETE);
    }

    @Override
    public boolean isConnectedToNetwork() {
        return connected;
    }

    public void setConnected(boolean connected)
    {
        this.connected = connected;
    }
}
