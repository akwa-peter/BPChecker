package ng.com.tjah.bpchecker.Hypertension;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ng.com.tjah.bpchecker.R;


public class FragmentOne extends Fragment {

    private static final String ARG_USERNAME = "username";
    SendMessage SM;
 public String test = "Trust is testing";
    public String test1 = "no  testing";
    public String test2 = "yes it is testing";
    public String data;
    Bundle mBundle;
    RadioButton radioButton;
    RadioGroup radioSexGroup;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(
                R.layout.hyper_symp_check1, container, false);


        radioSexGroup = rootView.findViewById(R.id.radioGroup1);

        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find the radiobutton by returned id
                radioButton = rootView.findViewById(checkedId);

                try {
                    SM.sendData(radioButton.getText().toString());
                }catch (Exception e){
                    //
                }
            }
        });

        // get selected radio button from radioGroup
        int selectedId = radioSexGroup.getCheckedRadioButtonId();






        return rootView;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }


    interface SendMessage {
        void sendData(String message);
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