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
	private String[] names= {"사과","배","체리"};
	
	private JLabel sumLabel;
	
	public CheckBoxItemEvenEx() {
		setTitle("CheckBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		
		//컨테이너에 Label을 바로부착
		c.add(new JLabel("사과 100원, 배 500원, 체리 20000원"));
		
		//리스너 등록
		MyItemListener listener=new MyItemListener();
		for (int i = 0; i < fruits.length; i++) {
			fruits[i]=new JCheckBox(names[i]);
			c.add(fruits[i]);
			fruits[i].setBorderPainted(true);
			fruits[i].addItemListener(listener);
		}
		sumLabel=new JLabel("현재 0원 입니다.");
		c.add(sumLabel);
		  
		setSize(250,200);
		setVisible(true);
	}
	class MyItemListener implements ItemListener{

		private int sum=0;
		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println("변화있음");
			
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
			sumLabel.setText("현재 "+sum+"원 입니다.");
			
		}
		
	}
	
	
	public static void main(String[] args) {
		new CheckBoxItemEvenEx();
	}
}
