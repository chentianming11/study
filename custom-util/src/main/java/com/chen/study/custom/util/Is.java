package com.chen.study.custom.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.stripToEmpty;

/**
 * Micro check tools (null-Safe)
 * 该类的所有操作都是返回boolean
 *
 * 所有对字符的操作，都会去掉前后空格
 *
 * @author 闷骚乔巴
 * @since 15/12/3
 */
public abstract class Is {

    private Is() {}

    //start empty.
    /**
     * 字符串为空的检查
     * empty ("") or null.
     *
     * <pre>
     * Is.empty(null)      = true
     * Is.empty("")        = true
     * Is.empty(" ")       = true
     * Is.empty("bob")     = false
     * Is.empty("  bob  ") = false
     * </pre>
     *
     * @param s
     * @return
     */
    public static boolean empty(final String s) {
        return isEmpty(stripToEmpty(s));
    }

    /**
     * 对象为空的检查
     * empty ("") or null.
     *
     * <pre>
     * Is.empty(null)      = true
     * Is.empty("")        = true
     * Is.empty(" ")       = true
     * Is.empty("bob")     = false
     * Is.empty("  bob  ") = false
     * </pre>
     * @param o
     * @return
     */
    public static boolean empty(final Object o) {
        if(o == null) {
            return true;
        }

        if(o instanceof CharSequence) {
            return isEmpty((CharSequence) o);
        }

        if (o instanceof Collection) {
            return CollectionUtils.isEmpty((Collection<?>) o);
        }

        if (o instanceof Map) {
            if(MapUtils.isEmpty((Map<?, ?>) o)) {
                return true;
            }

            String s = ((Map<?, ?>) o).get("") + "";
            if(empty(s)) {
                return true;
            }
        }

        if (o instanceof Object[]) {
            return ArrayUtils.isEmpty((Object[]) o);
        }

        return false;

    }
    //~end empty.


    public static boolean include(final String str, final String searchStr) {
        return StringUtils.contains(stripToEmpty(str), stripToEmpty(searchStr));
    }

    public static boolean startWith(String str, String prefix) {
        return StringUtils.startsWith(stripToEmpty(str), stripToEmpty(prefix));
    }

    public static boolean endsWith(String str, String prefix) {
        return StringUtils.endsWith(stripToEmpty(str), stripToEmpty(prefix));
    }

    public static boolean equalsIgnoreCase(final CharSequence str1, final CharSequence str2) {
        return StringUtils.equalsIgnoreCase(str1, str2);
    }

    //===========================================================================
    //start Object.

    /**
     * 是否相等(Null-safe)
     *
     * <pre>
     * Is.equals(null, null)                  = true
     * Is.equals(null, "")                    = false
     * Is.equals("", null)                    = false
     * Is.equals("", "")                      = true
     * Is.equals(Boolean.TRUE, null)          = false
     * Is.equals(Boolean.TRUE, "true")        = false
     * Is.equals(Boolean.TRUE, Boolean.TRUE)  = true
     * Is.equals(Boolean.TRUE, Boolean.FALSE) = false
     * </pre>
     *
     * @param object1  the first object, may be {@code null}
     * @param object2  the second object, may be {@code null}
     * @return
     */
    public static boolean equals(final Object object1, final Object object2) {
        return Objects.equals(object1, object2);
    }

    //~end Object.


    //start RegExp.
    /**
     * 检查email
     *
     * @param s
     * @return
     */
    public static boolean email(final String s) {
        if(empty(s)) return false;

        return EMAIL.matcher(stripToEmpty(s)).matches();
    }

    public static boolean url(final String s) {
        if(empty(s)) return false;

        return URL.matcher(stripToEmpty(s)).matches();
    }

    /**
     * 简单验证手机号码
     * <pre>
     * Assert.assertTrue(Is.mobile("15612341234"));
     * Assert.assertTrue(Is.mobile("13512341234"));
     * Assert.assertTrue(Is.mobile("013512341234"));
     * Assert.assertTrue(Is.mobile("0156 1234 1234"));
     * Assert.assertTrue(Is.mobile("0 1560 1234 811"));
     * Assert.assertTrue(Is.mobile("08615612341234"));
     * Assert.assertTrue(Is.mobile("8615612341234"));
     * Assert.assertTrue(Is.mobile("+8615612341234"));
     * </pre>
     * @param s
     * @return
     */
    public static boolean mobile(final String...s) {
        if(empty(s)) {
            return false;
        }

        return Arrays.asList(s).stream().allMatch(t ->
                MOBILE.matcher(stripToEmpty(t.replaceAll("([\\s\\t])|(^086|86)|", "").replaceAll("^\\+", ""))).matches()
        );

    }

    /**
     * 验证ip
     * 暂只支持ipv4
     *
     * @param s
     * @return
     */
    public static boolean ip(final String s) {
        if(empty(s)) return false;

        return ip4(stripToEmpty(s));
    }

    private static boolean ip4(final String s) {
        if(empty(s)) return false;

        return IP4.matcher(stripToEmpty(s)).matches();
    }

    //暂无需求，不支持
    private static boolean ip6(final String s) {
        if(empty(s)) return false;

        return IP6.matcher(stripToEmpty(s)).matches();
    }
    //~end RegExp.

    final static class not {

        public static boolean empty(final String s) {
            return !Is.empty(s);
        }

        public static boolean empty(final Object o) {
            return !Is.empty(o);
        }

        public static boolean equals(final Object object1, final Object object2) {
            return !Is.equals(object1, object2);
        }

        public static boolean equalsIgnoreCase(final CharSequence str1, final CharSequence str2) {
            return !Is.equalsIgnoreCase(str1, str2);
        }
    }


    final static class all {

        /**
         * 判断是否所有值都为空
         *
         * @param o
         * @return
         */
        public static boolean empty(final Object o) {
            if(Is.empty(o)) {
                return true;
            }

            if (o instanceof Collection) {
                return  ((Collection) o).stream().allMatch(pempty);
            }

            if (o instanceof Map) {
                return MapUtils.isEmpty((Map<?, ?>) o);
            }

            if (o instanceof Object[]) {
                return Arrays.asList((Object[]) o).stream().allMatch(pempty);
            }

            return false;
        }
    }


    private static final Predicate<? super Object> pempty = s -> Is.empty(s);

    //RegExp
    private static final Pattern EMAIL = Pattern.compile("^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))$", Pattern.CASE_INSENSITIVE);
    private static final Pattern URL = Pattern.compile("^(?:(?:https?|ftp):\\/\\/)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))(?::\\d{2,5})?(?:\\/\\S*)?$", Pattern.CASE_INSENSITIVE);
    private static final Pattern MOBILE = Pattern.compile("^(01|1)\\d{10}$");
    private static final Pattern IP4 = Pattern.compile("^(?:(?:\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(?:\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])$");
    private static final Pattern IP6 = Pattern.compile("^(([a-zA-Z]|[a-zA-Z][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z]|[A-Za-z][A-Za-z0-9\\-]*[A-Za-z0-9])$|^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$");
}
