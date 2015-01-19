package in.co.madhur.chatbubblesdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainActivity extends ActionBarActivity {

    private ListView chatListView;
    private EditText chatEditText1;
    private EditText chatEditText2;
    private ArrayList<ChatMessage> chatMessages;
    private ImageView enterChatView1, enterChatView2;
    private ChatListAdapter listAdapter;

    private EditText.OnKeyListener keyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            // If the event is a key-down event on the "enter" button
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                // Perform action on key press

                EditText editText = (EditText) v;

                if(v==chatEditText1)
                {
                    sendMessage(editText.getText().toString(), UserType.OTHER);
                }
                else
                    sendMessage(editText.getText().toString(), UserType.SELF);

                chatEditText1.setText("");
                chatEditText2.setText("");

                return true;
            }
            return false;

        }
    };

    private ImageView.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v==enterChatView1)
            {
                sendMessage(chatEditText1.getText().toString(), UserType.OTHER);
            }
            else if(v==enterChatView2)
                sendMessage(chatEditText2.getText().toString(), UserType.SELF);

            chatEditText1.setText("");
            chatEditText2.setText("");

        }
    };

    private final TextWatcher watcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (chatEditText1.getText().toString().equals("")) {

            } else {
                enterChatView1.setImageResource(R.drawable.ic_chat_send);

            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(editable.length()==0){
                enterChatView1.setImageResource(R.drawable.ic_chat_send);
            }else{
                enterChatView1.setImageResource(R.drawable.ic_chat_send_active);
            }
        }
    };

    private final TextWatcher watcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (chatEditText2.getText().toString().equals("")) {

            } else {
                enterChatView2.setImageResource(R.drawable.ic_chat_send);

            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(editable.length()==0){
                enterChatView2.setImageResource(R.drawable.ic_chat_send);
            }else{
                enterChatView2.setImageResource(R.drawable.ic_chat_send_active);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatMessages = new ArrayList<>();

        chatListView = (ListView) findViewById(R.id.chat_list_view);

        chatEditText1 = (EditText) findViewById(R.id.chat_edit_text1);
        chatEditText2 = (EditText) findViewById(R.id.chat_edit_text2);
        enterChatView1 = (ImageView) findViewById(R.id.enter_chat1);
        enterChatView2 = (ImageView) findViewById(R.id.enter_chat2);

         listAdapter = new ChatListAdapter(chatMessages, this);

        chatListView.setAdapter(listAdapter);

        chatEditText1.setOnKeyListener(keyListener);
        chatEditText2.setOnKeyListener(keyListener);

        enterChatView1.setOnClickListener(clickListener);
        enterChatView2.setOnClickListener(clickListener);

        chatEditText1.addTextChangedListener(watcher1);
        chatEditText2.addTextChangedListener(watcher2);
    }

    private void sendMessage(String messageText, UserType userType)
    {

        final ChatMessage message = new ChatMessage();
        message.setMessageStatus(Status.SENT);
        message.setMessageText(messageText);
        message.setUserType(userType);
        message.setMessageTime(new Date().getTime());
        chatMessages.add(message);

        if(listAdapter!=null)
            listAdapter.notifyDataSetChanged();

        // Mark message as delivered after one second

        final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

        exec.schedule(new Runnable(){
            @Override
            public void run(){
               message.setMessageStatus(Status.DELIVERED);

                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        listAdapter.notifyDataSetChanged();
                    }
                });


            }
        }, 1, TimeUnit.SECONDS);

    }
}
