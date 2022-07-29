package ManagerLogin; 
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
 
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MainFrame extends JFrame implements ActionListener{
    
    private JPanel northPanel;
    private JPanel centerPanel;
//    private TitlePanel tp;
//    private FirstPanel fp;
    private InsertDialog insert;
    private JButton insertAlbumBtn;
//    private ShowPanel sp;
        
    public MainFrame() {
        setTitle("메인화면");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        //Head
        northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setSize(100, 100);
        
        JButton showAlbumListBtn = new JButton("음악 목록 보기");
        northPanel.add(showAlbumListBtn);
        
        insertAlbumBtn = new JButton("음악 추가");
        northPanel.add(insertAlbumBtn);
        
        JButton updateAlbumBtn = new JButton("음악 수정");
        northPanel.add(updateAlbumBtn);
        
        JButton deleteAlbumBtn = new JButton("음악 삭제");
        northPanel.add(deleteAlbumBtn);
        
        //Head - 메인화면으로
//        tp = new TitlePanel();
//        tp.addMouseListener(this);
//        northPanel.add(tp);
//        
//        //Head - 음악 목록 보기
//        cp = new CategoriPanel();
//        cp.getShowAlbumListBtn().addActionListener(this);
        
        //Head - 음악 추가
        insertAlbumBtn.addActionListener(this);
        
        //Head - 음악 수정
        updateAlbumBtn.addActionListener(this);
        
        //Head - 음악 삭제
        deleteAlbumBtn.addActionListener(this);
        
        
        //Center
        centerPanel = new JPanel();
            
//        //Center - 첫패널
//        fp = new FirstPanel();
//        fp.setVisible(true);
//        centerPanel.add(fp);
        
        //Center - 음악 목록 보기 패널
//        sp = new ShowPanel();
//        sp.setVisible(false);
//        centerPanel.add(sp);
        
        //Center - 음악 추가 패널
        insert = new InsertDialog();
        insert.setVisible(false);
        centerPanel.add(insert);
        
        //Center - scrollpane
        JScrollPane scroll = new JScrollPane(centerPanel);
        //scroll.setSize(1900, 700);
        //scroll.setLocation(0, 50);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setViewportView(centerPanel);
        
        add(scroll, BorderLayout.CENTER);
                
        //add(centerPanel, BorderLayout.CENTER);
        
        
        setVisible(false);
    }
 
    //Head btn events
    @Override
    public void actionPerformed(ActionEvent e) {
        //음악 목록 보기 버튼을 눌렸을 때
//        if(e.getSource() == cp.getShowAlbumListBtn()){
//            sp.showMethod();
//            //sp.repaint();
//            fp.setVisible(false);
//            ip.setVisible(false);
//            sp.setVisible(true);
//        }
        
        //음악 삽입 버튼을 눌렸을 때
            if(e.getSource() == insertAlbumBtn){
//                fp.setVisible(false);
//                sp.setVisible(false);
                insert.setVisible(true);
            }
            
        //음악 수정 버튼을 눌렸을 때
//        if(e.getSource()==cp.getUpdateAlbumBtn()){
//            //음악 수정은 음악 목록 보기에서 체크 되어 있을 때만 가능
//            //음악 목록 보기가 활성화 되어 있는지 확인
//            if(sp.isVisible()){
//                if(sp.getChk()==1){ // 음악이 1개만 선택 되어 있다면?
//                    UpdateFrame uf = new UpdateFrame();                    
//                    fp.setVisible(false);
//                    ip.setVisible(false);
//                    sp.setVisible(false);
//                }else{ // 음악이 선택되지 않았거나 여러개 선택되었다면?
//                    new Dialog(new OneChkPlzDialog());
//                    fp.setVisible(false);
//                    ip.setVisible(false);
//                    sp.setVisible(true);
//                }
//            }else{ //음악 목록 보기가 활성화 되어 있지 않다면?
//                new Dialog(new ListPlzDialog());
//                fp.setVisible(false);
//                ip.setVisible(false);
//                sp.setVisible(true);
//            }
//            
//        }
//        //삭제 버튼을 눌렸을 때
//        if(e.getSource() == cp.getDeleteAlbumBtn()){
//            //음악 삭제도 음악 목록 보기에서 체크 되어 있을 때만 가능
//            //음악 목록 보기가 활성화 되어 있는지 확인
//            if(sp.isVisible()){
//                if(sp.getChk()==0){ // 음악이 선택 되어 있지않다면?
//                    new Dialog(new OneChkPlzDialog2());
//                    fp.setVisible(false);
//                    ip.setVisible(false);
//                    sp.setVisible(true);
//                }else{ 
//                    DeleteConfirmDialog dcd = new DeleteConfirmDialog();
//                    fp.setVisible(false);
//                    ip.setVisible(false);
//                    sp.setVisible(true);
//                }                
//            }else{ //음악 목록 보기가 활성화 되어 있지 않다면?
//                new Dialog(new ListPlzDialog());
//                fp.setVisible(false);
//                ip.setVisible(false);
//                sp.setVisible(true);
//            }
//        }
//        //음악 듣기 버튼을 눌렸을 때
//        if(e.getSource() == cp.getStartMusicBtn()){
//            //음악 듣기도 음악 목록 보기에서 체크되어 있을 때만 가능
//            //음악 목록 보기가 활성화 되어 있는지 확인
//            if(sp.isVisible()){
//                if(sp.getChk()==1){ // 음악이 1개만 선택 되어 있다면?
//                    MusicFrame mf = new MusicFrame();            
//                    fp.setVisible(false);
//                    ip.setVisible(false);
//                    sp.setVisible(true);
//                }else{ // 음악이 선택되지 않았거나 여러개 선택되었다면?
//                    new Dialog(new OneChkPlzDialog());
//                    fp.setVisible(false);
//                    ip.setVisible(false);
//                    sp.setVisible(true);
//                }
//            }else{ //음악 목록 보기가 활성화 되어 있지 않다면?
//                new Dialog(new ListPlzDialog2());
//                fp.setVisible(false);
//                ip.setVisible(false);
//                sp.setVisible(true);
//            }
//        }
    }
    
    public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
