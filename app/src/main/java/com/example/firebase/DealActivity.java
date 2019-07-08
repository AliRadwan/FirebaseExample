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

 public class DealActivity extends AppCompatActivity {


     /**
      * Firebase DataBase instance = this is entry point for database.
      * Firebase Database Reference = this id the location in DB where we can read and write
      */

     private FirebaseDatabase firebaseDatabase;
     private DatabaseReference databaseReference;
     TravelDeal travelDeal;
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
                Intent i = new Intent(DealActivity.this,ListActivity.class);
                startActivity(i);
            }
        });



        Intent intent = getIntent();
        TravelDeal deal = (TravelDeal) intent.getSerializableExtra("Deal");
        if ( deal == null){
            deal = new TravelDeal();
        }
        this.travelDeal = deal;
        title.setText(deal.getTitle());
        desc.setText(deal.getDescription());
        price.setText(deal.getPrice());
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

             case R.id.delete_menu:
                 deleteDeal();
                 Toast.makeText(this," removed deal",Toast.LENGTH_LONG).show();
                 backToList();
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

         travelDeal.setTitle(title.getText().toString());
         travelDeal.setDescription(price.getText().toString());
         travelDeal.setPrice(desc.getText().toString());

         if (travelDeal.getId() == null){
             databaseReference.push().setValue(travelDeal);
         }
         else {
             databaseReference.child(travelDeal.getId()).setValue(travelDeal);
         }
//         TravelDeal deal = new TravelDeal(txtTitle,txtPrice,txtDeac,"");
//         // to push data to firebase database
//         databaseReference.push().setValue(deal);
     }


     private void deleteDeal(){
         if (travelDeal == null){
             Toast.makeText(this,"PLZ Save before delete",Toast.LENGTH_LONG).show();
             return;
         }
         databaseReference.child(travelDeal.getId()).removeValue();
     }

     private void backToList(){
         Intent i = new Intent(this,ListActivity.class);
         startActivity(i);
     }




     // for clean edit text after sent data
     private void clean(){
         title.setText("");
         price.setText("");
         desc.setText("");
         title.requestFocus();
     }
}
