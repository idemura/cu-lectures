package ru.centraluniversity.stackvm;

public abstract class HostPort implements UrlSource {
  public abstract String host();

  public abstract int port();
}
