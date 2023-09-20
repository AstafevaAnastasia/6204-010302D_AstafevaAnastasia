public class MyFirstClass {
    public static void main(String[] s) {
        MySecondClass o = new MyFirstClass().new MySecondClass(5, 6);
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

    class MySecondClass {
        int num1;
        int num2;

        public MySecondClass(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        public int getNum1() {
            return num1;
        }

        public void setNum1(int num1) {
            this.num1 = num1;
        }

        public int getNum2() {
            return num2;
        }

        public void setNum2(int num2) {
            this.num2 = num2;
        }

        public int mult() {
            return num1 * num2;
        }
    }
}
