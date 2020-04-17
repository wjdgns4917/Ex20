package com.two;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ComboActionEx extends JFrame{
	private String[] fruits= {"apple", "pear", "cherry"}; //�޺��ڽ� ������
	
	private ImageIcon[] images= { //�̹��� ��ü �迭
			new ImageIcon("img/apple.png"),
			new ImageIcon("img/pear.png"),
			new ImageIcon("img/cherry.png")};
	private JLabel imgLabel=new JLabel(images[0]); //�̹��� ���̺� ������Ʈ
	private JComboBox<String> strCombo=new JComboBox<String>(fruits); // ���ڿ� �޺��ڽ�
	
	public ComboActionEx() {
		setTitle("ComboAction");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		c.add(strCombo);
		c.add(imgLabel);
		
		//Action ������ ���
		strCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String>cb=(JComboBox<String>)e.getSource();
				//Action �̺�Ʈ�� �߻��� �޺��ڽ� �˾Ƴ���
				int index=cb.getSelectedIndex(); //�޺��ڽ��� ���õ� �������� �ε��� ��ȣ �˾Ƴ���
				imgLabel.setIcon(images[index]); //�ε����� �̹����� �̹��� ���̺� ������Ʈ�� ���
				
			}
		});
		
		setSize(300,250);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ComboActionEx();
	}
}
