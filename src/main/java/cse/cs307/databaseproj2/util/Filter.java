package cse.cs307.databaseproj2.util;

import java.sql.Timestamp;
import java.util.List;

public class Filter {
  private String key;
  private String value;
  private List<Timestamp> timeValue;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public List<Timestamp> getTimeValue() {
    return timeValue;
  }

  public void setTimeValue(List<Timestamp> timeValue) {
    this.timeValue = timeValue;
  }

  @Override
  public String toString() {
    return "Filter{" +
        "key='" + key + '\'' +
        ", value='" + value + '\'' +
        ", timeValue=" + timeValue +
        '}';
  }
}
