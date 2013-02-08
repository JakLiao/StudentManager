package internalFrame;

import javax.swing.*;

import model.ScoreMenu;

import java.awt.*;

class BarGraphlAnasysJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private ScoreMenu scoreMenu;

	public BarGraphlAnasysJFrame(ScoreMenu scoreMenu) {
		this.scoreMenu = scoreMenu;
		this.setTitle("成绩单柱状图");
		JPanel left = new JPanel(new GridLayout(5, 1));
		int number = scoreMenu.getNumberOfAttendTestStudent();
		int i;
		for (i = 0; i < 5; i++) {
			left.add(new AsistantPanel(
					(int) Math.round(number * (1 - i / 5.0)), true));
		}
		this.add(left, BorderLayout.WEST);
		JPanel down = new JPanel(new GridLayout(1, 11));
		String[] score = { "<60分", "60-69分", "70-79分", "80-89分", ">=90分" };
		int j = 0;
		down.add(new JLabel(" 0"));
		for (i = 0; i < 10; i++) {
			if (i % 2 == 0)
				down.add(new JLabel(score[j++]));
			else
				down.add(new JLabel(""));
		}
		this.add(down, BorderLayout.SOUTH);
		this.add(new ColumnTypeJPanel());
	}

	class ColumnTypeJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		public ColumnTypeJPanel() {
			setBackground(Color.LIGHT_GRAY);
			repaint();
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int height = this.getHeight();
			int width = this.getWidth();
			int number = scoreMenu.getNumberOfAttendTestStudent();
			int[] score = scoreMenu.getAnasisOfScore();
			int interval = width / 10, y, i;
			g.setColor(Color.BLACK);
			for (i = 1; i < 5; i++) {
				y = i * height / 5;
				g.drawLine(0, y, width, y);
			}
			g.setColor(Color.BLUE);
			for (i = 0; i < 5; i++) {
				y = (int) (height * score[i] / (number * 1.0));
				g.fillRect((int) (interval * (0.5 + 2 * i)), height - y,
						interval, y);
			}
		}
	}

	class AsistantPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		AsistantPanel(int num, boolean upper) {
			setLayout(new GridLayout(4, 1));
			if (upper) {
				add(new JLabel(" " + String.valueOf(num)));
				for (int i = 0; i < 3; i++)
					add(new JLabel(""));
			} else {
				for (int i = 0; i < 3; i++)
					add(new JLabel(""));
				add(new JLabel(" " + String.valueOf(num)));
			}
		}
	}
}
