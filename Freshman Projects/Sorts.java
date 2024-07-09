public class Sorts {

    // no best/worst case for 
    
    //best case are sorted arrays for insertion sort O(n)
    //worst case is decesnding ordered array O(n^2)
    public static void insertionSort (int[] A) {
        for (int unsortedReigion = 1; unsortedReigion < A.length; unsortedReigion++) {
            int itemToInsert = A[unsortedReigion];
            int loc = unsortedReigion-1;
            while (loc >= 0 && A[loc] > itemToInsert) {
                A[loc + 1] = A[loc];
                loc = loc - 1;
            }

            A[loc + 1] = itemToInsert;
        }
    }

    public static void merge (int[] a, int left, int right) {

        //temporary array
        int[] temp = new int[right - left + 1];

        if (left >= right) return;

        int mid = (left + right)/2;
        int leftIndex = left;
        int rightIndex = mid + 1;
        int t = 0;

        while ((leftIndex <= mid) && (rightIndex <= right)) {

            if (a[leftIndex] <= a[rightIndex]) {
                temp[t] = a[leftIndex];
                leftIndex++;
            } else {
                temp[t] = a[rightIndex];
                rightIndex += 1;
            }
            t += 1;
        }

        while(leftIndex <= mid) {
            temp[t] = a[leftIndex];
            leftIndex+= 1;
            t +=1;
        }

        while (rightIndex <= right) {
            temp[t] = a[rightIndex];
            rightIndex += 1;
            t += 1;
        }

        for (t = left; t <= right; t++) {
            a[t] = temp[t - left];
        }
    }
}
    

