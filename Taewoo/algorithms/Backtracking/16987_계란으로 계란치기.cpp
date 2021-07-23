/*
Problem : https://www.acmicpc.net/problem/16987
Comment : 
1. ���� ������ ����� ���.
2. �տ� ��� �ִ� ������� ������ ���� �ٸ� ��� �߿��� �ϳ��� ģ��. 
   ��, �տ� �� ����� �����ų� ������ ���� �ٸ� ����� ������ ġ�� �ʰ� �Ѿ��. 
   ���� �տ� �� ����� ���� �ڸ��� �������� 3�� ������ �����Ѵ�.
3. ���� �ֱٿ� �� ����� �� ĭ ������ ����� �տ� ��� 2�� ������ �ٽ� �����Ѵ�. 
   ��, ���� �ֱٿ� �� ����� ���� �����ʿ� ��ġ�� ����� ��� ����� ġ�� ������ �����Ѵ�.
*/
#include <bits/stdc++.h>
using namespace std;

struct egg {
    int dura, weight;
    egg(int dura, int weight) : dura(dura), weight(weight) {}
};

int N, answer;
vector<egg> Eggs;
void input() {
    cin >> N;
    for(int i = 0; i < N; i++) {
        int d, w; cin >> d >> w;
        Eggs.push_back(egg(d, w));
    }
}

void go(int idx) {
    if(idx == N) {
        int cnt = 0;
        for(int i = 0; i < N; i++)
            if(Eggs[i].dura <= 0) cnt++;
        answer = max(answer, cnt);
        return;
    }

    if(Eggs[idx].dura <= 0) {
        go(idx + 1);
        return;
    }

    bool flag = false;
    for(int i = 0; i < N; i++) {
        if(idx == i || Eggs[i].dura <= 0) continue;
        
        Eggs[idx].dura = Eggs[idx].dura - Eggs[i].weight;
        Eggs[i].dura = Eggs[i].dura - Eggs[idx].weight;
        flag = true;
        go(idx + 1);
        Eggs[idx].dura = Eggs[idx].dura + Eggs[i].weight;
        Eggs[i].dura = Eggs[i].dura + Eggs[idx].weight;
    }
    if(flag == false) go(idx + 1);
}

void pro() {
    go(0);
    cout << answer;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    input();
    pro();
}

