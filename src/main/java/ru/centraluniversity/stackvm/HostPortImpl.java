package ru.centraluniversity.stackvm;

public class HostPortImpl extends HostPort implements UrlSource {
  private final String host;
  private final int port;

  HostPortImpl(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public String host() {
    return host == null ? "127.0.0.1" : host;
  }

  public int port() {
    return port == 0 ? defaultPort() : port;
  }

  private int defaultPort() {
    return 80;
  }

  public String toUrl() {
    return "http://%s:%d/".formatted(host(), port());
  }
}
