import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    int clikes;
    public Window(){
        setTitle("802.3 以太网封装");
        setBounds(300,100,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel jpup = new JPanel();
        JTextField jtf1 = new JTextField(15);
        JTextField jtf2 = new JTextField(15);
        JLabel jl1 = new JLabel("Resource");
        JLabel jl2 = new JLabel("Destination");
        jpup.add(jl1);
        jpup.add(jtf1);
        jpup.add(jl2);
        jpup.add(jtf2);
        JButton jb = new JButton("输入");
        jpup.add(jb);
        Container container = getContentPane();
        container.add(jpup,BorderLayout.NORTH);

        JPanel dataPanel = new JPanel();
        JTextArea jtf3 = new JTextArea(10,30);
        JLabel jl3 = new JLabel("Data");
        jtf3.setLineWrap(true);
        jtf3.setWrapStyleWord(true);
        JScrollPane jsp1 = new JScrollPane(jtf3);
        dataPanel.add(jl3);
        dataPanel.add(jsp1);

        JPanel crcPanel = new JPanel();
        JTextField crcTextField = new JTextField(30);
        JLabel crcLabel =  new JLabel("crc");
        crcPanel.add(crcLabel);
        crcPanel.add(crcTextField);

        JPanel toge = new JPanel();
        toge.setLayout(new BorderLayout());
        toge.add(dataPanel,BorderLayout.NORTH);
        toge.add(crcPanel,BorderLayout.CENTER);

        add(toge,BorderLayout.CENTER);

        JPanel ansPanel = new JPanel();
        JTextArea ans = new JTextArea(10,30);
        JLabel ansLabel =  new JLabel("ans");
        ans.setLineWrap(true);
        ans.setWrapStyleWord(true);
        JScrollPane jsp = new JScrollPane(ans);
        ansPanel.add(ansLabel);
        ansPanel.add(jsp);

        add(ansPanel,BorderLayout.SOUTH);
        jb.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String resource = jtf1.getText();
                String des = jtf2.getText();
                String data = jtf3.getText();
                Frame frame = null;
                frame = Frame.process(resource,des,data,frame);
                String s = frame.getCrc();
                crcTextField.setText(s);
                String[] s1 = frame.getAns();
                for(String temp:s1){
                    ans.append(temp);
                    ans.append("\n\n");
                }
            }
        });
        setVisible(true);
    }
}
