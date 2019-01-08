package com.hunter.utils;

import android.util.Log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The type Bus utils.
 */
public final class BusUtils {

    private static final Object NULL = new Object();

  /**
   * Post t.
   *
   * @param <T> the type parameter
   * @param name the name
   * @param objects the objects
   * @return the t
   */
  public static <T> T post(final String name, final Object... objects) {
        if (name == null || name.length() == 0) {
          return null;
        }
        Object o = injectShell(name, objects);
        if (NULL.equals(o)) {
            Log.e("BusUtils", "bus of <" + name + "> didn\'t exist.");
            return null;
        }
        return (T) o;
    }

    private static Object injectShell(final String name, final Object[] objects) {
        return NULL;
    }

  /**
   * The interface Subscribe.
   */
  @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    public @interface Subscribe {
    /**
     * Name string.
     *
     * @return the string
     */
    String name() default "";

    /**
     * Priority int.
     *
     * @return the int
     */
    int priority() default 0;
    }
}
