import java.io.*;
import java.util.*;



class item
{	
	private String item_code; // variable for item_code
	private String name; //variable for name
	private int  rate; // variable for rate
	private int quantity; //variable
	public item(){
		this.item_code="\0";		//Initialising itemcode
		name="\0";				//Initialising name	
		rate=0;			//Initialising rate
		quantity=0; 	//Initialising quantity
		}	
	 
	public	String get_name(){
			return name;	//returning name pointer for printing
		}
	public	String get_item_code(){
			return item_code; //function returning item_code
		}
	public	int get_rate(){
			return rate;	//function returning rate
		}
	public void set_rate(int r){ //function accepting int value to set the rate
			 rate=r;
		}
	public	int get_quantity(){ //function returning quantity
			return quantity;
		}
	public	void set_quantity(int q){ //function accepting int value to set the quantity
			this.quantity=q;
		}
	public	void update(int item_code,String n,int r,int q)
		{
		this.item_code=item_code;		//Initialising itemcode
		name=n;				//Initialising name	
		rate=r;			//Initialising rate
		quantity=q; 	//Initialising quantity
	
		}
};//End of Class Item

class stock 
{
	//item []list = new item[100];//Max no of list possible for items 
	 Arraylist <item> list =new Arraylist[];
	//public Scanner sc = new Scanner(System.in);
	public static int no_of_items_in_list=-1;//Static variable for current no of items in list

	public int noOfItemsGreaterThanAmount(){
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter the Amount :");int amt=sc.nextInt();
		int i=0,n=0;		
		while(i<stock.no_of_items_in_list)
			if(list[i++].get_rate()>=amt)	
				n++;
		return n;		
		}
	public  int check_exists_return_index(String itemcode)//Function to check for existence of item in list with given itemcode
	{	
			int i=0;
			//flag=0,
			int index=-1;
			for(i=0;i<=stock.no_of_items_in_list;i++)
				if(list[i].get_item_code().equals(itemcode))
				{	
					//flag=1;
					index=i;
				}
			/*if(flag==0)
				return -1;//If not found return (-1)
			else*/
				return index;//If found returns the index of item in list
				
	}
	public static  boolean isAlphaNumeric(String s ){
    	String pattern= "^[a-zA-Z0-9]*$";
            return s.matches(pattern);
       
}			
	public void add_to_list( )//function to add new item to list
	{		Scanner sc = new Scanner(System.in);
			String item_code;
			String name;
			int  rate=0;
			int quantity;
			System.out.println("\nEnter New Item Code to ADD to the List:");
			char choice='Y';int flag=0,f=0,flg=0,index=-1;
			do{
				item_code=sc.nextLine();				
				//scanf("%d",&item_code);
				if(isAlphaNumeric(item_code))//Checking for positive value of item_code 
				{	flg=1;
					index=(check_exists_return_index(item_code));
					if(index!=-1)//Checking for existence of itemcode in list
					{ 
					do{
						System.out.println("\nItem already exists with Item Code"+item_code);
		System.out.print("\nDo You Want to add item with this Item_code to the already existing item in the list ? (Y/N)");
						choice = (sc.next()).charAt(0);
						if(choice=='Y'||choice=='y')
						{
							this.receive(index);//call to receive already added item
							return;
						
						}				
						else if(choice=='N'||choice=='n')
						{
							System.out.print("\nEnter New Item_Code ");//option for adding with new itemcode
							flg=0;	
							f=1;
						}
						else{
						System.out.print("\nWRONG CHOICE !!! Enter Again :");//exception handling for wrong choice
							f=0;										
						}
					}while(f!=1);
					}
				else
				{	//Item code does not exists hence new item code has been entered
					flag=0;
						System.out.print("\nEnter Name of the New Item :");	
						name=sc.nextLine();//Taking Input as entire line
						flag=0;
					
					flag=0;
					do{
						System.out.print("\nEnter Rate :");
						rate=sc.nextInt();
						if(rate>0)
						{	
							flag=1;
							System.out.print("\nEnter Quantity :");
							quantity=sc.nextInt();
							if(quantity>=0)
							{
								no_of_items_in_list++;
						this.list[no_of_items_in_list].update(item_code,name,rate,quantity);//adding to the list
										
							}
							else{
						System.out.print("\nNegative quantity of items NOT allowed\n");
						System.out.print("\nEnter from Start\n");
								return;
							}
						}
						else{
							flag=0;
				System.out.print("\nNegative Rate for Items NOT Allowed!!!\n\t Enter Again :");//Exception handling
						}
					}while(flag==0);
				}
			}
			else
			{
				System.out.println("\nNegative Value for Item_code not allowed !!\n\t Enter Again :");//Exception Handling
				flg=0;
			}
			}while(flg==0);
		return;
		}//End of function add to list

	public  void rate_change( )//Funtion to change the rate
	{	Scanner sc = new Scanner(System.in);
			int i=0,rt=0,flag=0,itemcode=0;
			System.out.print("\nEnter ItemCode of Item whose rate is to be updated:");
			do
			{		
				itemcode=sc.nextInt();//Input Itemcode	
				
				//scanf("%d",&itemcode);
				if((i=check_exists_return_index(itemcode))!=-1){//checking for item with given itemcode in list
					flag=1;
					System.out.print("\nEnter the New Rate :\n");//Input rate
					do
					{ rt=sc.nextInt();
						if(rt>0){	//Checking for Positive value of rate
							flag=1;	
							list[i].set_rate(rt);
						}
						else{
							System.out.print("\nThe rate can NOT be negative!!! Enter again\n");//Exception handling
						flag=0;
						}
					}while(flag==0);
				}		
				else
					System.out.print("\nItem Code does NOT exist Enter Again:");	//Exception handling		
			}while(flag!=1);
		return;
		}
	public void receive(int index)
	{	Scanner sc = new Scanner(System.in);
			int i=0,rt=0,flag=0,itemcode=0,q=0;
			
			if(index!=-1){
			flag=1;
			i=index;
			}	
			else
			{			
			System.out.print("\nEnter ItemCode of Item which is to be received:");
			do
			{	if(flag!=0)
				itemcode=sc.nextLine();	
				if((i=check_exists_return_index(itemcode))!=-1)//Checking for Existence of Itemcode in list
				{flag=1;
				System.out.print("\nEnter the Quantity  :\n");rt=sc.nextInt();
					do
					{
						if(rt>0){	System.out.print("\nThe Quantity is updated !!");//Checking positive value of rate
								flag=1;	
								q=this.list[i].get_quantity();
								list[i].set_quantity(q+rt);//Setting rate
							}
						else{
							System.out.print("\nThe quantity can NOT be negative!!! Enter again\n");//Exception handling	
							flag=0;
						}
					}while(flag==0);
				}		
				else
					System.out.print("\nItem Code does NOT exist Enter Again:");//Exception handling				
			}while(flag!=1);
		
			}
		return;
		}
		void issue(int index)//Function for issuing item
		{	Scanner sc = new Scanner(System.in);
			int i=0,rt=0,flag=0,itemcode=0,q=0;
			
			if(index!=-1){
			flag=1;
			i=index;
			}	
			else
			{									
	  		System.out.print("\nEnter ItemCode of Item whose rate is to be updated:");
			do
			{		
				if(flag!=0)
				itemcode=sc.nextLine();
				if((i=check_exists_return_index(itemcode))!=-1)//Checking for Existence of Itemcode in list
				{
					flag=1;
					System.out.print("\nEnter the Quantity of Items to be Issued :\n");
					rt=sc.nextInt();
					do
					{
						if(rt<=list[i].get_quantity()){	
							flag=1;	
							q=list[i].get_quantity();
							list[i].set_quantity(q-rt);//Issuing Quantity
		System.out.println("\n"+rt+"Quantity of items with name"+list[i].get_name()+" and "+itemcode+" Itemcode has been issued");
						}
						else
					System.out.print("\nThe Quantity  asked to be Issued is more than existing quantity!!! Enter again\n");
					}while(flag==0);
				}		
				else
					System.out.print("\nItem Code does NOT exist Enter Again:");			
			}while(flag!=1);
			}
		return;
		}
		
		void show_price( )//Function for showing Price for item
		{	Scanner sc = new Scanner(System.in);
			int i=0,flag=0;String itemcode;
			System.out.print("\nEnter ItemCode of Item whose price is to be displayed:");
			do
			{	itemcode=sc.nextLine();
					//scanf("%d",&);	
				if((i=check_exists_return_index(itemcode))!=-1){ //Checking for Existence of Itemcode in list
				
					flag=1;
System.out.print("\nThe Price of Item with name "+list[i].get_name()+" and Itemcode "+list[i].get_item_code()+" is : "+list[i].get_rate());	
			}
				else
					System.out.print("\nItem Code does NOT exist Enter Again:");			
			}while(flag!=1);
		return;
		}
		void show_quantity( )//Function for showing quantity for item
		{	Scanner sc = new Scanner(System.in);
			int i=0,flag=0,itemcode=0;
			System.out.print("\nEnter ItemCode of Item whose price is to be displayed:");
			do
			{	itemcode=sc.nextLine();//scanf("%d",&itemcode);	
				if((i=check_exists_return_index(itemcode))!=-1) //Checking for Existence of Itemcode in list
				{
				flag=1;
System.out.print("\nThe Quantity of Item with name"+list[i].get_name()+"and Itemcode"+list[i].get_item_code()+" is :"+list[i].get_quantity());
			}
				else
					System.out.print("\nItem Code does NOT exist Enter Again:");//Exception handling			
			}while(flag!=1);
		return;
		}
};//End of class stock

//Initialising Static Variable


class ItemList{
public static void main(String args[]) {//Start of main()function
	Scanner sc = new Scanner(System.in);
	int choice=0;
	stock obj = new stock();
	//try{
	do//main Menu
	{
		System.out.print("\n\n*************************Main Menu*******************************\n\n");
		System.out.print("\nEnter 1 To Add New Item To the List\n");
		System.out.print("\nEnter 2 To Change the Rate of Item Already in the List\n");
		System.out.print("\nEnter 3 To Issue an Item with Respect to ItemCode\n");
		System.out.print("\nEnter 4 To Receive an Item with respect to ItemCode\n");
		System.out.print("\nEnter 5 To display the Price of an item with respect to Itemcode\n");
		System.out.print("\nEnter 6 To display the QuanTity of an Item with respect to Itemcode\n");
		System.out.print("\nEnter 7 To Display the number of items in List Having price Greater than a given amount");
		System.out.print("\nEnter -1 To EXIT !!\n");
		System.out.print("\n**********************************************************************\n\n");
		choice=sc.nextInt();

		switch(choice)
		{	case -1:
				System.out.print("\nEXITING!! Thank You !!");
				break;
			case 1:
				obj.add_to_list();//CAll for adding to list
				break;
			case 2:
				obj.rate_change();//call for rate change
				break;
			case 3:
				obj.issue(-1);//call for issuing item
				break;
			case 4:
				obj.receive(-1);//call for receiving item
				break;
			case 5:
				obj.show_price();//call for displaying price
				break;
			case 6:
				obj.show_quantity();//call for showing quantity
				break;
			case 7:
				int l=obj.noOfItemsGreaterThanAmount();
				System.out.println("The Number of items are "+l);
			default :
				System.out.print("WRONG CHOICE !!");//Exception handling
				break;
		}
	}while(choice!=-1);
	//}
	/*catch(IOException e){System.out.println(e);}
	finally{
		System.out.println("\nEnd of Program !!\n");
		}
	*/
	return ;
}//End of main()
}
//Compiling command
