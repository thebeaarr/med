public class Pro 
{
	public static void main(String[] args)
	{
		try
		{
			int a = 10 ;
			a = a / 0 ;
		}
		catch (ArithmeticException ee)
		{
			System.out.println("math m3a krk");
		}
		catch (Exception e)
		{
			System.out.println("printi wzid");
		}
		finally
		{
			System.out.println("merhba");
		}
	}
}