import java.io.*;
import java.util.Scanner;

class Item{
	private int code;
	private	string name;
	private	double rate;
	private	int quantity;
/*user gives the input*/
	Item(){			//user defined constructor
		int code=0;string name="";double rate=0.0;int quantity=0;
	}

/*for updating the quantity during issue and receive*/
       public void update_quantity(int k,int q){
		if(k==1)                  //for issue of an item
			this.quantity-=q;
		else if(k==0)             //for receiving an item
			this.quantity+=q;
       }

/*to access the code of an item*/
      public int get_code(){
	return(this.code);
      }

/*to access the quantity of an item*/
public int get_quantity(){
	return(this.quantity);
}

/*for dispalying the rate of an item*/
public void calc(){
	
	System.out.println("\t\t THE RATE is"+this.rate);
}

/*for displaying the quantity of an item*/
public void calc1(){
	
	System.out.println("\t\t THE QUANTITY is"+this.quantity);
}

/*to input the details of an item from the user*/
public void get_data(){
	
	System.out.println("\t\t ENTER DETAILS OF THE ITEM");
	Scanner s=new Scanner(System.in);
	System.out.println("\t\t ENTER NAME");
	this.name= s.next();
	System.out.println("\t\t ENTER CODE");
	this.code=s.nextInt();
	System.out.println("\t\t ENTER RATE");
	this.rate= s.nextInt();
	System.out.println("\t\t ENTER QUANTITY");
	this.quantity=s.nextInt();

}

/*to change the  rate of an item */
public void change_item_rate(){
        Scanner s=new Scanner(System.in);
	System.out.println("\t\t ENTER NEW RATE");
	this.rate=s.nextInt();
}

/*for displaying the details of an item*/
public void display(){
		
	System.out.println("\t\t"+this.name+"\t\t"+this.code+"\t\t"+this.rate+"\t\t"+this.quantity);
}

/*to store the updated code of the item*/
public void update_code(int g){
		this.code=g;
}

}
/*grocery class starts*/
public class grocery{

	private static int count=0;
		
	private ArrayList<Item> list = new ArrayList<Item>();    // a list of items of the class item

/*display the details of each member of list of items*/
public void show(){
		int i;
		System.out.println("\t NAME"+"\tCODE"+"\tRATE"+"\tQUANTITY");
		for(i=0;i<count;i++)
			(this.list.get(i)).display();
}

/*to check if the code of any item already exists or not*/
public int exists_or_not(int c){
		
	int j,flag=-1;
	for(j=0;j<count;j++){
		if((this.list.get(j)).get_code()==c){
			flag=j;
			break;
		}
	}
	return(flag);
}

	/*to add a new item*/	
public void add_item(item t){
	
	t.get_data();
	if(this.exists_or_not(t.get_code())==-1)	{//to check if the given item already exists or not
		this.list.get(count)=t;
		count++;
	}
	else
		System.out.println("\t\t ITEM ALREADY EXISTS");
}

/*for issuing items from the list*/
public void issue_item(item t,int q){
	int l=1;
	int c=this.exists_or_not(t.get_code());
	if(c!=-1){
		if((this.list.get(c)).get_quantity()<q)
			System.out.println("\t\t SUFFIECIENT QUANTITY IS NOT THERE");
		else
			(this.list.get(c)).update_quantity(l,q);
	}
}
/*for receiving new items*/
public void receiv(item t,int q){
		int l=0;
		int c= this.exists_or_not(t.get_code());
		if(c!=-1){
			(this.list.get(c)).update_quantity(l,q);
		}
		else
			System.out.println("\t\t ITEM DOES NOT EXIST");
}

	/*to incorporate changes in the list of items*/
public void change(item t,int k){
		
		(this.list.get(k)).change_item_rate();
		
}

public void change1(int k){
			
		(this.list.get(k)).calc();		
		(this.list.get(k)).calc1();
		
}

/*main function*/
public static int main(Strings args[]){
		grocery t;item t1;int q,co,choice,pos;
		Scanner s=new Scanner(System.in);
		System.out.println("\t\t--------------------------ENTER CHOICE-------------------------");
		while(true){
			/*menu driven program*/
			System.out.println("\t\t1.ADD ITEM");             //for adding an item
			System.out.println("\t\t2.CHANGE RATE OF ITEM");   //for changing the rate of an item by giving its corresponding code
			System.out.println("\t\t3.ISSUE ITEM");        //for taking out items from the list
			System.out.println("\t\t4.RECEIVE ITEM");        //for adding items to the list
			System.out.println("\t\t5.CHECK PRICE/ITEM"); //for checking price
			System.out.println("\t\t6.DISPLAY ITEM");
			System.out.println("\t\t7.EXIT");   //to come out of the menu driven options
			choice = s.nextInt();
			if(choice==7){
				System.out.println("\t\t THANK YOU");
				break;
			}
			else{
			switch(choice){
				case 1:t.add_item(t1);//adding a new item to the list
					break;
				case 2:System.out.println("\t\t ENTER ITEM CODE");
				       co = s.nextInt();
				/*to check for bad inputs*/
					if(co<0)
						System.out.println("\t\t ITEM CODE CANNOT BE NEGETIVE");
					else{
						t1.update_code(co);                   //updating the object to accept the new code
						pos=t.exists_or_not(t1.get_code());
						if(pos==-1)                              //item does not exist
							System.out.println("\t\tITEM NOT  FOUND");
						else
							t.change(t1,pos);
						break;
					}
				case 3:System.out.println("\t\tENTER ITEM CODE AND QUANTITY");
					co = s.nextInt();
					if(co<0)
						System.out.println("\t\t ITEM CODE CANNOT BE NEGETIVE");
					else{
						t1.update_code(co);
						/*check to see if the item really exists*/
						pos=t.exists_or_not(t1.get_code());
						if(pos==-1)
							System.out.println("\t\tITEM NOT  FOUND");
						else{
							q=s.nextInt();
							if(q<0)
								System.out.println("\t\tQUANTITY CANNOT BE  NEGETIVE");
							else
								t.issue_item(t1,q);
						}
						break;
					}
				case 4:System.out.println("\t\tENTER ITEM CODE AND QUANTITY");
					co = s.nextInt();
					if(co<0)
						System.out.println("\t\t ITEM CODE CANNOT BE NEGETIVE");
					else{
						t1.update_code(co);
						pos=t.exists_or_not(t1.get_code());
						if(pos==-1)
							System.out.println("\t\tITEM NOT  FOUND");
						else{
							q=s.nextInt();
							if(q<0)
								System.out.println("\t\tQUANTITY CANNOT BE  NEGETIVE");
							else
								t.receiv(t1,q);
						}
						break;
					}
				case 5:System.out.println("\t\tENTER CODE ITEM TO CHECK");
					co=s.nextInt();	
					if(co<0)
						System.out.println("\t\t ITEM CODE CANNOT BE NEGETIVE");
					else{
						t1.update_code(co);
						pos=t.exists_or_not(t1.get_code());
						if(pos==-1)
							System.out.println("\t\tITEM NOT  FOUND");
						else{
							t.change1(t1,pos);
						}
						break;
					}
				case 6:t.show();   //for displaying the entire table of items
					break;
				default:System.out.println("\t\tWRONG CHOICE");
			}
		}
	}
		return 0;
}//main function ends
}
