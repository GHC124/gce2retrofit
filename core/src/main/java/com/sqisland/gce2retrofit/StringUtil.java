package com.sqisland.gce2retrofit;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class StringUtil {
  public static String primitiveToObject(String type) {
    if ("boolean".equals(type)) {
      return "Boolean";
    }

    if ("int".equals(type)) {
      return "Integer";
    }

    if ("float".equals(type)) {
      return "Float";
    }

    if ("double".equals(type)) {
      return "Double";
    }

    return type;
  }

  public static String getPackageName(String baseUrl)
      throws URISyntaxException {
    URI uri = new URI(baseUrl);
    String domain = uri.getHost();
    String[] parts = domain.split("\\.");

    StringBuffer buf = new StringBuffer();
    for (int i = parts.length - 1; i >= 0; --i) {
      // Package name cannot have dashes. Replace with underscores.
      String part = parts[i].replace('-', '_');

      // Package name cannot start with a digit. Prepend with an underscore.
      if (part.charAt(0) >= '0' && part.charAt(0) <= '9') {
        buf.append('_');
      }

      buf.append(part);
      if (i != 0) {
        buf.append('.');
      }
    }
    return buf.toString();
  }

  public static String getPath(String packageName, String fileName) {
    return packageName.replace(".", "/") + File.separator + fileName;
  }
}