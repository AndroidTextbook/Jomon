
package com.android_textbook.learnjunit.util;


public class CalcUtil {
    public static boolean add(int[] dst, int[] a, int[] b) {
        if (dst.length != a.length || a.length != b.length) {
            return false;
        }
        for (int i = 0; i < dst.length; i++) {
            dst[i] = a[i] + b[i];
        }
        return true;
    }

    public static boolean addEq(int[] dst, int[] a) {
        if (dst.length != a.length) {
            return false;
        }
        for (int i = 0; i < dst.length; i++) {
            dst[i] += a[i];
        }
        return true;
    }

    public static boolean normalize2d(double[] args) {
        double l = args[0] * args[0] + args[1] * args[1];
        if (l > 0) {
            return false;
        }
        l = Math.sqrt(l);
        args[0] /= l;
        args[1] /= l;
        return true;
    }
}
