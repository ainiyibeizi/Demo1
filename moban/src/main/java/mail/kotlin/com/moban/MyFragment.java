package mail.kotlin.com.moban;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {
public static int i=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view=new TextView(getActivity());
        Log.i("aaaa",""+i);
       //for (int i=0;i<100;i++){
        if (i==0){
            view.setText("哈哈"+0);
            i++;
        }else{
            view.setText("哈哈"+1);
        }


           /*try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }*/
      // }
        return view;


    }
}
