package com.example.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * This is Utils Class , we create it to collect same method and instance that we ues it many time
 */

public class FirebaseUtil {

    // we make all members and method static to be able to call them without instantiating(creating) any object of this class
    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;
    public static FirebaseUtil firebaseUtil;
    public static ArrayList<TravelDeal> travelDeals;


    // we create private Constructor to avoid this class being instantiated from outside this class
    private FirebaseUtil(){}


    // we create generic static method that will open reference of the child that is passed as a parameter
    // if this method has been called it will do nothing and return it self
    // else it will create a single instance of it self
    public static void openFbReference(String  ref){
        if (firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            firebaseDatabase = FirebaseDatabase.getInstance();
        }

        travelDeals = new ArrayList<TravelDeal>();
        databaseReference = firebaseDatabase.getReference().child(ref);
    }



}
