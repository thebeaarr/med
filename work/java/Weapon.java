public class Weapon extends Item
{
	private String type ;
	private int damage ;
	public Weapon(String name , int quantity , String type , int damage )
	{
		super(name , quantity);
		this.type = type ;
		this.damage = damage ;
	}

	public String getType()
	{
		return this.type ;
	}


	public int getDamage()
	{
		return this.damage;
	}
	@Override 
	public String toString()
	{
		return super.toString() + " type : " + type ;
	}
}