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

public class table1prof extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    ArrayList<Professii> prof;
    MyTableModel myTableModel;  //расположение таблицы

    //создаем тело программы
    public table1prof() throws HeadlessException{
        setContentPane(panel1);
        myTableModel = new MyTableModel();
        prof = new ArrayList<>(); //запрос на спецификацию

        chtenie r = new chtenie();
        String mytext = r.chtenie("Ev\\table1.txt");
        String[] words = mytext.split(" ");

        int z=0;
        for (int i = 0; i < words.length; i = i + 2) {
            if(z==(words.length)/2)
                break;
            prof.add(new Professii(Integer.parseInt(words[i]),words[i+1]));
            z++;
        }

        myTableModel.setprof(prof);

        table1.setModel(myTableModel);

        button1.addMouseListener(new MouseAdapter() {   //удалить
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(table1.getSelectedRow()>0)
                myTableModel.removeAt(table1.getSelectedRow());
                myTableModel.fireTableDataChanged();
            }
        });

        button2.addMouseListener(new MouseAdapter() {    //добавить
            @Override
            public void mouseClicked(MouseEvent e) {
                formAddings af = new formAddings();
                af.setVisible(true);
                af.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);

                        prof.clear();
                        chtenie r = new chtenie();
                        String mytext = r.chtenie("Ev\\table1.txt");
                        String[] words = mytext.split(" ");

                        int z=0;
                        for (int i = 0; i < words.length; i = i + 2) {
                            if(z==(words.length)/2)
                                break;
                            prof.add(new Professii(Integer.parseInt(words[i]),words[i+1]));
                            z++;
                        }

                        myTableModel.setprof(prof);
                        table1.setModel(myTableModel);
                        myTableModel.fireTableDataChanged();
                        invalidate();
                        validate();
                    }
                });
                af.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                af.setSize(300, 200);
                af.setLocationRelativeTo(null);

            }
        });

        button3.addMouseListener(new MouseAdapter() {   //изменить
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(table1.getSelectedRow()>0)
                myTableModel.removeAt(table1.getSelectedRow());
                String str = textField1.getText();
                Professii r = readFromStr(str);
                if(r!=null)
                myTableModel.addItem(r);
                myTableModel.fireTableDataChanged();
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(table1.getSelectedRow()>=0)
                textField1.setText(myTableModel.getItem(table1.getSelectedRow()));
            }
        });

        pack();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                tableEvg1reader.write("Ev\\table1.txt", prof);
            }
        });

        setVisible(true);
        setSize(600, 300);
        setLocationRelativeTo(null);

    }

    public Professii readFromStr(String str){     // данные выводящиеся в табл
        String[]fields=str.split(" ");    // str название строки, делит через запятые.
        if(fields.length>=2) {
            String name = fields[1];
            try {                                //для того чтобы понять где не правильно введены данные
                int kod = Integer.parseInt(fields[0]);     //parseInt для того чтобы он брал из строики эл пробовал преобразовать в числ и знат все файлы записы правильно
                return new Professii(kod, name);
            } catch (Exception e) {
                return new Professii(0, name);    // расположение компонентов(чтобы если не смешались строки, если будет пустые значения то там будут нули
            }
        }
        else return null;
    }

    //создаем табличную модель
    class MyTableModel extends AbstractTableModel{  //класс обеспечивающий работу в таком виде, какая она есть.

        java.util.List<Professii>prof;

        public void setprof(java.util.List<Professii> prof) {
            this.prof = prof;
        }

        public void removeAt(int i){   //кнопка удаления  i- это все одна строка
            try{
                prof.remove(i);
            }catch (Exception e){    // e - определяэется по то номер строки по нажатию
                System.out.println(e.getLocalizedMessage());
            }
        }

        public void addItem(Professii num){      //добавление
            try{
                prof.add(num);
            }catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }
        }

        public String  getItem(int ind){
            return  prof.get(ind).toString();
        }   //получить элемент  строки (ее номер e )

        @Override
        public int getRowCount() {
            return prof!=null ? prof.size() :0;
        }

        @Override
        public int getColumnCount() {
            return 2;   //сколько в файле наименований
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex){
                case 0:
                    return prof.get(rowIndex).getKod();
                case 1:
                    return prof.get(rowIndex).getName();


            }
            throw new IllegalStateException("Неверно!");
        }
    }
    //создаем класс профессий
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
