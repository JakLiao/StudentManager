package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.lhj.SMFrame;


public class Student implements java.io.Serializable{   

	private static final long serialVersionUID = 1L;
	public static final String STUDENT_FILE="studentFile.dat";   
    private long id;   
    private String name;   
  
    public Student(long id,String name) {   
        setId(id);   
        setName(name);   
    }   
    public Student(){}   
    public void setId(long id){       
        this.id=id;   
    }   
    public long getId(){   
        return id;   
    }   
    public void setName(String name){   
        this.name=name;   
    }   
    public String getName(){   
        return name;   
    }   
    public boolean equals(Object o){   
        Student temp=(Student)(o);   
        if(id==temp.id)   
            return true;   
        else   
            return false;   
    }   
    public boolean saveToFile(){   
        SMFrame.initDataFile(STUDENT_FILE);   
        DataOutputStream output;        
        DataInputStream input;   
        String name;   
        long id;   
        try{   
            input=new DataInputStream(new FileInputStream(STUDENT_FILE));               
            while(input.available()>0){   
                id=input.readLong();   
                name=input.readUTF();   
                if(id==this.id){   
                    input.close();   
                    return false;   
                }   
            }   
            input.close();   
            output=new DataOutputStream(new FileOutputStream(STUDENT_FILE,true));   
            output.writeLong(this.id);   
            output.writeUTF(this.name);   
            output.close();   
            return true;   
        }catch(Exception e){         
            return false;   
        }   
    }      
    public static Student queryFromFile(long studentId){   
    	SMFrame.initDataFile(STUDENT_FILE);   
        DataInputStream input;   
        String name;   
        long id;   
        try{   
            input=new DataInputStream(new FileInputStream(STUDENT_FILE));   
            while(input.available()>0){   
                id=input.readLong();   
                name=input.readUTF();   
                if(id==studentId){   
                    input.close();   
                    return new Student(id,name);                       
                }   
            }   
            input.close();   
            return null;   
        }catch(Exception e){   
            return null;   
        }   
    }      
    public static Student[] loadFile(){   
        Student[] student=new Student[20];   
        int count=0;   
        SMFrame.initDataFile(STUDENT_FILE);   
        DataInputStream input;   
        try{   
            input=new DataInputStream(new FileInputStream(STUDENT_FILE));   
            while(input.available()>0){   
                if(count>=student.length){   
                    Student[] temp=new Student[student.length*2];   
                    for(int i=0;i<student.length;i++)   
                        temp[i]=student[i];   
                    student=temp;   
                }   
                student[count]=new Student();   
                student[count].setId(input.readLong());   
                student[count].setName(input.readUTF());   
                count++;   
            }   
            input.close();   
            return student;   
        }catch(Exception e){   
            return null;   
        }   
    }   
}   
