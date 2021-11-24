import java.util.Scanner;

/*
* 链接：https://ac.nowcoder.com/acm/problem/15403
来源：牛客网

题目描述
给你三组数列，分别为

现在给你一个式子：

然后我们可以将这个函数画在一个xoy直角坐标系下，x的范围为[0,100]，
* 当然我们可以得到一条曲线，现在你们涵哥让你们求出这个曲线的长度，结果保留两位小数
* */
public class Solution_6 {

    int n;
    static final double eps = 1e-8;
    double a[], b[], k[];

    Solution_6(int N) {
        a = new double[N];
        b = new double[N];
        k = new double[N];


    }

    private double fun(double x) {
        double t = 100;
        int i, id = 0;
        for (i = 1; i <= n; i++) {
            double now =  k[i]*(x-a[i])*(x-a[i])+b[i];
          // System.out.println("now ="+now);
            if (now < t) {
                t = now;
                id = i;
            }
        }

        t = 2 * k[id] * (x - a[id]);
        //System.out.println("t="+t);
        return Math.sqrt(1.0 + t * t);
    }

    private double simpson(double l, double r) {
        double mid = (l + r) / 2.0;
      /*  System.out.println("fun(l)="+fun(l));
        System.out.println("4*fun(mid)="+4*fun(mid));
        System.out.println("fun(r)="+fun(r));*/
        return (r - l) * (fun(l) + 4 * fun(mid) + fun(r)) / 6.0;
    }


    public double answer(double l, double r, double s, double EPS, int dep) {
        double mid = (l + r) / 2.0;
        double left = simpson(l, mid);
        double right = simpson(mid, r);
        if (dep >= 10 && Math.abs(left + right - s) <= eps * 15) return left + right + (left + right - s) / 15;

        return answer(l, mid, left, EPS / 2, dep + 1) + answer(mid, r, right, EPS / 2, dep + 1);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i, j;


        Solution_6 solution_6 = new Solution_6(110);
        solution_6. n = sc.nextInt();
        for (i = 1; i <=    solution_6. n; i++) {
            solution_6.k[i] = sc.nextDouble();
            solution_6.a[i] = sc.nextDouble();
            solution_6.b[i] = sc.nextDouble();
        }
        double l = 0, r = 100;
      //  System.out.println(solution_6.simpson(l, r));
        double ret = solution_6.answer(l, r, solution_6.simpson(l, r), eps, 1);

        System.out.printf("%.2f\n", ret);


    }

}
