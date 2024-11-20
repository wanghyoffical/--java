import java.util.Arrays;

public class quickSort {


    public  static void quickSort(int[] arr,int left,int right){
        if(left < right){
            //生成一个随机数取值为0-N;
           int random=(int)(Math.random()*(right-left+1))+left;
           swap(arr,right,random);
            int[] partition = partition(arr, left, right);
            quickSort(arr,left,partition[0]);
            quickSort(arr,partition[1],right);
        }
    }

    public static int[] partition(int[] arr,int left,int right){
        int less=left-1;
        int more=right+1;
       int i=left;
       int number=arr[right];
       while(i<more){
          if(arr[i]<number){
              swap(arr,i,less+1);
              less++;
              i++;
          } else if(arr[i]>number){
              swap(arr,i,more-1);
              more--;
          }
          else{
              i++;
          }
       }
       return new int[]{less,more};
    }

    public  static void swap(int[] arr,int i,int j){
        if(i!=j){
            arr[i]=arr[i]^arr[j];
            arr[j]=arr[i]^arr[j];
            arr[i]=arr[i]^arr[j];
        }
    }

    public static void main(String[] args) {
          int[] arr={4,7,2,5,2,5,3,7,0,199,-101};
          quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
