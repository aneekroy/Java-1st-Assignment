import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

class Patient{
	
	private int social_security_no;
	private String name;
	private int age;
	private int blood_pressure;
	private int temp;
	private String sex;
	private String doc_name;
	
	public void get_details_admit(File file) throws IOException{
	
		System.out.println("\t\t ENTER  5 DIGIT SOCIAL SECURITY NO OF PATIENT");	
		Scanner s= new Scanner(System.in);
		int s_n= s.nextInt();
		if(does_exists(s_n,file)==1)
			System.out.println("\t\t THE PATIENT ALREADY IS ADMITTED");
		else{
			this.social_security_no=s_n;
			System.out.println("\t\t ENTER NAME ");
			this.name= s.next();
			System.out.println("\t\t ENTER AGE ");
			this.age=s.nextInt();
			System.out.println("\t\t ENTER SEX OF PATIENT");
			this.sex= s.next();
			this.get_doc_name();
			this.get_blood_pressure_temp();
			this.write_file(file);
		}
	}
	
	public void write_file(File file)throws IOException{
	
		String str = Integer.toString(this.social_security_no) + "      " +this.name+"      "+ Integer.toString(this.age)+"      "+this.sex+"     "+this.doc_name+"       "+Integer.toString(this.temp)+"       "+Integer.toString(this.blood_pressure);

		//System.out.println(str);
		try{
           		BufferedReader bfr=new BufferedReader(new FileReader(file));
           		FileWriter fw=new FileWriter(file,true);            
                	fw.append(str+"\n");
          		bfr.close();
           	        fw.close();
       		}catch(FileNotFoundException fex){
            		fex.printStackTrace();
        	}
	}

	public void get_doc_name(){

		String doc[] = {"Dr XYZ","Dr asfas","Dr casfsfas","Dr dgsdg"};
		Random rnd = new Random();
		this.doc_name = doc[rnd.nextInt(doc.length)];
	}

	public void get_blood_pressure_temp(){
	
		System.out.println("\t\t THE PATIENT IS ASSIGNED DOCTOR: "+ this.doc_name);
		System.out.println("\t\t DOCTOR PLEASE ENTER PATIENTS BLOOD PRESSURE");
		Scanner s= new Scanner(System.in);
		this.blood_pressure = s.nextInt();
		System.out.println("\t\t DOCTOR PLEASE ENTER PATIENTS TEMPERATURE");
		this.temp=s.nextInt();
	}

	public void display(int d,File file)throws IOException{

		int flag=0;String str2= Integer.toString(d);
		//System.out.println(Integer.toString(d));
		Scanner scanner=new Scanner(file);
		while(scanner.hasNextLine()){
			String str1=(scanner.nextLine());
			if(str2.trim().equals(str1.substring(0,6).trim())){
    				System.out.println(str1);
				flag=1;
				break;
			}
      
      		}
		if(flag==0)
			System.out.println("\t\t THE PATIENT IS LONGER THERE");

	}

	public int does_exists(int f,File file) throws IOException{
	
		int flag=1;	    
		Scanner scanner=new Scanner(file);
		while(scanner.hasNextLine()){
			//System.out.println((scanner.nextLine()).substring(0,6));
   			if(Integer.toString(f).trim().equals((scanner.nextLine()).substring(0,6).trim())){
        			flag=0;
				break;
			}
        		else
				flag=1;
      		}
		if(flag==0)
			return 1;
		else
			return 0;
	}
	
	public void remove_patient(int g,File file) throws IOException{
		
		String lineToRemove="";		
		Scanner scanner=new Scanner(file);
		while(scanner.hasNextLine()){
			String str1=scanner.nextLine();
     			if(Integer.toString(g).trim().equals(str1.substring(0,6).trim())){
				lineToRemove=str1;
			}
		}
		File tempFile = new File("mytemp.txt");		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String currentLine;

		while((currentLine = reader.readLine()) != null) {
    // trim newline when comparing with lineToRemove
   			 String trimmedLine = currentLine.trim();
    			 if(trimmedLine.equals(lineToRemove)) continue;
    					writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.close(); 
		reader.close(); 
		boolean successful = tempFile.renameTo(file);
	}


	public void disp_file(File file) throws IOException{
		
		Scanner s= new Scanner(file);
		while(s.hasNextLine()){
			System.out.println(s.nextLine());
		}
	}

	public static void main(String args[]) throws IOException{
		
		Patient p;p = new Patient();
		System.out.println("\t\t WELCOME TO KPC HOSPITAL ");
		File file=new File("Patients.txt");       
                if(!file.exists()){
            		file.createNewFile();
        	}
		System.out.println("ENTER OPTIONS");
		while(true){
			System.out.println("\t\t 1.ENTER A PATIENT");		
			System.out.println("\t\t 2.PATIENT CHECK OUT");	
			System.out.println("\t\t 3.DISPLAY DETAILS OF A PARTICULAR PATIENT");
			System.out.println("\t\t 4.EXIT");
			Scanner s= new Scanner(System.in);
			int choice = s.nextInt();
			if(choice==4){
				System.out.println("\t\t THANK YOU-----------------------");
				break;
			}
			else{
				switch(choice){
				
					case 1:
					       p.get_details_admit(file);
					       p.disp_file(file);
					       break;
			
					case 2: System.out.println("\t\t PLEASE ENTER THE SOCIAL SECURITY NO");
						int gf=s.nextInt();
						p.remove_patient(gf,file);
						p.disp_file(file);
						break;
		
					case 3:System.out.println("\t\t PLEASE ENTER THE SOCIAL SECURITY NO OF THE PATIENT TO DISPLAY");
					       int g=s.nextInt();
		                               p.display(g,file);

						break;
					
					default:System.out.println("wrong choice given");
				}
			}
		}
	}
}
	
