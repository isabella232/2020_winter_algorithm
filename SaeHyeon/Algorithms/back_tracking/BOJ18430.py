# 문제 : 백준 18430
# 내용 : 런타임에러

import sys
input=sys.stdin.readline

N,M=map(int,input().split())
namu=[]
for _ in range(M):
    namu.append(list(map(int,input().split())))
visit=[[False]*M for _ in range(N)]
all_sum=0
def dfs(y,x,value):
    global all_sum
    
    if x==M:
        x=0
        y+=1
    if y==N:
        all_sum=max(all_sum,value)
        return
        
    # a a
    # a
    if y+1<N and x+1<M and not visit[y+1][x] and not visit[y][x+1] and not visit[y][x]:
        visit[y][x]=True
        visit[y+1][x]=True
        visit[y][x+1]=True
        result=value+(namu[y][x]*2)+namu[y+1][x]+namu[y][x+1]
        dfs(y,x+1,result)
        visit[y][x]=False
        visit[y+1][x]=False
        visit[y][x+1]=False
    
    #a a
    #  a
    if y+1<N and x-1>=0 and not visit[y+1][x] and not visit[y][x-1] and not visit[y][x]:
        visit[y][x]=True
        visit[y+1][x]=True
        visit[y][x-1]=True
        result=value+((namu[y][x]*2)+namu[y+1][x]+namu[y][x-1])
        dfs(y,x+1,result)
        visit[y][x]=False
        visit[y+1][x]=False
        visit[y][x-1]=False
        
    #   a
    # a a
    if y-1>=0 and x-1>=0 and not visit[y-1][x] and not visit[y][x-1] and not visit[y][x]:
        visit[y][x]=True
        visit[y-1][x]=True
        visit[y][x-1]=True
        result=value+((namu[y][x]*2)+namu[y-1][x]+namu[y][x-1])
        dfs(y,x+1,result)
        visit[y][x]=False
        visit[y-1][x]=False
        visit[y][x-1]=False
    
    # a
    # a a
    if y-1>=0 and x+1<M and not visit[y-1][x] and not visit[y][x+1] and not visit[y][x]:
        visit[y][x]=True
        visit[y-1][x]=True
        visit[y][x+1]=True
        result=value+((namu[y][x]*2)+namu[y-1][x]+namu[y][x+1])
        dfs(y,x+1,result)
        visit[y][x]=False
        visit[y-1][x]=False
        visit[y][x+1]=False
    dfs(y,x+1,value)

dfs(0,0,0)
print(all_sum)