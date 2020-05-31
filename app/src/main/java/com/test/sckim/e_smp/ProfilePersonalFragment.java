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

import static com.test.sckim.e_smp.R.layout;

/**
 * Created by SC Kim on 1/17/2015.
 */
public class ProfilePersonalFragment extends Fragment{


        private Button editButton;
        private Button doneButton;
        private EditText personal_name;
        private EditText personal_matricID;
        private EditText personal_profileID;
        private Spinner personal_religion;
        SharedPreferences pref;
        SharedPreferences.Editor editor;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            final View rootView = inflater.inflate(layout.fragment_profile_personal, container, false);


            editButton = (Button) rootView.findViewById(R.id.button_personal_edit);
            editButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    setEnabledProfile(rootView);

                }
            });

            doneButton = (Button) rootView.findViewById(R.id.button_personal_done);
            doneButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    editor.commit();
                    setDisabledProfile(rootView);
                    Toast.makeText(getActivity(), "Done Editing", Toast.LENGTH_SHORT).show();

                }
            });
            setDisabledProfile(rootView);


            String [] religions =
                    {"Malay/Bumiputera","Chinese","Indian","Others"};
            personal_religion = (Spinner) rootView.findViewById(R.id.spinner_religion);
            ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, religions);
            LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

            personal_religion.setAdapter(LTRadapter);
            return rootView;
        }
    private void setDisabledProfile(View view){
        personal_name = (EditText) view.findViewById(R.id.name_editTxt);
        personal_name.setEnabled(false);
        personal_name.setInputType(InputType.TYPE_NULL);
        personal_name.setFocusable(false);

        personal_matricID = (EditText) view.findViewById(R.id.matric_editTxt);
        personal_matricID.setEnabled(false);
        personal_matricID.setInputType(InputType.TYPE_NULL);
        personal_matricID.setFocusable(false);

        personal_profileID = (EditText) view.findViewById(R.id.ic_editTxt);
        personal_profileID.setEnabled(false);
        personal_profileID.setInputType(InputType.TYPE_NULL);
        personal_profileID.setFocusable(false);

        personal_religion = (Spinner) view.findViewById(R.id.spinner_religion);
        personal_religion.setEnabled(false);
        personal_religion.setClickable(false);

        doneButton.setVisibility(View.GONE);
        editButton.setVisibility(View.VISIBLE);


//        personal_religion.setAdapter(typeAdapter);
    }


    private void setEnabledProfile(View view){



        personal_name = (EditText) view.findViewById(R.id.name_editTxt);
        personal_name.setEnabled(true);
        personal_name.setInputType(InputType.TYPE_CLASS_TEXT);
        personal_name.setFocusableInTouchMode(true);

        personal_matricID = (EditText) view.findViewById(R.id.matric_editTxt);
        personal_matricID.setEnabled(true);
        personal_matricID.setInputType(InputType.TYPE_CLASS_TEXT);
        personal_matricID.setFocusableInTouchMode(true);

        personal_profileID = (EditText) view.findViewById(R.id.ic_editTxt);
        personal_profileID.setEnabled(true);
        personal_profileID.setInputType(InputType.TYPE_CLASS_TEXT);
        personal_profileID.setFocusableInTouchMode(true);

        personal_religion = (Spinner) view.findViewById(R.id.spinner_religion);
        personal_religion.setEnabled(true);
        personal_religion.setClickable(true);

        doneButton.setVisibility(View.VISIBLE);
        editButton.setVisibility(View.GONE);
//        personal_religion.setAdapter(typeAdapter);

        pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
        editor.putString("personal_name", "string value");
        editor.putString("personal_matricID", "string value");
        editor.putString("personal_profileID", "int value");



    }
}






