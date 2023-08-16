package com.basejava.webapp;

import java.util.*;

/**
 * реализовать метод через стрим int minValue(int[] values).
 * Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
 * составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
 * Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
 * <p>
 * реализовать метод List<Integer> oddOrEven(List<Integer> integers)
 * если сумма всех чисел нечетная - удалить все нечетные, если четная - удалить все четные.
 * Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
 */
public class MainStream {

    public static void main(String[] args) {
        int[] array = new int[]{2, 9, 5, 6, 2, 1, 8, 1, 7, 1};
        System.out.println(minValue(array));


        List<Integer> list = converter(array);
        System.out.println(oddOrEven(list));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).sorted().distinct().reduce(0, (a, b) -> (a * 10) + b);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private static List<Integer> oddOrEven(List<Integer> integers) {
        Integer num = integers.stream().reduce(Integer::sum).get();
        integers.removeIf(s -> (num % 2 == 0) == (s % 2 == 0));

        return integers;
    }

    private static List<Integer> converter(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int j : array) {
            list.add(j);
        }
        return list;
    }
}
