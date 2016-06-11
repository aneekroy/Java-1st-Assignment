import java.util.*;
import java.io.*;

class Parenthesis {
	public static void main(String args[]) throws IOException {
		Scanner sc =new Scanner(System.in);
		
		String str;
		int len;
		boolean found = true;
		System.out.println("Enter the string of brackets :");
		str =sc.next();
		Stack<Character> st = new Stack<Character>();
		for(int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			if(ch == '(' || ch == '{' || ch == '[') {
				st.push(ch);
			}
			else {
				char st_ch = (char) st.pop();
				if(!((st_ch == '(' && ch == ')') || (st_ch == '[' && ch == ']') || (st_ch == '{' && ch == '}'))) {
					found = false;
					break;
				}
			}
		}
		if(found && st.empty())
			System.out.println(found);
		else
			System.out.println(found);
	}
};
