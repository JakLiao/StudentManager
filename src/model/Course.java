package model;

import java.io.*;

import com.lhj.SMFrame;

public class Course implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public static final String COURSE_FILE = "courseFile.dat";
	private String id;
	private String name;
	private float credit;
	private int period;

	public Course() {
	}

	public Course(String id, String name, float credit, int period) {
		setId(id);
		setName(name);
		setCredit(credit);
		setPeriod(period);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public float getCredit() {
		return credit;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getPeriod() {
		return period;
	}

	public boolean equals(Object o) {
		return (((Course) o).getId().equals(this.id));
	}

	public boolean saveToFile() {
		SMFrame.initDataFile(COURSE_FILE);
		DataOutputStream output;
		DataInputStream input;
		String id, name;
		float credit;
		int period;
		try {
			input = new DataInputStream(new FileInputStream(COURSE_FILE));
			while (input.available() > 0) {
				id = input.readUTF();
				name = input.readUTF();
				credit = input.readFloat();
				period = input.readInt();
				if (id.equals(this.id)) {
					input.close();
					return false;
				}
			}
			input.close();
			output = new DataOutputStream(new FileOutputStream(COURSE_FILE,
					true));
			output.writeUTF(this.id);
			output.writeUTF(this.name);
			output.writeFloat(this.credit);
			output.writeInt(this.period);
			output.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Course queryFromFile(String courseId) {
		SMFrame.initDataFile(COURSE_FILE);
		DataInputStream input;
		String id, name;
		float credit;
		int period;
		try {
			input = new DataInputStream(new FileInputStream(COURSE_FILE));
			while (input.available() > 0) {
				id = input.readUTF();
				name = input.readUTF();
				credit = input.readFloat();
				period = input.readInt();
				if (id.equals(courseId)) {
					input.close();
					return new Course(id, name, credit, period);
				}
			}
			input.close();
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public static Course[] loadFile() {
		SMFrame.initDataFile(COURSE_FILE);
		int count = 0;
		Course[] course = new Course[20];
		DataInputStream input;
		try {
			input = new DataInputStream(new FileInputStream(COURSE_FILE));
			while (input.available() > 0) {
				if (count >= course.length) {
					Course[] temp = new Course[20];
					for (int i = 0; i < course.length; i++)
						temp[i] = course[i];
					course = temp;
				}
				course[count] = new Course();
				course[count].setId(input.readUTF());
				course[count].setName(input.readUTF());
				course[count].setCredit(input.readFloat());
				course[count].setPeriod(input.readInt());
				count++;
			}
			input.close();
			return course;
		} catch (Exception e) {
			return null;
		}
	}
}
