package profesiya;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

/**
 * Created by Evgeny on 18.05.2016.
 */

public class table2prof extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    //создаем тело программы
    public table2prof() throws HeadlessException {
        setContentPane(panel1);
        MyTableMode2 myTableModel = new MyTableMode2();
        ArrayList<Sostav> sost = new ArrayList<>();

        chtenie r = new chtenie();
        String mytext = r.chtenie("Ev\\ishod1.txt");
        String[] words = mytext.split(" ");

        int z = 0;
        for (int i = 0; i < words.length; i = i + 8) {
            if (z == (words.length) / 8)
                break;
            sost.add(new Sostav(Integer.parseInt(words[i]), Integer.parseInt(words[i + 1]), Integer.parseInt(words[i + 2]), Integer.parseInt(words[i + 3]),
                    Integer.parseInt(words[i + 4]), words[i + 5], Integer.parseInt(words[i + 6]), words[i + 7]));
            z++;
        }

        myTableModel.setAct(sost);

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
                Sostav r = readFromStr(str);
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
                Sostav r = readFromStr(str);
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
                tableEvg2reader.write1("Ev\\ishod1.txt", sost);
            }
        });

        setVisible(true);
        setSize(700, 300);
        setLocationRelativeTo(null);

    }

    private Sostav readFromStr(String str) {
        String[] fields = str.split(" ");


        String polj = fields[5];
        String familiya = fields[7];
        try {
            int hex = Integer.parseInt(fields[0]);
            int tab = Integer.parseInt(fields[1]);
            int kod = Integer.parseInt(fields[2]);
            int raz = Integer.parseInt(fields[3]);
            int has = Integer.parseInt(fields[4]);
            int staj = Integer.parseInt(fields[6]);

            return new Sostav(hex, tab, kod, raz, has, polj, staj, familiya);
        } catch (Exception e) {
            return new Sostav(0, 0, 0, 0, 0, polj, 0, familiya);
        }
    }

    //создаем табличная модель
    class MyTableMode2 extends AbstractTableModel {

        java.util.List<Sostav> sost;

        public void setAct(java.util.List<Sostav> sost) {
            this.sost = sost;
        }

        public void removeAt(int i) {
            try {
                sost.remove(i);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        public void addItem(Sostav num) {
            try {
                sost.add(num);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        public String getItem(int ind) {
            return sost.get(ind).toString();
        }

        @Override
        public int getRowCount() {
            return sost != null ? sost.size() : 0;
        }

        @Override
        public int getColumnCount() {
            return 8;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return sost.get(rowIndex).gettceh();
                case 1:
                    return sost.get(rowIndex).gettabel();
                case 2:
                    return sost.get(rowIndex).getrazryad();
                case 3:
                    return sost.get(rowIndex).getkod();
                case 4:
                    return sost.get(rowIndex).getstavka();
                case 5:
                    return sost.get(rowIndex).getpolj();
                case 6:
                    return sost.get(rowIndex).getstaj();
                case 7:
                    return sost.get(rowIndex).getfamiliya();

            }
            throw new IllegalStateException("Неверно!");
        }
    }

    //создаем класс Состав рабочих
    class Sostav {
        int tceh;
        int tabel;
        int kod;
        int razryad;
        int stavka;
        String polj;
        int staj;
        String familiya;


        public Sostav(int tceh, int tabel, int kod, int razryad, int stavka, String polj, int staj, String familiya) {
            this.tceh = tceh;
            this.tabel = tabel;
            this.kod = kod;
            this.razryad = razryad;
            this.stavka = stavka;
            this.polj = polj;
            this.staj = staj;
            this.familiya = familiya;
        }

        public int gettceh() {
            return tceh;
        }

        public int gettabel() {
            return tabel;
        }

        public int getrazryad() {
            return razryad;
        }

        public int getkod() {
            return kod;
        }

        public int getstavka() {
            return stavka;
        }

        public String getpolj() {
            return polj;
        }

        public int getstaj() {
            return staj;
        }

        public String getfamiliya() {
            return familiya;
        }


        @Override
        public String toString() {
            return tceh + " " + tabel + " " + razryad + " " + kod + " " + stavka + " " + polj + " " + staj + " " + familiya;
        }
    }
}
