package ru.centraluniversity.stackvm;

import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class AsmGen {
  private final String classOutputPath;

  public AsmGen(String classOutputPath) {
    this.classOutputPath = classOutputPath;
  }

  LambdaFunc generateLambda() {
    var cw = new ClassWriter(0);

    var className = "SquareLong";

    cw.visit(
        V21,
        ACC_PUBLIC,
        packageName(className),
        null,
        "java/lang/Object",
        new String[] {packageName("LambdaFunc")});

    // Default constructor
    visitMethod(
        cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null),
        method -> {
          method.visitCode();
          method.visitVarInsn(ALOAD, 0);
          method.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
          method.visitInsn(RETURN);
          method.visitMaxs(1, 1);
          method.visitEnd();
        });

    visitMethod(
        cw.visitMethod(ACC_PUBLIC, "apply", "(J)J", null, null),
        method -> {
          method.visitCode();
          method.visitVarInsn(LLOAD, 1);
          method.visitVarInsn(LLOAD, 1);
          method.visitInsn(LMUL);
          method.visitInsn(LRETURN);
          method.visitMaxs(4, 3);
          method.visitEnd();
        });

    cw.visitEnd();

    // Get bytecode as array
    byte[] bytes = cw.toByteArray();

    // Write to file
    try (var os = new FileOutputStream(classOutputPath + packageName(className + ".class"))) {
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
      return (LambdaFunc) clazz.getDeclaredConstructor().newInstance();
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  String packageName(String name) {
    return getClass().getPackageName().replace('.', '/') + "/" + name;
  }

  static void visitMethod(MethodVisitor m, Consumer<MethodVisitor> body) {
    body.accept(m);
  }
}
