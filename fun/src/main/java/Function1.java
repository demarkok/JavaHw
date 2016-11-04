@FunctionalInterface
public interface Function1<ArgT, ResT> {

    ResT apply(ArgT x);

    default <CompResT> Function1 <ArgT, CompResT> compose(Function1<? super ResT, CompResT> g) {
        return x -> g.apply(Function1.this.apply(x));
    }
}
