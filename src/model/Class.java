package model;

import java.io.*;

import com.lhj.SMFrame;

public class Class implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_FILE = "classFile.dat";
	private int grade;
	private String major;
	private int classNumber;
	private int studentNumber = 0;
	private Student students[];

	public Class() {
		setGrade(2010);
		setMajor("Èí¼þ¹¤³Ì");
		setClassNumber(3);
		setStudentNumber(0);
		students = new Student[20];
	}

	public Class(int grade, String major, int classNumber, int studentNumber,
			Student students[]) {
		setGrade(grade);
		setMajor(major);
		setClassNumber(classNumber);
		setStudentNumber(0);
		this.students = new Student[20];
		int i;
		for (i = 0; i < studentNumber; i++)
			addStudent(students[i]);
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getGrade() {
		return grade;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}

	public int getClassNumber() {
		return classNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public int getStudentNumber() {
		return studentNumber;
	}

	public Student[] getStudents() {
		return students;
	}

	public Student findStudent(Student student) {
		int i;
		for (i = 0; i < studentNumber; i++) {
			if (students[i].equals(student))
				return students[i];
		}
		return null;
	}

	public boolean addStudent(Student newStudent) {
		if (findStudent(newStudent) != null)
			return false;
		if (studentNumber >= students.length) {
			int i;
			Student[] temp = new Student[2 * students.length + 1];
			for (i = 0; i < students.length; i++)
				temp[i] = students[i];
			students = temp;
		}
		students[studentNumber++] = newStudent;
		return true;
	}

	public boolean removeStudent(Student student) {
		int i;
		for (i = 0; i < studentNumber; i++) {
			if (!students[i].equals(student))
				continue;
			if (i < studentNumber) {
				studentNumber--;
				for (; i < studentNumber; i++)
					students[i] = students[i + 1];
				return true;
			}
		}
		return false;
	}

	public boolean equals(Object o) {
		Class temp = (Class) o;
		return ((temp.grade == grade) && (temp.major.equals(major)) && (temp.classNumber == classNumber));
	}

	public boolean saveToFile() {
		SMFrame.initDataFile(CLASS_FILE);
		DataInputStream input;
		DataOutputStream output;
		int grade, classNumber, studentNumber;
		String major;
		Student student = new Student();
		try {
			input = new DataInputStream(new FileInputStream(CLASS_FILE));
			while (input.available() > 0) {
				grade = input.readInt();
				major = input.readUTF();
				classNumber = input.readInt();
				studentNumber = input.readInt();
				for (int i = 0; i < studentNumber; i++) {
					student.setId(input.readLong());
					student.setName(input.readUTF());
				}
				if ((grade == this.grade) && (classNumber == this.classNumber)
						&& (major.equals(this.major))) {
					input.close();
					return false;
				}
			}
			input.close();
			output = new DataOutputStream(
					new FileOutputStream(CLASS_FILE, true));
			output.writeInt(this.grade);
			output.writeUTF(this.major);
			output.writeInt(this.classNumber);
			output.writeInt(this.studentNumber);
			for (int i = 0; i < this.studentNumber; i++) {
				output.writeLong(students[i].getId());
				output.writeUTF(students[i].getName());
			}
			output.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Class queryFromFile(int grade, String major, int classNumber) {
		SMFrame.initDataFile(CLASS_FILE);
		Class temp = new Class();
		DataInputStream input;
		Student student;
		try {
			input = new DataInputStream(new FileInputStream(CLASS_FILE));
			while (input.available() > 0) {
				temp.setGrade(input.readInt());
				temp.setMajor(input.readUTF());
				temp.setClassNumber(input.readInt());
				int studentNumber = input.readInt();
				temp.setStudentNumber(0);
				for (int i = 0; i < studentNumber; i++) {
					student = new Student();
					student.setId(input.readLong());
					student.setName(input.readUTF());
					temp.addStudent(student);
				}
				if ((grade == temp.getGrade())
						&& (major.equals(temp.getMajor()))
						&& (classNumber == temp.getClassNumber())) {
					input.close();
					return temp;
				}
			}
			input.close();
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public static Class[] loadFile() {
		Class[] array = new Class[20];
		int count = 0;
		SMFrame.initDataFile(CLASS_FILE);
		Student student;
		DataInputStream input;
		try {
			input = new DataInputStream(new FileInputStream(CLASS_FILE));
			while (input.available() > 0) {
				if (count >= array.length) {
					Class[] temp = new Class[array.length * 2];
					for (int i = 0; i < array.length; i++)
						temp[i] = array[i];
					array = temp;
				}
				array[count] = new Class();
				array[count].setGrade(input.readInt());
				array[count].setMajor(input.readUTF());
				array[count].setClassNumber(input.readInt());
				array[count].setStudentNumber(0);
				int num = input.readInt();
				for (int i = 0; i < num; i++) {
					student = new Student();
					student.setId(input.readLong());
					student.setName(input.readUTF());
					array[count].addStudent(student);
				}
				count++;
			}
			input.close();
			return array;
		} catch (Exception e) {
			return null;
		}
	}
}
