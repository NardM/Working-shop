package profesiya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Evgeny on 18.05.2016.
 */

public class formAddings extends JFrame {
    private JTextField textField1;
    private JButton button1;
    private JPanel panel1;

    public formAddings() throws HeadlessException{
        setContentPane(panel1);
        ArrayList<Professii> prof = new ArrayList<>();


        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String str = textField1.getText();
                Professii r = readFromStr(str);
                ArrayList<Professii> prof = new ArrayList<>();
                if(r!=null) {
                    prof.add(r);
                    System.out.println(prof);
                    newEvgWriter.newEvgWriter("Ev\\table1.txt", prof);
                }
            }
        });
        pack();


    }

    public Professii readFromStr(String str){
        String[]fields=str.split(" ");
        if(fields.length>=2) {
            String name = fields[1];
            try {
                int kod = Integer.parseInt(fields[0]);
                return new Professii(kod, name);
            } catch (Exception e) {
                return new Professii(0, name);
            }
        }
        else return null;
    }



    public class Professii{
        int kod;
        String name;

        public Professii(int kod, String name) {
            this.kod = kod;
            this.name = name;

        }
        public int getKod() {
            return kod;
        }

        public String getName() {
            return name;
        }



        @Override
        public String toString() {
            return   kod + " " + name;
        }
    }
}
