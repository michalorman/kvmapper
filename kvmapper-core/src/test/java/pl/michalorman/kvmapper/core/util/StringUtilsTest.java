package pl.michalorman.kvmapper.core.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static pl.michalorman.kvmapper.core.util.StringUtils.*;

public class StringUtilsTest {
    @Test
    public void testUncapitalized() throws Exception {
        assertEquals(uncapitalized("Abc"), "abc");
        assertEquals(uncapitalized("abc"), "abc");
        assertEquals(uncapitalized("ABC"), "aBC");
    }

    @Test
    public void testHasLength() throws Exception {
        assertTrue(hasLength("a"));
        assertTrue(hasLength("abc"));
        assertFalse(hasLength(null));
        assertFalse(hasLength(""));
        assertTrue(hasLength("\n"));
        assertTrue(hasLength(" "));
    }

    @Test
    public void testIsBlank() throws Exception {
        assertTrue(isBlank(""));
        assertTrue(isBlank(" "));
        assertTrue(isBlank("     \t\t    \n"));
        assertTrue(isBlank(null));

        assertFalse(isBlank("a"));
        assertFalse(isBlank("           a  \n"));
    }

    @Test
    public void testJoin() throws Exception {
        String[] strings = {
                "Lorem", "ipsum", "dolor", "sit", "amet"
        };
        assertEquals(join(strings, " "), "Lorem ipsum dolor sit amet");
        assertEquals(join(strings, ","), "Lorem,ipsum,dolor,sit,amet");
    }
}
