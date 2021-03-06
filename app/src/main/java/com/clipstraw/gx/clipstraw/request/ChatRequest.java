package com.clipstraw.gx.clipstraw.request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Faizzy on 22-02-2016.
 */
public class ChatRequest extends Request {

    private static final String API_NAME = "Chat";
    public static final String ADD_MEMBER = "add_member";
    public static final String REMOVE_MEMBER = "remove_member";
    public static final String DELETE = "delete";
    public static final String FETCH_ALL_GROUPS = "fetch_all_groups";
    public static final String LEAVE_GROUP = "leave_group";
    public static final String FETCH_ALL_MSG = "fetch_all_msg";
    public static final String DELETE_CHAT_MESSAGE_ITEM = "delete_chat_message_item";
    public static final String SEND_CHAT = "send_chat";
    public static final String RECEIVE_CHAT = "receive_chat";
    public static final String CLEAR = "clear";


    public ChatRequest(String apiEndPoint, RequestCallback callback) {

        super(API_NAME, apiEndPoint);
        this.callback = callback;

    }

    @Override
    protected void onRawResponse(String response) {
        JSONObject jsonResponse = new JSONObject();
        switch (apiEndPoint) {
            case ADD_MEMBER:
                try {
                    jsonResponse.put("user_id", parameters.getString("user_id"));
                    callback.onCompleted(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case REMOVE_MEMBER:
                try {
                    jsonResponse.put("user_id", parameters.getString("user_id"));
                    callback.onCompleted(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case DELETE:
                try {
                    jsonResponse.put("id", parameters.getString("id"));
                    callback.onCompleted(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case FETCH_ALL_GROUPS:

                try {
                    JSONArray groups = new JSONArray();
                    JSONObject group;
                    for (int i = 0; i < 15; i++) {
                        group = new JSONObject();
                        group.put("name", "Fun " + i);
                        group.put("group_id", "group1234");
                        group.put("group_image_url", "abc");
                        group.put("time", "12:00");
                        group.put("last_msg", "this is a message");
                        group.put("unread_count", "5");
                        groups.put(group);
                    }
                    jsonResponse.put("groups", groups);
                    callback.onCompleted(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case FETCH_ALL_MSG:
                try {
                    JSONArray chatMsgs = new JSONArray();
                    JSONObject chatMsg;
                    for (int i = 0; i < 15; i++) {
                        chatMsg = new JSONObject();
                        chatMsg.put("id", "" + i);
                        chatMsg.put("content", "this is a content");
                        chatMsg.put("time", "12:00");
                        JSONObject user = new JSONObject();
                        user.put("user_id","user123"+i);
                        user.put("user_name","john "+i);
                        user.put("profile_img_url","https//:something");
                        chatMsg.put("sender", user);
                        chatMsgs.put(chatMsg);

                    }

                    jsonResponse.put("chat_Msgs", chatMsgs);

                    callback.onCompleted(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case LEAVE_GROUP:
                try {
                    //jsonResponse.put("group_id",parameters.getString("group_id"));
                    jsonResponse.put("user_id", parameters.getString("user_id"));
                    callback.onCompleted(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            case SEND_CHAT:
                try {
                    jsonResponse.put("id", "chat1234");
                    callback.onCompleted(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case RECEIVE_CHAT:

                try {
                    jsonResponse.put("id", "chat1234");
                    callback.onCompleted(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            case CLEAR:
                try {
                    jsonResponse.put("id", parameters.getString("id"));
                    callback.onCompleted(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


        }


    }
}
