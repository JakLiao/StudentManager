package com.lhj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.lhj.login.Login;

public class SMFrame {

	private JDesktopPane desktopPane;
	private JFrame frame;
	private JLabel backLabel;
	// ���������Map���ͼ��϶���
	private Map<String, JInternalFrame> ifs = new HashMap<String, JInternalFrame>();
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;// ��ȡ�����Ļ�Ŀ�
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;// ��ȡ�����Ļ�ĸ�

	public SMFrame() {
		frame = new JFrame("ѧ���ɼ�����ϵͳ");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(width / 2 - 400, height / 2 - 300);// ���ô���λ��
		ImageIcon img = new ImageIcon("res/welcome.png");
		backLabel = new JLabel(img);
		backLabel.setVerticalAlignment(SwingConstants.TOP);
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backLabel.setIcon(img);
		backLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		desktopPane = new JDesktopPane();
		desktopPane.setOpaque(false);
		desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));
		frame.getContentPane().add(desktopPane);
		JTabbedPane navigationPanel = createNavigationPanel();
		frame.getContentPane().add(navigationPanel, BorderLayout.NORTH);
		frame.setVisible(true);
		frame.setResizable(false);// ���ڴ�С���ɸ���
	}

	/**
	 * �����������ķ���
	 */
	private JTabbedPane createNavigationPanel() {
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(false);
		tabbedPane.setBackground(new Color(211, 230, 192));
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));

		JPanel baseManagePanel = new JPanel();
		baseManagePanel.setBackground(new Color(215, 223, 194));
		baseManagePanel.setLayout(new BoxLayout(baseManagePanel,
				BoxLayout.X_AXIS));
		baseManagePanel.add(createFrameButton("ѧ����Ϣ����", "StuGuanli"));
		baseManagePanel.add(new JLabel("ѧ����Ϣ����"));
		baseManagePanel.add(createFrameButton("�༶��Ϣ����", "ClassGuanli"));
		baseManagePanel.add(new JLabel("�༶��Ϣ����"));
		baseManagePanel.add(createFrameButton("�γ���Ϣ����", "CourseGuanli"));
		baseManagePanel.add(new JLabel("�γ���Ϣ����"));
		baseManagePanel.add(createFrameButton("�ɼ���Ϣ����", "ScoreMenuGuanli"));
		baseManagePanel.add(new JLabel("�ɼ���Ϣ����"));

		JPanel searchSatisticPanel = new JPanel();
		searchSatisticPanel.setBounds(0, 0, 600, 41);
		searchSatisticPanel.setBackground(new Color(215, 223, 194));
		searchSatisticPanel.setName("searchSatisticPanel");
		searchSatisticPanel.setLayout(new BoxLayout(searchSatisticPanel,
				BoxLayout.X_AXIS));
		searchSatisticPanel.add(createFrameButton("ѧ����Ϣ��ѯ", "StuChaxun"));
		searchSatisticPanel.add(new JLabel("ѧ����Ϣ��ѯ"));
		searchSatisticPanel.add(createFrameButton("�༶��Ϣ��ѯ", "ClassChaxun"));
		searchSatisticPanel.add(new JLabel("�༶��Ϣ��ѯ"));
		searchSatisticPanel.add(createFrameButton("�γ���Ϣ��ѯ", "CourseChaxun"));
		searchSatisticPanel.add(new JLabel("�γ���Ϣ��ѯ"));

		JPanel scoreAnalysePanel = new JPanel();
		scoreAnalysePanel.setBounds(0, 0, 600, 41);
		scoreAnalysePanel.setBackground(new Color(215, 223, 194));
		scoreAnalysePanel.setName("scoreAnalysePanel");
		scoreAnalysePanel.setLayout(new BoxLayout(scoreAnalysePanel,
				BoxLayout.X_AXIS));
		scoreAnalysePanel.add(createFrameButton("�ɼ������ı�����", "ScoreAnalysis"));
		scoreAnalysePanel.add(new JLabel("�ɼ������ı�����"));
		scoreAnalysePanel.add(createFrameButton("�����η�����״ͼ", "BarGraph"));
		scoreAnalysePanel.add(new JLabel("�����η�����״ͼ"));
		scoreAnalysePanel.add(createFrameButton("�����η�����״ͼ", "PieGraph"));
		scoreAnalysePanel.add(new JLabel("�����η�����״ͼ"));
		
		tabbedPane.addTab("������Ϣ����", null, baseManagePanel, "������Ϣ����");
		tabbedPane.addTab("������Ϣ��ѯ", null, searchSatisticPanel, "������Ϣ��ѯ");
		tabbedPane.addTab("�ɼ�����", null, scoreAnalysePanel, "�ɼ�����");
		return tabbedPane;
	}

	/**
	 * Ϊ�ڲ��������Action�ķ���
	 * 
	 * @param fName
	 * @param cname
	 * @return
	 */
	private JButton createFrameButton(String fName, String cname) {
		String imgUrl = "res/ActionIcon/" + fName + ".png";
		String imgUrl_roll = "res/ActionIcon/" + fName + "_roll.png";
		String imgUrl_down = "res/ActionIcon/" + fName + "_down.png";
		Icon icon = new ImageIcon(imgUrl);
		Icon icon_roll = null;
		if (imgUrl_roll != null) {
			icon_roll = new ImageIcon(imgUrl_roll);
		}
		Icon icon_down = null;
		if (imgUrl_down != null) {
			icon_down = new ImageIcon(imgUrl_down);
		}
		Action action = new openFrameAction(fName, cname, icon);

		JButton button = new JButton(action);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setHideActionText(true);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		if (icon_roll != null) {
			button.setRolloverIcon(icon_roll);
		}
		if (icon_down != null) {
			button.setPressedIcon(icon_down);
		}
		return button;
	}

	/**
	 * ������˵���ĵ����¼�������
	 */
	protected final class openFrameAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private String frameName = null;

		public openFrameAction(String cname, String frameName, Icon icon) {
			this.frameName = frameName;
			putValue(Action.NAME, cname);
			putValue(Action.SHORT_DESCRIPTION, cname);
			putValue(Action.SMALL_ICON, icon);
		}

		public void actionPerformed(final ActionEvent e) {
			JInternalFrame jf = getIFrame(frameName);
			// ���ڲ�����չ�ʱ�����ڲ���������ifs����������ô��塣
			jf.addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameClosed(InternalFrameEvent e) {
					ifs.remove(frameName);
				}
			});
			if (jf.getDesktopPane() == null) {
				desktopPane.add(jf);
				jf.setVisible(true);
			}
			try {
				jf.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * �����ļ�
	 * 
	 * @param fileName
	 */
	public static void initDataFile(String fileName) {
		File file = new File(fileName);
		try {
			if (!file.exists())
				file.createNewFile();
		} catch (Exception e) {
		}
	}

	/**
	 * ��ȡ�ڲ�����Ψһʵ������
	 */
	private JInternalFrame getIFrame(String frameName) {
		JInternalFrame jf = null;
		if (!ifs.containsKey(frameName)) {
			try {
				Class fClass = Class.forName("internalFrame." + frameName);
				Constructor constructor = fClass.getConstructor(null);
				jf = (JInternalFrame) constructor.newInstance(null);
				ifs.put(frameName, jf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			jf = ifs.get(frameName);
		}
		return jf;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Login();
				//new SMFrame();
			}
		});
	}

	/**
	 * ʹ�ó�����񱾵�Ӧ�ó���
	 */
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
