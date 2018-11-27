package iwscream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class NotePad extends JFrame implements ActionListener {

    private String title = "这是一个记事本qwq";

    private String menu1 = "文件(F)";
    private String menu2 = "编辑(E)";
    private String menu3 = "格式(O)";
    private String menu4 = "查看(V)";
    private String menu5 = "帮助(H)";

    //menu1的Item的nameString
    private String menu1Item1 = "   新建(N)           Ctrl+N";
    private String menu1Item2 = "   打开(O)...        Ctrl+O";
    private String menu1Item3 = "   保存(S)           Ctrl+S";
    private String menu1Item4 = "   另存为(A)...";
    private String menu1Item5 = "   页面设置(U)...";
    private String menu1Item6 = "   打印(P)...        Ctrl+P";
    private String menu1Item7 = "   退出(X)";

    //menu2的Item的nameString
    private String menu2Item1 = "   撤销(U)           Ctrl+Z";
    private String menu2Item2 = "   剪切(T)           Ctrl+X";
    private String menu2item3 = "   复制(C)           Ctrl+C";
    private String menu2Item4 = "   粘贴(P)           Ctrl+V";
    private String menu2Item5 = "   删除(L)                Del";
    private String menu2Item6 = "   查找(F)...         Ctrl+F";
    private String menu2Item7 = "   查找下一个(N)     F3";
    private String menu2Item8 = "   替换(G)...        Ctrl+H";
    private String menu2Item9 = "   转到(R)...        Ctrl+G";
    private String menu2Item10 = "   全选(A)           Ctrl+A";
    private String menu2Item11 = "   时间/日期(D)         F5";

    //menu3的Item的nameString
    private String menu3Item1 = "   自动换行(W)    ";
    private String menu3Item2 = "   字体(F)...    ";

    //menu4的Item的nameString
    private String menu4Item = "    状态栏(S)          ";

    //menu5的Item的nameString
    private String menu5Item1 = "   查+看帮助(H)        ";
    private String menu5Item2 = "   关于记事本(A)      ";

    boolean close = false;

    private JTextArea jTextArea = new JTextArea();
    private JMenuBar jMenuBar = new JMenuBar();
    private JFileChooser jFileChooser = null;
    private FileReader fileReader = null;
    private FileWriter fileWriter = null;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;

    public static void main(String[] args) {
        NotePad notePad = new NotePad();
        notePad.init();
    }

    private void init(){


        JMenu jMenu1 = new JMenu(menu1);
        JMenu jMenu2 = new JMenu(menu2);
        JMenu jMenu3 = new JMenu(menu3);
        JMenu jMenu4 = new JMenu(menu4);
        JMenu jMenu5 = new JMenu(menu5);

        //添加menu1的item
        this.addJMenuItem(menu1Item1,jMenu1);
        this.addJMenuItem(menu1Item2,jMenu1);
        this.addJMenuItem(menu1Item3,jMenu1);
        this.addJMenuItem(menu1Item4,jMenu1);
        jMenu1.addSeparator();
        this.addJMenuItem(menu1Item5,jMenu1);
        this.addJMenuItem(menu1Item6,jMenu1);
        jMenu1.addSeparator();
        this.addJMenuItem(menu1Item7,jMenu1);

        //添加menu2的item
        this.addJMenuItem(menu2Item1,jMenu2);
        jMenu2.addSeparator();
        this.addJMenuItem(menu2Item2,jMenu2);
        this.addJMenuItem(menu2item3,jMenu2);
        this.addJMenuItem(menu2Item4,jMenu2);
        this.addJMenuItem(menu2Item5,jMenu2);
        jMenu2.addSeparator();
        this.addJMenuItem(menu2Item6,jMenu2);
        this.addJMenuItem(menu2Item7,jMenu2);
        this.addJMenuItem(menu2Item8,jMenu2);
        this.addJMenuItem(menu2Item9,jMenu2);
        jMenu2.addSeparator();
        this.addJMenuItem(menu2Item10,jMenu2);
        this.addJMenuItem(menu2Item11,jMenu2);

        //添加menu3的item
        this.addJMenuItem(menu3Item1,jMenu3);
        this.addJMenuItem(menu3Item2,jMenu3);

        //添加menu4的item
        this.addJMenuItem(menu4Item,jMenu4);

        //添加menu4的item
        this.addJMenuItem(menu5Item1,jMenu5);
        jMenu5.addSeparator();
        this.addJMenuItem(menu5Item2,jMenu5);

        jMenuBar.add(jMenu1);
        jMenuBar.add(jMenu2);
        jMenuBar.add(jMenu3);
        jMenuBar.add(jMenu4);
        jMenuBar.add(jMenu5);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                Object[] options ={ "是(Y)", "否(N)" };
                int result = JOptionPane.showOptionDialog(null, "是否将改动保存到" + "\""+title+"\"", title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if(result == JOptionPane.OK_OPTION){

                    JFileChooser jFileChooser1 = new JFileChooser();
                    jFileChooser1.setDialogTitle("另存为... ...");
                    jFileChooser1.showSaveDialog(null);
                    jFileChooser1.setVisible(true);

                    try {

                        fileWriter = new FileWriter(jFileChooser1.getSelectedFile().getAbsolutePath());
                        bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write(jTextArea.getText());

                        close = true;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    } finally{
                        try {
                            bufferedWriter.close();
                            fileWriter.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }

                    System.exit(0);
                }
            }
        });

        jTextArea.setBackground(Color.WHITE);

        this.add(jTextArea);
        this.setJMenuBar(jMenuBar);
        this.setTitle(title);
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        open(e);
        save(e);
        saveAs(e);

    }

    /**
     *  下面是一个封装了的添加JManuItem的方法。
     *
     * @param name 传入一个name作为该菜单项和交互事件的名字
     * @param menu 把该JMenuItem加到给定的menu中
     */

    private void addJMenuItem(String name, JMenu menu){

        JMenuItem jMenuItem = new JMenuItem(name);
        jMenuItem.addActionListener(this);
        jMenuItem.setActionCommand(name);
        menu.add(jMenuItem);
    }

    private void saveAs(ActionEvent e){

        if(e.getActionCommand().equals(menu1Item4)) {

            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("另存为... ...");
            jFileChooser.showSaveDialog(null);
            jFileChooser.setVisible(true);

            try {
                title = jFileChooser.getSelectedFile().getAbsolutePath();
                fileWriter = new FileWriter(jFileChooser.getSelectedFile().getAbsolutePath());
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(this.jTextArea.getText());

                close = true;
            } catch (Exception e2) {
                e2.printStackTrace();
            } finally{
                try {
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }

        }

    }

    private void save(ActionEvent e){

    }

    private void open(ActionEvent e){

        if(e.getActionCommand().equals(menu1Item2)) {
            jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("选择文件...");
            jFileChooser.showOpenDialog(null);
            jFileChooser.setVisible(true);

            String address = jFileChooser.getSelectedFile().getAbsolutePath();

            try {
                fileReader = new FileReader(address);
                this.setTitle(address);
                bufferedReader = new BufferedReader(fileReader);
                String str;
                String strAll = "";

                while((str = bufferedReader.readLine()) != null)
                {
                    strAll += str + "\r\n";
                }

                jTextArea.setText(strAll);
            } catch (Exception e2) {
                e2.printStackTrace();
            } finally{
                try {
                    bufferedReader.close();
                    fileReader.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }

            }
        }
    }
}
