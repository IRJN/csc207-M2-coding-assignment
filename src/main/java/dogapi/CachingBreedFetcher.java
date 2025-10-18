package dogapi;

import java.util.*;

/**
 * This BreedFetcher caches fetch request results to improve performance and
 * lessen the load on the underlying data source. An implementation of BreedFetcher
 * must be provided. The number of calls to the underlying fetcher are recorded.
 *
 * If a call to getSubBreeds produces a BreedNotFoundException, then it is NOT cached
 * in this implementation. The provided tests check for this behaviour.
 *
 * The cache maps the name of a breed to its list of sub breed names.
 */
public class CachingBreedFetcher implements BreedFetcher {
    // TODO Task 2: Complete this class
    private final BreedFetcher delegate; // Underlying fetcher to call on cache miss
    private final Map<String, List<String>> cache; // Cache map to store results
    private int callsMade = 0;

    public CachingBreedFetcher(BreedFetcher fetcher) {
        // Initialize fields
        this.delegate = fetcher;
        this.cache = new HashMap<>();
    }

    @Override
    public List<String> getSubBreeds(String breed) {
        // return statement included so that the starter code can compile and run.
        // 1. Check if the breed is in the cache (Cache Hit)
        if (cache.containsKey(breed)) {
            return cache.get(breed);
        }

        // 2. Cache Miss: Call the underlying delegate
        callsMade++;

        // Note: The delegate.getSubBreeds(breed) call will propagate
        // the unchecked BreedNotFoundException if thrown.
        List<String> result = delegate.getSubBreeds(breed);

        // 3. Cache the successful result
        // (If the delegate throws an exception, this line is skipped, satisfying the requirement)
        cache.put(breed, result);

        return result;
    }

    public int getCallsMade() {
        return callsMade;
    }
}