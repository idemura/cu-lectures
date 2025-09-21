package ru.centraluniversity.stackvm;

public class StackMaps {
  String[] getKeys(String text) {
    var parts = text.split(";");
    var keys = new String[parts.length];
    for (int i = 0; i < parts.length; i++) {
      var s = parts[i];
      String key = s.substring(0, s.indexOf('='));
      keys[i] = key;
    }
    return keys;
  }
}
