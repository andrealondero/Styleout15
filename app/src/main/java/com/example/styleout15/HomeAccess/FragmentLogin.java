package com.example.styleout15.HomeAccess;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.styleout15.DataBase.DBAdapterLogin;
import com.example.styleout15.R;

public class FragmentLogin extends Fragment {

    EditText email;
    EditText password;

    ImageButton btnLogin;
    ImageButton btnRegistrati;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);

        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegistrati = view.findViewById(R.id.btnRegister);

        final DBAdapterLogin db = new DBAdapterLogin(view.getContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailInserita = email.getText().toString();
                String passwordInserita = password.getText().toString();

                db.getLogin(emailInserita, passwordInserita);
                if(emailInserita != null && passwordInserita!= null) {
                    if(db.isEmailPresent(emailInserita)) {
                        if(db.getPassword(emailInserita).equals(passwordInserita))
                            Toast.makeText(container.getContext(), "logged", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(container.getContext(), "user not found", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentRegister = new FragmentRegister();
                getFragmentManager().beginTransaction().replace(R.id.container, fragmentRegister).commit();
            }
        });
        return view;
    }
}
