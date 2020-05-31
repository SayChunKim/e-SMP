package com.test.sckim.e_smp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by SC Kim on 1/17/2015.
 */
public class ProfileAddressFragment extends Fragment {

    private Button editButton_address;
    private Button doneButton_address;
    private EditText address_line1;
    private EditText address_line2;
    private EditText mobile_number;
    private Spinner gender_spinner;
    private Spinner marital_status_spinner;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_profile_address, container, false);
        editButton_address = (Button) rootView.findViewById(R.id.button_address_edit);
        editButton_address.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setEnabledProfile(rootView);


            }
        });

        doneButton_address = (Button) rootView.findViewById(R.id.button_address_done);
        doneButton_address.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                editor.commit();
                setDisabledProfile(rootView);
                Toast.makeText(getActivity(),"Done Editing", Toast.LENGTH_SHORT).show();
            }
        });

//        setButtonDisplay(rootView);
        setSpinnerDisplay(rootView);
        setDisabledProfile(rootView);
        return rootView;
    }

    private void setDisabledProfile(View view){
        address_line1 = (EditText) view.findViewById(R.id.address_line1);
        address_line1.setEnabled(false);
        address_line1.setInputType(InputType.TYPE_NULL);
        address_line1.setFocusable(false);

        address_line2 = (EditText) view.findViewById(R.id.address_line2);
        address_line2.setEnabled(false);
        address_line2.setInputType(InputType.TYPE_NULL);
        address_line2.setFocusable(false);

        mobile_number = (EditText) view.findViewById(R.id.mobile_editTxt);
        mobile_number.setEnabled(false);
        mobile_number.setInputType(InputType.TYPE_NULL);
        mobile_number.setFocusable(false);

        gender_spinner = (Spinner) view.findViewById(R.id.spinner_gender);
        gender_spinner.setEnabled(false);
        gender_spinner.setClickable(false);

        marital_status_spinner = (Spinner) view.findViewById(R.id.spinner_marital_status);
        marital_status_spinner.setEnabled(false);
        marital_status_spinner.setClickable(false);

        doneButton_address.setVisibility(View.GONE);
        editButton_address.setVisibility(View.VISIBLE);


//        personal_religion.setAdapter(typeAdapter);
    }


    private void setEnabledProfile(View view){

        address_line1 = (EditText) view.findViewById(R.id.address_line1);
        address_line1.setEnabled(true);
        address_line1.setInputType(InputType.TYPE_CLASS_TEXT);
        address_line1.setFocusableInTouchMode(true);

        address_line2 = (EditText) view.findViewById(R.id.address_line2);
        address_line2.setEnabled(true);
        address_line2.setInputType(InputType.TYPE_CLASS_TEXT);
        address_line2.setFocusableInTouchMode(true);

        mobile_number = (EditText) view.findViewById(R.id.mobile_editTxt);
        mobile_number.setEnabled(true);
        mobile_number.setInputType(InputType.TYPE_CLASS_TEXT);
        mobile_number.setFocusableInTouchMode(true);

        gender_spinner = (Spinner) view.findViewById(R.id.spinner_gender);
        gender_spinner.setEnabled(true);
        gender_spinner.setClickable(true);

        marital_status_spinner = (Spinner) view.findViewById(R.id.spinner_marital_status);
        marital_status_spinner.setEnabled(true);
        marital_status_spinner.setClickable(true);

        doneButton_address.setVisibility(View.VISIBLE);
        editButton_address.setVisibility(View.GONE);
//        personal_religion.setAdapter(typeAdapter);

        pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
        editor.putString("personal_name", "string value");
        editor.putString("personal_matricID", "string value");
        editor.putString("personal_profileID", "int value");



    }

    private  void setSpinnerDisplay(View view){
        String [] genders =
                {"Male", "Female"};
        gender_spinner = (Spinner) view.findViewById(R.id.spinner_gender);
        ArrayAdapter<String> Gadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, genders);
        Gadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);



        String [] marital_status =
                {"Single", "Married"};
        marital_status_spinner = (Spinner) view.findViewById(R.id.spinner_marital_status);
        ArrayAdapter<String> Madapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, marital_status);
        Madapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        marital_status_spinner.setAdapter(Madapter);
        gender_spinner.setAdapter(Gadapter);
    }

//    private void setButtonDisplay(View view){
//        editButton_address = (Button) view.findViewById(R.id.button_address_edit);
//        editButton_address.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View viewEdit)
//            {
//                setEnabledProfile(viewEdit);
//
//            }
//        });
//
//        doneButton_address = (Button) view.findViewById(R.id.button_address_done);
//        doneButton_address.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View viewDone)
//            {
//                editor.commit();
//                setDisabledProfile(viewDone);
//            }
//        });
//    }

}
