
package in.co.madhur.chatbubblesdemo.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.RelativeLayout;

import in.co.madhur.chatbubblesdemo.AndroidUtilities;


public class SizeNotifierRelativeLayout extends RelativeLayout {

    private Rect rect = new Rect();
    public SizeNotifierRelativeLayoutDelegate delegate;

    public abstract interface SizeNotifierRelativeLayoutDelegate {
        public abstract void onSizeChanged(int keyboardHeight);
    }

    public SizeNotifierRelativeLayout(Context context) {
        super(context);
    }

    public SizeNotifierRelativeLayout(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    public SizeNotifierRelativeLayout(android.content.Context context, android.util.AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * Calculate the soft keyboard height and report back to listener
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (delegate != null) {
            View rootView = this.getRootView();
            int usableViewHeight = rootView.getHeight() - AndroidUtilities.statusBarHeight - AndroidUtilities.getViewInset(rootView);
            this.getWindowVisibleDisplayFrame(rect);
            int keyboardHeight = usableViewHeight - (rect.bottom - rect.top);
            delegate.onSizeChanged(keyboardHeight);
        }
    }


}
