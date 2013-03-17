public class Arreglo2 {
	public static void main(String[] args) {
		int i[];
//		float f=5.1f;

		i = new int[] { 1, 2, 3 };

		for (int pos = 0; pos < i.length; pos++) {
			System.out.print(i[pos] + (pos < i.length - 1 ? "," : ""));
		}
		System.out.println();

		for (int pos = 0; pos < i.length; pos++) {
			if (pos > 0)
				System.out.print(",");
			System.out.print(i[pos]);
		}
		System.out.println();
//		System.out.println(f);
	}
}
