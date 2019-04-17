package javax0.util.string;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public class Str {
    private final static int LEFT = 0x01;
    private final static int RIGHT = 0x02;
    private final static int BOTH = LEFT | RIGHT;
    public final Str the = this;
    public final Condition is = new Condition();
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
        this.nullCorrection = nullCorrection;
    }

    private Str(String... string) {
        this.string = null;
        this.strings = string;
        this.isArrayOp = true;
        this.ignoreCase = false;
        this.nullCorrection = null;
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

    public String substring(int start) {
        notArray();
        return nullCorrection.apply(StringUtils.substring(string, start));
    }

    public String after(String separator) {
        notArray();
        return nullCorrection.apply(StringUtils.substringAfter(string, separator));
    }

    public String before(String separator) {
        notArray();
        return nullCorrection.apply(StringUtils.substringBefore(string, separator));
    }

    public String[] betweens(String open, String close) {
        notArray();
        return StringUtils.substringsBetween(string, open, close);
    }

    public String between(String open, String close) {
        notArray();
        return nullCorrection.apply(StringUtils.substringBetween(string, open, close));
    }

    public String between(String tag) {
        notArray();
        return nullCorrection.apply(StringUtils.substringBetween(string, tag));
    }

    @Deprecated()
    public String trim() {
        notArray();
        return nullCorrection.apply(StringUtils.trim(string));
    }
    /*!jamal
    //<editor-fold id="">
    {{@for nameOfTheMethod in (swapCase,lowerCase,upperCase,stripAccents,chop,chomp,uncapitalize)=
    public String nameOfTheMethod() {
        notArray();
        return nullCorrection.apply(StringUtils.nameOfTheMethod(string));
    }
    }}
    //</editor-fold>
    */
    //<editor-fold id="">

    public String swapCase() {
        notArray();
        return nullCorrection.apply(StringUtils.swapCase(string));
    }

    public String lowerCase() {
        notArray();
        return nullCorrection.apply(StringUtils.lowerCase(string));
    }

    public String upperCase() {
        notArray();
        return nullCorrection.apply(StringUtils.upperCase(string));
    }

    public String stripAccents() {
        notArray();
        return nullCorrection.apply(StringUtils.stripAccents(string));
    }

    public String chop() {
        notArray();
        return nullCorrection.apply(StringUtils.chop(string));
    }

    public String chomp() {
        notArray();
        return nullCorrection.apply(StringUtils.chomp(string));
    }

    public String uncapitalize() {
        notArray();
        return nullCorrection.apply(StringUtils.uncapitalize(string));
    }

    //</editor-fold>
    //__END__
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

    public String wrap(final char wrapWith) {
        notArray();
        return nullCorrection.apply(StringUtils.wrap(string, wrapWith));
    }

    public String wrap(final String wrapWith) {
        notArray();
        return nullCorrection.apply(StringUtils.wrap(string, wrapWith));
    }

    public String wrapIfMissing(final char wrapWith) {
        notArray();
        return nullCorrection.apply(StringUtils.wrapIfMissing(string, wrapWith));
    }

    public String wrapIfMissing(final String wrapWith) {
        notArray();
        return nullCorrection.apply(StringUtils.wrapIfMissing(string, wrapWith));
    }



    public String truncate(int maxWidth) {
        notArray();
        return nullCorrection.apply(StringUtils.truncate(string, maxWidth));
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

    public Str fforceNull() {
        return new Str(nullIsLess, side, s -> str(s).is.blank() ? null : s, ignoreCase, is.blank() ? null : string);
    }

    public Str left() {
        return new Str(nullIsLess, LEFT, s -> str(s).is.blank() ? null : s, ignoreCase, string);
    }

    public Str right() {
        return new Str(nullIsLess, RIGHT, s -> str(s).is.blank() ? null : s, ignoreCase, string);
    }

    public Str both() {
        return new Str(nullIsLess, BOTH, s -> str(s).is.blank() ? null : s, ignoreCase, string);
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

        /*!jamal
        //<editor-fold id="chains">{{@use global javax0.jamal.extensions.IndexStringTable}}{{@define $forsep=\s*,\s*}}
        {{#eval{{@for nameOfTheMethod in (after|String,before|String,substring|int,
                                          between|String,wrap|char,wrap|String,
                                          wrapIfMissing|char,wrapIfMissing|String,
                                          truncate|int,strip|String,pad|int)=
        public Chain {{#get 0 |nameOfTheMethod}}(final {{#get 1 |nameOfTheMethod}} arg) {
            return copy(Str.this.{{#get 0 |nameOfTheMethod}}(arg)).new Chain();
        } }}}}
        //</editor-fold>
        */
        //<editor-fold id="chains">

        public Chain after(final String arg) {
            return copy(Str.this.after(arg)).new Chain();
        }

        public Chain before(final String arg) {
            return copy(Str.this.before(arg)).new Chain();
        }

        public Chain substring(final int arg) {
            return copy(Str.this.substring(arg)).new Chain();
        }

        public Chain between(final String arg) {
            return copy(Str.this.between(arg)).new Chain();
        }

        public Chain wrap(final char arg) {
            return copy(Str.this.wrap(arg)).new Chain();
        }

        public Chain wrap(final String arg) {
            return copy(Str.this.wrap(arg)).new Chain();
        }

        public Chain wrapIfMissing(final char arg) {
            return copy(Str.this.wrapIfMissing(arg)).new Chain();
        }

        public Chain wrapIfMissing(final String arg) {
            return copy(Str.this.wrapIfMissing(arg)).new Chain();
        }

        public Chain truncate(final int arg) {
            return copy(Str.this.truncate(arg)).new Chain();
        }

        public Chain strip(final String arg) {
            return copy(Str.this.strip(arg)).new Chain();
        }

        public Chain pad(final int arg) {
            return copy(Str.this.pad(arg)).new Chain();
        } 
        //</editor-fold>
        //__END__

        /*!jamal
        //<editor-fold id="chains">{{@use global javax0.jamal.extensions.IndexStringTable}}{{@define $forsep=\s*,\s*}}
        {{#eval{{@for nameOfTheMethod in (truncate|int|int,substring|int|int,
                                          between|String|String,mid|int|int,
                                          prependIfMissing|CharSequence|CharSequence...,
                                          pad|int|char)=
        public Chain {{#get 0 |nameOfTheMethod}}(final {{#get 1 |nameOfTheMethod}} arg1,final {{#get 2 |nameOfTheMethod}} arg2) {
            return copy(Str.this.{{#get 0 |nameOfTheMethod}}(arg1,arg2)).new Chain();
        } }}}}
        //</editor-fold>
        */
        //<editor-fold id="chains">
        public Chain truncate(final int arg1,final int arg2) {
            return copy(Str.this.truncate(arg1,arg2)).new Chain();
        } 
        public Chain substring(final int arg1,final int arg2) {
            return copy(Str.this.substring(arg1,arg2)).new Chain();
        } 
        public Chain between(final String arg1,final String arg2) {
            return copy(Str.this.between(arg1,arg2)).new Chain();
        } 
        public Chain mid(final int arg1,final int arg2) {
            return copy(Str.this.mid(arg1,arg2)).new Chain();
        } 
        public Chain prependIfMissing(final CharSequence arg1,final CharSequence... arg2) {
            return copy(Str.this.prependIfMissing(arg1,arg2)).new Chain();
        } 
        public Chain pad(final int arg1,final char arg2) {
            return copy(Str.this.pad(arg1,arg2)).new Chain();
        } 
        //</editor-fold>
        //__END__

        @Deprecated()
        public Chain trim(final int start, final int end) {
            return copy(Str.this.trim()).new Chain();
        }

        public Chain betweens(final String open, final String close) {
            return new Str(Str.this.betweens(open, close)).new Chain();
        }

        /*!jamal
        //<editor-fold id="chains">{{@define $forsep=\s*,\s*}}
        {{@for nameOfTheMethod in (swapCase,lowerCase,upperCase,normalizeSpace,
                                   uncapitalize,strip,stripAccents,chop,chomp)=
        public Chain nameOfTheMethod() {
            return copy(Str.this.nameOfTheMethod()).new Chain();
        } }}
        //</editor-fold>
        */
        //<editor-fold id="chains">

        public Chain swapCase() {
            return copy(Str.this.swapCase()).new Chain();
        } 
        public Chain lowerCase() {
            return copy(Str.this.lowerCase()).new Chain();
        } 
        public Chain upperCase() {
            return copy(Str.this.upperCase()).new Chain();
        } 
        public Chain normalizeSpace() {
            return copy(Str.this.normalizeSpace()).new Chain();
        } 
        public Chain uncapitalize() {
            return copy(Str.this.uncapitalize()).new Chain();
        } 
        public Chain strip() {
            return copy(Str.this.strip()).new Chain();
        } 
        public Chain stripAccents() {
            return copy(Str.this.stripAccents()).new Chain();
        } 
        public Chain chop() {
            return copy(Str.this.chop()).new Chain();
        } 
        public Chain chomp() {
            return copy(Str.this.chomp()).new Chain();
        } 
        //</editor-fold>
        //__END__

        /*!jamal
        //<editor-fold id="chains">{{@define $forsep=\s*,\s*}}
        {{@for nameOfTheMethod in (notNull,ignoreCase,nullIsMore,left,
        right,both,forceNull,fforceNull)=
        public Chain nameOfTheMethod() {
            return Str.this.nameOfTheMethod().new Chain();
        } }}
        //</editor-fold>
        */
        //<editor-fold id="chains">

        public Chain notNull() {
            return Str.this.notNull().new Chain();
        } 
        public Chain ignoreCase() {
            return Str.this.ignoreCase().new Chain();
        } 
        public Chain nullIsMore() {
            return Str.this.nullIsMore().new Chain();
        } 
        public Chain left() {
            return Str.this.left().new Chain();
        } 
        public Chain right() {
            return Str.this.right().new Chain();
        } 
        public Chain both() {
            return Str.this.both().new Chain();
        } 
        public Chain forceNull() {
            return Str.this.forceNull().new Chain();
        } 
        public Chain fforceNull() {
            return Str.this.fforceNull().new Chain();
        } 
        //</editor-fold>
        //__END__

        public boolean contains(String other) {
            return Str.this.contains(other);
        }

        public StringBuilder toStringBuilder() {
            return new StringBuilder(string);
        }

        public String toString() {
            return string;
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
    }

    public class Condition {
        public final Condition the = this;
        final public AnyOrNone any;
        final public AnyOrNone none;
        final public Condition not;
        public final ShorterThan shorter = new ShorterThan();
        public final LongerThan longer = new LongerThan();
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
            return boolCorrection.apply(StringUtils.contains(string, other));
        }

        public boolean empty() {
            return boolCorrection.apply(StringUtils.isEmpty(string));
        }

        public boolean blank() {
            return boolCorrection.apply(StringUtils.isBlank(string));
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
            return nullCorrection.apply(StringUtils.substringAfterLast(string, separator));
        }
    }

    public class BeforeLast {
        public final BeforeLast the = this;

        public String last(String separator) {
            notArray();
            return nullCorrection.apply(StringUtils.substringBeforeLast(string, separator));
        }
    }

}
