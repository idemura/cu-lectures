package ru.centraluniversity.stackvm;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 2)
@Measurement(iterations = 3, timeUnit = TimeUnit.SECONDS, time = 2)
public class PerfConstantFolding {
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  static int constJavaC(int x) {
    return (11 % 5) + x * (11 / 5);
  }

  @Benchmark
  public void testJavaC(Blackhole bh) {
    bh.consume(constJavaC(6));
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  static int constJIT(int x) {
    int a = 11;
    int b = 5;
    int k0 = a % b;
    int k1 = a / b;
    return k0 + x * k1;
  }

  @Benchmark
  public void testJIT(Blackhole bh) {
    bh.consume(constJIT(6));
  }
}
