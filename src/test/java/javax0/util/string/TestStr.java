package javax0.util.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static javax0.util.string.Str.*;

class TestStr {

    @Test
    @DisplayName("Test different empty and non empty strings")
    void testIsEmpty() {

        Assertions.assertEquals("", str("abrakadabra").before("ab"));
        Assertions.assertEquals("", str("abrakadabra").ignoreCase().before("AB"));
        Assertions.assertEquals("abrakad", str("abrakadabra").before.last("ab"));
        Assertions.assertEquals("abrakad", str("abrakadabra").before.the.last("ab"));
        Assertions.assertEquals("abrakad", str("abrakadabra").ignoreCase().before.the.last("AB"));
        Assertions.assertNull(str("abrakadabra").forceNull().before("ab"));
        Assertions.assertNull(str("    abrakadabra").fforceNull().before("ab"));

        Assertions.assertFalse(string("abrakadabra").before("ab").is.not.empty());
        Assertions.assertEquals(string("a"), string("abrakadabra").before("ka").after("br"));

        final var starting = string("alma").before.last("a");
        Assertions.assertEquals("lm", starting.ignoreCase().after("a").toString());
        Assertions.assertEquals("Aalm", starting.prependIfMissing("A").toString());
        Assertions.assertEquals("alm", starting.ignoreCase().prependIfMissing("A").toString());

        Assertions.assertTrue(str("").is.empty());
        Assertions.assertFalse(str("").is.not.empty());
        Assertions.assertTrue(str("").is.not.not.empty());
        Assertions.assertTrue(string("").is.empty());
        Assertions.assertFalse(string("").is.not.empty());
        Assertions.assertTrue(string("").is.not.not.empty());

        Assertions.assertTrue(str(null).is.empty());
        Assertions.assertFalse(str(null).is.not.empty());
        Assertions.assertTrue(str(null).is.not.not.empty());
        Assertions.assertTrue(string(null).is.empty());
        Assertions.assertFalse(string(null).is.not.empty());
        Assertions.assertTrue(string(null).is.not.not.empty());

        Assertions.assertFalse(str(" ").is.empty());
        Assertions.assertTrue(str(" ").is.not.empty());
        Assertions.assertFalse(str(" ").is.not.not.empty());
        Assertions.assertFalse(string(" ").is.empty());
        Assertions.assertTrue(string(" ").is.not.empty());
        Assertions.assertFalse(string(" ").is.not.not.empty());
    }


    @Test
    @DisplayName("Test different blank and non blank strings")
    void testIsBlank() {
        Assertions.assertTrue(str("").is.blank());
        Assertions.assertFalse(str("").is.not.blank());
        Assertions.assertTrue(str("").is.not.not.blank());
        Assertions.assertTrue(string("").is.blank());
        Assertions.assertFalse(string("").is.not.blank());
        Assertions.assertTrue(string("").is.not.not.blank());

        Assertions.assertTrue(str(" ").is.blank());
        Assertions.assertFalse(str(" ").is.not.blank());
        Assertions.assertTrue(str(" ").is.not.not.blank());
        Assertions.assertTrue(string(" ").is.blank());
        Assertions.assertFalse(string(" ").is.not.blank());
        Assertions.assertTrue(string(" ").is.not.not.blank());

        Assertions.assertTrue(str(null).is.blank());
        Assertions.assertFalse(str(null).is.not.blank());
        Assertions.assertTrue(str(null).is.not.not.blank());
        Assertions.assertTrue(string(null).is.blank());
        Assertions.assertFalse(string(null).is.not.blank());
        Assertions.assertTrue(string(null).is.not.not.blank());

        Assertions.assertFalse(str("X").is.blank());
        Assertions.assertTrue(str("X").is.not.blank());
        Assertions.assertFalse(str("X").is.not.not.blank());
        Assertions.assertFalse(string("X").is.blank());
        Assertions.assertTrue(string("X").is.not.blank());
        Assertions.assertFalse(string("X").is.not.not.blank());
    }

    @Test
    @DisplayName("Test different any and non blank and non blank strings")
    void testIsAnyCharBlank() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> str("").is.any.blank());
    }

    @Test
    @DisplayName("Test different any and non empty and non empty string arrays")
    void testIsAnyEmpty() {
        Assertions.assertTrue(strs("").is.any.empty());
        Assertions.assertFalse(strs("").is.not.any.empty());
        Assertions.assertFalse(strs("").is.none.empty());
        Assertions.assertTrue(strings("").is.any.empty());
        Assertions.assertFalse(strings("").is.none.empty());

        Assertions.assertTrue(strs(" X", "", "x").is.any.empty());
        Assertions.assertFalse(strs("X ", "", "x").is.none.empty());
        Assertions.assertTrue(strings(" ", "").is.any.empty());
        Assertions.assertFalse(strings("", " ").is.none.empty());

        Assertions.assertTrue(strs((String[]) null).is.any.empty());
        Assertions.assertFalse(strs((String[]) null).is.not.any.empty());
        Assertions.assertFalse(strs((String[]) null).is.none.empty());
        Assertions.assertTrue(strings((String[]) null).is.any.empty());
        Assertions.assertFalse(strings((String[]) null).is.none.empty());

        Assertions.assertFalse(strs("X", "x").is.any.empty());
        Assertions.assertTrue(strs("X", "x").is.none.empty());
        Assertions.assertFalse(strings("X", "x").is.any.empty());
        Assertions.assertTrue(strings("X", "x").is.none.empty());
    }

    @Test
    @DisplayName("Test different any and non blank and non blank string arrays")
    void testIsAnyBlank() {
        Assertions.assertTrue(strs("").is.any.blank());
        Assertions.assertFalse(strs("").is.not.any.blank());
        Assertions.assertFalse(strs("").is.none.blank());
        Assertions.assertTrue(strings("").is.any.blank());
        Assertions.assertFalse(strings("").is.none.blank());

        Assertions.assertTrue(strs(" X", " ", "x").is.any.blank());
        Assertions.assertFalse(strs("X ", " ", "x").is.none.blank());
        Assertions.assertTrue(strings(" ", " ").is.any.blank());
        Assertions.assertFalse(strings(" ", " ").is.none.blank());

        Assertions.assertTrue(strs((String[]) null).is.any.blank());
        Assertions.assertFalse(strs((String[]) null).is.not.any.blank());
        Assertions.assertFalse(strs((String[]) null).is.none.blank());
        Assertions.assertTrue(strings((String[]) null).is.any.blank());
        Assertions.assertFalse(strings((String[]) null).is.none.blank());

        Assertions.assertFalse(strs("X", "x").is.any.blank());
        Assertions.assertTrue(strs("X", "x").is.none.blank());
        Assertions.assertFalse(strings("X", "x").is.any.blank());
        Assertions.assertTrue(strings("X", "x").is.none.blank());
    }

    @Test
    @DisplayName("Test substringAfter functionality")
    void testAfter() {
        Assertions.assertNull(str(null).after("*"));
        Assertions.assertEquals("", str("").after("*"));
        Assertions.assertEquals("", str("abrajadabra").after(null));
        Assertions.assertEquals("bc", str("abc").after("a"));
        Assertions.assertEquals("bc", str("abc").ignoreCase().after("A"));
        Assertions.assertEquals("bc", str("abc").after("a"));
        Assertions.assertEquals("cba", str("abcba").after("b"));
        Assertions.assertEquals("", str("abc").after("c"));
        Assertions.assertEquals("", str("abc").after("d"));
        Assertions.assertEquals("abc", str("abc").after(""));
    }

    @Test
    @DisplayName("test substring functionality")
    void testSubstring(){
        Assertions.assertNull(str(null).substring(0,2));
        Assertions.assertEquals("lm",str("alma").substring(1,3));
    }

    @Test
    @DisplayName("Test substringAfterLast functionality")
    void testAfterLast() {
        Assertions.assertNull(str(null).after.last(""));
        Assertions.assertEquals("", str("").after.last(""));
        Assertions.assertEquals("", str("").after.last(""));
        Assertions.assertEquals("", str("").after.last(null));
        Assertions.assertEquals("bc", str("abc").after.last("a"));
        Assertions.assertEquals("a", str("abcba").after.last("b"));
        Assertions.assertEquals("a", str("abcba").ignoreCase().after.last("B"));
        Assertions.assertEquals("", str("abc").after.last("c"));
        Assertions.assertEquals("", str("a").after.the.last("a"));
        Assertions.assertEquals("", str("a").after.last("z"));
        Assertions.assertEquals("", str(null).notNull().after.last(""));
        Assertions.assertNull(str("").forceNull().after.last(""));
        Assertions.assertEquals(" ", str("a ").after.last("a"));
        Assertions.assertEquals(" ", str("a ").forceNull().after.last("a"));
        Assertions.assertNull(str("a ").fforceNull().after.last("a"));

        Assertions.assertNull(string(null).after.last("").toString());
        Assertions.assertEquals("", string("").after.last("").toString());
        Assertions.assertEquals("", string("").after.last("").toString());
        Assertions.assertEquals("", string("").after.last(null).toString());
        Assertions.assertEquals("bc", string("abc").after.last("a").toString());
        Assertions.assertEquals("a", string("abcba").after.last("b").toString());
        Assertions.assertEquals("a", string("abcba").ignoreCase().after.last("B").toString());
        Assertions.assertEquals("", string("abc").after.last("c").toString());
        Assertions.assertEquals("", string("a").after.the.last("a").toString());
        Assertions.assertEquals("", string("a").after.last("z").toString());
        Assertions.assertEquals("", string(null).notNull().after.last("").toString());
        Assertions.assertNull(string("").forceNull().after.last("").toString());
        Assertions.assertEquals(" ", string("a ").after.last("a").toString());
        Assertions.assertEquals(" ", string("a ").forceNull().after.last("a").toString());
        Assertions.assertNull(string("a ").fforceNull().after.last("a").toString());
    }


    @Test
    @DisplayName("Test substringBefore functionality")
    void testBefore() {
        Assertions.assertNull(str(null).before(""));
        Assertions.assertEquals("", str("").before(""));
        Assertions.assertEquals("", str("abc").before("a"));
        Assertions.assertEquals("a", str("abcba").before("b"));
        Assertions.assertEquals("ab", str("abc").before("c"));
        Assertions.assertEquals("abc", str("abc").before("d"));
        Assertions.assertEquals("", str("bc").before(""));
        Assertions.assertEquals("abc", str("abc").before(null));
    }


    @Test
    @DisplayName("Test substringBeforeLast functionality")
    void testBeforeLast() {
        Assertions.assertNull(str(null).before.last(""));
        Assertions.assertEquals("", str("").before.last(""));
        Assertions.assertEquals("abc", str("abcba").before.last("b"));
        Assertions.assertEquals("ab", str("abc").before.last("c"));
        Assertions.assertEquals("", str("a").before.last("a"));
        Assertions.assertEquals("a", str("a").before.last("z"));
        Assertions.assertEquals("a", str("a").before.last(null));
        Assertions.assertEquals("a", str("a").before.last(""));
    }

    @Test
    @DisplayName("Test length comparison")
    void testLengthComparition() {
        Assertions.assertTrue(str(null).is.shorter.than(1));
        Assertions.assertTrue(str("").is.shorter.than(1));
        Assertions.assertFalse(str("A").is.shorter.than(1));
        Assertions.assertTrue(str("A").is.shorter.than("AA"));
        Assertions.assertTrue(str("A").is.shorter.than(string("AA").before.last("A").toStringBuilder().append("A")));
        Assertions.assertTrue(str("AA").is.longer.than("A"));
        Assertions.assertFalse(str("A").is.longer.than(1));
        Assertions.assertTrue(str("A").is.longer.than(0));
        Assertions.assertTrue(str("A").is.not.longer.than(1));
        Assertions.assertEquals(1, str("A").length());
        Assertions.assertTrue(str("A").is.theSameLength.as("B"));
    }

    @Test
    @DisplayName("Convert to String builder at the end of a chain")
    void conversionToStringBuilder() {
        Assertions.assertEquals("AA", string("AA").before.last("A").toStringBuilder().append("A").toString());
    }

    @Test
    @DisplayName("string null correction in toString() works")
    void testToStringNull(){
        Assertions.assertNull( string(null).toString());
        Assertions.assertNotNull( string(null).notNull().toString());
        Assertions.assertNotNull( string("").toString());
        Assertions.assertNotNull( string("").notNull().toString());
        Assertions.assertNull( string("").forceNull().toString());
        Assertions.assertNotNull( string(" ").forceNull().toString());
        Assertions.assertNull( string(" ").fforceNull().toString());
        Assertions.assertEquals( "",string(null).forceEmpty().toString());
        Assertions.assertEquals( "",string("").forceEmpty().toString());
        Assertions.assertEquals( "",string(" ").forceEmpty().toString());
    }

    @Test
    @DisplayName("Test padding")
    void testPadding() {
        Assertions.assertEquals("   ", str(null).notNull().pad(3));
        Assertions.assertEquals("   ", str("").pad(3));
        Assertions.assertEquals(" A ", str("A").pad(3));
        Assertions.assertEquals("  A", str("A").left().pad(3));
        Assertions.assertEquals("A  ", str("A").right().pad(3));
        Assertions.assertEquals(" A  ", str("A").both().pad(4));
        Assertions.assertEquals(". A .", "" + string("A").left().pad(2).right().pad(3).both().pad(5, '.'));
    }


    @Test
    @DisplayName("Normalizing space")
    void testSpaceNormalization(){
        Assertions.assertEquals("The red fox jumped over the boring job.", str("The  red    fox  jumped   over  the boring job.").normalizeSpace());
        Assertions.assertEquals("The red fox jumped over the boring job.", string("The  red    fox  jumped   over  the boring job.").normalizeSpace().toString());
    }

    @Test
    @DisplayName("Characters can be stripped from left, right and both sides")
    void testStrip(){
        Assertions.assertEquals("", str(null).notNull().strip());
        Assertions.assertEquals("lm", str("alma").notNull().strip("a"));
        Assertions.assertEquals("lmA", str("almA").notNull().strip("a"));
        Assertions.assertEquals("lm", str("almA").ignoreCase().notNull().strip("a"));

        Assertions.assertEquals("lma", str("alma").notNull().left().strip("a"));
        Assertions.assertEquals("lmA", str("almA").notNull().left().strip("a"));
        Assertions.assertEquals("lmA", str("almA").ignoreCase().notNull().left().strip("a"));

        Assertions.assertEquals("alm", str("alma").notNull().right().strip("a"));
        Assertions.assertEquals("almA", str("almA").notNull().right().strip("a"));
        Assertions.assertEquals("alm", str("almA").ignoreCase().notNull().right().strip("a"));
    }
    @Test
    @DisplayName("Strings expression can be converted to string array")
    void testToArray(){
        Assertions.assertArrayEquals(new String[]{"a","b","c"}, strings("a","b","c").toArray());
        Assertions.assertArrayEquals(new String[]{"a","b","c"}, strs("a","b","c").toArray());
    }

    @Test
    @DisplayName("Test contains")
    void testContains() {
        Assertions.assertTrue(str("abrakadabra").contains("kadab"));
        Assertions.assertTrue(str("abrakadabra").does.contain("kadab"));
        Assertions.assertFalse(str("abrakadabra").does.not.contain("kadab"));
        Assertions.assertTrue(str("abrakadabra").does.not.contain("susisa"));

        Assertions.assertTrue(string("abrakadabra").contains("kadab"));
        Assertions.assertTrue(string("abrakadabra").does.contain("kadab"));
        Assertions.assertFalse(string("abrakadabra").does.not.contain("kadab"));
        Assertions.assertTrue(string("abrakadabra").does.not.contain("susisa"));
    }
}