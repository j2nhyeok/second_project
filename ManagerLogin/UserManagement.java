package ManagerLogin;
//MenuJTabaleExam.java
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import ManagerLogin.Management;

import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;

import ManagerLogin.ManagerLogin;
import javax.swing.JLabel;


public class UserManagement extends JDialog implements ActionListener {
	private String fieldName;
	
	String[] name = { "user_id" , "user_pw" , "user_name" };
	
	DefaultTableCellRenderer dtable = new DefaultTableCellRenderer(); // 텍스트 가운데 정렬하기위한 생성자
	
	DefaultTableModel dt = new DefaultTableModel(name,0) { // 테이블 안의 내용 더블클릭 , 수정 불가하게
		@Override
		public boolean isCellEditable(int rowlndex, int mCollndex) {
			return false;
		}
	};
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	/*
	 * South 영역에 추가할 Componet들
	 */
	JPanel p = new JPanel();
	String[] comboName = {"ALL", "user_id" , "user_name"}; // 검색할 리스트
	// 하단 검색기능구현
	JComboBox combo = new JComboBox(comboName); // 검색할 항목들 배열에 선언된 리스트 콤보박스에넣음
	JTextField jtf = new JTextField(20);
	JButton serach = new JButton("검색"); // 검색버튼
	
	UserManagementJTableDAO dao = new UserManagementJTableDAO();
	/**
	 * 화면구성 및 이벤트등록
	 */
	public UserManagement(Management management) {
		super(management , true);
		setTitle("User Management");
		
		// South영역
		p.setBackground(Color.black);
		SpringLayout sl_p = new SpringLayout();
		sl_p.putConstraint(SpringLayout.WEST, serach, 6, SpringLayout.EAST, jtf);
		sl_p.putConstraint(SpringLayout.SOUTH, serach, 0, SpringLayout.SOUTH, combo);
		sl_p.putConstraint(SpringLayout.WEST, combo, 10, SpringLayout.WEST, p);
		sl_p.putConstraint(SpringLayout.EAST, combo, -6, SpringLayout.WEST, jtf);
		sl_p.putConstraint(SpringLayout.SOUTH, jtf, 0, SpringLayout.SOUTH, combo);
		sl_p.putConstraint(SpringLayout.NORTH, serach, 8, SpringLayout.NORTH, p);
		sl_p.putConstraint(SpringLayout.SOUTH, combo, -10, SpringLayout.SOUTH, p);
		sl_p.putConstraint(SpringLayout.WEST, jtf, 96, SpringLayout.WEST, p);
		sl_p.putConstraint(SpringLayout.EAST, jtf, -329, SpringLayout.EAST, p);
		sl_p.putConstraint(SpringLayout.NORTH, jtf, 8, SpringLayout.NORTH, p);
		sl_p.putConstraint(SpringLayout.NORTH, combo, 8, SpringLayout.NORTH, p);
		p.setLayout(sl_p);
		p.add(combo);
		p.add(jtf);
		p.add(serach);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, p, 0, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, jsp, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, jsp, 333, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, p, 333, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, jsp, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, jsp, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, p, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, p, 0, SpringLayout.EAST, getContentPane());
		getContentPane().setLayout(springLayout);

		getContentPane().add(jsp);
		
		getContentPane().add(p);
		
		JButton btnNewButton = new JButton("나가기");
		sl_p.putConstraint(SpringLayout.NORTH, btnNewButton, 7, SpringLayout.NORTH, p);
		sl_p.putConstraint(SpringLayout.WEST, btnNewButton, -85, SpringLayout.EAST, p);
		sl_p.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, p);
		sl_p.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, p);
		btnNewButton.addActionListener(new ActionListener() { // 돌아가기 버튼
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		p.add(btnNewButton);

		setSize(600, 410);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// 이벤트등록
		serach.addActionListener(this);

		// 모든레코드를 검색하여 DefaultTableModle에 올리기
		dao.userSelectAll(dt);
		
		//첫번행 선택.
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
		
		jt.getTableHeader().setReorderingAllowed(false); // 테이블 열,행 조정불가
		jt.getTableHeader().setResizingAllowed(false); // 테이블 열,행 조정불가
		
		dtable.setHorizontalAlignment(SwingConstants.CENTER); // 정렬을 가운데 정렬로 지정
		  
		TableColumnModel tcolum = jt.getColumnModel(); // 정렬할 테이블(jt) 를 TableColumnModel 에 가져옴
		
		for (int i = 0; i < tcolum.getColumnCount(); i++) { // 반복문을 이용하여 추가 데이터를 계속 가운데정렬
			tcolum.getColumn(i).setCellRenderer(dtable);
		}
	}// 생성자끝

	/**
	 * main메소드 작성
	 */

	/**
	 * 가입/수정/삭제/검색기능을 담당하는 메소드
	 * */

	public void actionPerformed(ActionEvent e) {  // 검색기능을 위한 
			if (e.getSource() == serach) {// 검색 버튼 클릭
			// JComboBox에 선택된 value 가져오기
			fieldName = combo.getSelectedItem().toString();
			System.out.println("필드명 " + fieldName);

			if (fieldName.trim().equals("ALL")) {// 전체검색
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				if (jtf.getText().trim().equals("")) {
					UserManagementJDailogGUI.messageBox(this, "검색단어를 입력해주세요!");
					jtf.requestFocus();
				} else {// 검색어를 입력했을경우
					dao.getUserSearch(dt, fieldName, jtf.getText());
					if (dt.getRowCount() > 0)
						jt.setRowSelectionInterval(0, 0);
				} // 얘 메소드에서 UserDefaultjTableDAO 클래스로 넘어가서 확인해보셈
			}
		}

	}//actionPerformed()----------
}
