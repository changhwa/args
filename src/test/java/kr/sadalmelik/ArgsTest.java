package kr.sadalmelik;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class ArgsTest {

    Args arg;

    @Before
    public void setUp() throws Exception{
        arg = new Args("l,p#,d*", new String[]{"-l", "-p", "2000", "-d", "/test/test"});
    }

    @Test
    public void argsStringTest() throws Exception{
        assertThat(arg.getString('d'), is("/test/test"));
    }

    @Test
    public void argsIntTest() throws Exception {
        assertThat(arg.getInt('p'), is(2000));
    }

    @Test
    public void argsBooleanTest() throws Exception{
        assertThat(arg.getBoolean('l'), is(true));
    }

    @Test
    public void argsTotalTest() throws Exception{
        assertThat(arg.getString('d'), is("/test/test"));
        assertThat(arg.getInt('p'), is(2000));
        assertThat(arg.getBoolean('l'), is(true));
    }


}