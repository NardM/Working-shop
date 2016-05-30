package profesiya;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by Evgeny on 18.05.2016.
 */

public class table3prof extends JFrame {
    private JTable table1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public table3prof() throws HeadlessException {
        setContentPane(panel1);
        MyTableMode3 myTableModel = new MyTableMode3();
        ArrayList<Rabota> rab = new ArrayList<>();

        chtenie r = new chtenie();
        String mytext = r.chtenie("Ev\\ishod2.txt");
        String[] words = mytext.split(" ");
        int z = 0;
        for (int i = 0; i < words.length; i = i + 11) {
            if (z == (words.length) / 11)
                break;
            rab.add(new Rabota(Integer.parseInt(words[i]), Integer.parseInt(words[i + 1]), Integer.parseInt(words[i + 2]), words[i + 3],
                    Integer.parseInt(words[i + 4]), Integer.parseInt(words[i + 5]), Integer.parseInt(words[i + 6]),
                    Integer.parseInt(words[i + 7]), Integer.parseInt(words[i + 8]), Integer.parseInt(words[i + 9]),
                    Integer.parseInt(words[i + 10].replace(":",""))));
            z++;
        }

        myTableModel.setAct(rab);

        table1.setModel(myTableModel);

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (table1.getSelectedRow() > 0)
                    myTableModel.removeAt(table1.getSelectedRow());
                myTableModel.fireTableDataChanged();
            }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String str = textField1.getText();
                Rabota r = readFromStr(str);
                if (r != null)
                    myTableModel.addItem(r);
                myTableModel.fireTableDataChanged();
            }
        });

        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (table1.getSelectedRow() > 0)
                    myTableModel.removeAt(table1.getSelectedRow());
                String str = textField1.getText();
                Rabota r = readFromStr(str);
                if (r != null)
                    myTableModel.addItem(r);
                myTableModel.fireTableDataChanged();
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (table1.getSelectedRow() > 0)
                    textField1.setText(myTableModel.getItem(table1.getSelectedRow()));
            }
        });

        pack();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                tableEvg3reader.write2("Ev\\ishod2.txt", rab);
            }
        });

        setVisible(true);
        setSize(850, 300);
        setLocationRelativeTo(null);
    }

    private Rabota readFromStr(String str) {
        String[] fields = str.split(" ");
        String gotovaya = fields[3];
        try {
            int tceh = Integer.parseInt(fields[0]);
            int nomerUcha = Integer.parseInt(fields[1]);
            int kodSbor = Integer.parseInt(fields[2]);

            int tabNomer = Integer.parseInt(fields[4]);
            int nomerOper = Integer.parseInt(fields[5]);
            int kolGod = Integer.parseInt(fields[6]);
            int kolBrak = Integer.parseInt(fields[7]);
            int proc = Integer.parseInt(fields[8]);
            int god = Integer.parseInt(fields[9]);
            int mes = Integer.parseInt(fields[10]);
            return new Rabota(tceh, nomerUcha, kodSbor, gotovaya, tabNomer, nomerOper, kolGod, kolBrak, proc, god, mes);
        } catch (Exception e) {
            return new Rabota(0, 0, 0, gotovaya, 0, 0, 0, 0, 0, 0, 0);
        }
    }

    class MyTableMode3 extends AbstractTableModel {

        java.util.List<Rabota> rab;

        public void setAct(java.util.List<Rabota> rab) {
            this.rab = rab;
        }

        public void removeAt(int i) {
            try {
                rab.remove(i);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        public void addItem(Rabota num) {
            try {
                rab.add(num);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        public String getItem(int ind) {
            return rab.get(ind).toString();
        }

        @Override
        public int getRowCount() {
            return rab != null ? rab.size() : 0;
        }

        @Override
        public int getColumnCount() {
            return 11;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return rab.get(rowIndex).gettceh();
                case 1:
                    return rab.get(rowIndex).getnomerUcha();
                case 2:
                    return rab.get(rowIndex).getkodSbor();
                case 3:
                    return rab.get(rowIndex).getgotovaya();
                case 4:
                    return rab.get(rowIndex).gettabNomer();
                case 5:
                    return rab.get(rowIndex).getnomerOper();
                case 6:
                    return rab.get(rowIndex).getkolGod();
                case 7:
                    return rab.get(rowIndex).getkolBrak();
                case 8:
                    return rab.get(rowIndex).getproc();
                case 9:
                    return rab.get(rowIndex).getgod();
                case 10:
                    return rab.get(rowIndex).getmes();
            }
            throw new IllegalStateException("Неверно!");
        }
    }

    class Rabota {   //класс Рабочих
        int tceh;
        int nomerUcha;
        int kodSbor;
        String gotovaya;
        int tabNomer;
        int nomerOper;
        int kolGod;       //хорошая
        int kolBrak;    //бракованнаяя
        int proc;
        int god;
        int mes;

        public Rabota(int tceh, int nomerUcha, int kodSbor, String gotovaya, int tabNomer, int nomerOper, int kolGod, int kolBrak, int proc, int god, int mes) {
            this.tceh = tceh;
            this.nomerUcha = nomerUcha;
            this.kodSbor = kodSbor;
            this.gotovaya = gotovaya;
            this.tabNomer = tabNomer;
            this.nomerOper = nomerOper;
            this.kolGod = kolGod;
            this.kolBrak = kolBrak;
            this.proc = proc;
            this.god = god;
            this.mes = mes;
        }

        public int gettceh() {
            return tceh;
        }

        public int getnomerUcha() {
            return nomerUcha;
        }

        public int getkodSbor() {
            return kodSbor;
        }

        public String getgotovaya() {
            return gotovaya;
        }

        public int gettabNomer() {
            return tabNomer;
        }

        public int getnomerOper() {
            return nomerOper;
        }

        public int getkolGod() {
            return kolGod;
        }

        public int getkolBrak() {
            return kolBrak;
        }

        public int getproc() {
            return proc;
        }

        public int getgod() {
            return god;
        }

        public int getmes() {
            return mes;
        }


        @Override
        public String toString() {
            return tceh + " " + kodSbor +
                    " " + nomerUcha + " " + gotovaya + " " + tabNomer + " "
                    + nomerOper + " " + kolGod + " " + kolBrak + " " + proc + " " + god + " " + mes;
        }
    }
}
