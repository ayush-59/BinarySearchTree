package binarySearchTree;

public class Student implements Comparable<Student>{
	private int id;
	private String name;
	private double gpa;
	
	public Student(int id, String name, double gpa) {
		this.id = id;
		this.name = name;
		this.gpa = gpa;
	}
	
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public double getGpa() {
		return this.gpa;
	}
	
	@Override
	public int compareTo(Student s) {
		return this.id - s.getId();
	}
	
	@Override
	public String toString() {
		return "{name="+name+",id="+id+",gpa="+gpa+"}";
	}
}
