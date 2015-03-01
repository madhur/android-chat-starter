package in.co.madhur.chatbubblesdemo;

import android.app.Application;
import android.os.Handler;

/**
 * Created by madhur on 3/1/15.
 */
public class App extends Application {

    private static App Instance;
    public static volatile Handler applicationHandler = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Instance=this;

        applicationHandler = new Handler(getInstance().getMainLooper());

        NativeLoader.initNativeLibs(App.getInstance());

    }

    public static App getInstance()
    {
        return Instance;
    }
}
