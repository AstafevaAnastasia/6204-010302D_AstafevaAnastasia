import myfirstpackage.MyFirstPackage;
public class MyFirstClass {
	public static void main(String[] s) {
		MyFirstPackage o = new MyFirstPackage(5, 6);
		System.out.println(o.mult());
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				o.setNum1(i);
				o.setNum2(j);
				System.out.print(o.mult());
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
