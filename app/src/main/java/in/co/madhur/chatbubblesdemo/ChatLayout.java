package in.co.madhur.chatbubblesdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by madhur on 17/01/15.
 */
public class ChatLayout extends RelativeLayout {



    public ChatLayout(Context context) {
        super(context);
    }

    public ChatLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ChatLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(getChildCount()<3)
            return;

        int imageViewWidth = getChildAt(0).getMeasuredWidth();
        int timeWidth = getChildAt(1).getMeasuredWidth();
        int messageHeight = getChildAt(2).getMeasuredHeight();

        setMeasuredDimension(  getChildAt(2).getMeasuredWidth() + imageViewWidth + timeWidth + 38, messageHeight);
    }

}
