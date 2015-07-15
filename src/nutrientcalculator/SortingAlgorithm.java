/* Isaac Wismer
 *  July 15 2015
 */
package nutrientcalculator;

import java.util.Random;

public class SortingAlgorithm {

    public static void main(String[] args) {
        String[] listLong = new String[1000];
        for (int i = 0; i < 1000; i++) {
            Random rand = new Random();
            int rando = rand.nextInt((20 - 1) + 1) + 1;
            String add = "";
            for (int j = 0; j < rando; j++) {
                int randomNum = rand.nextInt((90 - 65) + 1) + 65;
                add += (char) randomNum + "";
            }
            listLong[i] = add;
        }
        String[] list = {"WOOD", "TREES", "LEAF", "BARK", "WORLD", "TREE", "ARMS", "ARM", "ARMY", "A", "AA", "AAA", "ABA"};
        sort(listLong);
    }

    public static void sort(String[] list) {
        for (int j = 0; j < list.length; j++) {
            for (int i = 0; i < list.length - 1; i++) {
                if ((int) list[i].charAt(0) > (int) list[i + 1].charAt(0)) {
                    String temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                } else if ((int) list[i].charAt(0) == (int) list[i + 1].charAt(0)) {
                    int longest = Math.max(list[i].length(), list[i + 1].length());
                    int shortest = Math.min(list[i].length(), list[i + 1].length());
                    for (int k = 1; k < shortest; k++) {
                        if ((int) list[i].charAt(k) > (int) list[i + 1].charAt(k)) {
                            String temp = list[i];
                            list[i] = list[i + 1];
                            list[i + 1] = temp;
                            k = longest + 1;
                        } else if ((int) list[i].charAt(k) < (int) list[i + 1].charAt(k)) {
                            k = longest + 1;
                        }
                    }
                    if (list[i].substring(0, shortest).equals(list[i + 1].substring(0, shortest))) {
                        if (list[i].length() > list[i + 1].length()) {
                            String temp = list[i];
                            list[i] = list[i + 1];
                            list[i + 1] = temp;
                        }
                    }
                }
            }
        }
//        for (int i = 0; i < list.length; i++) {
//            System.out.println(list[i]);
//        }
    }

}
