package com.chen.redis;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 观察一下随机整数的数量和 k 值的关系。
 */
public class PfTest {

  static class BitKeeper {
    private int maxbits;



    public void random() {
      long value = ThreadLocalRandom.current().nextLong(2L << 32);
      int bits = lowZeros(value);
      if (bits > this.maxbits) {
        this.maxbits = bits;
      }
    }

    /**
     * 算低位零的个数
     * @param value
     * @return
     */
    private int lowZeros(long value) {
      int i = 1;
      for (; i < 32; i++) {
        if (value >> i << i != value) {
          break;
        }
      }
      return i - 1;
    }
  }

  static class Experiment {
    private int n;
    private BitKeeper keeper;

    public Experiment(int n) {
      this.n = n;
      this.keeper = new BitKeeper();
    }

    public void work() {
      for (int i = 0; i < n; i++) {
        this.keeper.random();
      }
    }

    public void debug() {
      System.out.printf("%d %.2f %d\n", this.n, Math.log(this.n) / Math.log(2), this.keeper.maxbits);
    }
  }

  public static void main(String[] args) {
    for (int i = 1000; i < 100000; i += 100) {
      Experiment exp = new Experiment(i);
      exp.work();
      exp.debug();
    }
  }

}
