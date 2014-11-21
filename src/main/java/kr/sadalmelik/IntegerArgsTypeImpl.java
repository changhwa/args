package kr.sadalmelik;

import java.util.List;

public class IntegerArgsTypeImpl implements ArgsType<Integer> {

    private int arg = 0;

    @Override
    public void set(List<String> args, int index) throws ArgsException {
        this.arg = Integer.parseInt(args.get(++index));
    }

    @Override
    public Integer get() {
        return arg;
    }
}
