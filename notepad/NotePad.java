package com.notepad;

import java.awt.*; //GUI 객체를 사용하기 위한 패키지
import java.awt.event.*; //이벤트 발생을 위한 패키지
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*; //swing컴포넌트를 사용하기 위한 패키지   

import java.util.Calendar; //날짜 계산을 위한 패키지

public class NotePad extends JFrame implements ItemListener, ActionListener { // JFrame상속받고 ItemListener,ActionListener
																				// 인터페이스 상속
	public static byte[] bytecounter; // 글자수 계산을 위한 bytecounter 선언

	private boolean isButton = false; // 글꼴확인버튼여부
	private String name = "굴림"; // 기본 글꼴
	private int style = 0; // 기본 폰트
	private int fontsize = 12; // 기본 폰트 사이즈

	// font 컴포넌트
	// 생성-----------------------------------------------------------------------------
	JPanel fontPanel1, fontPanel2; // Panel 생성
	Choice fontCombo1, fontCombo2, fontCombo3; // Choice 생성
	JButton fontConfirm, fontCancel; // Button 생성
	String[] fontList = { "굴림", "굴림체", "궁서", "궁서체", "견고딕", "고딕체", "verdana" }; // fontCombo1에 들어갈 리스트
	String[] fontStyleList = { "보통", "기울임", "굵게" }; // fontCombo2에 들어갈 리스트
	String[] fontSizeList = { "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", // fontCombo3에
																											// 들어갈 리스트
			"30", "40", "60", "72" };
	JTextField txtFont, txtFontStyle, txtFontSize; // JTextField 3개 생성
	JFrame fontFrame; // 글꼴창 프레임생성
	
	JMenuBar menuBar=new JMenuBar();
	JMenu mnFile=new JMenu("파일(F)");
	JMenu mnEdit=new JMenu("편집(E)");
	JMenu mnForm=new JMenu("서식(O)");
	JMenu mnHelp=new JMenu("도움말(H)");
	
	//파일 메뉴 아이템
	JMenuItem new_m=new JMenuItem("새로만들기(N)");
	JMenuItem open_m=new JMenuItem("열기(O)");
	JMenuItem save_m=new JMenuItem("저장(S)");
	JMenuItem exit_m=new JMenuItem("종료(X)");
	//편집 메뉴 아이템
	JMenuItem cut_m=new JMenuItem("잘라내기(T)");
	JMenuItem copy_m=new JMenuItem("복사(C)");
	JMenuItem paste_m=new JMenuItem("붙여넣기(P)");
	JMenuItem allsel_m=new JMenuItem("모두선택(A)");
	JMenuItem del_m=new JMenuItem("삭제(L)");
	
	JMenuItem font_m=new JMenuItem("글꼴(F)");
	
	JMenuItem info_m=new JMenuItem("메모장 보기(A)");
	JMenuItem time_m=new JMenuItem("시간 보기");
	
	JFileChooser jfc=new JFileChooser();
	
	JTextArea txtArea=null;
	JScrollPane scrollPane=null;
	JTextField txtfield=new JTextField();
	
	
	public NotePad() {
		setTitle("메모장-제목없음");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(menuBar,"North");
		menuBar.add(mnFile);
		menuBar.add(mnEdit);
		menuBar.add(mnForm);
		menuBar.add(mnHelp);
		
		mnFile.add(new_m);
		mnFile.add(open_m);
		mnFile.add(save_m);
		mnFile.addSeparator();
		mnFile.add(exit_m);
		
		mnEdit.add(cut_m);
		mnEdit.add(copy_m);
		mnEdit.add(paste_m);
		mnEdit.add(del_m);
		mnEdit.addSeparator();
		mnEdit.add(allsel_m);
		
		mnForm.add(font_m);
		
		mnHelp.add(info_m);
		mnHelp.add(time_m);
		
		scrollPane=new JScrollPane(txtArea=new JTextArea());
		
		add(scrollPane,"Center");
		add(txtfield,"South");
		
		//폰트 구성
		fontFrame=new JFrame("글꼴");
		fontPanel1=new JPanel();
		fontPanel2=new JPanel();
		
		txtFont=new JTextField(10);
		txtFontStyle=new JTextField(8);
		txtFontSize=new JTextField(5);
		fontConfirm=new JButton("확인");
		fontCancel=new JButton("취소");
		
		fontCombo1=new Choice();
		fontCombo2=new Choice();
		fontCombo3=new Choice();
		
//		for이용 combo상자에 fontList추가
		for (int i = 0; i < fontList.length; i++) {
			fontCombo1.add(fontList[i]);
		}
		for (int i = 0; i < fontStyleList.length; i++) {
			fontCombo2.add(fontStyleList[i]);
		}
		for (int i = 0; i < fontSizeList.length; i++) {
			fontCombo3.add(fontSizeList[i]);
		}	
		fontCombo1.select(0);
		fontCombo2.select(0);
		fontCombo3.select(4);
		
		//font 판넬1에 라벨,j텍스트필드,초이스 추가
		fontPanel1.setLayout(new GridLayout(3,3));
		fontPanel1.add(new Label("글꼴"));
		fontPanel1.add(new Label("글꼴 스타일"));
		fontPanel1.add(new Label("글꼴 크기"));
 		
		fontPanel1.add(txtFont);
		fontPanel1.add(txtFontStyle);
		fontPanel1.add(txtFontStyle);
		
		fontPanel1.add(fontCombo1);
		fontPanel1.add(fontCombo2);
		fontPanel1.add(fontCombo3);
		
		//font 판넬2에 확인/취소버튼 추가
		fontPanel2.setLayout(new GridLayout(3,1));
		fontPanel2.add(fontConfirm);
		fontPanel2.add(fontCancel);
		
		//폰트 프레임에 폰트 판넬1,2 부착
		fontFrame.add(fontPanel2,BorderLayout.EAST);
		fontFrame.add(fontPanel1,BorderLayout.CENTER);
		
		//수신자 부착
		fontConfirm.addActionListener(this);
		fontCancel.addActionListener(this);
		fontCombo1.addItemListener(this);
		fontCombo2.addItemListener(this);
		fontCombo3.addItemListener(this);
		
		fontFrame.setBounds(300, 300, 300, 100);
		
		//수신자 부착 파일 편집 서식메뉴
		new_m.addActionListener(this);
		save_m.addActionListener(this);
		exit_m.addActionListener(this);
		open_m.addActionListener(this);
		open_m.setAccelerator(KeyStroke.getKeyStroke('O',Event.CTRL_MASK));
		
		cut_m.addActionListener(this);
		copy_m.addActionListener(this);
		paste_m.addActionListener(this);
		allsel_m.addActionListener(this);
		del_m.addActionListener(this);
		
		font_m.addActionListener(this);
		
		//time_m 익명클래스로 수신자 부착 
		time_m.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar cal=Calendar.getInstance();
				int year=cal.get(Calendar.YEAR);
				int month=cal.get(Calendar.MONTH);
				int day=cal.get(Calendar.DAY_OF_MONTH);
				int hour=cal.get(Calendar.HOUR);
				int min=cal.get(Calendar.MINUTE);
				int sec=cal.get(Calendar.SECOND);
				txtArea.setText(txtArea.getText()+year+"년"+month+"월"+day+"일");
				
			}
		});
		
		txtArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String text;
				int legth;
				text=txtArea.getText();
				bytecounter=text.getBytes();
				legth=bytecounter.length;
				txtfield.setText(legth+"bytes");
				//정렬
				txtfield.setHorizontalAlignment(JTextField.RIGHT);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		setSize(800, 500);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("신호:"+e.getActionCommand());
		String itemPressed=e.getActionCommand();
		String str="";
		int c;
		if (itemPressed.equals("열기(O)")) {
			jfc.showOpenDialog(this);
			File file=jfc.getSelectedFile();
			String path=file.getPath();
			
			try {
				BufferedReader in=new BufferedReader(
						new FileReader(path));
				while((c=in.read())>=0) {
					str+=(char)c;
				}
				txtArea.setText(str);
				in.close();
				
			}catch (Exception e1) {
				
				
			}
		
		}
		if (itemPressed.equals("저장(S)")) {
//			jfc.showOpenDialog(this);
			jfc.showSaveDialog(this);
			File file=jfc.getSelectedFile();
			String path=file.getPath();
			System.out.println("경로: "+path);
			
			try {
				BufferedWriter out=new BufferedWriter(
						new FileWriter(path));
				out.write(txtArea.getText());
				out.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if (itemPressed.equals("새로만들기(N)")) {
			int r=JOptionPane.showConfirmDialog(this, "저장하시겠습니까?","Con Dialog",JOptionPane.YES_NO_OPTION);
			System.out.println("r : "+r);
			txtArea.setText(""); //내용삭제
			if (r==0) {//yes를 클릭하면
				jfc.showSaveDialog(this);
				
			}
			
		}
		if (itemPressed.equals("복사(C)")) {
			txtArea.copy();
		}
		if (itemPressed.equals("붙여넣기(P)")) {
			txtArea.paste();
		}
		
		
		
		if (e.getSource().equals(font_m)) {
			fontFrame.setVisible(true);
		}
		
		if (e.getSource().equals(fontConfirm)) {
			isButton=true;
			fontFrame.setVisible(false);
			txtArea.setFont(new Font(name, style, fontsize));
		}	
		else if (e.getSource().equals(fontCancel)) {
			isButton=false;
			fontFrame.setVisible(false);
		}
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("전환");
		if (e.getSource().equals(fontCombo1)) {
			name=fontCombo1.getSelectedItem();
			txtFont.setText(name);
		}
		
		if (e.getSource().equals(fontCombo2)) {
			String style1=fontCombo2.getSelectedItem();
			if (style1=="보통") {
				style=0;
			}else if (style1=="기울임") {
				style=Font.ITALIC;
			}else if (style1=="굵게") {
				style=Font.BOLD;
			}
		txtFontStyle.setText(style1);
		}
		if (!fontCombo3.getSelectedItem().equals("사용자 정의")) {
			fontsize=Integer.parseInt(fontCombo3.getSelectedItem());
			txtFontSize.setText(fontCombo3.getSelectedItem());
		}
		if (isButton) {
			txtArea.setFont(new Font(name, style, fontsize));
		}
		
	}
	
	public static void main(String[] args) {
		new NotePad();
	}// main

}// class