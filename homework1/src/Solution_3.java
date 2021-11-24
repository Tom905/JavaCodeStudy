import java.util.Random;


//内容三 最近点问题
public class Solution_3 {

    // 内容三 用到的类
   static class Point {
        int x, y;

        Point() {
        }

        Point(int x, int y) {
            this.x = x;
            this.y = y;

        }

        public void print() {
            System.out.println("( " + this.x + "," + this.y + " )");
        }
    }

    //    内容三 最近点对问题(枚举法)
    /*description : 枚举法：依次计算两个点的距离，取最小的点
      params ：points (Point[]):点对
               N（int）：点对的数量
      return ：Point[] ：返回两个相距最短的点
    * */
    public static Point[] closestPoint(Point[] points, int N) {
        if (N < 0 || N == 1) return null;
        Point[] ret = new Point[2];
        ret[0] = new Point();
        ret[1] = new Point();
        //默认选一组点 作为最小值
        double minDistance = distance(points[0], points[1]);
        ret[0] = points[0];
        ret[1] = points[1];
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                double curDistance = distance(points[i], points[j]);
                if (curDistance < minDistance) {
                    ret[0] = points[i];
                    ret[1] = points[j];
                    minDistance = curDistance;
                }
            }
        }

        System.out.printf("最短距离为:%.2f\n", minDistance);
        return ret;
    }

    private static double distance(Point p1, Point p2) {
        return Math.pow((Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2)), 0.5);
    }


    public static void main(String[] args) {
        Random random = new Random();
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(random.nextInt(50), random.nextInt(50));
        }
        for (Point point : points) {
            point.print();
        }
        Point[] points1 = Solution_3.closestPoint(points, 10);
        System.out.println("---------------");
        for (Point point : points1) {
            point.print();
        }
    }

}
