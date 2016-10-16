interface Predicate <ArgT> extends Function1 <ArgT, Boolean> {

    default Predicate < ArgT > or(Predicate<? super ArgT> p) {
        return x -> Predicate.this.apply(x) || p.apply(x);
    }

    default Predicate < ArgT > and(Predicate<? super ArgT> p) {
        return x -> Predicate.this.apply(x) && p.apply(x);
    }

    default Predicate < ArgT > not() {
        return x -> !Predicate.this.apply(x);
    }

    final class ALWAYS_TRUE <ArgT> implements Predicate <ArgT> {
        @Override
        public Boolean apply(ArgT x) {
            return true;
        }
    }

    final class ALWAYS_FALSE <ArgT> implements Predicate <ArgT> {
        @Override
        public Boolean apply(ArgT x) {
            return false;
        }
    }
}
