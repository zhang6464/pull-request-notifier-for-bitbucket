package se.bjurr.prnfb.settings;

import static se.bjurr.prnfb.admin.AdminFormValues.NAME;

import java.util.Map;

import com.google.common.base.Predicate;

public class PrnfbPredicates {
 public static Predicate<Map<String, String>> predicate(final String name) {
  return new Predicate<Map<String, String>>() {
   @Override
   public boolean apply(Map<String, String> input) {
    return input.get(NAME) != null && input.get(NAME).equals(name);
   }
  };
 }

 private PrnfbPredicates() {
 }

}
