package kr.sadalmelik;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class ArgsTest {
    @Test
    public void argsTest() throws Exception {
        Args arg = new Args("l,p#,d*", new String[]{"-l", "-p", "2000", "-d", "/test/test"});
//        assertThat(arg.getBoolean('l'), is(true));
//        assertThat(arg.getInt('p'), is(2000));
        assertThat(arg.getString('d'), is("/test/test"));
    }


}