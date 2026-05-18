package com.example.tp4_persist.model;


import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialiser {

    public Serialiser(){super();}
    public void serialise(String filename, Object objet, Context context) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput(filename,Context.MODE_PRIVATE));
        oos.writeObject(objet);
        oos.flush();
        oos.close();
    }

    public Object deSerialise(String filename,Context context)throws IOException{
        Object obj;
        try{
            ObjectInputStream ois =new ObjectInputStream(context.openFileInput(filename));
            obj = ois.readObject();
            ois.close();
            return obj;
        } catch (ClassNotFoundException   e) {
            System.out.println("File Not found");
            return null;
        }

    }

}