package com.clipstraw.gx.clipstraw.model.chat;

import android.os.Bundle;

import com.clipstraw.gx.clipstraw.model.ClipstrawError;
import com.clipstraw.gx.clipstraw.model.user.UserSkeleton;
import com.clipstraw.gx.clipstraw.request.ChatRequest;
import com.clipstraw.gx.clipstraw.request.Request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Faizzy on 15-02-2016.
 */
public class ChatMessageItem {

    private String id;

    private UserSkeleton sender;
    private boolean isIncoming;
    private String time;
    private boolean isSeen;
    private boolean isDelivered;
    private boolean isSent;
    private String content;
    private int dataSize;
    private String conversationId;
    private ChatItemListener chatItemListener;


    public ChatMessageItem(String conversationId, boolean isIncoming, String time, String content, UserSkeleton sender) {
        this.isIncoming = isIncoming;
        this.time = time;
        this.content = content;
        this.sender = sender;
        this.conversationId = conversationId;
    }

    public UserSkeleton getSender() {
        return sender;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setChatItemListener(ChatItemListener chatItemListener) {
        this.chatItemListener = chatItemListener;
    }

    public boolean isIncoming() {
        return isIncoming;
    }


    public String getTime() {
        return time;
    }


    public boolean isSeen() {
        return isSeen;
    }

    public void setIsSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setIsDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setIsSent(boolean isSent) {
        this.isSent = isSent;
    }


    public String getContent() {
        return content;
    }


    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public void receive() {

        Request receiveRequest = new ChatRequest(ChatRequest.RECEIVE_CHAT, new Request.RequestCallback() {
            @Override
            public void onCompleted(JSONObject response) {

                try {
                    if (!response.has("error")) {

                        id = response.getString("id");
                        if (chatItemListener != null) {
                            chatItemListener.onReceive(ChatMessageItem.this);

                        }
                    } else {
                        if (chatItemListener != null) {
                            chatItemListener.onError(ClipstrawError.createError(response.getJSONObject("error")));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        Bundle params = new Bundle();
        params.putString("id", this.id);
        receiveRequest.setParameters(params);
        receiveRequest.execute();


    }

    public void deleteChatItem() {
        Request deleteChatItemRequest = new ChatRequest(ChatRequest.DELETE_CHAT_MESSAGE_ITEM, new Request.RequestCallback() {
            @Override
            public void onCompleted(JSONObject response) {

                try {
                    if (!response.has("error")) {

                        id = response.getString("id");
                        if (chatItemListener != null) {
                            chatItemListener.onDeleteChatItem(ChatMessageItem.this);

                        }
                    } else {
                        if (chatItemListener != null) {
                            chatItemListener.onError(ClipstrawError.createError(response.getJSONObject("error")));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        Bundle params = new Bundle();
        params.putString("id", this.id);
        deleteChatItemRequest.setParameters(params);
        deleteChatItemRequest.execute();


    }

    public void send() {
        Request sendRequest = new ChatRequest(ChatRequest.SEND_CHAT, new Request.RequestCallback() {
            @Override
            public void onCompleted(JSONObject response) {
                try {
                    if (!response.has("error")) {

                        id = response.getString("id");

                        if (chatItemListener != null) {
                            chatItemListener.onSendChat(ChatMessageItem.this);
                        }

                    } else {

                        if (chatItemListener != null) {

                            chatItemListener.onError(ClipstrawError.createError(response.getJSONObject("error")));
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("conversation_id", getConversationId());
        parameters.putString("time", getTime());
        parameters.putString("content", getContent());
        sendRequest.setParameters(parameters);
        sendRequest.execute();

    }

    public interface ChatItemListener {
        public void onSendChat(ChatMessageItem chatMessageItem);

        public void onReceive(ChatMessageItem receivedId);

        public void onError(ClipstrawError error);

        public void onDeleteChatItem(ChatMessageItem item);
    }

}
