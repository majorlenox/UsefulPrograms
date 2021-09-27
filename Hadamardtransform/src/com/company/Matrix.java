package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;

public class Matrix {

    private int n;
    private int m;
    private ArrayList<ArrayList<Integer>> M;

    public  Matrix(){
        n = 0;
        m = 0;
        M = null;
    }

    public Matrix(ArrayList<Integer> f, int n, int m){
        lineToMatrix(f, n, m);
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public void showMatrix (){
        if (M == null){return;}

        System.out.print("[");

    for (int i = 0; i<n; i++){

        for (int j = 0; j<m - 1; j++){
        System.out.print(M.get(i).get(j) + ", ");
        }
        System.out.print(M.get(i).get(m - 1));
        if (i != n-1){ System.out.print("\n ");}
    }

        System.out.println("]");
    }

    public void hmatrixToFile() throws IOException {

        Formatter f = new Formatter(".\\HMatrix.txt");
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (M.get(i).get(j)==1){f.format("  1"); }else{
                    f.format(" -1");
                }

            }
        f.format("\n");
        }
        f.close();

    }

    public void makeHadamardMatrix(int n){

        if (n < 1){return;}

        ArrayList<Integer> hPrev = new ArrayList<Integer>();
        hPrev.add(1);
        hPrev.add(1);
        hPrev.add(1);
        hPrev.add(-1);

        if (n == 1){ lineToMatrix(hPrev, 2, 2); return; }

        int n1 =  4;
        int n2 = (int) Math.pow(2, n);
        while (n1 <= n2){

            ArrayList<Integer> hCur = new ArrayList<Integer>();

            for (int i=0; i<n1; i++){
                for (int j=0; j<n1; j++){

                    if ((i<n1/2)&&(j<n1/2)){
                        hCur.add(hPrev.get(i*n1/2 + j));
                    }

                    if ((i<n1/2)&&(j>=n1/2)){
                        hCur.add(hPrev.get(i*n1/2 + j%(n1/2)));
                    }

                    if ((i>=n1/2)&&(j<n1/2)){
                        hCur.add(hPrev.get((i%(n1/2))*n1/2 + j));
                    }

                    if ((i >= n1/2)&&(j >= n1/2)){
                        hCur.add(-hPrev.get((i%(n1/2))*(n1/2) + j%(n1/2)));
                    }
                }
            }

            hPrev.clear();
            hPrev = hCur;

            n1*=2;
        }

        lineToMatrix(hPrev, n2, n2);
        this.m = this.n = n2;

    }

    public ArrayList<Integer> multiplyingVectorByMatrix(ArrayList<Integer> f){
        ArrayList<Integer> res = new ArrayList<Integer>();
        int a;
        if (n == f.size()){

            for (int i=0; i<m; i++){
                a = 0;
                for (int j=0; j<n; j++){
                    a+=M.get(i).get(j)*f.get(j);
                }
                res.add(a);
            }

        }else{
            if (m == f.size()){
                for (int i=0; i<n; i++){
                    a = 0;
                    for (int j=0; j<m; j++){
                        a+=M.get(i).get(j)*f.get(j);
                    }
                    res.add(a);
                }
            }else{
                return null;
            }
        }

        return res;
    }

    private void lineToMatrix(ArrayList<Integer> f, int n, int m){

        if ((f != null)&&(n>0)&&(m>0)) {
            if (f.size() != n * m){ return;}

            if (M == null){ M = new ArrayList<ArrayList<Integer>>(); }
            this.n = n;
            this.m = m;

            for (int i = 0; i < n; i++) {
                ArrayList<Integer> arr = new ArrayList<Integer>(n);
                for (int j = 0; j < m; j++) {
                    arr.add(f.get(j*m + i));
                }
                M.add(arr);
            }
        }
    }

}
