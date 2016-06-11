import java.io.*;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;

/*date class to check the  validity of the admission date*/
class date{
		public boolean  isleapyear(int year){
			
			return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
		}
		
		public int valid_date(int year,int month,int day){ //to check if a day is a valid day or not
				
				int monthlen[]={31,28,31,30,31,30,31,31,30,31,30,31};
				if (year!=0 || month!=0 || day!=0 || month>12)
					return -1;
				if (isleapyear(year))
					monthlen[1]++;
				if (day>monthlen[month-1])
					return -1;
				if(year!=2015)
					return -1;
				return 1;
		}
			/*taking the date as dd/mm/yyyyy format*/
		public int is_valid_date(String s){
		
				int c=s.length(),l=0,p[]= new int[3];String k="";
				s=s+" ";
				/*extracting the day month and year from the string*/
				for(int h=0;h<c;h++){
					if(s.charAt(h)!='/')
						k=k+s.charAt(h);
					else{
						p[l++]=Integer.parseInt(k);
						k="";
					}		
				}
				p[l++]=Integer.parseInt(k);//converting from string to integer
				return(valid_date(p[2],p[1],p[0]));//calling function valid date
		}
} 	



class Student{
	private String name;
	private	int roll;
	private	String date;
	//private	String course;
	private int marks[]=new int [6];
	private	String sub[]=new String[6];
	private int total;
	//ArrayList<student> list = new ArrayList<Student>();
	
		Student(){          /*user defined constructor*/
			roll=100000;
		}


/*to get the details of a student*/
public int get_roll(){

		return(roll);
}
public String get_name(){//to get  the students name

		return(name);
}
/*public String get_course(){//to get the students course

		return(course);
}*/

public int get_total(){
	return(total);
}
/*asking the user to give details of the student*/
public void input(){
		Scanner s = new Scanner(System.in);date d1=new date();
		int m,j;String s1;int fg=0;
		System.out.println("\t\t ENTER DETAILS OF STUDENT");
		System.out.println("enter name:");
		name=s.next();
		
	/*to check if a string contains digits or not*/
		for(int h=0;h<name.length();h++){
			if(name.charAt(h)=='1' || name.charAt(h)=='2'||name.charAt(h)=='3'||name.charAt(h)=='4'||name.charAt(h)=='5'||name.charAt(h)=='6'||name.charAt(h)=='7'||name.charAt(h)=='8'||name.charAt(h)=='9'){
				fg=1;
				System.out.println("name contains digits enter correctlty");
				break;}
		}
		if(fg!=1){
			System.out.println("enter roll no:");
			roll=s.nextInt();
			System.out.println();
			System.out.println("enter date(in dd/mm/yyyy format):");
			date=s.next();
			/*while(true){
				date=s.next();
				if(d1.is_valid_date(date)==-1){
					System.out.println("wrong admission date");
				}
				else
					break;
			}*/
			/*System.out.println("enter course name");
			course=s.next();*/
			System.out.println("enter marks and subject");
			for(j=0;j<5;j++){
				s1=s.next();
				m= s.nextInt();
				if(m<0 || m>100){         //marks should not > 100 or <0
					System.out.println("wrong marks entered");break;}
				sub[j]=s1;
				marks[j]=m;
				total=total+m;
			}
		}
}

/*function to change the marks of any of the subjects of a particular student*/
public void change_marks(){
		int f,sub_code,new_marks;
		System.out.println("enter no of subjects whose marks needs to be changed");
		Scanner s = new Scanner(System.in);
		f=s.nextInt();
		System.out.println("enter subject codes");
		while(f!=0){
			sub_code=s.nextInt();	
			if(sub_code>=1 && sub_code<=5){
				System.out.println("enter new marks");
				new_marks=s.nextInt();
				marks[sub_code-1]=new_marks;
			}
			else
				System.out.println("wrong subject code entered");
			f--;
	}
}
	/*displaying the marksheet of a particular student*/	
public void display(){
		
		System.out.println("--------------------------MARKSHEET---------------------------------------");
		System.out.println();
		System.out.println("--------------------------------------------------------------------------");
		//System.out.println("NAME \t\t COURSE");
		/*System.out.println(name+"\t\t"+course);
		System.out.println();*/
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("ROLL NO :\t\t" +roll);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("SUBJECT \t\t MARKS");
		for(int y=0;y<5;y++){
			System.out.println((y+1)+"\t\t"+sub[y]+"\t \t\t "+marks[y]);
		}
		System.out.println("TOTAL"+total);
}
/*to make changes in the roll no of any particular student*/
public void update_roll(int k){
		roll=k;
}
}


class Department extends Student{

	private String dept;
	//private HashMap<String,ArrayList<Department> >m = new HashMap<String,ArrayList<Department> >();	
	
	public void input(){
		Student s1;
		super.input();
		System.out.println("\t\t ENTER DEPARTMENT OF STUDENT");
		Scanner s=new Scanner(System.in);
		dept=s.next();
		//m.put(dept,this);
		
	}
	public int get_roll(){
		
		return(super.get_roll());
	}
	
	public String get_dept(){
		return(this.dept);
	}

	public void display(){

		super.display();
		System.out.println("the department is"+dept);
	}
	public int get_total(){
		return(super.get_total());
	}
	
	public String get_name(){
		return(super.get_name());
	}

	public void display1(){

		System.out.println("NAME \t\t ROLL \t\t TOTAL");
		System.out.println(this.get_name()+"\t\t"+this.get_roll()+"\t\t"+this.get_total());
	}
		

}

public class Batch{
	
	private ArrayList<Department> de = new ArrayList<Department>();
	private HashMap<String,ArrayList<Department> >m = new HashMap<String,ArrayList<Department> >();
	
	public void admit(Department d){
	
		d.input();
		if(exists_or_not(d.get_roll())==-1){
			
			de.add(d);
			m.put(d.get_dept(),de);
		}
	}

	public int exists_or_not(int c){
		
		int j,flag=-1;
		for(j=0;j<de.size();j++){
			if(de.get(j).get_roll()==c){
				flag=j;
				break;
			}
		}
		return(flag);
	}

	public void sort_student(String s){

		ArrayList<Department> myObject = new ArrayList<Department>(m.get(s));	
		Collections.sort(myObject,new Sort_total());
		System.out.println("FOR THE DEPARTMENT"+s);
		for(Department e:myObject){
            		e.display1();
		}
	}

	public void remove_stuent(){
	
		Collections.sort(de,new Sort_total());
		System.out.println("REMOVED STUDENT IS");
		de.get(0).display();
		System.gc();
	}
	
	public static void main(String args[]){

		Batch b=new Batch();	
		Scanner s=new Scanner(System.in);
		System.out.println("------------ENTER CHOICE-----------------");
		while(true){
			System.out.println("\t\t1. ADMIT A NEW STUDENT");
			System.out.println("\t\t2. DISPLAY DETAILS OF A  STUDENT");
			System.out.println("\t\t3. SORT A PARTICULAR DEPARTMENT");
			System.out.println("\t\t4. EXIT");
			int choice = s.nextInt();
			if(choice==4)
				break;
			else{
				switch(choice){
					case 1: Department d= new Department();
						b.admit(d);
						break;
					case 2: System.out.println("ENTER ROLL ");
						int r=s.nextInt();
						int g=b.exists_or_not(r);
						(b.de.get(g)).display();
						break;
			
					case 3:System.out.println("\t\t CHOOSE DEPARTMENT");
						String dep=s.next();
						b.sort_student(dep);
						break;
					default: System.out.println("wrong choice");
							break;
				}
			}
						
	}
}
}


class Sort_total implements Comparator<Department>{
	public int compare(Department d1,Department d2){
		return(Integer.compare(d1.get_total(),d2.get_total()));
	}
}
	

