package kr.sadalmelik;

import java.util.List;

public class BooleanArgsTypeImpl implements ArgsType<Boolean> {

    private boolean arg;

    @Override
    public void set(List<String> list, int index) throws ArgsException {
        this.arg = true;
    }

    @Override
    public Boolean get() {
        return arg;
    }
}
