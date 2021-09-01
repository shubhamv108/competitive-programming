package main
import "fmt"

type Node struct {
  var next map[byte]Node
  word string
}

func (n Node) add(word string) Node {
  return n.name
}


func solution(A []int) int {
    N := len(A);
    for i := 0; i < N; i++ {
        if A[i] <= 0 || A[i] >= N {
            continue;
        }
        cur := A[i];
        next := 0;
        for cur > 0 && cur <= N && A[cur - 1] != cur {
            next = A[cur - 1];
            A[cur - 1] = cur;
            cur = next;
        }
    }
    for i := 0; i < N; i++ {
        if A[i] != i+1 {
            return i + 1;
        }
    }
    return N + 1;
}

func main(){
	fmt.Println( solution([]int{1, 2, 3, 6 , 7}) );
}