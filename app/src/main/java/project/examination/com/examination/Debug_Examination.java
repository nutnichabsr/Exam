package project.examination.com.examination;

import android.util.Log;

public final class Debug_Examination {
    private Debug_Examination(){

    }

    public static void out (Object msg){
        Log.w ("Examination", msg.toString ());
    }
}