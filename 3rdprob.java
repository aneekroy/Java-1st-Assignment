import java.util.*;
import java.io.*;

class parenthesis {
	public static void main(String args[]) throws IOException {
		InputStreamReader read = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(read);
		String str;
		int len;
		boolean found = true;
		System.out.println("Enter the string of brackets.\n");
		str = in.readLine();
		Stack st = new Stack();
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
			System.out.println(true);
		else
			System.out.println(false);
	}
};
