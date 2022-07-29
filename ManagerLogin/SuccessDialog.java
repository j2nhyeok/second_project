package ManagerLogin;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
 
public class SuccessDialog extends JFrame implements ActionListener {
 
    public SuccessDialog() {
        setSize(500, 300);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize(); 
        setLocation(screenSize.width /2 - 250 ,screenSize.height/2 - 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel lbl = new JLabel("데이터베이스에 저장되었습니다.");
        lbl.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        getContentPane().add(lbl, BorderLayout.CENTER);
        
        JButton btn = new JButton("확인");
        btn.addActionListener(this);
        getContentPane().add(btn, BorderLayout.SOUTH);
        
        setVisible(true);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
