#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

string s;
int ans;
int temp;
vector<int> selected, cnt;
int main() {
    cin >> s;
    string f = "quack";
    selected.resize(s.size());
    cnt.resize(s.size());

    bool flag = true;
    for(int i = 0; i < s.size(); i++) {
        if(s[i] != 'q') continue;
        int cur = i;
        int idx = 0;
        while(idx < 5  and cur < s.size()) {
            if(selected[cur] == 0 and s[cur] == f[idx]){
                selected[cur] = 1;
                idx++;
            }
            cur++;
        }
        if(idx != 5) flag = false;
        for(int j = i; j < cur; j++) cnt[j]++;
    }

    for(int i = 0; i < s.size(); i++) {
        ans = max(ans, cnt[i]);
        if(!cnt[i]) flag = false;
    }

    if(flag) cout << ans;
    else cout << -1;
}

// 112222221221111
// 112112132332233
// quqacukqauackck

