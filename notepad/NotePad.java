package com.notepad;

import java.awt.*; //GUI ��ü�� ����ϱ� ���� ��Ű��
import java.awt.event.*; //�̺�Ʈ �߻��� ���� ��Ű��
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*; //swing������Ʈ�� ����ϱ� ���� ��Ű��   

import java.util.Calendar; //��¥ ����� ���� ��Ű��

public class NotePad extends JFrame implements ItemListener, ActionListener { // JFrame��ӹް� ItemListener,ActionListener
																				// �������̽� ���
	public static byte[] bytecounter; // ���ڼ� ����� ���� bytecounter ����

	private boolean isButton = false; // �۲�Ȯ�ι�ư����
	private String name = "����"; // �⺻ �۲�
	private int style = 0; // �⺻ ��Ʈ
	private int fontsize = 12; // �⺻ ��Ʈ ������

	// font ������Ʈ
	// ����-----------------------------------------------------------------------------
	JPanel fontPanel1, fontPanel2; // Panel ����
	Choice fontCombo1, fontCombo2, fontCombo3; // Choice ����
	JButton fontConfirm, fontCancel; // Button ����
	String[] fontList = { "����", "����ü", "�ü�", "�ü�ü", "�߰��", "���ü", "verdana" }; // fontCombo1�� �� ����Ʈ
	String[] fontStyleList = { "����", "�����", "����" }; // fontCombo2�� �� ����Ʈ
	String[] fontSizeList = { "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", // fontCombo3��
																											// �� ����Ʈ
			"30", "40", "60", "72" };
	JTextField txtFont, txtFontStyle, txtFontSize; // JTextField 3�� ����
	JFrame fontFrame; // �۲�â �����ӻ���
	
	JMenuBar menuBar=new JMenuBar();
	JMenu mnFile=new JMenu("����(F)");
	JMenu mnEdit=new JMenu("����(E)");
	JMenu mnForm=new JMenu("����(O)");
	JMenu mnHelp=new JMenu("����(H)");
	
	//���� �޴� ������
	JMenuItem new_m=new JMenuItem("���θ����(N)");
	JMenuItem open_m=new JMenuItem("����(O)");
	JMenuItem save_m=new JMenuItem("����(S)");
	JMenuItem exit_m=new JMenuItem("����(X)");
	//���� �޴� ������
	JMenuItem cut_m=new JMenuItem("�߶󳻱�(T)");
	JMenuItem copy_m=new JMenuItem("����(C)");
	JMenuItem paste_m=new JMenuItem("�ٿ��ֱ�(P)");
	JMenuItem allsel_m=new JMenuItem("��μ���(A)");
	JMenuItem del_m=new JMenuItem("����(L)");
	
	JMenuItem font_m=new JMenuItem("�۲�(F)");
	
	JMenuItem info_m=new JMenuItem("�޸��� ����(A)");
	JMenuItem time_m=new JMenuItem("�ð� ����");
	
	JFileChooser jfc=new JFileChooser();
	
	JTextArea txtArea=null;
	JScrollPane scrollPane=null;
	JTextField txtfield=new JTextField();
	
	
	public NotePad() {
		setTitle("�޸���-�������");
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
		
		//��Ʈ ����
		fontFrame=new JFrame("�۲�");
		fontPanel1=new JPanel();
		fontPanel2=new JPanel();
		
		txtFont=new JTextField(10);
		txtFontStyle=new JTextField(8);
		txtFontSize=new JTextField(5);
		fontConfirm=new JButton("Ȯ��");
		fontCancel=new JButton("���");
		
		fontCombo1=new Choice();
		fontCombo2=new Choice();
		fontCombo3=new Choice();
		
//		for�̿� combo���ڿ� fontList�߰�
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
		
		//font �ǳ�1�� ��,j�ؽ�Ʈ�ʵ�,���̽� �߰�
		fontPanel1.setLayout(new GridLayout(3,3));
		fontPanel1.add(new Label("�۲�"));
		fontPanel1.add(new Label("�۲� ��Ÿ��"));
		fontPanel1.add(new Label("�۲� ũ��"));
 		
		fontPanel1.add(txtFont);
		fontPanel1.add(txtFontStyle);
		fontPanel1.add(txtFontStyle);
		
		fontPanel1.add(fontCombo1);
		fontPanel1.add(fontCombo2);
		fontPanel1.add(fontCombo3);
		
		//font �ǳ�2�� Ȯ��/��ҹ�ư �߰�
		fontPanel2.setLayout(new GridLayout(3,1));
		fontPanel2.add(fontConfirm);
		fontPanel2.add(fontCancel);
		
		//��Ʈ �����ӿ� ��Ʈ �ǳ�1,2 ����
		fontFrame.add(fontPanel2,BorderLayout.EAST);
		fontFrame.add(fontPanel1,BorderLayout.CENTER);
		
		//������ ����
		fontConfirm.addActionListener(this);
		fontCancel.addActionListener(this);
		fontCombo1.addItemListener(this);
		fontCombo2.addItemListener(this);
		fontCombo3.addItemListener(this);
		
		fontFrame.setBounds(300, 300, 300, 100);
		
		//������ ���� ���� ���� ���ĸ޴�
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
		
		//time_m �͸�Ŭ������ ������ ���� 
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
				txtArea.setText(txtArea.getText()+year+"��"+month+"��"+day+"��");
				
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
				//����
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
		System.out.println("��ȣ:"+e.getActionCommand());
		String itemPressed=e.getActionCommand();
		String str="";
		int c;
		if (itemPressed.equals("����(O)")) {
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
		if (itemPressed.equals("����(S)")) {
//			jfc.showOpenDialog(this);
			jfc.showSaveDialog(this);
			File file=jfc.getSelectedFile();
			String path=file.getPath();
			System.out.println("���: "+path);
			
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
		if (itemPressed.equals("���θ����(N)")) {
			int r=JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?","Con Dialog",JOptionPane.YES_NO_OPTION);
			System.out.println("r : "+r);
			txtArea.setText(""); //�������
			if (r==0) {//yes�� Ŭ���ϸ�
				jfc.showSaveDialog(this);
				
			}
			
		}
		if (itemPressed.equals("����(C)")) {
			txtArea.copy();
		}
		if (itemPressed.equals("�ٿ��ֱ�(P)")) {
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
		System.out.println("��ȯ");
		if (e.getSource().equals(fontCombo1)) {
			name=fontCombo1.getSelectedItem();
			txtFont.setText(name);
		}
		
		if (e.getSource().equals(fontCombo2)) {
			String style1=fontCombo2.getSelectedItem();
			if (style1=="����") {
				style=0;
			}else if (style1=="�����") {
				style=Font.ITALIC;
			}else if (style1=="����") {
				style=Font.BOLD;
			}
		txtFontStyle.setText(style1);
		}
		if (!fontCombo3.getSelectedItem().equals("����� ����")) {
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