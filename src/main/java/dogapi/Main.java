package dogapi;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String breed = "hound";
        BreedFetcher breedFetcher = new CachingBreedFetcher(new BreedFetcherForLocalTesting());
        int result = getNumberOfSubBreeds(breed, breedFetcher);
        System.out.println(breed + " has " + result + " sub breeds");

        breed = "cat";
        result = getNumberOfSubBreeds(breed, breedFetcher);
        System.out.println(breed + " has " + result + " sub breeds");
    }

    /**
     * Return the number of sub breeds that the given dog breed has according to the
     * provided fetcher.
     * @param breed the name of the dog breed
     * @param breedFetcher the breedFetcher to use
     * @return the number of sub breeds. Zero should be returned if there are no sub breeds
     * returned by the fetcher
     */
    public static int getNumberOfSubBreeds(String breed, BreedFetcher breedFetcher) {
        // TODO Task 3 implement this code so that it is entirely consistent with its provided documentation.
        try {
            List<String> subBreeds = breedFetcher.getSubBreeds(breed);

            // The method returns the number of sub breeds (which is the list size).
            // This handles the case where there are no sub breeds (size 0).
            return subBreeds.size();

        } catch (BreedFetcher.BreedNotFoundException e) {

            // The test files expect a return value of -1 when the breed is not found.
            // Output an error message to stderr for consistent logging.
            System.err.println("Error fetching breed '" + breed + "': " + e.getMessage());
            return -1;
        }
    }
}
