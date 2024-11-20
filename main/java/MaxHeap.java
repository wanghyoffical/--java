import java.util.Arrays;
import java.util.Comparator;

public class MaxHeap {

    public static void heapify(int[] arr, int index,int heapSize){
        int left=2*index+1;
        while(left<heapSize){
           int largest=left+1<heapSize&&arr[left+1]>arr[left]?left+1:left;
           largest=arr[largest]>arr[index]?largest:index;
           if(largest==index){
               break;
           }
            swap(arr,index,largest);
           index=largest;
           left=2*index+1;
        }
    }

    public static void heapInsert(int[] arr,int index){
          while(arr[index]>arr[(index-1)/2]){
              swap(arr,index,(index-1)/2);
              index=(index-1)>>1;
          }
    }

    public static void heapSort(int[] arr){
       if(arr==null || arr.length<2) {
           return;
       }
       //o(n)的时间复杂度
       for(int i=arr.length-1;i>=0;i--){
          heapify(arr,i,arr.length);
       }
        int heapSize=arr.length;
       swap(arr,0,--heapSize);
      while(heapSize>0){
          heapify(arr,0,heapSize);
          swap(arr,0,--heapSize);
      }
    }


    public  static void swap(int[] arr,int i,int j){
        if(i!=j){
            arr[i]=arr[i]^arr[j];
            arr[j]=arr[i]^arr[j];
            arr[i]=arr[i]^arr[j];
        }
    }
    public static  void radixSort(int[] arr,int left,int right){
       int maxBit=MaxBit(arr);
       int i=0; int j=0;
       int[] bucket=new int[right-left+1];
       int radix=right-left+1;
      for(int d=1;i<maxBit;d++){
         int[] count=new int[radix];
         for(i=left;i<=right;i++){
             j=getDigit(arr[i],d);
             count[j]++;
         }
        //求count的前缀和;
          for(i=1;i<radix;i++){
              count[i]+=count[i-1];
          }
          for(i=right;i>=left;i--){
            j=getDigit(arr[i],d);
           bucket[count[j]-1]=arr[i];
           count[j]--;
          }
          for(i=left,j=0;i<=right;i++,j++){
            arr[i]=bucket[j];
          }
      }

    }

    public  static int getDigit(int number,int position){
        int digit=0;
        for(int i=0;i<position;i++){
           digit=number%10;
           number=number/10;
        }
        return digit;
    }

    public static int MaxBit(int[] arr){
        int max=Integer.MIN_VALUE;
        int bit=0;
        for(int i=0;i<arr.length;i++){
           if(arr[i]>max){
               max=arr[i];
           }
        while(max!=0){
            max=max/10;
            bit++;
        }
        }
        return bit;

    }
    //计数排序
    public static void  countSort(int[] arr){
        int[] counts=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            counts[arr[i]]++;
        }
        int count=0;
       for(int i=0;i<arr.length;i++){
           if(counts[i]>0){
          arr[count++]=i;
          counts[i--]--;
           }
       }
    }

    public static void main(String[] args) {
        int[] arr={3,5,1,9,4,6,2,5,8,9};
        radixSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

}
