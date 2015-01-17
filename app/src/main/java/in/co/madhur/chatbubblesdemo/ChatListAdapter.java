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
        ViewHolder1 holder1;
        ViewHolder2 holder2;

        if(message.getUserType()==UserType.SELF)
        {
            if(convertView==null)
            {
                    v= LayoutInflater.from(context).inflate(R.layout.chat_user1_item, null, false);
                    holder1 = new ViewHolder1();

                holder1.messageTextView = (TextView) v.findViewById(R.id.message_text);
                holder1.timeTextView=(TextView)v.findViewById(R.id.time_text);

                    v.setTag(holder1);


            }
            else
            {
                    v=convertView;
                holder1 = (ViewHolder1) v.getTag();

            }

            holder1.messageTextView.setText(message.getMessageText());




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
        public   TextView messageTextView;
        public  TextView timeTextView;


    }

    private  class ViewHolder2
    {
        public  TextView userNameTextView;
        public  TextView messageTextView;

    }
}
