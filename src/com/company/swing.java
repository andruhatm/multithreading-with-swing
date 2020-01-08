package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;

class swing implements ActionListener {
    private JButton jb1;
    private JButton jb2;
    private JButton jb3;
    private JButton jb2_2;
    public JTextField tf;
    public JPanel tf_p;
    private JButton tf_b;
    private JButton tf_b_2;
    public static JPanel jPanel;
    public static JFrame jfrm;
    private JFileChooser fileChooser;
    private JFileChooser fj;
    public static JLabel jl1;
    public static JLabel jl2;
    public static JLabel jl3;
    public static JPanel j ;
    public static int s_count=3;
    public static int y_grid=3;
    static int smart,video,proc,gc,kb,nb,hd,sd,tb,ps,xb =0;
    int [] vivod= new int[11];
    ArrayList <thread> v = new ArrayList<thread>();
    static int []bd ={smart,video,proc,gc,kb,nb,hd,sd,tb,ps,xb};
    //"Smatphone","Video camera","Processor","Graphic Card","Keyboard","Notebook","HDD","SSD","Tablet","Playstation","Xbox"
    swing(){
        jfrm = new JFrame("Demonstration window");
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(600,350);

        Font f = new Font("Arial",Font.BOLD,22);

        GridBagLayout gb = new GridBagLayout();
        //jp.setLayout(gb);
        jfrm.setLayout(gb);
        jfrm.getContentPane().setBackground(Color.CYAN);

        jb1 = new JButton("Старт");
        jb2 =new JButton("Остановка");
        jb2_2 = new JButton("Возобновление");
        jb3 = new JButton("Завершение");
        tf_b = new JButton("Установить кол-во магазинов");
        tf_b_2 = new JButton("Ok");

        tf_p = new JPanel(gb);
        jPanel = new JPanel();
        swing.jPanel.setBackground(Color.CYAN);
        GridBagLayout q = new GridBagLayout();
        jPanel.setLayout(q);
        GridBagConstraints i = new GridBagConstraints();
        i.gridx = 0; // с какой ячейки строки начинаем
        i.gridy = swing.y_grid;
        i.gridheight = 1;//высота ячейки
        i.gridwidth = 4;// ширина ячейки
        i.fill = GridBagConstraints.CENTER;
        swing.jfrm.add(jPanel,i);

        GridBagConstraints q1 = new GridBagConstraints();
        j = new JPanel();
        q1.weighty = 0;
        q1.weightx = 0;
        q1.gridx = 0; // с какой ячейки строки начинаем
        q1.gridy = 3;
        q1.gridheight = 3;//высота ячейки
        q1.gridwidth = 4;// ширина ячейки
        jfrm.add(j,q1);
        j.setBackground(Color.CYAN);
        j.setLayout(gb);

        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb2_2.addActionListener(this);
        jb3.addActionListener(this);
        tf_b.addActionListener(this);
        tf_b_2.addActionListener(this);

        jl1 = new JLabel("00:00:00");
        jl2 = new JLabel("Choose number of shops");
        jl2.setFont(f);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10,5,5,5);
        g.gridx = 0; // с какой ячейки строки начинаем
        g.gridy = 0;
        g.gridheight = 1;//высота ячейки
        g.gridwidth = 4;// ширина ячейки

        tf_p.setBackground(Color.CYAN);
        g.fill = GridBagConstraints.CENTER;
        tf_p.setVisible(false);
        tf_p.add(jl2,g);
        g.gridy=1;
        g.gridwidth = 4;// ширина ячейки
        g.fill = GridBagConstraints.HORIZONTAL;
        tf = new JTextField("   ");
        tf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9'))) {
                    e.consume();  // игнорим введенные буквы и пробел
                }
            }
        });

        tf_p.add(tf,g);
        g.gridy=2;
        g.fill = GridBagConstraints.CENTER;
        tf_p.add(tf_b_2,g);
        jfrm.add(tf_p);

        GridBagConstraints c2 = new GridBagConstraints();

        c2.weighty = 0;
        c2.weightx = 0;
        c2.gridx = 0; // с какой ячейки строки начинаем
        c2.gridy = 0;
        c2.gridheight = 1;//высота ячейки
        c2.gridwidth = 4;// ширина ячейки
        Font с = new Font("Arial",Font.BOLD,50);
        jl1.setFont(с);
        jfrm.add(jl1,c2);

        c2.insets =new Insets(10,3,5,3);
        c2.weighty = 0;
        c2.weightx = 0;
        c2.gridx = 0; // с какой ячейки строки начинаем
        c2.gridy = 2;
        c2.gridheight = 1;//высота ячейки
        c2.gridwidth = 1;// ширина ячейки
        jfrm.add(jb1,c2);

        c2.gridx = 1;
        jb2.setEnabled(false); ///disabled
        jfrm.add(jb2,c2);
        jb2_2.setVisible(false);
        jfrm.add(jb2_2,c2);

        c2.gridx=2;
        jfrm.add(tf_b,c2);
        c2.gridx = 3;
        jfrm.add(jb3,c2);
        jfrm.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb1){
            //Запуск потока
            exit_r_all(v);
            int response = JOptionPane.showConfirmDialog(jfrm,"Upload data from file?","Before the start",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(response == JOptionPane.YES_OPTION){
                //Выбор файла
                fileChooser = new JFileChooser("C:/Users/Andrey/Downloads/");
                fileChooser.setDialogTitle("Choose a file");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new txt_filter());
                int result = fileChooser.showOpenDialog(jfrm);
                if(result==JFileChooser.CANCEL_OPTION){
                }
                if(result==JFileChooser.APPROVE_OPTION){
                    String path = fileChooser.getSelectedFile().getPath();
                    JOptionPane.showMessageDialog(jfrm,path);
                    int[] array = null;
                    try(BufferedReader fis = new BufferedReader(new FileReader(path))){
                        array = fis.lines().mapToInt(Integer::parseInt).toArray();
                        for(int i =0;i<array.length;i++){
                            swing.bd[i]=array[i];
                        }
                        tf_b.setVisible(false);
                        jb1.setEnabled(false);
                        start();
                    }
                    catch (IOException  | NumberFormatException ex) {
                        JOptionPane.showMessageDialog(jfrm,"Incorrect data file");
                        for(int i =0;i<bd.length;i++){
                            swing.bd[i]=0;
                        }
                        System.out.println(ex.getMessage());
                    }
                }
                j.setVisible(true);

                jb2.setEnabled(true);
            }
            if(response == JOptionPane.NO_OPTION){
                //Запуск потока
                tf_b.setVisible(false);
                j.setVisible(true);
                jb2.setEnabled(true);
                jb1.setEnabled(false);
                //time time = new time("current_time",10,0,0,0,0);
                start();
            }
        }

        if(e.getSource()==jb2){
            //Остановка
            String res = null;
            try{
                res = JOptionPane.showInputDialog(jfrm,"enter number or type 'all'");
                res=res.replaceAll(" ","");
                int a = Integer.parseInt(res);
                if(a-1<v.size()){
                    stp(a - 1);
                    jb2.setVisible(false);
                    jb2_2.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(jfrm,"Invalid data");
                }
            }
            catch (NumberFormatException | NullPointerException nfe){
                try {
                    if(res.equals("all")){
                        stp_all(v);
                        vivod();
                        j.setVisible(false);
                        jfrm.setSize(600,500);
                        jPanel.setVisible(true);
                        jb2.setVisible(false);
                        jb2_2.setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(jfrm,"Invalid data");
                    }
                }catch (NullPointerException n){
                    n.getCause();
                }
            }
            t_stp();
            System.out.println("state false");
        }
        if(e.getSource()==jb2_2){ //возобновление
            String res = null;
            try{
                res = JOptionPane.showInputDialog(jfrm,"enter number or type 'all'");
                res=res.replaceAll(" ","");
                int a = Integer.parseInt(res);
                if(a-1<v.size()){
                    run(a - 1);
                    jb2.setVisible(true);
                    jb2_2.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(jfrm,"Invalid data");
                }
            }
            catch (NumberFormatException | NullPointerException nfe){
                try {
                    if (res.equals("all")) {
                        run_all(v);
                        jb2.setVisible(true);
                        jb2_2.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(jfrm, "Invalid data");
                    }
                }
                catch (NullPointerException n){
                    n.getCause();
                }
            }
            t_stp_r();
            jfrm.setSize(600,350);
            jPanel.setVisible(false);
            j.setVisible(true);
            jPanel.removeAll();
        }
        if(e.getSource()==tf_b){
            jl1.setVisible(false);
            jb1.setVisible(false);
            jb2.setVisible(false);
            jb3.setVisible(false);
            tf_b.setVisible(false);
            tf_p.setVisible(true);
        }
        if(e.getSource()==tf_b_2){
            String str = tf.getText();
            str=str.replaceAll(" ","");
            System.out.println(str);
            s_count=Integer.parseInt(str);
            tf_p.setVisible(false);
            jl1.setVisible(true);
            jb1.setVisible(true);
            jb2.setVisible(true);
            jb3.setVisible(true);
            tf_b.setVisible(true);
            tf_b.setVisible(false);
        }
        if(e.getSource()==jb3){
            //Выход
            System.out.println(v.size());
            if(v.size() == 0){
                jfrm.dispose();
            }
            else{
                String res = null;
                try{
                    res = JOptionPane.showInputDialog(jfrm,"enter number or type 'all'");
                    res=res.replaceAll(" ","");
                    int a = Integer.parseInt(res);
                    if(a-1<v.size()){
                        exit(a - 1);
                        int response1 = JOptionPane.showConfirmDialog(jfrm,"Do you want to save current data?","Close and save",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(response1 == JOptionPane.YES_OPTION) {
                            saving(a-1);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(jfrm,"Invalid data");
                    }
                }
                catch (NumberFormatException | NullPointerException nfe){
                    if(res.equals("all")){
                        int response1 = JOptionPane.showConfirmDialog(jfrm,"Do you want to save current data?","Close and save",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(response1 == JOptionPane.YES_OPTION) {
                            for(int i=0;i<s_count;i++) {
                                if(!v.get(i).thrd.isInterrupted()){
                                    saving(i);
                                    exit(i);
                                }
                            }
                        }
                        exit_all(v);
                        j.setVisible(false);
                        tf_b.setVisible(true);
                        jb1.setEnabled(true);
                        jb2.setVisible(true);
                        jb2.setEnabled(false);
                        jfrm.setSize(600,350);
                        jb2_2.setVisible(false);
                        t_ext();
                    }
                    else {
                        JOptionPane.showMessageDialog(jfrm,"Invalid data");
                    }
                }
                for(int i=0;i<bd.length;i++){
                    bd[i]=0;
                }
                jPanel.removeAll();
                y_grid=3;
                for(int i=0;i<v.size();i++) {
                    if(v.get(i).thrd.isAlive()){
                        break;
                    }
                    else{
                        v.clear();
                    }
                }
            }
        }
    }
    public static int y_set(int a) {
        a = y_grid +a+2;
        return a;
    }
    public static void t_stp(){timer.state=false;}
    public static void t_stp_r(){timer.state=true;}
    public static void t_exit_r(){timer.exit=false;}
    public static void t_ext(){timer.exit=true;}
    public void stp(int a){ v.get(a).pause =false; }
    public void run(int a){ v.get(a).pause = true; }
    public void exit(int a){ v.get(a).exit=true; }
    public void exit_r(int a){ v.get(a).exit=false; }

    public void stp_all (ArrayList <thread>a){
        for(int i=0;i<a.size();i++){
            stp(i);
        }
    }
    public void run_all (ArrayList <thread> a){
        for(int i=0;i<a.size();i++){
            run(i);
        }
    }
    public void exit_all(ArrayList <thread>a){
        for(int i=0;i<a.size();i++){
            exit(i);
        }
    }
    public void exit_r_all(ArrayList <thread>a){
        for(int i=0;i<a.size();i++){
            exit_r(i);
        }
    }
    public void saving(int a){

        fj = new JFileChooser("C:/Users/Andrey/Downloads/");
        int result = fj.showSaveDialog(jfrm);
        if(result == JFileChooser.CANCEL_OPTION){
        }
        if(result == JFileChooser.APPROVE_OPTION){
            try(FileWriter fw = new FileWriter(fj.getSelectedFile()+v.get(a).thrd.getName()+".txt")){
                for(int i=0;i<v.get(a).temp.length;i++){
                    fw.write(v.get(a).temp[i]+"\n");
                }
            }
            catch (IOException ex)
            {
                ex.getMessage(); }
        }
    }
    public void save_one(int a){

    }
    public void start(){
        for(int i=0;i<s_count;i++){
            v.add(i, new thread("shop " + (i+1), 5,y_set(i)));
        }
        new timer("t",00,00,00,000);
    }
    public void vivod(){ // чтение из файла // сохранение //
        Font f = new Font("Arial",Font.BOLD,22);
        for(int i=0;i<swing.bd.length;i++){
            JLabel jl =new JLabel();
            jl.setFont(f);
            GridBagConstraints c = new GridBagConstraints();
            c.weighty = 0;
            c.weightx = 0;
            c.gridx = 0; // с какой ячейки строки начинаем
            c.gridy = swing.y_grid;
            c.gridheight = 1;//высота ячейки
            c.gridwidth = 4;// ширина ячейки
            c.fill = GridBagConstraints.CENTER;
            jl.setText(thread.veshi[i]+" was sold for "+swing.bd[i]+" times");
            vivod[i] = bd[i];
            System.out.println(bd[i]);
            swing.y_grid++;
            jPanel.add(jl,c);
        }
    }
}