/*
 * Shamim Bin Zahid
 * Roll 43
 * Lab 01
 */
import java.util.Scanner;
public class Main {
    private static String alignmentA,alignmentB;
    private static int lenA,lenB;
    private static int match, mismatch, gap;
    private static int score=0;
    private static int[][] scores= new int[100][100]; 

    public static int SimilarityCheck(char a, char b){
        if(a==b) 
            return match;
        else if(a=='_' || b=='_') 
            return gap;
        else 
            return mismatch;
    }

    public static void CalculateScores(String sequenceA,String sequenceB){
        int align, delete, insert;
        int lenA = sequenceA.length();
        int lenB = sequenceB.length();
        for (int i=0; i<=lenA; i++){
            scores[i][0] = gap * i;
        }
        for(int j=0; j<=lenB; j++){
            scores[0][j] = gap * j;
        }
        for(int i=1; i<=lenA; i++){
            for (int j=1; j<=lenB; j++){
                align = scores[i-1][j-1] + SimilarityCheck(sequenceA.charAt(i-1),sequenceB.charAt(j-1));
                delete = scores[i-1][j] + gap;
                insert = scores[i][j-1] + gap;
                scores[i][j] = Math.max(align,Math.max(delete,insert));
            }
        }
    }

    public static void PrintScores(String sequenceA, String sequenceB){
        int lenA = sequenceA.length();
        int lenB = sequenceB.length();
        System.out.print("\t"+"\t");
        for(int j=0; j<lenB; j++){
            System.out.print(sequenceB.charAt(j)+"\t");
        }
        System.out.println();
        for(int i=0; i<=lenA; i++){
            if(i==0) {
                System.out.print("\t");
            }
            if(i>0){
                System.out.print(sequenceA.charAt(i-1)+"\t");
            }
            for(int j=0; j<=lenB; j++){
                System.out.print(scores[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public static void GlobalAlignment(String sequenceA, String sequenceB){
        String ansAlignA="";
        String ansAlignB="";
        int i = sequenceA.length();
        int j = sequenceB.length();
        while(i>0 || j>0){
            if(i>0 && j>0 && scores[i][j]==scores[i-1][j-1]+SimilarityCheck(sequenceA.charAt(i-1),sequenceB.charAt(j-1))){
                ansAlignA += sequenceA.charAt(i-1);
                ansAlignB += sequenceB.charAt(j-1);
                i--;
                j--;
            }
            else if(i>0 && scores[i][j]==scores[i-1][j]+gap){
                ansAlignA += sequenceA.charAt(i-1);
                ansAlignB += "_";
                i--;
            }
            else{
                ansAlignA += "_";
                ansAlignB += sequenceB.charAt(j-1);
                j--;
            }
        }
        int lenA = ansAlignA.length();
        int lenB = ansAlignB.length();
        System.out.print("Sequence A \t");
        for(int k=lenA-1; k>=0; k--){
            System.out.print(ansAlignA.charAt(k));
        }
        System.out.println();
        System.out.print("Sequence B \t");
        for(int k=lenB-1; k>=0; k--){
            System.out.print(ansAlignB.charAt(k));
        }
        System.out.println();
        for(int k=lenA-1;k>=0;k--){
            score += SimilarityCheck(ansAlignA.charAt(k),ansAlignB.charAt(k));
        }
        System.out.println("Score value of the global alignment is: "+score);
    }

    public static void main(String[] args){
        alignmentA = "CTCGCAGC";
        alignmentB = "CATTCAG";
        System.out.println("Sequence A: "+alignmentA);
        System.out.println("\nSequence B: "+alignmentB);
        lenA = alignmentA.length();
        lenB = alignmentB.length();
        match = +10;
        mismatch = -2;
        gap = -5;
        System.out.println("\nMatch Point: "+match);
        System.out.println("\nMismatch Penalty: "+mismatch);
        System.out.println("\nGap Penalty: "+gap);

        CalculateScores(alignmentA, alignmentB);

        System.out.println("\n\nGlobal Alignment Table");
        PrintScores(alignmentA, alignmentB);
        
        System.out.println("\n\n");
        GlobalAlignment(alignmentA, alignmentB);
    }
}