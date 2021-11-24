import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
//内容二 众数问题
public class Solution_2 {

/*
    description：使用映射 存储元素（Key），元该素的重数（Value）
                 考虑到众数可能多个，在方法中对上面的映射进行遍历，将结果返回到一个新的映射中
    param ：arr 数组
    return ：Map集合 Key 是该数组的众数， Value是该众数的重数
 */
    public static Map<Integer, Integer> majorityElement(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        TreeMap<Integer, Integer> ret = new TreeMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i]) + 1);
            else
                map.put(nums[i], 1);
        }
        int majorityElement = nums[0];

        for (Integer key : map.keySet()) {
            if (map.get(majorityElement) < map.get(key))
                majorityElement = key;
        }
        ret.put(majorityElement, map.get(majorityElement));
        //数组中可能含有多个众数，所以再遍历一次
        for (Integer key : map.keySet()) {
            if (map.get(majorityElement) == map.get(key))
                ret.put(key, map.get(key));
        }

        return ret;

    }

// 主函数 测试用例，可直接运行
    public static void main(String[] args) {
        int arr[] = new int[20];
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            arr[i] = random.nextInt(10);
        }

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        Map<Integer, Integer> ret = Solution_2.majorityElement(arr);

        for (Integer key : ret.keySet()) {
            System.out.println(key + "--" + ret.get(key));
        }
    }


}
