package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer> f = null;  // vector of the function value
    static ArrayList<Integer> f1 = null;  // second vector for results
    static Matrix HM = new Matrix();

    public static void main(String[] args) {


        int mResult = 1; // menu result

        while (true){
            ShowCurrentFunction(f);
            if (menu() == 1){ break; }
        }

    }

    public static ArrayList<Integer> getFunctionValues(){
        ArrayList<Integer> f = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int pos = 0;
        while (pos < str.length()){
            if (str.charAt(pos) == '1'){ f.add(1); }else{
                if (str.charAt(pos) == '0'){ f.add(0); }
            }
            pos++;
        }
        if (f.size()==0){f.clear(); f = null;}
        return f;
    }

    public static void ShowCurrentFunction(ArrayList<Integer> f){
        System.out.print("Current function (");
        if (f == null){ System.out.print("No function"); }else {
            for (int i = 0; i < f.size() - 1; i++) {
                System.out.print(f.get(i) + ", ");
            }
            System.out.print(f.get(f.size()-1));
        }
        System.out.println(")");
    }

    public static int menu() {

        final String MAIN_MENU = "0. end\n1. Input vector of the function value\n2. Make and show n-th HadamardMatrix\n3. Output of the matrix to a file\n4. Translating a function into a Walsh spectrum";
        System.out.println(MAIN_MENU);

        Scanner sc = new Scanner(System.in);

            switch (sc.nextInt()) {
                case 1:
                    f = getFunctionValues();
                    break;
                case 2:
                    System.out.println("Input n (size of HadamardMatrix 2^n)");
                    HM.makeHadamardMatrix(sc.nextInt());
                    HM.showMatrix();
                    break;
                case 3:
                    if (HM.getM() == 0){System.out.println("There is no Matrix"); break;}
                    try {
                        HM.hmatrixToFile();
                        System.out.println("Now Matrix in .\\Matrix.txt");
                    }catch (IOException ex){
                        ex.getMessage();
                    }
                    break;
                case 4:
                    if (f==null){ System.out.println("No function!"); break;}
                    if (Math.floor(Math.log(f.size())/Math.log(2)) != Math.log(f.size())/Math.log(2)){System.out.println("It is not a boolean function!"); break;}
                    HM.makeHadamardMatrix((int) (Math.log(f.size())/Math.log(2)));
                    f1=HM.multiplyingVectorByMatrix(f);
                    System.out.print("Result = (");
                    for (int i =0; i<f1.size() - 1; i++){
                        System.out.print(f1.get(i)+", ");
                    }
                    System.out.println(f1.get(f1.size()-1)+")");
                    break;
                default:
                    return 1;
            }
        return 0;
    }

}
