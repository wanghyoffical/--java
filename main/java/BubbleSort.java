import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class BubbleSort {

    //冒泡排序算法
    public static  void bubbleSort(int[] arr){
        if(arr == null || arr.length < 2) {
            return;
        }
        for(int i=arr.length-1; i>=0; i--){
           for(int j=0;j<i;j++){
               if(arr[j]>arr[j+1]){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
               }
           }
        }
    }
    //选择排序
    public static void selectionSort(int[] arr){
        if(arr == null || arr.length < 2) {
            return;
        }
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
 //异或实现数组两值交换
    public  static void exchange(int[] arr, int a, int b){
        if(arr == null || a==b) {
            return;
        }
        arr[a]=arr[a]^arr[b];
        arr[b]=arr[a]^arr[b];
        arr[a]=arr[a]^arr[b];
    }

   int[] generateRandomArray(int maxSize,int maxValue){
       double random = Math.random()*(maxSize+1);
      int randomSize=(int)random+1;
       int[] arr = new int[randomSize];
       for(int i=0;i<randomSize;i++){
          arr[i]=(int) (Math.random()*(maxValue+1));
       }
       return arr;
   }

    public static void insertSort(int[] arr){
        if(arr == null || arr.length < 2) {
            return;
        }
        for(int i=1;i<arr.length;i++){
           for(int j=i-1;j>=0&&arr[j]>arr[j+1];j--){
              if(arr[j+1]<arr[j]){
                exchange(arr,j,j+1);
              }
           }
        }
    }

   public static  void printfOddTimesNum2(int[] arr){
       int eor=0;
       for(int i=0;i<arr.length;i++){
           eor=eor^arr[i];
       }
       int rightOne=eor&(~eor+1);
       int onlyOne=0;
       for(int cur:arr){
           if((cur & rightOne)==0 ){
               onlyOne=onlyOne^cur;
           }
       }
       eor=eor^onlyOne;
       System.out.println(eor+" "+onlyOne);
   }

    public static void main(String[] args) {
        int[] arr={1,2,5,3,8,6};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
