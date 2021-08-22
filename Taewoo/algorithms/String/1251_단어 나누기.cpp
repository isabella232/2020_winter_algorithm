#include <bits/stdc++.h>
using namespace std;
string s, ans;
vector<string> v;
int main() {
    cin >> s;

    for(int i = 1; i < s.size() -1; i++) {
        for(int j = i+1; j < s.size(); j++) {
            string a = s.substr(0, i);
            string b = s.substr(i, j-i);
            string c = s.substr(j, s.size());
            reverse(a.begin(), a.end());
            reverse(b.begin(), b.end());
            reverse(c.begin(), c.end());
            string temp = a;
            temp += b;
            temp += c;
            v.push_back(temp);
        }
    }
    sort(v.begin(), v.end());
    cout << v[0];
}

// a b c d | e | f