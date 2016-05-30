package profesiya;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Evgeny on 18.05.2016.
 */

public class tableEvg2reader {
    public static void write1(String filename, ArrayList<table2prof.Sostav> arrayList){
        File file = new File(filename);
        try{
            FileWriter out = new FileWriter(file);
            try{
                for (table2prof.Sostav anArrayList : arrayList) {
                    out.write(String.valueOf(anArrayList) + ":");
                }
            }
            finally {
                JOptionPane.showMessageDialog(null, "Выполнено!");
                out.close();
            }
        }
        catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
}
