package ru.centraluniversity.stackvm;

public class Invocation {
  static String calls(HostPort hp) {
    return hp.host() + ":" + hp.port();
  }

  static String calls(UrlSource hp) {
    return hp.toUrl();
  }
}
