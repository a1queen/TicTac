package com.example.tictac;

import android.os.Bundle;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.fail;

class AddGameActivityTest {

    Bundle bundle = new Bundle();

    @Test
    void tworzenieKlasy() {
       try{
        AddGameActivity act = new AddGameActivity();
         }
       catch(Exception exception){
           fail ("nie utworzono");
       }
       }
}