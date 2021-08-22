// 1. 문자열이 폭발 문자열을 포함하고 있는 경우에, 모든 폭발 문자열이 폭발하게 된다. 
//    남은 문자열을 순서대로 이어 붙여 새로운 문자열을 만든다.
// 2. 새로 생긴 문자열에 폭발 문자열이 포함되어 있을 수도 있다.
// 3. 폭발은 폭발 문자열이 문자열에 없을 때까지 계속된다.
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