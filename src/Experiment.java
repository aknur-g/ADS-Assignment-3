public class Experiment {

    private Sorter sorter;
    private Searcher searcher;

    public Experiment(Sorter sorter, Searcher searcher) {
        this.sorter = sorter;
        this.searcher = searcher;
    }

    // Measure sorting time
    public long measureSortTime(int[] arr, String type) {
        int[] copy = arr.clone();

        long start = System.nanoTime();

        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(copy);
        } else if (type.equalsIgnoreCase("advanced")) {
            sorter.advancedSort(copy);
        }

        long end = System.nanoTime();
        return end - start;
    }

    // Measure searching time
    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();
        searcher.search(arr, target);
        long end = System.nanoTime();

        return end - start;
    }

    // Run all experiments
    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000};

        for (int size : sizes) {
            System.out.println("\nArray Size: " + size);

            int[] randomArray = sorter.generateRandomArray(size);
            int[] sortedArray = randomArray.clone();
            sorter.advancedSort(sortedArray); // sort for binary search

            long basicRandomTime = measureSortTime(randomArray, "basic");
            long advancedRandomTime = measureSortTime(randomArray, "advanced");
            long searchTime = measureSearchTime(sortedArray, sortedArray[size / 2]);

            System.out.println("Bubble Sort (Random): " + basicRandomTime + " ns");
            System.out.println("Merge Sort  (Random): " + advancedRandomTime + " ns");
            System.out.println("Binary Search:       " + searchTime + " ns");

            long basicSortedTime = measureSortTime(sortedArray, "basic");
            long advancedSortedTime = measureSortTime(sortedArray, "advanced");

            System.out.println("Bubble Sort (Sorted): " + basicSortedTime + " ns");
            System.out.println("Merge Sort  (Sorted): " + advancedSortedTime + " ns");
        }
    }
}