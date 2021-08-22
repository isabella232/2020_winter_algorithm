#include <bits/stdc++.h>
using namespace std;

int N, K;
vector<int> vec;
int ans = INT_MAX;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> K;
    vec.resize(N);
    for(int i = 0; i < N; i++) cin >> vec[i];

    int l = 0; int r = 0;
    int lionCnt = 0;

    while(r != N + 1) {
        if(lionCnt < K) {
            if(vec[r] == 1) lionCnt++;
            r++;
        }
        else {
            ans = min(ans, r - l);
            if(vec[l] == 1) {
                lionCnt--;
            }
            l++;
        }
    }
    if(ans == INT_MAX) cout << -1;
    else cout << ans;
    return 0;
}
