package com.one;

import java.awt.Checkbox;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CheckBoxItemEvenEx extends JFrame {
	
	private JCheckBox[] fruits=new JCheckBox[3];
	private String[] names= {"���","��","ü��"};
	
	private JLabel sumLabel;
	
	public CheckBoxItemEvenEx() {
		setTitle("CheckBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		
		//�����̳ʿ� Label�� �ٷκ���
		c.add(new JLabel("��� 100��, �� 500��, ü�� 20000��"));
		
		//������ ���
		MyItemListener listener=new MyItemListener();
		for (int i = 0; i < fruits.length; i++) {
			fruits[i]=new JCheckBox(names[i]);
			c.add(fruits[i]);
			fruits[i].setBorderPainted(true);
			fruits[i].addItemListener(listener);
		}
		sumLabel=new JLabel("���� 0�� �Դϴ�.");
		c.add(sumLabel);
		  
		setSize(250,200);
		setVisible(true);
	}
	class MyItemListener implements ItemListener{

		private int sum=0;
		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println("��ȭ����");
			
//			System.out.println(e.getStateChange());
//			System.out.println(e.getItem());
			if (e.getStateChange()==ItemEvent.SELECTED) {
				if (e.getItem()==fruits[0]) {
					sum+=100;
				}else if (e.getItem()==fruits[1]) {
					sum+=500;
				}
				else if (e.getItem()==fruits[2]) {
					sum+=20000;
			}
			}else if (e.getStateChange()==ItemEvent.DESELECTED) {
				if (e.getItem()==fruits[0]) {
					sum-=100;
				}else if (e.getItem()==fruits[1]) {
					sum-=500;
				}
				else if (e.getItem()==fruits[2]) {
					sum-=20000;
			}
			}
			sumLabel.setText("���� "+sum+"�� �Դϴ�.");
			
		}
		
	}
	
	
	public static void main(String[] args) {
		new CheckBoxItemEvenEx();
	}
}
