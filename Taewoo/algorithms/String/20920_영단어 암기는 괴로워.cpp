#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
using namespace std;
int n, m;
unordered_map<string, int> mp;
vector<string> v;

bool compare(string str1, string str2) {
    if(mp[str1] == mp[str2]) {
        if(str1.size() == str2.size()) return str1 < str2;
        else return str1.size() > str2.size();
    }
    else return mp[str1] > mp[str2];
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin >> n >> m;
    for(int i = 0; i < n; i++) {
        string str;
        cin >> str;
        if(str.size() >= m) {
            if(mp.find(str) != mp.end()) {
                mp[str]++;
            }
            else {
                mp[str]++;
                v.push_back(str);
            }
        }
    }
    sort(v.begin(), v.end(), compare);
    for(auto it : v) cout << it << "\n";
}