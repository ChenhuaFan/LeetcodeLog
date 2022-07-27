import java.lang.reflect.Array;
import java.text.NumberFormat.Style;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Solution {

    // q1
    public char repeatedCharacter(String s) {
        Set<Character> his = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (his.contains(c))
                return c;
            his.add(c);
        }
        return '?';
    }

    // q2
    public int equalPairs(int[][] grid) {
        Map<String, Integer> his = new HashMap<>();
        int N = grid.length;
        if (N == 0)
            return 1;
        int[] tmp = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[j] = grid[i][j];
            }
            String key = Arrays.toString(tmp);
            if (his.containsKey(key)) {
                his.put(key, his.get(key) + 1);
            } else {
                his.put(key, 1);
            }
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[j] = grid[j][i];
            }
            String key = Arrays.toString(tmp);
            if (his.containsKey(key))
                res += his.get(key);
        }
        return res;
    }

    // q3
    class FoodRatings {

        class Food {
            int rate;
            String name;
            String cuisines;

            Food(String n, String c, int r) {
                rate = r;
                name = n;
                cuisines = c;
            }
        }

        class SortbyFood implements Comparator<String> {

            @Override
            public int compare(String food1, String food2) {
                Food f1 = food2Food.get(food1),
                        f2 = food2Food.get(food2);
                if (f1.rate == f2.rate)
                    return f2.name.compareTo(f1.name);
                return f1.rate - f2.rate;
            }

        }

        Map<String, TreeMap<String, Food>> data; // cuisines -> Foods (sorted map);
        Map<String, String> food2cuisines; // food -> cuisines;
        Map<String, Food> food2Food; // food: str -> food: Food;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            data = new HashMap<>();
            food2cuisines = new HashMap<>();
            food2Food = new HashMap<>();
            for (int i = 0; i < foods.length; i++) {
                String food = foods[i],
                        cuisine = cuisines[i];
                int rate = ratings[i];
                food2cuisines.put(food, cuisine);
                Food _food = new Food(food, cuisine, rate);
                food2Food.put(food, _food);
                if (data.containsKey(cuisine)) {
                    data.get(cuisine).put(food, _food);
                } else {
                    TreeMap<String, Food> tm = new TreeMap<>(new SortbyFood());
                    tm.put(food, _food);
                    data.put(cuisine, tm);
                }
            }
        }

        /**
         * logN
         * 
         * @param food
         * @param newRating
         */
        public void changeRating(String food, int newRating) {
            TreeMap<String, Food> tm = data.get(food2cuisines.get(food));
            Food f = tm.get(food);
            tm.remove(food);
            System.out.println(food2Food.get(food).rate);
            f.rate = newRating;
            System.out.println(food2Food.get(food).rate);
            tm.put(food, f);
        }

        /*
         * O1
         */
        public String highestRated(String cuisine) {
            return data.get(cuisine).lastEntry().getValue().name;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        // test
        // String str = "abccbaacz";
        // System.out.println(s.repeatedCharacter(str));
        // //
        // int[][] grid = { { 3, 1, 2, 2 }, { 1, 4, 4, 5 }, { 2, 4, 2, 2 }, { 2, 4, 2, 2
        // } };
        // System.out.println(s.equalPairs(grid));
        String[] foods = { "kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi" },
                cuisines = { "korean", "japanese", "japanese", "greek", "japanese", "korean" };
        int[] ratings = { 9, 12, 8, 15, 14, 7 };
        FoodRatings fr = s.new FoodRatings(foods, cuisines, ratings);
        System.out.println(fr.highestRated("korean"));
        System.out.println(fr.highestRated("japanese"));
        fr.changeRating("sushi", 16);
        fr.changeRating("ramen", 16);
    }
}
