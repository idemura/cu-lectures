package ru.centraluniversity.stackvm;

import org.openjdk.jmh.annotations.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 2)
@Measurement(iterations = 3, timeUnit = TimeUnit.SECONDS, time = 2)
public class PerfLocalVar {
  @State(Scope.Thread)
  public static class MyState {
    public int[] array;

    public MyState() {
      array = new int[100];
      int n = (int) (Math.random() * 10);
      Arrays.fill(array, n);
    }

    void iterateIndex() {
      for (int i = 0; i < array.length; i++) {
        consume(array[i]);
      }
    }

    void iterateIndexAndLocal() {
      var a = array;
      for (int i = 0; i < a.length; i++) {
        consume(a[i]);
      }
    }

    void iterateForEach() {
      for (int n : array) {
        consume(n);
      }
    }
  }

  @Benchmark
  public void testIndex(MyState state) {
    state.iterateIndex();
  }

  @Benchmark
  public void testIndexAndLocal(MyState state) {
    state.iterateIndexAndLocal();
  }

  @Benchmark
  public void testForEach(MyState state) {
    state.iterateForEach();
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  static void consume(int a) {
    // Nothing
  }
}
