package model;

import java.io.*;
import com.lhj.SMFrame;

public class ScoreMenu implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SCORE_MENU_FILE = "testScoreFile.dat";
	public static final float MAX_SCORE = 100;
	private Class classOfScoreMenu;
	private Course course;
	private int[] testTime;// 包括年，月，日，小时，分钟
	private int numberOfAttendTestStudent;
	private long idOfStudents[];
	private float scoreOfTest[];

	public ScoreMenu() {
		idOfStudents = new long[20];
		scoreOfTest = new float[20];
		numberOfAttendTestStudent = 0;
		testTime = new int[5];
	}

	public ScoreMenu(Class classes, Course course, int[] testTime, int num,
			long[] ids, float[] scores) {
		setClassOfScoreMenu(classes);
		setCourse(course);
		setTestTime(testTime);
		this.numberOfAttendTestStudent = num;
		if (num != 0) {
			this.idOfStudents = ids;
			this.scoreOfTest = scores;
		} else {
			this.idOfStudents = new long[20];
			this.scoreOfTest = new float[20];
		}
	}

	public void setClassOfScoreMenu(Class newClass) {
		this.classOfScoreMenu = newClass;
	}

	public Class getClassOfScoreMenu() {
		return classOfScoreMenu;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Course getCourse() {
		return course;
	}

	public void setTestTime(int[] testTime) {
		this.testTime = testTime;
	}

	public int[] getTestTime() {
		return testTime;
	}

	public int getNumberOfAttendTestStudent() {
		return numberOfAttendTestStudent;
	}

	public long[] getIdOfStudents() {
		return this.idOfStudents;
	}

	public float[] getScoreOfTest() {
		return this.scoreOfTest;
	}

	private int findIndexOfStudent(long id) {
		for (int i = 0; i < numberOfAttendTestStudent; i++) {
			if (id == idOfStudents[i])
				return i;
		}
		return -1;
	}

	@SuppressWarnings("unused")
	private int findIndexOfStudent(Student student) {
		return findIndexOfStudent(student.getId());
	}

	private boolean isIdInClass(long id) {
		Student[] student = classOfScoreMenu.getStudents();
		for (int i = 0; i < classOfScoreMenu.getStudentNumber(); i++) {
			if (student[i].getId() == id)
				return true;
		}
		return false;
	}

	public boolean setScoreOfTest(float score, long studentId) {
		if (!isIdInClass(studentId))
			return false;
		if (numberOfAttendTestStudent >= scoreOfTest.length) {
			float[] temp = new float[scoreOfTest.length * 2 + 1];
			for (int i = 0; i < scoreOfTest.length; i++)
				temp[i] = scoreOfTest[i];
			scoreOfTest = temp;
		}
		int index = findIndexOfStudent(studentId);
		if (index == -1) {
			scoreOfTest[numberOfAttendTestStudent] = score;
			idOfStudents[numberOfAttendTestStudent] = studentId;
			numberOfAttendTestStudent++;
		} else
			scoreOfTest[index] = score;
		return true;
	}

	public boolean setScoreOfTest(float score, Student student) {
		return setScoreOfTest(score, student.getId());
	}

	public float getHighestScore() {
		int i = 1;
		float high = scoreOfTest[0];
		for (; i < numberOfAttendTestStudent; i++) {
			if (high < scoreOfTest[i])
				high = scoreOfTest[i];
		}
		return high;
	}

	public float getLowestScore() {
		int i = 1;
		float low = scoreOfTest[0];
		for (; i < numberOfAttendTestStudent; i++) {
			if (low > scoreOfTest[i])
				low = scoreOfTest[i];
		}
		return low;
	}

	public float getAverageScore() {
		int i = 0;
		float scores = 0;
		for (; i < numberOfAttendTestStudent; i++)
			scores += scoreOfTest[i];
		return scores / numberOfAttendTestStudent;
	}

	/**
	 * 返回0－59、60－69、70－79、80－89、90－100各个分数阶段的学生人数
	 **/
	public int[] getAnasisOfScore() {
		int[] array = new int[] { 0, 0, 0, 0, 0 };
		int i = 0;
		float score;
		for (; i < numberOfAttendTestStudent; i++) {
			score = scoreOfTest[i];
			if (score < 60)
				array[0]++;
			else if (score < 70)
				array[1]++;
			else if (score < 80)
				array[2]++;
			else if (score < 90)
				array[3]++;
			else
				array[4]++;
		}
		return array;
	}

	public boolean saveToFile() {
		SMFrame.initDataFile(SCORE_MENU_FILE);
		DataInputStream input;
		DataOutputStream output;
		Class tempClass = new Class();
		Student student = new Student();
		Course course = new Course();
		int[] testTime = new int[5];
		int numberOfAttendTestStudent;
		long idOfStudents[];
		float scoreOfTest[];
		try {
			input = new DataInputStream(new FileInputStream(SCORE_MENU_FILE));
			while (input.available() > 0) {
				tempClass.setGrade(input.readInt());
				tempClass.setMajor(input.readUTF());
				tempClass.setClassNumber(input.readInt());
				int studentNumber = input.readInt();
				tempClass.setStudentNumber(0);
				for (int i = 0; i < studentNumber; i++) {
					student.setId(input.readLong());
					student.setName(input.readUTF());
				}
				course.setId(input.readUTF());
				course.setName(input.readUTF());
				course.setCredit(input.readFloat());
				course.setPeriod(input.readInt());
				for (int i = 0; i < 5; i++)
					testTime[i] = input.readInt();
				numberOfAttendTestStudent = input.readInt();
				idOfStudents = new long[numberOfAttendTestStudent];
				scoreOfTest = new float[numberOfAttendTestStudent];
				for (int i = 0; i < numberOfAttendTestStudent; i++)
					idOfStudents[i] = input.readLong();
				for (int i = 0; i < numberOfAttendTestStudent; i++)
					scoreOfTest[i] = input.readFloat();
				if ((this.classOfScoreMenu.equals(tempClass))
						&& (this.course.equals(course))) {
					input.close();
					return false;
				}
			}
			input.close();
			output = new DataOutputStream(new FileOutputStream(SCORE_MENU_FILE,
					true));
			output.writeInt(this.classOfScoreMenu.getGrade());
			output.writeUTF(this.classOfScoreMenu.getMajor());
			output.writeInt(this.classOfScoreMenu.getClassNumber());
			int num = this.classOfScoreMenu.getStudentNumber();
			output.writeInt(num);
			Student[] students = this.classOfScoreMenu.getStudents();
			for (int i = 0; i < num; i++) {
				output.writeLong(students[i].getId());
				output.writeUTF(students[i].getName());
			}
			output.writeUTF(this.course.getId());
			output.writeUTF(this.course.getName());
			output.writeFloat(this.course.getCredit());
			output.writeInt(this.course.getPeriod());
			for (int i = 0; i < 5; i++)
				output.writeInt(this.testTime[i]);
			output.writeInt(this.numberOfAttendTestStudent);
			for (int i = 0; i < this.numberOfAttendTestStudent; i++)
				output.writeLong(this.idOfStudents[i]);
			for (int i = 0; i < this.numberOfAttendTestStudent; i++)
				output.writeFloat(this.scoreOfTest[i]);
			output.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static ScoreMenu queryFromFile(Class classes, Course courses) {
		SMFrame.initDataFile(SCORE_MENU_FILE);
		DataInputStream input;
		Class tempClass = new Class();
		Student student;
		Course course = new Course();
		int[] testTime = new int[5];
		int numberOfAttendTestStudent;
		long idOfStudents[];
		float scoreOfTest[];
		try {
			input = new DataInputStream(new FileInputStream(SCORE_MENU_FILE));
			while (input.available() > 0) {
				tempClass.setGrade(input.readInt());
				tempClass.setMajor(input.readUTF());
				tempClass.setClassNumber(input.readInt());
				int studentNumber = input.readInt();
				tempClass.setStudentNumber(0);
				for (int i = 0; i < studentNumber; i++) {
					student = new Student();
					student.setId(input.readLong());
					student.setName(input.readUTF());
					tempClass.addStudent(student);
				}
				course.setId(input.readUTF());
				course.setName(input.readUTF());
				course.setCredit(input.readFloat());
				course.setPeriod(input.readInt());
				for (int i = 0; i < 5; i++)
					testTime[i] = input.readInt();
				numberOfAttendTestStudent = input.readInt();
				idOfStudents = new long[numberOfAttendTestStudent];
				scoreOfTest = new float[numberOfAttendTestStudent];
				for (int i = 0; i < numberOfAttendTestStudent; i++)
					idOfStudents[i] = input.readLong();
				for (int i = 0; i < numberOfAttendTestStudent; i++)
					scoreOfTest[i] = input.readFloat();
				if ((classes.equals(tempClass)) && (courses.equals(course))) {
					input.close();
					return new ScoreMenu(tempClass, course, testTime,
							numberOfAttendTestStudent, idOfStudents,
							scoreOfTest);
				}
			}
			input.close();
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public static ScoreMenu[] loadFile() {
		SMFrame.initDataFile(SCORE_MENU_FILE);
		DataInputStream input;
		Course course;
		Class tempClass;
		Student student;
		int[] testTime;
		long[] id;
		float[] score;
		ScoreMenu[] scoreMenu = new ScoreMenu[20];
		int count = 0;
		try {
			input = new DataInputStream(new FileInputStream(SCORE_MENU_FILE));
			while (input.available() > 0) {
				if (count >= scoreMenu.length) {
					ScoreMenu[] temp = new ScoreMenu[scoreMenu.length * 2];
					for (int i = 0; i < scoreMenu.length; i++)
						temp[i] = scoreMenu[i];
					scoreMenu = temp;
				}
				tempClass = new Class();
				tempClass.setGrade(input.readInt());
				tempClass.setMajor(input.readUTF());
				tempClass.setClassNumber(input.readInt());
				tempClass.setStudentNumber(0);
				int num = input.readInt();
				for (int i = 0; i < num; i++) {
					student = new Student();
					student.setId(input.readLong());
					student.setName(input.readUTF());
					tempClass.addStudent(student);
				}
				course = new Course();
				course.setId(input.readUTF());
				course.setName(input.readUTF());
				course.setCredit(input.readFloat());
				course.setPeriod(input.readInt());
				testTime = new int[5];
				for (int i = 0; i < 5; i++)
					testTime[i] = input.readInt();
				num = input.readInt();
				id = new long[num];
				score = new float[num];
				for (int i = 0; i < num; i++)
					id[i] = input.readLong();
				for (int i = 0; i < num; i++)
					score[i] = input.readFloat();
				scoreMenu[count] = new ScoreMenu(tempClass, course, testTime,
						num, id, score);
				count++;
			}
			input.close();
			return scoreMenu;
		} catch (Exception e) {
			return null;
		}
	}
}
