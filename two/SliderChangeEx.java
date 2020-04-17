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
	private JSlider[] sl=new JSlider[3]; //3개의 슬라이더 배열 생성
	
	public SliderChangeEx() {
		setTitle("SliderChangeEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		
		colorLabel=new JLabel("        SLIDER EXAMPLE       ");
		
		//슬라이더를 3개 생성하고 모양을 제어
		for (int i = 0; i < sl.length; i++) {
			//0~255사이의 값을선택할 수 있는 슬라이더 생성. 초깃값은 128
			sl[i]=new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
			sl[i].setPaintLabels(true);
			sl[i].setPaintTicks(true);
			sl[i].setPaintTrack(true);
			sl[i].setMajorTickSpacing(50);
			sl[i].setMinorTickSpacing(10);
			
			//슬라이더에 changd리스너 등록
			sl[i].addChangeListener(new MyChangeListener());
			c.add(sl[i]); //슬라이더 컨텐트팬에 삽입			
		}
		
		sl[0].setForeground(Color.RED); 
		sl[1].setForeground(Color.GREEN); 
		sl[2].setForeground(Color.BLUE);
		
		//현재 3개의 슬라이더로 부터 값을 얻어 초기 colorLabel의 배경색 설정
		int r=sl[0].getValue();
		int g=sl[1].getValue();
		int b=sl[2].getValue();
		colorLabel.setOpaque(true); //배경색이 출력되도록 불투명성 설정
		colorLabel.setBackground(new Color(r,g,b)); //rgb값으로 새로운 색 설정
		
		c.add(colorLabel);
		setSize(300,230);
		setVisible(true);
	}
	
	//change 리스너 구현
	class MyChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) { //슬라이더의 값이 변경할 때 호출
			//3개의 슬라이더로부터 값을 얻어 colorLabel의 배경색 변경
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
