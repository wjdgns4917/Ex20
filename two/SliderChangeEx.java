package com.two;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderChangeEx extends JFrame {
	private JLabel colorLabel;
	private JSlider[] sl=new JSlider[3]; //3���� �����̴� �迭 ����
	
	public SliderChangeEx() {
		setTitle("SliderChangeEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		
		colorLabel=new JLabel("        SLIDER EXAMPLE       ");
		
		//�����̴��� 3�� �����ϰ� ����� ����
		for (int i = 0; i < sl.length; i++) {
			//0~255������ ���������� �� �ִ� �����̴� ����. �ʱ갪�� 128
			sl[i]=new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
			sl[i].setPaintLabels(true);
			sl[i].setPaintTicks(true);
			sl[i].setPaintTrack(true);
			sl[i].setMajorTickSpacing(50);
			sl[i].setMinorTickSpacing(10);
			
			//�����̴��� changd������ ���
			sl[i].addChangeListener(new MyChangeListener());
			c.add(sl[i]); //�����̴� ����Ʈ�ҿ� ����			
		}
		
		sl[0].setForeground(Color.RED); 
		sl[1].setForeground(Color.GREEN); 
		sl[2].setForeground(Color.BLUE);
		
		//���� 3���� �����̴��� ���� ���� ��� �ʱ� colorLabel�� ���� ����
		int r=sl[0].getValue();
		int g=sl[1].getValue();
		int b=sl[2].getValue();
		colorLabel.setOpaque(true); //������ ��µǵ��� ������ ����
		colorLabel.setBackground(new Color(r,g,b)); //rgb������ ���ο� �� ����
		
		c.add(colorLabel);
		setSize(300,230);
		setVisible(true);
	}
	
	//change ������ ����
	class MyChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) { //�����̴��� ���� ������ �� ȣ��
			//3���� �����̴��κ��� ���� ��� colorLabel�� ���� ����
			System.out.println("r : "+sl[0].getValue());
			int r=sl[0].getValue();
			int g=sl[1].getValue();
			int b=sl[2].getValue();
			colorLabel.setBackground(new Color(r,g,b));
		}
		
	}
	public static void main(String[] args) {
		new SliderChangeEx();
	}
}
