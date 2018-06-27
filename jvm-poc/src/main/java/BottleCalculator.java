public class BottleCalculator {

    private int totalBottles;//瓶子数量
    private int emptyBottles;//空瓶数量
    private int bottleCaps;//瓶盖数量

    /**
     * 算法：一元钱可买一瓶子水，2个空瓶子可以换一瓶子水，4个瓶盖可以换一瓶子水。
     * 请问10元可以得到多少瓶子水？
     */
    public static void main(String[] args) {
        BottleCalculator calculator = new BottleCalculator();
        calculator.totalBottles = 10;
        calculator.emptyBottles = 10;
        calculator.bottleCaps = 10;

        calculator.recursive0();
        System.out.println("totalBottles = " + calculator.totalBottles);
        System.out.println("emptyBottles = " + calculator.emptyBottles);
        System.out.println("bottleCaps = " + calculator.bottleCaps);
    }

    public void recursive0() {
        if (emptyBottles < 2 && bottleCaps < 4) {
            return;
        }
        recursive1();
        recursive2();

        recursive0();
    }

    public void recursive1() {
        if (emptyBottles < 2) {
            return;
        }
        totalBottles += emptyBottles / 2;
        bottleCaps += emptyBottles / 2;
        emptyBottles = emptyBottles / 2 + emptyBottles % 2;
        recursive1();
    }

    public void recursive2() {
        if (bottleCaps < 4) {
            return;
        }
        totalBottles += bottleCaps / 4;
        emptyBottles += bottleCaps / 4;
        bottleCaps = bottleCaps / 4 + bottleCaps % 4;
        recursive2();
    }

}
