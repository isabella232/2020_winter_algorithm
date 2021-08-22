#include <bits/stdc++.h>
using namespace std;

int n;

bool compare(string a, string b) {
    transform(a.begin(), a.end(), a.begin(), ::tolower);
    transform(b.begin(), b.end(), b.begin(), ::tolower);

    if(a < b) return true;
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    while(true) {
        cin >> n;
        if(n == 0) break;

        vector<string> vec;
        vec.resize(n);
        for(int i = 0; i < n; i++) {
            cin >> vec[i];
        }

        sort(vec.begin(), vec.end(), compare);

        cout << vec[0] << "\n";
    }
    
}
