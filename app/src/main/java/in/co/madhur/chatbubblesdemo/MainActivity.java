package in.co.madhur.chatbubblesdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView chatListView;
    private EditText chatEditText1;
    private EditText chatEditText2;
    private ArrayList<ChatMessage> chatMessages;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatMessages = new ArrayList<>();

        chatListView = (ListView) findViewById(R.id.chat_list_view);

        chatEditText1 = (EditText) findViewById(R.id.chat_edit_text1);
        chatEditText2 = (EditText) findViewById(R.id.chat_edit_text2);

        ChatListAdapter listAdapter = new ChatListAdapter(chatMessages, this);

        chatListView.setAdapter(listAdapter);

        chatEditText1.setOnKeyListener(keyListener);
        chatEditText2.setOnKeyListener(keyListener);
    }

    private void sendMessage(String messageText, UserType userType)
    {

        ChatMessage message = new ChatMessage();
        message.setMessageStatus(Status.SENT);
        message.setMessageText(messageText);
        message.setUserType(userType);

        chatMessages.add(message);

    }
}
