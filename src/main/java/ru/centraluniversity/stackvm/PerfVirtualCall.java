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
public class PerfVirtualCall {
  private static final int SIZE = 12_000;
  private int arg;
  private EvalNode[] mono;
  private EvalNode[] bi;
  private EvalNode[] mega;

  @Setup(Level.Iteration)
  public void setup() {
    arg = 10;
    mono = new EvalNode[SIZE];
    for (int i = 0; i < SIZE; i++) {
      mono[i] = new NodeMono();
    }
    bi = new EvalNode[SIZE];
    for (int i = 0; i < SIZE; i += 2) {
      bi[i] = new NodeBi0();
      bi[i + 1] = new NodeBi1();
    }
    // bi = new EvalNode[SIZE];
    // for (int i = 0; i < SIZE / 2; i++) {
    //   bi[i] = new NodeBi0();
    // }
    // for (int i = 0; i < SIZE / 2; i++) {
    //   bi[SIZE / 2 + i] = new NodeBi1();
    // }
    mega = new EvalNode[SIZE];
    for (int i = 0; i < SIZE; i += 3) {
      mega[i] = new NodeMega0();
      mega[i + 1] = new NodeMega1();
      mega[i + 2] = new NodeMega2();
    }
  }

  @Benchmark
  public void perfMonomorphic(Blackhole bh) {
    for (var f : mono) {
      bh.consume(f.eval(arg));
    }
  }

  @Benchmark
  public void perfBimorphic(Blackhole bh) {
    for (var f : bi) {
      bh.consume(f.eval(arg));
    }
  }

  @Benchmark
  public void perfMegamorphic(Blackhole bh) {
    for (var f : mega) {
      bh.consume(f.eval(arg));
    }
  }
}
