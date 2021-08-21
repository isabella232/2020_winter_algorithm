import sys
input=sys.stdin.readline

def change(S,D):
    result=[0 for _ in range(len(S))]
    #print(result)
    for i in range(len(D)):
        result[D[i]-1]=S[i]
    return result

def main():
    N,K=map(int,input().split())
    #print(N,K)
    S=list(map(int,input().split()))
    D=list(map(int,input().split()))
    P=[]

    for i in range(K):
        P=change(S,D)
        S=P[:]

    for i in range(len(P)):
        print(P[i],end=' ')


if __name__ == '__main__':
    main()