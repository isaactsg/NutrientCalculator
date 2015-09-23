/* Isaac Wismer
 *  July 15 2015
 */
package nutrientcalculator;

import java.util.Random;

/**
 *
 * @author isaac
 */
public class SortingAlgorithm {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String[] listLong = new String[1000];//1000 strings to sort
        for (int i = 0; i < 1000; i++) {//make 100 random strings
            Random rand = new Random();//new seed
            int rando = rand.nextInt((20 - 1) + 1) + 1;//generate random length 1-20 inc
            String add = "";
            for (int j = 0; j < rando; j++) {//generate each char
                int r = rand.nextInt((2 - 1) + 1) + 1;//randomize upper/lower case
                if (r == 1) {
                    int randomNum = rand.nextInt((90 - 65) + 1) + 65;//generate uppercase
                    add += (char) randomNum + "";
                } else {
                    int randomNum = rand.nextInt((122 - 97) + 1) + 97;//generate lowercase
                    add += (char) randomNum + "";//add to string
                }
            }
            listLong[i] = add;//add to list
        }
        String[] list = {"WOOD", "TREES", "LEAF", "BARK", "WORLD", "TREE", "ARMS", "ARM", "ARMY", "A", "AA", "AAA", "ABA"};
        sort(list);
    }

    /**
     *
     * @param list
     */
    public static void sort(String[] list) {
        for (int j = 0; j < list.length; j++) {//repeat the sort the length of the string (so the bottom could get to the top or vice versa
            for (int i = 0; i < list.length - 1; i++) {//go through the list once
                if (getChar(list[i], 0) > getChar(list[i + 1], 0)) {//if the once above is lower in the alphabet then flip
                    String temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                } else if (getChar(list[i], 0) == (int) list[i + 1].charAt(0)) {//if theyre the same then go deeper
                    int shortest = Math.min(list[i].length(), list[i + 1].length());//get the length of the shortest string
                    for (int k = 1; k < shortest; k++) {
                        if (getChar(list[i], k) > getChar(list[i + 1], k)) {//sheck that the next letter is lower
                            String temp = list[i];
                            list[i] = list[i + 1];
                            list[i + 1] = temp;
                            k = shortest + 1;
                        } else if (getChar(list[i], k) < getChar(list[i + 1], k)) {//if its lower stop, if its the same coninue
                            k = shortest + 1;
                        }
                    }
                    if (list[i].substring(0, shortest).equals(list[i + 1].substring(0, shortest))) {//if the strings are the same, one just has extra letters then put the longest one after (arm, army)
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

    /**
     *
     * @param s
     * @param index
     * @return
     */
    public static int getChar(String s, int index) {
        if ((s.charAt(index) < 65 || (s.charAt(index) > 90 && s.charAt(index) < 97) || s.charAt(index) > 122) && s.charAt(index) != 32) {
            try {
                throw new Exception("Incompatable String'" + s.charAt(index) + "'");
            } catch (Exception ex) {
               // System.out.println("Error: " + ex.toString());
            }
        }
        if ((int) s.charAt(index) > 96) {//if its lowercase, make it look like upper
            return (int) s.charAt(index) - 32;
        } else {
            return (int) s.charAt(index);
        }
    }

}
