 package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

 public class MainActivity extends AppCompatActivity {


     /**
      * Firebase DataBase instance = this is entry point for database.
      * Firebase Database Reference = this id the location in DB where we can read and write
      */

     private FirebaseDatabase firebaseDatabase;
     private DatabaseReference databaseReference;

     private EditText title;
     private EditText price;
     private EditText desc;


     Button button;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup database and create object

        FirebaseUtil.openFbReference("traveldeals");
        firebaseDatabase = FirebaseUtil.firebaseDatabase;
        databaseReference = FirebaseUtil.databaseReference;


        title = findViewById(R.id.edt_title);
        price = findViewById(R.id.edt_price);
        desc = findViewById(R.id.edt_description);


        button = findViewById(R.id.btn_show_data);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ListActivity.class);
                startActivity(i);
            }
        });
    }


    // save menu item to firebase
     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item ) {

         switch (item.getItemId()){
             case R.id.save_menu:
                 saveDeal();
                 Toast.makeText(this,"Deal save",Toast.LENGTH_LONG).show();
                 clean();
                 return true;
                 default:
                     return  super.onOptionsItemSelected(item);
         }
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.save,menu);
         return true;
     }


     // method that will save data by sent it to database
     private void saveDeal(){

         String txtTitle = title.getText().toString();
         String txtPrice = price.getText().toString();
         String txtDeac = desc.getText().toString();
         TravelDeal deal = new TravelDeal(txtTitle,txtPrice,txtDeac,"");
         // to push data to firebase database
         databaseReference.push().setValue(deal);
     }

     // for clean edit text after sent data
     private void clean(){
         title.setText("");
         price.setText("");
         desc.setText("");
         title.requestFocus();
     }
}
