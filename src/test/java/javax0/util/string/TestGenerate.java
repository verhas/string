package javax0.util.string;

import javax0.geci.engine.Geci;
import javax0.geci.repeated.Repeated;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static javax0.geci.api.Source.maven;

class TestGenerate {

    /**
     * Single String argument methods that return a String, except those
     * that can be composed with fluent API. For example {@code
     * trimToNull}, and {@code trimToEmpty} are not present because they
     * can be used using the null correction.
     *
     * 'strip()' is implemented to incorporate left and right as extra
     * functionality.
     */
    public static final String SINGLE_STRING_ARGUMENT_METHODS =
            "deleteWhitespace,trim,swapCase,lowerCase,upperCase,"
                    + "normalizeSpace,stripAccents,chop,chomp,uncapitalize,capitalize,"
                    + "getDigits,reverse";

    @Test
    void generateCode() throws Exception {
        final var geci = new Geci();
        Assertions.assertFalse(
                geci.source(maven().mainSource()).only("Str.java")
                        .register(Repeated.builder()
                                .values("substring|int," +
                                        "wrap|char,wrap|String," +
                                        "wrapIfMissing|char,wrapIfMissing|String," +
                                        "truncate|int")
                                .define((ctx, s) -> {
                                    final var p = s.split("\\|");
                                    ctx.segment().param("method", p[0], "type", p[1]);
                                })
                                .template("```@Generated(\"by Geci\")\n" +
                                        "public String {{method}}({{type}} arg) {\n" +
                                        "    notArray();\n" +
                                        "    return nullCorrection.apply(StringUtils.{{method}}(string, arg));\n" +
                                        "}\n```")
                                .mnemonic("oneArgMethods")
                                .build())
                        .register(Repeated.builder()
                                .values(SINGLE_STRING_ARGUMENT_METHODS)
                                .template("```@Generated(\"by Geci\")\n" +
                                        "public String {{value}}() {\n" +
                                        "    notArray();\n" +
                                        "    return nullCorrection.apply(StringUtils.{{value}}(string));\n" +
                                        "}```")
                                .mnemonic("sameNameMethods")
                                .build())
                        .register(Repeated.builder()
                                .values("after|String,before|String,substring|int," +
                                        "between|String,wrap|char,wrap|String," +
                                        "wrapIfMissing|char," +
                                        "wrapIfMissing|String," +
                                        "truncate|int,strip|String,pad|int")
                                .define((ctx, s) -> {
                                    final var p = s.split("\\|");
                                    ctx.segment().param("method", p[0], "type", p[1]);
                                })
                                .template("```@Generated(\"by Geci\")\n" +
                                        "public Chain {{method}}(final {{type}} arg) {\n" +
                                        "    return copy(Str.this.{{method}}(arg)).new Chain();\n" +
                                        "}```")
                                .mnemonic("chains1")
                                .build())
                        .register(Repeated.builder()
                                .values("truncate|int|int,substring|int|int," +
                                        "between|String|String,mid|int|int," +
                                        "prependIfMissing|CharSequence|CharSequence...," +
                                        "pad|int|char")
                                .define((ctx, s) -> {
                                    final var p = s.split("\\|");
                                    ctx.segment().param("method", p[0], "type1", p[1], "type2", p[2]);
                                })
                                .template("```@Generated(\"by Geci\")\n" +
                                        "public Chain {{method}}(final {{type1}} a,final {{type2}} b) {\n" +
                                        "    return copy(Str.this.{{method}}(a,b)).new Chain();\n" +
                                        "}```")
                                .mnemonic("chains2")
                                .build())
                        .register(Repeated.builder()
                                .values(SINGLE_STRING_ARGUMENT_METHODS)
                                .template("```@Generated(\"by Geci\")\n" +
                                        "public Chain {{value}}() {\n" +
                                        "    return copy(Str.this.{{value}}()).new Chain();\n" +
                                        "}```")
                                .mnemonic("chains0")
                                .build())
                        .register(Repeated.builder()
                                .values("notNull,ignoreCase,nullIsMore,left,right,both,forceNull,fforceNull,forceEmpty")
                                .template("```@Generated(\"by Geci\")\n" +
                                        "public Chain {{value}}() {\n" +
                                        "    return Str.this.{{value}}().new Chain();\n" +
                                        "}```")
                                .mnemonic("chains0_noCopy")
                                .build())
                        .generate(),
                geci.failed()
        );
    }
}
