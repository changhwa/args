package kr.sadalmelik;

import java.util.List;

public class StringArgsTypeImpl implements ArgsType<String>{

    private String arg;

    @Override
    public void set(List<String> args, int index) throws ArgsException {
        // 예외처리?
        this.arg = args.get(++index);
    }

    @Override
    public String get(){
        return arg;
    }
}
