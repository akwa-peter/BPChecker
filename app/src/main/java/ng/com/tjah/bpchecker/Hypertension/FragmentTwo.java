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


public class FragmentTwo extends Fragment {

    TextView txtData;
    public  String data;
    RadioButton radioButton;
    RadioGroup radioSexGroup;
   public String test = "Trust is testing";
    public String test1 = "no  testing";
    public String test2 = "yes it is testing";
    Bundle mBundle;
    SendMessage SM;
   // FragmentOne.SendMessage SM;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(
                R.layout.hyper_symp_check2, container, false);

        radioSexGroup = rootView.findViewById(R.id.radioGroup1);

        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find the radiobutton by returned id
                radioButton = rootView.findViewById(checkedId);

                try {
                    SM.sendData1(radioButton.getText().toString());
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

        //  txtData = (TextView)view.findViewById(R.id.txtData);




    }

    protected void displayReceivedData(String message)
    {
        //Toast.makeText(getActivity(),message , Toast.LENGTH_LONG).show();
    }

    interface SendMessage {
        void sendData1(String message);
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