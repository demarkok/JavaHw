@SuppressWarnings("WeakerAccess")
public interface Predicate<ArgT> extends Function1<ArgT, Boolean> {

    Predicate ALWAYS_TRUE = x -> true;
    Predicate ALWAYS_FALSE = x -> false;

    default Predicate < ArgT > or(Predicate<? super ArgT> p) {
        return x -> apply(x) || p.apply(x);
    }

    default Predicate < ArgT > and(Predicate<? super ArgT> p) {
        return x -> apply(x) && p.apply(x);
    }

    default Predicate < ArgT > not() {
        return x -> !apply(x);
    }
}
