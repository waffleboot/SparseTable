import java.io.*;
import java.util.*;

public class Solution {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int depth = 32 - Integer.numberOfLeadingZeros(n - 1);
        int[][] sptable = new int[depth][];
        for (int i = 0; i < depth; i++) {
            sptable[i] = new int[n];
        }
        for (int i = 0; i < n; i++) {
            sptable[0][i] = scan.nextInt();
        }
        for (int k = 1, size = 1; k < depth; k++, size *= 2) {
            for (int i = 0; i < n - 2 * size + 1; i++) {
                sptable[k][i] = Math.max(sptable[k-1][i],sptable[k-1][i + size]);
            }
        }
//        for (int k = 0; k < depth; k++) {
//            System.out.println(Arrays.toString(sptable[k]));
//        }
        for (int i = 0; i < m; i++) {
            int size = scan.nextInt();
            int d = 31 - Integer.numberOfLeadingZeros(size);
            int globalMin = Integer.MAX_VALUE;
            for (int a = 0; a < n - size + 1; a++) {
                int b = a + size - 1;
                int x = a;
                int y = b - (1<<d) + 1;
                //System.out.println("a=" + a + " b=" + b + " d=" + d + " x=" + x + " y=" + y);
                int localMax = x == y ? sptable[d][x] : Math.max(sptable[d][x],sptable[d][y]);
                if (localMax < globalMin) globalMin = localMax;
            }
            System.out.println(globalMin);
        }
    }
