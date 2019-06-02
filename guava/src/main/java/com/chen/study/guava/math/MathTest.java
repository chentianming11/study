package com.chen.study.guava.math;

import com.google.common.math.IntMath;
import org.junit.Test;

import java.math.RoundingMode;

/**
 * Guava Math 主要处理三种整数类型：int、long 和 BigInteger
 * 这三种类型的运算工具类分别叫做 IntMath、LongMath 和 BigIntegerMath。
 * @author 陈添明
 * @date 2019/6/2
 */
public class MathTest {

    /**
     * 有溢出检查的运算
     * Guava Math 提供了若干有溢出检查的运算方法：
     * 结果溢出时，这些方法将快速失败而不是忽略溢出
     */
    @Test
    public void test1() {
        // throws ArithmeticException
        IntMath.checkedAdd(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * 实数运算
     * IntMath、LongMath 和 BigIntegerMath 提供了很多实数运算的方法，并把最终运算结果舍入成整数。
     * 这些方法接受一个 java.math.RoundingMode 枚举值作为舍入的模式：
     *   DOWN：向零方向舍入（去尾法）
         UP：远离零方向舍入
         FLOOR：向负无限大方向舍入
         CEILING：向正无限大方向舍入
         UNNECESSARY：不需要舍入，如果用此模式进行舍入，应直接抛出 ArithmeticException
         HALF_UP：向最近的整数舍入，其中 x.5 远离零方向舍入
         HALF_DOWN：向最近的整数舍入，其中 x.5 向零方向舍入
         HALF_EVEN：向最近的整数舍入，其中 x.5 向相邻的偶数舍入
     */
    @Test
    public void test2() {
        System.out.println(IntMath.divide(10, 3, RoundingMode.DOWN));
        System.out.println(IntMath.divide(10, 3, RoundingMode.UP));
        System.out.println(IntMath.divide(10, 3, RoundingMode.FLOOR));
        System.out.println(IntMath.divide(10, 3, RoundingMode.UNNECESSARY));
    }
}
