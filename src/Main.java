
public class Main {
	
	public static boolean isPow2(int value) {
		if (value <= 0)
			return false;
		else
			return (value & (value - 1)) == 0;
	}

	public static void main(String[] args) {
		System.out.println(isPow2(1));
		System.out.println(isPow2(2));
		System.out.println(isPow2(3));
		System.out.println(isPow2(4));
		System.out.println(isPow2(5));
	}

}
