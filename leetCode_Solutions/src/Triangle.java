import java.util.Arrays;


/*完成一个程序：判断三个整形边长能否构成三角形；
如果是三角形，则判断它是否是直角三角形、等边三角形、等腰三角形、锐角三角形、钝角三角形。
1)声明函数；
2)实现函数：根据输入的三个边长判断是否可以构成一个三角形；
3)在main中根据函数的返回值判断是否是三角形，
如果构成三角形则判断是否为直角三角形、等边三角形、等腰三角形、锐角三角形、钝角三角形，并给出结论*/
public class Triangle {
    public static void main(String[] args) {
        System.out.println(new Triangle().isTriangle(1, 2, 3));

    }

    private String isTriangle(int edge1, int edge2, int edge3) {
        StringBuilder sb = new StringBuilder();
        if (edge1 <= 0 || edge2 <= 0 || edge3 <= 0) {

            return sb.append("参数错误！").toString();
        }
        int edges[] = new int[]{edge1, edge2, edge3};
        Arrays.sort(edges);

        //判断是否为三角形
        if (edges[0] + edges[2] > edges[1] && edges[0] + edges[1] > edges[2]) {
            sb.append("能构成三角形!");
            if (edge1 == edge2 && edge3 == edge2){
                return  sb.append("且为等边三角形！").toString();
            }

           if (edges[0] == edges[1] && edges[0] != edges[2] || edges[1] == edges[2] && edges[0] != edges[2])//等腰三角形：a,a,b 或 a,b,b
                sb.append("且为等腰三角形！");

            if (edges[0] * edges[0] + edges[1] * edges[1] == edges[2] * edges[2])
                sb.append("且为直角三角形！");
            else if (edges[0] * edges[0] + edges[1] * edges[1] > edges[2] * edges[2] && edges[0] * edges[0] + edges[2] * edges[2] > edges[1] * edges[1])
                sb.append("且为锐角三角形！");
            else
                sb.append("且为钝角三角形！");
            return sb.toString();
        }
        return sb.append("无法构成三角形！").toString();
    }
}

