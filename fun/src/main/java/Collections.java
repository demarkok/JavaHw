import java.util.Iterator;
import java.util.NoSuchElementException;

class Collections {
    static <ResT, Q> Iterable<ResT> map(Function1<? super Q, ResT> f, Iterable<Q> a) {
        return () -> new Iterator <ResT>() {
            private final Iterator <Q> it = a.iterator();
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public ResT next() {
                return f.apply(it.next());
            }
        };
    }

    static <Q> Iterable<Q> filter(Predicate<? super Q> p, Iterable<Q> a) {
        return () -> new Iterator<Q>() {
            private final Iterator<Q> it = a.iterator();
            boolean superHasNext = true;
            private Q buffValue = null;
            {
                shiftToTrue();
            }
            private void shiftToTrue() {
                superHasNext = false;
                while (it.hasNext()) {
                    buffValue = it.next();
                    if (p.apply(buffValue)) {
                        superHasNext = true;
                        break;
                    }
                }
            }
            @Override
            public boolean hasNext() {
                return superHasNext;
            }

            @Override
            public Q next() {
                if (!hasNext()) {
                    throw (new NoSuchElementException());
                }
                Q tmp = buffValue;
                shiftToTrue();
                return tmp;
            }
        };
    }

    static <Q> Iterable<Q> takeWhile(Predicate<? super Q> p, Iterable<Q> a) {
        return () -> new Iterator<Q>() {

            private final Iterator<Q> it = a.iterator();
            boolean superHasNext = it.hasNext();
            private Q buffValue = it.next();

            @Override
            public boolean hasNext() {
                return superHasNext && p.apply(buffValue);
            }

            @Override
            public Q next() {
                if (!hasNext()) {
                    throw (new NoSuchElementException());
                }
                if (!it.hasNext()) {
                    superHasNext = false;
                    return buffValue;
                }
                Q tmp = buffValue;
                buffValue = it.next();
                return tmp;
            }
        };
    }

    static <Q> Iterable<Q> takeUnless(Predicate<? super Q> p, Iterable<Q> a) {
        return takeWhile(p.not(), a);
    }

    static <A, B> A foldL(Function2<? super A, ? super B, ? extends A> f, A init, Iterable<B> a) {
        A value = init;
        for (B it : a) {
            value = f.apply(value, it);
        }
        return value;
    }

    static <A, B> B foldR(Function2<? super A, ? super B, ? extends B> f, B init, Iterable<A> a) {
        Iterator<A> it = a.iterator();
        if (!it.hasNext()) {
            return init;
        } else {
            A tmp = it.next();
            return f.apply(tmp, foldR(f, init, (Iterable<A>) () -> it));
        }
    }
}
