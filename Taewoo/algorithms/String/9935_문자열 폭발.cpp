// 1. ���ڿ��� ���� ���ڿ��� �����ϰ� �ִ� ��쿡, ��� ���� ���ڿ��� �����ϰ� �ȴ�. 
//    ���� ���ڿ��� ������� �̾� �ٿ� ���ο� ���ڿ��� �����.
// 2. ���� ���� ���ڿ��� ���� ���ڿ��� ���ԵǾ� ���� ���� �ִ�.
// 3. ������ ���� ���ڿ��� ���ڿ��� ���� ������ ��ӵȴ�.
// 
#include <bits/stdc++.h>
using namespace std;

string a, b;
char ret[1000001];
int main() {
    cin >> a >> b;

    int idx = 0;
    for(int i = 0; i < a.size(); i++) {
        ret[idx++] = a[i];

        if(a[i] == b[b.size() - 1]) {
            if(idx - b.size() < 0) continue;
            
            bool flag = true;
            for(int j = 0; j < b.size(); j++) {
                if(ret[idx-1-j] != b[b.size()-j-1]) {
                    flag = false;
                    break;
                }
            }
            if(flag) idx -= b.size();
        }
    }

    if(idx == 0) cout << "FRULA";
    else {
        for(int i = 0; i < idx; i++) cout << ret[i];
    }

    return 0;
}