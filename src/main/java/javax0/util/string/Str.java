package javax0.util.string;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.processing.Generated;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * This is a small class that is a facade for {@code
 * org.apache.commons.lang3.StringUtils}. Most of the methods in that
 * class have a fluent method in this class so that the different
 * methods can be invoked in a much more readable manner. Some of the
 * methods are not only serve as a fluent front-end but provide a
 * consistent mean to combine the effects of different methods.
 *
 * <p> For more information have a look at the test file {@code TestStr}
 */
public class Str {
    private final static int LEFT = 0x01;
    private final static int RIGHT = 0x02;
    private final static int BOTH = LEFT | RIGHT;
    public final Str the = this;
    public final Condition is = new Condition();
    public final Condition has = is;
    public final Condition does = is;
    public final AfterLast after = new AfterLast();
    public final BeforeLast before = new BeforeLast();
    final private String string;
    final private String[] strings;
    final private boolean isArrayOp;
    final private boolean ignoreCase;
    private final Function<String, String> nullCorrection;
    private final int side;
    private final boolean nullIsLess;

    private Str(boolean nullIsLess, int side, Function<String, String> nullCorrection, boolean ignoreCase, CharSequence string) {
        this.side = side;
        if (string == null) {
            this.string = null;
        } else {
            this.string = string.toString();
        }
        this.nullIsLess = nullIsLess;
        this.strings = null;
        this.isArrayOp = false;
        this.ignoreCase = ignoreCase;
        this.nullCorrection = Objects.requireNonNullElse(nullCorrection, s -> s);
    }

    private Str(String... string) {
        this.string = null;
        this.strings = string;
        this.isArrayOp = true;
        this.ignoreCase = false;
        this.nullCorrection = s -> s;
        this.side = BOTH;
        this.nullIsLess = true;
    }

    public static Str strs(String... strings) {
        return new Str(strings);
    }

    public static Str str(CharSequence string) {
        return new Str(true, BOTH, s -> s, false, string);
    }

    public static Chain string(String string) {
        return new Str(true, BOTH, s -> s, false, string).new Chain();
    }

    public static Chain strings(String... strings) {
        return new Str(strings).new Chain();
    }

    @Override
    public String toString() {
        return nullCorrection.apply(string);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Str str = (Str) o;
        return Objects.equals(string, str.string) &&
                Arrays.equals(strings, str.strings);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(string);
        result = 31 * result + Arrays.hashCode(strings);
        return result;
    }

    private Str copy(String string) {
        return new Str(nullIsLess, side, nullCorrection, ignoreCase, string);
    }

    public boolean contains(String other) {
        return is.contain(other);
    }

    public String substring(int start, int end) {
        notArray();
        return nullCorrection.apply(StringUtils.substring(string, start, end));
    }

    public String before(String arg) {
        notArray();
        return nullCorrection.apply(substringBefore(string, arg, ignoreCase));
    }

    public String after(String arg) {
        notArray();
        return nullCorrection.apply(substringAfter(string, arg, ignoreCase));
    }

    public String between(String arg) {
        notArray();
        return nullCorrection.apply(substringBetween(string, arg, ignoreCase));
    }

    //<editor-fold id="oneArgMethods">

    @Generated("by Geci")
    public String substring(int arg) {
        notArray();
        return nullCorrection.apply(StringUtils.substring(string, arg));
    }

    @Generated("by Geci")
    public String wrap(char arg) {
        notArray();
        return nullCorrection.apply(StringUtils.wrap(string, arg));
    }

    @Generated("by Geci")
    public String wrap(String arg) {
        notArray();
        return nullCorrection.apply(StringUtils.wrap(string, arg));
    }

    @Generated("by Geci")
    public String wrapIfMissing(char arg) {
        notArray();
        return nullCorrection.apply(StringUtils.wrapIfMissing(string, arg));
    }

    @Generated("by Geci")
    public String wrapIfMissing(String arg) {
        notArray();
        return nullCorrection.apply(StringUtils.wrapIfMissing(string, arg));
    }

    @Generated("by Geci")
    public String truncate(int arg) {
        notArray();
        return nullCorrection.apply(StringUtils.truncate(string, arg));
    }

    //</editor-fold>

    public String[] betweens(String open, String close) {
        notArray();
        return StringUtils.substringsBetween(string, open, close);
    }

    public String between(String open, String close) {
        notArray();
        return nullCorrection.apply(substringBetween(string, open, close, ignoreCase));
    }

    @Deprecated()
    public String trim() {
        notArray();
        return nullCorrection.apply(StringUtils.trim(string));
    }

    //<editor-fold id="sameNameMethods">
    @Generated("by Geci")
    public String swapCase() {
        notArray();
        return nullCorrection.apply(StringUtils.swapCase(string));
    }

    @Generated("by Geci")
    public String lowerCase() {
        notArray();
        return nullCorrection.apply(StringUtils.lowerCase(string));
    }

    @Generated("by Geci")
    public String upperCase() {
        notArray();
        return nullCorrection.apply(StringUtils.upperCase(string));
    }

    @Generated("by Geci")
    public String stripAccents() {
        notArray();
        return nullCorrection.apply(StringUtils.stripAccents(string));
    }

    @Generated("by Geci")
    public String chop() {
        notArray();
        return nullCorrection.apply(StringUtils.chop(string));
    }

    @Generated("by Geci")
    public String chomp() {
        notArray();
        return nullCorrection.apply(StringUtils.chomp(string));
    }

    @Generated("by Geci")
    public String uncapitalize() {
        notArray();
        return nullCorrection.apply(StringUtils.uncapitalize(string));
    }
    //</editor-fold>

    public String mid(int pos, int len) {
        notArray();
        return nullCorrection.apply(StringUtils.mid(string, pos, len));
    }

    public String normalizeSpace() {
        notArray();
        return nullCorrection.apply(StringUtils.normalizeSpace(string));
    }

    public String strip(final String stripChars) {
        notArray();
        switch (side) {
            case LEFT:
                return nullCorrection.apply(StringUtils.stripStart(string, stripChars));
            case RIGHT:
                return nullCorrection.apply(StringUtils.stripEnd(string, stripChars));
            case BOTH:
                return nullCorrection.apply(StringUtils.strip(string, stripChars));
        }
        throw new IllegalArgumentException("SNAFU side value is " + side);
    }

    public String pad(final int n) {
        return pad(n, ' ');
    }

    public String pad(final int n, char paddingChar) {
        notArray();
        switch (side) {
            case LEFT:
                return nullCorrection.apply(StringUtils.leftPad(string, n, paddingChar));
            case RIGHT:
                return nullCorrection.apply(StringUtils.rightPad(string, n, paddingChar));
            case BOTH:
                return nullCorrection.apply(StringUtils.center(string, n, paddingChar));
        }
        throw new IllegalArgumentException("SNAFU side value is " + side);
    }

    public String strip() {
        return strip(null);
    }


    public String prependIfMissing(final CharSequence prefix, final CharSequence... prefixes) {
        notArray();
        if (ignoreCase) {
            return nullCorrection.apply(StringUtils.prependIfMissingIgnoreCase(string, prefix, prefixes));
        } else {
            return nullCorrection.apply(StringUtils.prependIfMissing(string, prefix, prefixes));
        }
    }

    public String truncate(int offset, int maxWidth) {
        notArray();
        return nullCorrection.apply(StringUtils.truncate(string, offset, maxWidth));
    }

    public int compare(String other) {
        notArray();
        if (ignoreCase) {
            return StringUtils.compareIgnoreCase(string, other, nullIsLess);
        } else {
            return StringUtils.compare(string, other, nullIsLess);
        }
    }

    public int length() {
        if (isArrayOp)
            return strings == null ? 0 : strings.length;
        else
            return string == null ? 0 : string.length();
    }

    private void notArray() {
        if (isArrayOp) {
            throw new IllegalArgumentException("Operation does not work on string array.");
        }
    }

    private void onlyArray() {
        if (!isArrayOp) {
            throw new IllegalArgumentException("Operation does not work on strings. It needs an array of strings.");
        }
    }

    public Str ignoreCase() {
        return new Str(nullIsLess, side, nullCorrection, true, string);
    }

    public Str nullIsMore() {
        return new Str(false, side, s -> s == null ? "" : s, ignoreCase, string);
    }

    public Str notNull() {
        return new Str(nullIsLess, side, s -> s == null ? "" : s, ignoreCase, string == null ? "" : string);
    }

    public Str forceNull() {
        return new Str(nullIsLess, side, s -> str(s).is.empty() ? null : s, ignoreCase, is.empty() ? null : string);
    }

    public Str forceEmpty() {
        return new Str(nullIsLess, side, s -> str(s).is.blank() ? "" : s, ignoreCase, is.empty() ? null : string);
    }

    public Str fforceNull() {
        return new Str(nullIsLess, side, s -> str(s).is.blank() ? null : s, ignoreCase, is.blank() ? null : string);
    }

    public Str left() {
        return new Str(nullIsLess, LEFT, nullCorrection, ignoreCase, string);
    }

    public Str right() {
        return new Str(nullIsLess, RIGHT, nullCorrection, ignoreCase, string);
    }

    public Str both() {
        return new Str(nullIsLess, BOTH, nullCorrection, ignoreCase, string);
    }

    public class Chain {
        public final Chain the = this;
        public final Condition is = Str.this.is;
        public final Condition does = is;
        public final AfterLast after = new AfterLast();
        public final BeforeLast before = new BeforeLast();

        private Str getStr() {
            return Str.this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Chain otherChain = (Chain) o;
            return Str.this.equals(otherChain.getStr());
        }

        @Override
        public int hashCode() {
            return Str.this.hashCode();
        }

        public int length() {
            return Str.this.length();
        }

        public int compare(String other) {
            return Str.this.compare(other);
        }

        //<editor-fold id="chains1">
        @Generated("by Geci")
        public Chain after(final String arg) {
            return copy(Str.this.after(arg)).new Chain();
        }

        @Generated("by Geci")
        public Chain before(final String arg) {
            return copy(Str.this.before(arg)).new Chain();
        }

        @Generated("by Geci")
        public Chain substring(final int arg) {
            return copy(Str.this.substring(arg)).new Chain();
        }

        @Generated("by Geci")
        public Chain between(final String arg) {
            return copy(Str.this.between(arg)).new Chain();
        }

        @Generated("by Geci")
        public Chain wrap(final char arg) {
            return copy(Str.this.wrap(arg)).new Chain();
        }

        @Generated("by Geci")
        public Chain wrap(final String arg) {
            return copy(Str.this.wrap(arg)).new Chain();
        }

        @Generated("by Geci")
        public Chain wrapIfMissing(final char arg) {
            return copy(Str.this.wrapIfMissing(arg)).new Chain();
        }

        @Generated("by Geci")
        public Chain wrapIfMissing(final String arg) {
            return copy(Str.this.wrapIfMissing(arg)).new Chain();
        }

        @Generated("by Geci")
        public Chain truncate(final int arg) {
            return copy(Str.this.truncate(arg)).new Chain();
        }

        @Generated("by Geci")
        public Chain strip(final String arg) {
            return copy(Str.this.strip(arg)).new Chain();
        }

        @Generated("by Geci")
        public Chain pad(final int arg) {
            return copy(Str.this.pad(arg)).new Chain();
        }
        //</editor-fold>

        //<editor-fold id="chains2">
        @Generated("by Geci")
        public Chain truncate(final int arg1, final int arg2) {
            return copy(Str.this.truncate(arg1, arg2)).new Chain();
        }

        @Generated("by Geci")
        public Chain substring(final int arg1, final int arg2) {
            return copy(Str.this.substring(arg1, arg2)).new Chain();
        }

        @Generated("by Geci")
        public Chain between(final String arg1, final String arg2) {
            return copy(Str.this.between(arg1, arg2)).new Chain();
        }

        @Generated("by Geci")
        public Chain mid(final int arg1, final int arg2) {
            return copy(Str.this.mid(arg1, arg2)).new Chain();
        }

        @Generated("by Geci")
        public Chain prependIfMissing(final CharSequence arg1, final CharSequence... arg2) {
            return copy(Str.this.prependIfMissing(arg1, arg2)).new Chain();
        }

        @Generated("by Geci")
        public Chain pad(final int arg1, final char arg2) {
            return copy(Str.this.pad(arg1, arg2)).new Chain();
        }
        //</editor-fold>

        @Deprecated()
        public Chain trim(final int start, final int end) {
            return copy(Str.this.trim()).new Chain();
        }

        public Chain betweens(final String open, final String close) {
            return new Str(Str.this.betweens(open, close)).new Chain();
        }

        //<editor-fold id="chains3">
        @Generated("by Geci")
        public Chain swapCase() {
            return copy(Str.this.swapCase()).new Chain();
        }

        @Generated("by Geci")
        public Chain lowerCase() {
            return copy(Str.this.lowerCase()).new Chain();
        }

        @Generated("by Geci")
        public Chain upperCase() {
            return copy(Str.this.upperCase()).new Chain();
        }

        @Generated("by Geci")
        public Chain normalizeSpace() {
            return copy(Str.this.normalizeSpace()).new Chain();
        }

        @Generated("by Geci")
        public Chain uncapitalize() {
            return copy(Str.this.uncapitalize()).new Chain();
        }

        @Generated("by Geci")
        public Chain strip() {
            return copy(Str.this.strip()).new Chain();
        }

        @Generated("by Geci")
        public Chain stripAccents() {
            return copy(Str.this.stripAccents()).new Chain();
        }

        @Generated("by Geci")
        public Chain chop() {
            return copy(Str.this.chop()).new Chain();
        }

        @Generated("by Geci")
        public Chain chomp() {
            return copy(Str.this.chomp()).new Chain();
        }
        //</editor-fold>

        //<editor-fold id="chains4">
        @Generated("by Geci")
        public Chain notNull() {
            return Str.this.notNull().new Chain();
        }

        @Generated("by Geci")
        public Chain ignoreCase() {
            return Str.this.ignoreCase().new Chain();
        }

        @Generated("by Geci")
        public Chain nullIsMore() {
            return Str.this.nullIsMore().new Chain();
        }

        @Generated("by Geci")
        public Chain left() {
            return Str.this.left().new Chain();
        }

        @Generated("by Geci")
        public Chain right() {
            return Str.this.right().new Chain();
        }

        @Generated("by Geci")
        public Chain both() {
            return Str.this.both().new Chain();
        }

        @Generated("by Geci")
        public Chain forceNull() {
            return Str.this.forceNull().new Chain();
        }

        @Generated("by Geci")
        public Chain fforceNull() {
            return Str.this.fforceNull().new Chain();
        }

        @Generated("by Geci")
        public Chain forceEmpty() {
            return Str.this.forceEmpty().new Chain();
        }
        //</editor-fold>

        //__END__

        public boolean contains(String other) {
            return Str.this.contains(other);
        }

        public StringBuilder toStringBuilder() {
            return new StringBuilder(str(string).notNull().toString());
        }

        public String toString() {
            return nullCorrection.apply(string);
        }

        public String[] toArray() {
            return strings;
        }

        public class AfterLast {
            public final AfterLast the = this;

            public Chain last(String separator) {
                return copy(Str.this.after.last(separator)).new Chain();
            }
        }

        public class BeforeLast {
            public final BeforeLast the = this;

            public Chain last(String separator) {
                return copy(Str.this.before.last(separator)).new Chain();
            }
        }
    }

    public class AnyOrNone {
        public final AnyOrNone the = this;
        final private Function<Boolean, Boolean> correction;

        private AnyOrNone(Function<Boolean, Boolean> correction) {
            this.correction = correction;
        }

        public boolean blank() {
            onlyArray();
            return correction.apply(StringUtils.isAnyBlank(strings));
        }

        public boolean empty() {
            onlyArray();
            return correction.apply(StringUtils.isAnyEmpty(strings));
        }
    }

    public class Condition {
        public final Condition the = this;
        final public AnyOrNone any;
        final public AnyOrNone none;
        final public Condition not;
        public final ShorterThan shorter = new ShorterThan();
        public final LongerThan longer = new LongerThan();
        public final As theSameLength = new As();
        final private Function<Boolean, Boolean> boolCorrection;

        private Condition(Condition it) {
            not = it;
            boolCorrection = b -> !b;
            any = new AnyOrNone(b -> !b);
            none = new AnyOrNone(b -> b);
        }

        private Condition() {
            not = new Condition(this);
            boolCorrection = b -> b;
            any = new AnyOrNone(b -> b);
            none = new AnyOrNone(b -> !b);
        }

        public boolean contain(String other) {
            if (ignoreCase) {
                return boolCorrection.apply(StringUtils.containsIgnoreCase(string, other));
            } else {
                return boolCorrection.apply(StringUtils.contains(string, other));
            }
        }

        public boolean empty() {
            return boolCorrection.apply(StringUtils.isEmpty(string));
        }

        public boolean blank() {
            return boolCorrection.apply(StringUtils.isBlank(string));
        }

        public class As {
            public boolean as(CharSequence other) {
                return boolCorrection.apply(length() == other.length());
            }
        }

        public class LongerThan {
            public boolean than(int n) {
                return boolCorrection.apply(length() > n);
            }

            public boolean than(CharSequence other) {
                return boolCorrection.apply(length() > str(other).length());
            }

            public boolean than(Str other) {
                return boolCorrection.apply(length() > other.length());
            }

            public boolean than(Chain other) {
                return boolCorrection.apply(length() > other.length());
            }
        }

        public class ShorterThan {
            public boolean than(int n) {
                return boolCorrection.apply(length() < n);
            }

            public boolean than(CharSequence other) {
                return boolCorrection.apply(length() < str(other).length());
            }

            public boolean than(Str other) {
                return boolCorrection.apply(length() < other.length());
            }

            public boolean than(Chain other) {
                return boolCorrection.apply(length() < other.length());
            }
        }
    }

    public class AfterLast {
        public final AfterLast the = this;

        public String last(String separator) {
            notArray();
            if (ignoreCase) {
                final var string0 = str(string).notNull().toString();
                final var s = StringUtils.substringAfterLast(string0.toLowerCase(), str(separator).notNull().toString().toLowerCase());
                return nullCorrection.apply(string0.substring(0, s.length()));
            } else {
                return nullCorrection.apply(StringUtils.substringAfterLast(string, separator));
            }
        }
    }

    public class BeforeLast {
        public final BeforeLast the = this;

        public String last(String separator) {
            notArray();
            if (ignoreCase) {
                final var string0 = str(string).notNull().toString();
                final var s = StringUtils.substringBeforeLast(string0.toLowerCase(), str(separator).notNull().toString().toLowerCase());
                return nullCorrection.apply(string0.substring(0, s.length()));
            } else {
                return nullCorrection.apply(StringUtils.substringBeforeLast(string, separator));
            }
        }
    }

    private static int indexOf(String base, String search, boolean ignoreCase) {
        if (ignoreCase) {
            return base.toLowerCase().indexOf(search.toLowerCase());
        } else {
            return base.indexOf(search);
        }
    }

    private static int indexOf(String base, String search, int start, boolean ignoreCase) {
        if (ignoreCase) {
            return base.toLowerCase().indexOf(search.toLowerCase(), start);
        } else {
            return base.indexOf(search, start);
        }
    }

    private static String substringAfter(final String str, final String separator, boolean ignoreCase) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return StringUtils.EMPTY;
        }
        final int pos = indexOf(str, separator, ignoreCase);
        if (pos == StringUtils.INDEX_NOT_FOUND) {
            return StringUtils.EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    private static String substringBefore(final String str, final String separator, boolean ignoreCase) {
        if (StringUtils.isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.isEmpty()) {
            return StringUtils.EMPTY;
        }
        final int pos = indexOf(str, separator, ignoreCase);
        if (pos == StringUtils.INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(0, pos);
    }

    private static String substringBetween(final String str, final String tag, boolean ignoreCase) {
        return substringBetween(str, tag, tag, ignoreCase);
    }

    public static String substringBetween(final String str, final String open, final String close, boolean ignoreCase) {
        if (str == null || open == null || close == null) {
            return null;
        }
        final int start = indexOf(str, open, ignoreCase);
        if (start != StringUtils.INDEX_NOT_FOUND) {
            final int end = indexOf(str, close, start + open.length(), ignoreCase);
            if (end != StringUtils.INDEX_NOT_FOUND) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }
}
