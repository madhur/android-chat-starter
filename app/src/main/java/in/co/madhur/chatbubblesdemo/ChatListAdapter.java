package in.co.madhur.chatbubblesdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by madhur on 17/01/15.
 */
public class ChatListAdapter extends BaseAdapter {

    private ArrayList<ChatMessage> chatMessages;
    private Context context;

    public ChatListAdapter(ArrayList<ChatMessage> chatMessages, Context context)
    {
        this.chatMessages=chatMessages;
        this.context=context;

    }


    @Override
    public int getCount() {
        return chatMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return chatMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v=null;
        ChatMessage message = chatMessages.get(position);

        if(message.getUserType()==UserType.SELF)
        {
            if(convertView==null)
            {
                    v= LayoutInflater.from(context).inflate(R.layout.chat_user1_item, null, false);



            }
            else
            {
                v=convertView;

            }



        }
        else if(message.getUserType()==UserType.OTHER)
        {

            if(convertView==null)
            {
                v= LayoutInflater.from(context).inflate(R.layout.chat_user2_item, null, false);



            }
            else
            {
                v=convertView;

            }



        }


        return v;
    }

    private  class ViewHolder1
    {
        public TextView messageTextView;
        public TextView timeTextView;


    }

    private  class ViewHolder2
    {
        public TextView userNameTextView;
        public TextView messageTextView;

    }
}
