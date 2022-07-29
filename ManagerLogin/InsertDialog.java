package ManagerLogin;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.co.greenart.dbutil.DBUtil;
//import Dialog.SuccessDialog;
 
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import userPlaylist.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dialog;
 
public class InsertDialog extends JDialog implements ActionListener{
    
    private JTextField tf1, tf2, tf3;
    private JLabel lb1, lb2, lb3, lb8, lb9;
    private JButton btn1, btn2, btn3;
    private JFrame jf;
    private FileDialog fd1;
    private InputStream fis, musicFis;
//    private String picName, musicName;
    private int picLength, musicLength;
    
    public InsertDialog() {
    	setModal(true);
        getContentPane().setLayout(null);
        
        lb1 = new JLabel("곡명");
        lb1.setBounds(34, 34, 62, 18);
        getContentPane().add(lb1);
                
        lb2 = new JLabel("아티스트");
        lb2.setBounds(34, 64, 62, 18);
        getContentPane().add(lb2);
                
        lb3 = new JLabel("장르");
        lb3.setBounds(34, 94, 62, 18);
        getContentPane().add(lb3);
        
        tf1 = new JTextField();
        tf1.setBounds(106, 31, 330, 24);
        getContentPane().add(tf1);
        tf1.setColumns(10);
        
        tf2 = new JTextField();
        tf2.setBounds(106, 61, 330, 24);
        getContentPane().add(tf2);
        tf2.setColumns(10);
        
        tf3 = new JTextField();
        tf3.setBounds(106, 91, 330, 24);
        getContentPane().add(tf3);
        tf3.setColumns(10);
        
        lb8 = new JLabel("음악");
        lb8.setBounds(34, 124, 62, 18);
        getContentPane().add(lb8);
        
        lb9 = new JLabel("");
        lb9.setBounds(106, 124, 330, 24);
        getContentPane().add(lb9);
        
        btn2 = new JButton("저장");
        btn2.setBounds(331, 154, 105, 39);
        btn2.addActionListener(this);
        getContentPane().add(btn2);
        
        btn3 = new JButton("파일찾기");
        btn3.setBounds(212, 154, 105, 39);
        btn3.addActionListener(this);
        getContentPane().add(btn3);
        setSize(491, 263);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //저장
        if(e.getSource() == btn2){
            String title = tf1.getText().toString();
            String artist = tf2.getText().toString();
            String genre = tf3.getText().toString();
            ManagerMusicsDaoImpl dao = new ManagerMusicsDaoImpl();
            if (title == null || artist == null || genre == null || musicFis == null) {
            	JOptionPane.showConfirmDialog(btn2, "값을 모두 입력해주세요.");
            } else {
	            Connection conn = null;
	            try {
	            	conn = DBUtil.getConnection();
	            	conn.setAutoCommit(false);
	            	dao.create(conn, title, artist, genre, musicFis, musicLength);
	            	conn.commit();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					DBUtil.closeConn(conn);
				}
	            
	            SuccessDialog sd = new SuccessDialog();
	            Dialog dl = new Dialog(sd);
	            
	            tf1.setText("");
	            tf2.setText("");
	            tf3.setText("");
	            lb9.setText("");
            }
        }
        //음악 넣기
        if(e.getSource() == btn3){
            try {
                jf = new JFrame();
                jf.setSize(300, 300);
                jf.setDefaultCloseOperation(jf.DISPOSE_ON_CLOSE);
                
                fd1 = new FileDialog(jf, "음악을 선택하시오.", FileDialog.LOAD);
                fd1.setVisible(true);
                String fileDir = fd1.getDirectory(); // 선택한 파일 경로가져옴
                String fileName = fd1.getFile(); // 파일이름,확장자
                
                File file = new File(fileDir + fileName);
                musicFis = new FileInputStream(file);
//              musicName = fileDir + fileName;
                musicLength = (int) file.length();
                
                lb9.setText(fileDir + "\\" + fileName);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
