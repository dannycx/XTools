package com.danny.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * @author danny
 * @since 2022/5/22
 */
public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            if (text == null || !text.contains(" ")) {
                continue;
            }
            String[] split = text.split(" ");
            int length = split.length;
            if (length < 1 || length > 1000) {
                continue;
            }
            Stack<Integer> stack = new Stack<>();
            try {
                for (String s : split) {
                    int number = Integer.parseInt(s);
                    if (stack.isEmpty()) {
                        stack.push(number);
                    } else {
                        Map<Integer, Stack<Integer>> map = getSum(number, stack);
                        Set<Integer> set = map.keySet();
                        int sum = 0;
                        for (Iterator<Integer> it = set.iterator(); it.hasNext(); ) {
                            sum = it.next();
                        }
                        stack = map.get(sum);
                    }
                }
            } catch (NumberFormatException e) {
                continue;
            }
            for (Integer i : stack) {
                System.out.print(i + " ");
            }
        }

    }

//    private static Map<Integer, Stack<Integer>> getSum(int number, Stack<Integer> stack) {
//        int sum = 0;
//        int index = 0;
//        for (int i = 0; i < stack.size(); i++) {
//            sum += stack.get(i);
//            if (number == sum) {
//                index = i;
//                break;
//            }
//        }
//        Stack<Integer> s = new Stack<>();
//        for (int i = index + 1; i < stack.size(); i++) {
//            s.push(stack.get(i));
//        }
//        if (sum == number) {
//            s.push(2 * number);
//        } else {
//            s.push(number);
//        }
//        Map<Integer, Stack<Integer>> map = new HashMap<>();
//        map.put(sum, s);
//        return map;
//    }

    public static void x3() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            if (text == null || !text.contains(" ")) {
                continue;
            }
            String[] split = text.split(" ");
            int length = split.length;
            if (length < 1 || length > 1000) {
                continue;
            }
            Stack<Integer> stack = new Stack<>();
            try {
                for (String s : split) {
                    int number = Integer.parseInt(s);
                    if (stack.isEmpty()) {
                        stack.push(number);
                    } else {
                        Map<Integer, Stack<Integer>> map = getSum(number, stack);
                        Set<Integer> set = map.keySet();
                        int sum = 0;
                        for (Iterator<Integer> it = set.iterator(); it.hasNext(); ) {
                            sum = it.next();
                        }
                        stack = map.get(sum);
                    }
                }
            } catch (NumberFormatException e) {
                continue;
            }
            for (Integer i : stack) {
                System.out.print(i + " ");
            }
        }

    }

    private static Map<Integer, Stack<Integer>> getSum(int number, Stack<Integer> stack) {
        int sum = 0;
        int index = 0;
        for (int i = 0; i < stack.size(); i++) {
            sum += stack.get(i);
            if (number == sum) {
                index = i;
                break;
            }
        }
        Stack<Integer> s = new Stack<>();
        for (int i = index + 1; i < stack.size(); i++) {
            s.push(stack.get(i));
        }
        if (sum == number) {
            s.push(2 * number);
        } else {
            s.push(number);
        }
        Map<Integer, Stack<Integer>> map = new HashMap<>();
        map.put(sum, s);
        return map;
    }


    public static void x2() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            if (text.matches(".*[^0-9a-z].*") || text.matches("^.*[0-9]+$")) {
                System.out.println("!error");
                continue;
            }
            String result = "";
            StringBuilder number = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                char tmp = text.charAt(i);
                if (tmp >= 'a' && tmp <= 'z') {
                    int numberCount = 0;
                    if (number.length() > 0) {
                        numberCount = Integer.parseInt(number.toString());
                    }
                    if (numberCount > 0) {
                        if (numberCount < 3) {
                            result = "!error";
                            break;
                        }
                        for (int j = 0; j < numberCount; j++) {
                            result += tmp;
                        }
                        number = new StringBuilder();
                    } else {
                        int count = 1;
                        for (int j = i; j < text.length(); j++) {
                            if (tmp != text.charAt(count)) {
                                break;
                            }
                            count++;
                        }
                        if (count > 3) {
                            result = "!error";
                            break;
                        } else {
                            for (int j = 0; j < count; j++) {
                                result += tmp;
                            }
                        }
                    }
                }
                if (tmp >= '0' && tmp <= '9') {
                    number.append(tmp);
                }
            }
            System.out.println(result);
        }
    }

    public static void x1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            if (text == null || !text.contains(",")) {
                continue;
            }
            String[] split = text.split(",");
            List<Integer> number = new ArrayList<>();
            try {
                for (String s : split) {
                    int x = Integer.parseInt(s);
                    if (x >= 1 && x <= Math.pow(10, 9)) {
                        number.add(x);
                    }
                }
            } catch (NumberFormatException e) {
                continue;
            }
            number.sort((o1, o2) -> o2 - o1);
            int size = number.size();
            if (size < 2) {
                continue;
            }
            int s = 0;
//            if (size % 2 == 0) {
//                s = number.get(size / 2) * (size / 2);
//                s = Math.max(s, number.get(size / 2 - 1) * (size / 2 - 1));
//            } else {
//                s = number.get(size / 2) * (size / 2);
//            }
            for (int i = 0; i < size; i++) {
                s = Math.max(s, number.get(i) * i);
            }
            System.out.println(s);
        }
    }
}
