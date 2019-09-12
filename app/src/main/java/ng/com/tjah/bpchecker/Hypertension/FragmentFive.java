package ng.com.tjah.bpchecker.Hypertension;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ng.com.tjah.bpchecker.R;


public class FragmentFive extends Fragment {

    String  data;
    RadioButton radioButton;
    RadioGroup radioSexGroup;
    SendMessage SM;
    Bundle mBundle;
    String test = "Trust is testing";
    String test1 = "no  testing";
    String test2 = "yes it is testing";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(
                R.layout.hyper_symp_check5, container, false);

        radioSexGroup = rootView.findViewById(R.id.radioGroup1);

        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find the radiobutton by returned id
                radioButton = rootView.findViewById(checkedId);


                try {
                    SM.sendData4(radioButton.getText().toString());
                }catch (Exception e){
                    //
                }
            }
        });

        return rootView;



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
    private void SendData(String mdata){
        data = mdata;
    }

    public String dataEntered(){

        return data;
    }
    protected void displayReceivedData(String message)
    {
       // Toast.makeText(getActivity(),message , Toast.LENGTH_LONG).show();
    }

    interface SendMessage {
        void sendData4(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

}