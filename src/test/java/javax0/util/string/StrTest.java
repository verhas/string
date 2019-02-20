package javax0.util.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static javax0.util.string.Str.*;

public class StrTest {

    @Test
    @DisplayName("Test different empty and non empty strings")
    void testIsEmpty() {
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
        Assertions.assertEquals("bc", str("abc").after("a"));
        Assertions.assertEquals("cba", str("abcba").after("b"));
        Assertions.assertEquals("", str("abc").after("c"));
        Assertions.assertEquals("", str("abc").after("d"));
        Assertions.assertEquals("abc", str("abc").after(""));
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
        Assertions.assertEquals("", str("abc").after.last("c"));
        Assertions.assertEquals("", str("a").after.last("a"));
        Assertions.assertEquals("", str("a").after.last("z"));
        Assertions.assertEquals("", str(null).notNull().after.last(""));
        Assertions.assertNull(str("").forceNull().after.last(""));
        Assertions.assertEquals(" ", str("a ").after.last("a"));
        Assertions.assertEquals(" ", str("a ").forceNull().after.last("a"));
        Assertions.assertNull(str("a ").fforceNull().after.last("a"));
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
}