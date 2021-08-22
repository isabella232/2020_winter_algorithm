import sys
input=sys.stdin.readline

def dir_select(arr,num,a,b):
    direction=[[-1,0],[-1,1],[0,1],[1,1],[1,0],[1,-1],[0,-1],[-1,-1]]

    for i in range(8):
        na=a+direction[i][0]
        nb=b+direction[i][1]
        if na<0 or nb<0 or na>=19 or na>=19:
            continue
        if arr[na][nb]==num:
            return direction[i]

def who_winner(arr,num,a,b,dir_x,dir_y):
    cnt=1
    for i in range(19):
        if arr[a+dir_x][b+dir_y]==num:
            cnt+=1
            a+=dir_x
            b+=dir_y
        else:
            break

    if cnt==5:
        return num
    else:
        return -1

def main():
    flag=True
    board=[]
    for _ in range(19):
        board.append(list(map(int,input().split())))

    for i in range(19):
        if flag==False:
            break
        for j in range(19):
            if board[i][j]==1:
                x,y=dir_select(board,1,i,j)
                if who_winner(board,1,i,j,x,y)==1:
                    print(1)
                    print(i+1,j+1)
                    flag=False
                    break
            elif board[i][j]==2:
                x,y=dir_select(board,2,i,j)
                if who_winner(board,2,i,j,x,y)==2:
                    print(2)
                    print(i+1,j+1)
                    flag=False
                    break
    if flag==True:
        print(0)                        

if __name__ == '__main__':
    main()