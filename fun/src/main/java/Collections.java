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

    static <T> T foldL (Function2<? super T, ? super T, ? extends T> f, T init, Iterable<T> a) {
        T value = init;
        for (T it: a) {
            value = f.apply(value, it);
        }
        return value;
    }

    static <T> T foldR (Function2<? super T, ? super T, ? extends T> f, T init, Iterable<T> a) {
        Iterator<T> it = a.iterator();
        if (!it.hasNext()) {
            return init;
        } else {
            T tmp = it.next();
            return f.apply(tmp, foldR(f, init, (Iterable<T>) () -> it));
        }
    }
}
