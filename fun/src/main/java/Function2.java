@SuppressWarnings("WeakerAccess")
@FunctionalInterface
public interface Function2<Arg1T, Arg2T, ResT> {

    ResT apply(Arg1T x, Arg2T y);

    default <CompResT> Function2 <Arg1T, Arg2T, CompResT> compose(Function1<? super ResT, CompResT> g) {
        return (x, y) -> g.apply(apply(x, y));
    }

    default Function1 <Arg2T, ResT> bind1(Arg1T x) {
        return y -> apply(x, y);
    }

    default Function1 <Arg1T, ResT> bind2(Arg2T y) {
        return x -> apply(x, y);
    }

    default Function1<Arg1T, Function1<Arg2T, ResT>> curry() {
        return x -> y -> apply(x, y);
    }
}
