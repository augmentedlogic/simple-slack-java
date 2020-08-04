/**
  Copyright (c) 2019 Wolfgang Hauptfleisch <wh@augmentedlogic.com>

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.
 **/
package com.augmentedlogic.simpleslack;

import java.net.URL;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
/**
 *
 *  A Simple Slack Client to send a message to a slack channel
 *
 **/
public class SlackMessage {


     private String message = null;
     private String username = "bot";
     private String channel = "general";
     private String hook = null;
     private String icon_emoji = ":robot_face:";
     private int connect_timeout = 8000;


     public void setMessage(String message)
     {
          this.message = message;
     }

     public void setUsername(String username)
     {
          this.username = username;
     }

     public void setChannel(String channel)
     {
          this.channel = channel;
     }

     public void setEmoji(String emoji)
     {
          this.icon_emoji = emoji;
     }

     public void setHook(String hook)
     {
          this.hook = hook;
     }

     public void setConnectTimeout(int connect_timeout)
     {
          this.connect_timeout = connect_timeout;
     }

     private String makePayload()
     {
         Map<String,String> map = new HashMap<String,String>();
                            map.put("text", this.message);
                            map.put("username", this.username);
                            map.put("channel",this.channel);
                            map.put("icon_emoji", this.icon_emoji);

                                String jsonStr = "";

                                Gson gsonObj = new Gson();
                                try {
                                   jsonStr = gsonObj.toJson(map);
                                } catch(Exception e) {

                                }
        return jsonStr;
     }

     public String send() throws Exception {

        URL obj = new URL(this.hook);

        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setConnectTimeout(this.connect_timeout);

        String urlParameters = "payload=" + URLEncoder.encode(this.makePayload(), "UTF-8");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();

          BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
          String inputLine;
               StringBuffer response = new StringBuffer();
                  while ((inputLine = in.readLine()) != null) {
                          response.append(inputLine);
                  }
                  in.close();

    return  response.toString();
    }

}


