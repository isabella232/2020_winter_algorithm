#Problem : https://www.acmicpc.net/problem/1158
#Comment : 결과는 맞게 나왔는데 틀림, 나중에 다시 풀어보기
from collections import deque

n,k=map(int,input().split())

queue=deque()
for i in range(1,n+1):
    queue.append(i)
result=[]
cnt=1
while queue:
    if cnt==3:
        cnt=1
        result.append(queue.popleft())
        continue
    if cnt < 3:
        a=queue.popleft()
        queue.append(a)
        cnt+=1

print('<',end='')
for i in range(len(result)):
    if i == len(result)-1:
        print(result[i],end='')
        break
    print(result[i],end=', ')
print('>')
