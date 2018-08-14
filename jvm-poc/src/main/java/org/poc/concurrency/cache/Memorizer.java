package org.poc.concurrency.cache;

import java.util.Map;
import java.util.concurrent.*;

public class Memorizer<A, V> implements Computable<A, V> {

    private Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private Computable<A, V> computable;

    public Memorizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                FutureTask<V> ft = new FutureTask<>(new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return computable.compute(arg);
                    }
                });
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }

            try {
                return f.get();
            } catch (ExecutionException e) {
                cache.remove(arg);
            }
        }
    }
}
