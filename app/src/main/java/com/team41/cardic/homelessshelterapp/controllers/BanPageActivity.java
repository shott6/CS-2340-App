package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team41.cardic.homelessshelterapp.controllers.R;
import com.team41.cardic.homelessshelterapp.model.Admin;
import com.team41.cardic.homelessshelterapp.model.HomelessPerson;


public class BanPageActivity extends AppCompatActivity {

    private EditText banView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_page);

        banView = findViewById(R.id.banID);

        Button banButton = findViewById(R.id.BanHammer);
        banButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable userViewEditable = banView.getText();
                final String userViewString = userViewEditable.toString();

                FirebaseDatabase dataInstance = FirebaseDatabase.getInstance();
                DatabaseReference dataRef = dataInstance.getReference();
                DatabaseReference usersRef = dataRef.child("users");
                DatabaseReference userViewRef = usersRef.child(userViewString);

                userViewRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    HomelessPerson refHomeless;
                    Admin refAdmin;
                    String uRefHomeless;
                    String uRefAdmin;
                    boolean matched = false;
                    boolean isAdmin;
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        refHomeless = dataSnapshot.getValue(HomelessPerson.class);
                        refAdmin = dataSnapshot.getValue(Admin.class);

                        FirebaseDatabase dataInstanceInner = FirebaseDatabase.getInstance();
                        DatabaseReference dataRefInner = dataInstanceInner.getReference();
                        DatabaseReference usersRefInner = dataRefInner.child("users");
                        DatabaseReference userViewRefInner = usersRefInner.child(userViewString);

                        if ((refHomeless == null) || (refAdmin == null)) {
                            banView.setError("Invalid username");
                        } else {
                            isAdmin = refAdmin.getAdmin();
                            uRefAdmin = refAdmin.getUsername();
                            uRefHomeless = refHomeless.getUsername();
                            if (userViewString.equals(uRefAdmin) ||
                                    userViewString.equals(uRefHomeless)) {
                                userViewRefInner.child("accountLocked").setValue(true);
                                matched = true;
                            } else {
                                banView.setError("Invalid username");
                            }
                            if (matched) {
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                    public void onCancelled(DatabaseError databaseError) {}
                });
            }
        });

        Button backButton2 = findViewById(R.id.backButton2);
        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AdminPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
