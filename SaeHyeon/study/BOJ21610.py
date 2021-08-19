import sys

input=sys.stdin.readline

# 바구니
# A[r][c]=(r,c)에 있는 물의 양을 의미
# 1,1 ~ N,N인데 N,N다음에는 1,1로 연결되어 있음
# 비바라기 : (N,1),(N,2),(N-1,1),(N-1,2)
# 방향은 8방향 : [[-1,0],[-1,-1],[0,-1],[1,-1],[1,0],[1,1],[0,1],[-1,1]]

# 순서
# 1. 모든 구름이 d[i]방향으로 s[i]칸 이동한다.
# 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1증가한다.
# 3. 구름이 모두 사라진다.
# 4. 2에서 물이 증가한 칸(r,c)에 물복사 버그 마법을 시전한다. 물복사버그마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r,c)
#     에 있는 바구니의 물의양이 증가
# 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이떄 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.

# M 번의 이동이 모두 끝난 후 바구니에 들어있는 물의 야으이 합을 구해보자.


N,M=map(int,input().split())
basket=[]
direction=[[0,-1],[-1,-1],[-1,0],[-1,1],[0,1],[1,1],[1,0],[1,-1]]
d=[]
s=[]
for i in range(N):
    basket.append(list(map(int,input().split())))

for i in range(M):
    a,b=map(int,input().split())
    d.append(a)
    s.append(b)

# cloud=[[0]*N for _ in range(N)]

# cloud[N-1][0],cloud[N-1][1],cloud[N-2][0],cloud[N-2][1]=1,1,1,1
# for i in range(len(cloud)):
#     print(cloud[i])

cloud=[[N-1,0],[N-1,1],[N-2,0],[N-2,1]]
diagonal=[[-1,-1],[-1,1],[1,-1],[1,1]]

# M번 구름 이동
for i in range(M):
    # 방향, 거리
    dir=d[i]-1
    dis=s[i]
    next_clouds=[]
    # 1번 과정
    for j in range(len(cloud)):
        row,col=cloud[j]
        nr = (N+row+direction[dir][0]*dis)%N
        nc = (N+col+direction[dir][1]*dis)%N
        next_clouds.append([nr,nc])
        #basket[nr][col]+=1
    
    # 2번 과정
    visited=[[False]*N for _ in range(N)]
    for cc in next_clouds:
        r=cc[0]
        c=cc[1]
        basket[r][c]+=1
        visited[r][c]=True
    #3번 과정
    cloud=[]

    # 4번 과정
    for j in next_clouds:
        row,col=j    
        cnt=0
        for k in range(4):
            nr=row+diagonal[k][0]
            nc=col+diagonal[k][1]
            if nr<0 or nc<0 or nr>=N or nc>=N:
                continue
            if not basket[nr][nc]==0:
                cnt+=1
        basket[row][col]+=cnt

    for j in range(N):
        for k in range(N):
            if basket[j][k]>=2 and visited[j][k]==False:
                basket[j][k]-=2
                cloud.append([j,k])

result=0
for i in range(N):
    result+=sum(basket[i])

print(result)
