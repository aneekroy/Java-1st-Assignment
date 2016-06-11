import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class book_indexing{
	
	
	public static void main(String args[])throws IOException{
		
	      List<String> lines;	
	      HashMap<String, ArrayList<Integer>> occurences = new HashMap< String, ArrayList<Integer> >();
	      lines = Files.readAllLines(Paths.get("/home/saurajit/Desktop/java_assignment/xyz.txt"), StandardCharsets.UTF_8);
	     // for(int i=0;i<lines.size();i++){ System.out.println(lines.get(i));}
		
	      for(int j=0;j<lines.size();j++){
			
			String[] words = lines.get(j).split("\\W+");
               	        for (String word : words) {
                    		word = word.toLowerCase();
				if(!word.equals(".")){
                    			ArrayList<Integer> list = occurences.get(word);
                   	        	if (list == null) {
                        			list = new ArrayList<>();
                       		        	list.add(j+1);
                   		 	} else {
                       				 list.add(j+1);
                    		   	}
                   		 	occurences.put(word, list);
                		}
	    
			}
		}
			Map<String,ArrayList<Integer> > treeMap = new TreeMap<String, ArrayList<Integer> >(occurences);
	    	         for (String key :treeMap.keySet()) {
    				System.out.println(key + " " + treeMap.get(key));
			}

		
	}
		
}
