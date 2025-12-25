public class Main
{
	public static void main(String[] args)
	{
		Inventory inventory = new Inventory();
		Weapon weapon = new Weapon("Weapon" , 30 , "aka" , 100);
		Fruit fruit = new Fruit("Fuji" , "Apple" , 20);
		Item item = new Item("Generic item" , 10 );
		inventory.addItem(item);
		inventory.addItem(fruit);
		inventory.addItem(weapon);
		inventory.displayInventory();
	}
}