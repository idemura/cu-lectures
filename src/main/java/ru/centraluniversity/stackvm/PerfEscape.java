package ru.centraluniversity.stackvm;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 2)
@Measurement(iterations = 3, timeUnit = TimeUnit.SECONDS, time = 2)
public class PerfEscape {
  @Param({"3"})
  int paramX;

  @Param({"4"})
  int paramY;

  private Set<Integer> set;
  private Integer boxedKey;
  private int primitiveKey;

  public PerfEscape() {}

  @Setup(Level.Iteration)
  public void setup() {
    set = new HashSet<>();
    for (int i = 0; i < 1_000; i++) {
      set.add(i);
    }
    boxedKey = 123;
    primitiveKey = 123;
  }

  public record Point(int x, int y) {}

  @Benchmark
  public void testNoEscape(Blackhole bh) {
    var p = new Point(paramX, paramY);
    bh.consume(p);
  }

  @Benchmark
  public void testEscape(Blackhole bh) {
    var p = new Point(paramX, paramY);
    bh.consume(p.x + p.y);
  }

  @Benchmark
  public void testBoxedKey(Blackhole bh) {
    bh.consume(set.contains(boxedKey));
  }

  @Benchmark
  public void testPrimitiveKey(Blackhole bh) {
    bh.consume(set.contains(primitiveKey));
  }
}
