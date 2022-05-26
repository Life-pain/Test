import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//коммент 1
public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String str = scan.nextLine();
            String operand = isOperand(str);               //здесь содержится операнд

            //извлекаем цифры в строках
            String strA = str.substring(0, str.indexOf(operand)).trim();
            String strB = str.substring(str.indexOf(operand) + 1).trim();
            String sOrA = romanOrArabic(strA, strB);        //проверяем арабские или римские

            //преобразовываем в int
            int a = 0, b = 0, result = 0;
            if (sOrA.equals("is arabic")) {
                a = Integer.parseInt(strA);
                b = Integer.parseInt(strB);
            }
            if (sOrA.equals("is roman")) {
                a = RomanNumber.valueOf(strA).getArabic();
                b = RomanNumber.valueOf(strB).getArabic();
            }

            //проверяем есть ли операнд. проверяем, что оба римские или оба арабские. проверяем входят ли в рамки
            if ((operand == null) || (sOrA == null) || (a < 1) || (a > 10) || (b < 1) || (b > 10))
                throw new Exception();

            switch (operand) {          //совершаем арифм. операцию
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
            }
            ;
            if (sOrA.equals("is arabic")) System.out.println(result); // результат, если арабские
            if (sOrA.equals("is roman")) if (result < 1) throw new Exception();
            else System.out.println(arabicToRoman(result));
        }
    }
    public static String isOperand(String str) {
        System.out.println();
        if (str.indexOf("+") > 0) return "+";
        if (str.indexOf("-") > 0) return "-";
        if (str.indexOf("/") > 0) return "/";
        if (str.indexOf("*") > 0) return "*";
        return null;
    }   //вычисляем какой операнд

    public static String romanOrArabic(String a, String b) {
        List<String> roman = new ArrayList<>(Arrays.asList("I", "V", "X"));
        int isRoman1 = 0;
        int isRoman2 = 0;

        for (String x : roman) {
            if (a.contains(x)) isRoman1++;
            if (b.contains(x)) isRoman2++;
        }
        if ((isRoman1 > 0) && (isRoman2 > 0)) return "is roman"; else
            if ((isRoman1 > 0) ^ (isRoman2 > 0)) return null;


        List<String> arabic = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7","8","9"));
        int isArabic1 = 0;
        int isArabic2 = 0;
        for (String x:arabic) {
            if (a.contains(x)) isArabic1++;
            if (b.contains(x)) isArabic2++;
        }
        if( (isArabic1>0) && (isArabic2>0)) return "is arabic"; else
            if( (isArabic1>0) ^ (isArabic2>0)) return null;
        return null;
    } //римские, арабские или ошибка

    public static String arabicToRoman (int x){
        if (x==100) return "C";
        StringBuilder result = new StringBuilder();
        while (x>0){
            if (x>=90) {result.append("XC"); x-=90;continue;}
            if (x>=50) {result.append("L"); x-=50;continue;}
            if (x>=40) {result.append("XL"); x-=40;continue;}
            if (x>=10) {result.append("X"); x-=10;continue;}
            if (x==9) {result.append("IX"); x-=9;continue;}
            if (x>=5) {result.append("V"); x-=5;continue;}
            if (x>=4) {result.append("IV"); x-=4;continue;}
            if (x>=1) {result.append("I"); x--;}
        }
        return result.toString();
    }       //перевод из арабских в римские
}