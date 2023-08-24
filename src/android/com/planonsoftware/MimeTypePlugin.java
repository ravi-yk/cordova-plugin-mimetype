/**
 */
package com.planonsoftware;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import java.net.FileNameMap;
import java.net.URLConnection;

public class MimeTypePlugin extends CordovaPlugin {
  private static final String TAG = "MimeTypePlugin";

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing MimeTypePlugin");

   FileNameMap defaultMap = URLConnection.getFileNameMap();

    URLConnection.setFileNameMap((file) -> {
      String contentType = defaultMap.getContentTypeFor(file);
      if (contentType == null && file.endsWith(".js")) {
        Log.d(TAG, "Mimetype is set for js files");
        return "text/javascript";
      }

      return contentType;
    });
  }

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    Log.d(TAG, "execute called");

    return true;
  }

}
