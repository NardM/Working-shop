package profesiya;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Evgeny on 18.05.2016.
 */

public class newEvgWriter {
    public static void newEvgWriter(String filename, ArrayList<formAddings.Professii> arrayLList){
        File file = new File(filename);
        try{
            FileWriter out = new FileWriter(file, true);
            try{
                for (formAddings.Professii anArrayList : arrayLList) {
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
