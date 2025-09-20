package ru.centraluniversity.stackvm;

import java.time.LocalDate;

public record Pet(String name, LocalDate birthday, boolean vegetarian, Integer weight) {
  static Builder builder(String name) {
    return new Builder(name);
  }

  int weightOrZero() {
    return weight == null ? 0 : weight;
  }

  static final class Builder {
    private String name;
    private LocalDate birthday;
    private boolean vegetarian;
    private Integer weight;

    Builder(String name) {
      this.name = name;
    }

    Pet build() {
      return new Pet(name, birthday, vegetarian, weight);
    }

    Builder birthday(LocalDate birthday) {
      this.birthday = birthday;
      return this;
    }

    Builder birthday(boolean vegetarian) {
      this.vegetarian = vegetarian;
      return this;
    }

    Builder weight(int weight) {
      this.weight = weight;
      return this;
    }
  }
}
