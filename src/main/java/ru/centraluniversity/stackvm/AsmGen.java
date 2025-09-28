package ru.centraluniversity.stackvm;

import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public final class AsmGen {
  private final String classOutputPath;

  public AsmGen(String classOutputPath) {
    this.classOutputPath = classOutputPath;
  }

  void generateInvokeDynamic() {
    final var packageName = getClass().getPackageName().replace('.', '/') + "/";
    final var baseClassName = packageName + "IntFunc";
    final var mainClassName = packageName + "InDyGen";

    var cw = new ClassWriter(0);

    // Create a class
    cw.visit(V21, ACC_PUBLIC, mainClassName, null, baseClassName, null);

    // Default constructor
    {
      var ctor = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
      ctor.visitCode();
      ctor.visitVarInsn(ALOAD, 0);
      ctor.visitMethodInsn(INVOKESPECIAL, baseClassName, "<init>", "()V", false);
      ctor.visitInsn(RETURN);
      ctor.visitMaxs(1, 1);
      ctor.visitEnd();
    }

    // Method "apply" with invoke dynamic
    var method = cw.visitMethod(ACC_PUBLIC, "apply", "(I)I", null, null);
    method.visitCode();
    method.visitVarInsn(ILOAD, 1);
    var bsm =
        new Handle(
            H_INVOKESTATIC,
            packageName + "AsmGen",
            "bootstrapInDy",
            MethodType.methodType(
                    CallSite.class,
                    MethodHandles.Lookup.class,
                    String.class,
                    MethodType.class,
                    int.class)
                .toMethodDescriptorString(),
            false);
    method.visitInvokeDynamicInsn("myIndyAdd", "(I)I", bsm, 101);
    method.visitInsn(IRETURN);
    method.visitMaxs(2, 3);

    cw.visitEnd();

    // Get bytecode as array
    byte[] bytes = cw.toByteArray();

    // Write to file
    try (var os = new FileOutputStream(classOutputPath + mainClassName + ".class")) {
      os.write(bytes);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      var loader =
          new ClassLoader() {
            Class<?> define(byte[] bytes) {
              return defineClass(null, bytes, 0, bytes.length);
            }
          };

      Class<?> clazz = loader.define(bytes);
      var lambda = (IntFunc) clazz.getDeclaredConstructor().newInstance();
      System.out.println(lambda.apply(12));
      System.out.println(lambda.apply(13));
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  public static CallSite bootstrapInDy(
      MethodHandles.Lookup lookup, String name, MethodType methodType, int extra) {
    try {
      final MethodHandle biMethod;
      switch (name) {
        case "myIndyAdd":
          biMethod =
              lookup.findStatic(
                  AsmGen.class,
                  "myAddMethod",
                  MethodType.methodType(int.class, int.class, int.class));
          break;
        case "myIndyMul":
          biMethod =
              lookup.findStatic(
                  AsmGen.class,
                  "myMulMethod",
                  MethodType.methodType(int.class, int.class, int.class));
          break;
        default:
          throw new RuntimeException("Not found: " + name);
      }
      return new ConstantCallSite(MethodHandles.insertArguments(biMethod, 0, extra));
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  public static int myAddMethod(int a, int b) {
    return a + b;
  }

  public static int myMulMethod(int a, int b) {
    return a * b;
  }
}
