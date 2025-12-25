public class Item
{
	protected  String name;
	protected  int quantity;

	@Override
	public String toString()
	{
		return "name : " + this.name + " quantity : " + this.quantity ;
	}

	public Item(String name , int quantity)
	{
		this.name  = name ;
		this.quantity = quantity;
	}
	public String getName()
	{
		return this.name ;
	}
	public int getQuantity()
	{
		return this.quantity;
	}
}