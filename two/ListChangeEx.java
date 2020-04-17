package com.two;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ListChangeEx extends JFrame{
	private JTextField tf= new JTextField(10);
	private Vector<String>v=new Vector<String>();
	private JList<String> nameList=new JList<String>(v);
	
	public ListChangeEx() {
		setTitle("����Ʈ ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("�̸� �Է��� ����Ű"));
		c.add(tf);
		
		v.add("Ȳ����");
		v.add("���繮");
		nameList.setVisibleRowCount(5); //����Ʈ�� �����ִ� ���� ��
		nameList.setFixedCellWidth(100); //����Ʈ�� ��
		c.add(new JScrollPane(nameList));
		
		setSize(300,300);
		setVisible(true);
		
		//JTextField�� ActionListener ���. ����Ű ó��
		tf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField t=(JTextField)e.getSource();
				v.add(t.getText()); // ����v�� �Էµ� �̸� �߰�
				t.setText("");
				nameList.setListData(v); //���� v�� �Էµ� ��� �̸����� ����Ʈ ���� �����
				
			}
		});
	}
	public static void main(String[] args) {
		new ListChangeEx();
	}
}
