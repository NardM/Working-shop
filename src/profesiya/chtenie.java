package profesiya;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Evgeny on 18.05.2016.
 */

public class chtenie {
    String filename;
    public String chtenie(String filename) {
        File f = new File(filename);
        String mytext = "";
        try(FileReader reader = new FileReader(f)){
            char[] buffer = new char[(int)f.length()];
            reader.read(buffer);
            mytext = new String(buffer);
            reader.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return mytext;
    }
}
