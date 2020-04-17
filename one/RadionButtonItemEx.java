package com.one;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class RadionButtonItemEx extends JFrame {
	private JRadioButton[]radio=new JRadioButton[3]; //���� ��ư �迭
	private String[]text= {"���","��","ü��"}; //���� ��ư�� ���ڿ�
	private ImageIcon[]image= { //�̹��� ��ü �迭
			new ImageIcon("img/apple.png"),
			new ImageIcon("img/pear.png"),
			new ImageIcon("img/cherry.png")};
	private JLabel imageLabel=new JLabel(); //�̹����� ��µ� ���̺�
	
	public RadionButtonItemEx() {
		setTitle("RItemEvent");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel radioPanel=new JPanel(); //3���� ���� ��ư�� ������ �г�
		radioPanel.setBackground(Color.GRAY);
		
		ButtonGroup g=new ButtonGroup(); //��ư �׷� ��ü ����
		for (int i = 0; i < radio.length; i++) { //3���� ���� ��ư�� ����
			radio[i]=new JRadioButton(text[i]); //���� ��ư ����
			g.add(radio[i]); //��ư �׷쿡 ����
			radioPanel.add(radio[i]); //�гο� ����
			radio[i].addItemListener(new MyItemListener2()); //���� ��ư�� Item ������ ���
		}
		radio[2].setSelected(true); //ü�� ���� ��ư�� ���� ���·� ����
		c.add(radioPanel, BorderLayout.NORTH); //����Ʈ���� �뽺�� ���� �г� ����
		c.add(imageLabel, BorderLayout.CENTER); //����Ʈ���� ���Ϳ� �̹��� ���̺� ����
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER); //�̹����� �߾� ����
		
		setSize(250,200);
		setVisible(true);
	}
	//������ ������ �ۼ�
	class MyItemListener2 implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange()==ItemEvent.DESELECTED) {
				return; //������ ��� �׳� ����('���'�� ���� ����)
			}
			if (radio[0].isSelected()) { //����� ���õ� ���
				imageLabel.setIcon(image[0]);
			}else if (radio[1].isSelected()) { //�谡 ���õ� ���
				imageLabel.setIcon(image[1]);
			}else { //ü���� ���õ� ���
				imageLabel.setIcon(image[2]);
			}
			
		}
		
	}
	public static void main(String[] args) {
		new RadionButtonItemEx();
	}
}
