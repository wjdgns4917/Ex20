package com.one;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamblingEx2 extends JFrame implements ActionListener{
	private JLabel[] labels;
	private JButton button;
	private int[] numbers;
	JLabel rLabel;
	
	//�̹��� �����
	private ImageIcon[] imgs;
	
	public GamblingEx2() {
		setTitle("Open Challenge 10");
		JPanel panel=new JPanel();
		
		panel.setLayout(null);
		
//		ImageIcon n0=new ImageIcon("src/Images/ĸ��0.png");
//		ImageIcon n1=new ImageIcon("src/Images/ĸ��1.png");
//		ImageIcon n2=new ImageIcon("src/Images/ĸ��2.png");
//		ImageIcon n3=new ImageIcon("src/Images/ĸ��3.png");
//		ImageIcon n4=new ImageIcon("src/Images/ĸ��4.png");
		
		labels=new JLabel[3];
		rLabel=new JLabel("����");
		numbers=new int[3];
		imgs=new ImageIcon[5];
		
		//�迭�� �̹��� �ֱ�
		for (int i = 0; i < imgs.length; i++) {
			imgs[i]=new ImageIcon("img/num"+i+".PNG");
		}
		
		//for������ label�� ����
		for (int i = 0; i < labels.length; i++) {
			labels[i]=new JLabel("0");
			labels[i].setFont(new Font("impact", Font.BOLD, 100));
			labels[i].setSize(100,100);
			labels[i].setLocation(100+100*i, 20);
			labels[i].setIcon(imgs[0]);
			panel.add(labels[i]);
		}
		rLabel.setSize(200, 100);
		rLabel.setLocation(150, 100);
		panel.add(rLabel);
		
		//������ư
		button=new JButton("�����");
		button.setSize(250,50);
		button.setLocation(100, 180);
		panel.add(button);
		button.addActionListener(this);
		
		add(panel);
		setSize(500,300);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("��ȣ");
		for (int i = 0; i < labels.length; i++) {
			numbers[i]=(int)(Math.random()*5);
//			labels[i].setText(""+numbers[i]);
			labels[i].setIcon(imgs[numbers[i]]);
		}
		
		boolean flag=(numbers[0]==numbers[1]&&numbers[0]==numbers[2]);
		if (flag) {
			rLabel.setText("����");
		}
		
		
	}
	public static void main(String[] args) {
		new GamblingEx2();
	}
}
