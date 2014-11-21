package kr.sadalmelik;

import java.util.List;

public interface ArgsType<T> {

    void set(List<String> list, int index) throws ArgsException ;

    T get();
}
