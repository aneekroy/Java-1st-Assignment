import java.util.Collections;
import java.util.ArrayList;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Comparator;


class FileNameComparator implements Comparator<File> {
    public int compare( File a, File b ) {
        return a.getName().compareTo( b.getName() );
    }
}

class FileSizeComparator implements Comparator<File> {
    public int compare( File a, File b ) {
        int aSize = (int)a.length();
        int bSize = (int)b.length();
        if ( aSize == bSize ) {
            return 0;
        }
        else {
            return Integer.compare(aSize,bSize);
        }
    }
}

class FileDateComparator implements Comparator<File> {
    public int compare( File a, File b ) {
        return Integer.compare((int)a.lastModified(),(int)b.lastModified());
    }
}


public class File_list_sort
{
	private File[] fList;
    		
	public void listf(String directoryName) {
		
    	// .............list file
   	        File directory = new File(directoryName);

   	 // get all the files from a directory
   	        this.fList = directory.listFiles();

   	        /*for (File file : this.fList) {
      			  if (file.isDirectory()) {
           			 listf(file.getAbsolutePath());
       	                  }
    		}*/
	}
		

	public void display(){
	
		for (File file : this.fList){
		        System.out.println(file.getName() + "\t" + file.length() + " B\t" + new Date(file.lastModified()));
   		 //return fList;
		}
		
	}

	public void sort_by_name(){
	
		Arrays.sort(this.fList, new FileNameComparator());
		this.display();
	}

	public void sort_by_size(){
	
		Arrays.sort(this.fList, new FileSizeComparator());
		this.display();
	}
	
	public void sort_by_date(){
	
		Arrays.sort(this.fList, new FileDateComparator());
		this.display();
	}
	
	public static void main(String[] args){

		File_list_sort f= new File_list_sort();
		
		f.listf(args[0]);
		f.display();
		System.out.println();
		String ch=args[1];
		char c= ch.charAt(1);
		if(c=='s')
			f.sort_by_size();
		else if(c=='d')
			f.sort_by_date();
		else
			f.sort_by_name();
		
		f.display();
	}
}
	
	
