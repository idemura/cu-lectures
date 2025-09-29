package ru.centraluniversity.stackvm;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 2)
@Measurement(iterations = 3, timeUnit = TimeUnit.SECONDS, time = 2)
public class PerfGVN {
  double x = Math.PI;
  double y = 0.45;

  public PerfGVN() {}

  @Benchmark
  public double testRedundant() {
    return Math.sin(x * (y + 1)) + Math.sin(x * (y + 1));
  }

  @Benchmark
  public double testOptimized1() {
    double t = x * (y + 1);
    return Math.sin(t) + Math.sin(t);
  }

  @Benchmark
  public double testOptimized2() {
    double t = Math.sin(x * (y + 1));
    return t + t;
  }
}
