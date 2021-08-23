import sys
from collections import deque
input=sys.stdin.readline

def main():
    N,M,R=map(int,input().split())
    arr=[list(map(int,input().split())) for _ in range(N)]
    
    for z in range(R):
        row=N-1
        col=M-1
        x,y=0,0
        while (row>0 and col>0):
            tmp=deque()
            tmp.append(arr[x][y])
            
            # 아래로
            for i in range(0,row):
                tmp.append(arr[x+1][y])
                arr[x+1][y]=tmp.popleft()
                x+=1
            #오른쪽으로
            for i in range(0,col):
                tmp.append(arr[x][y+1])
                arr[x][y+1]=tmp.popleft()
                y+=1
            
            # 위로
            for i in range(row,0,-1):
                tmp.append(arr[x-1][y])
                arr[x-1][y]=tmp.popleft()
                x-=1
            # 왼쪽으로
            for i in range(col,0,-1):
                tmp.append(arr[x][y-1])
                arr[x][y-1]=tmp.popleft()
                y-=1

            x+=1
            y+=1
            row-=2
            col-=2
            #break

    for i in range(len(arr)):
        print(*arr[i])
if __name__ == '__main__':
    main()