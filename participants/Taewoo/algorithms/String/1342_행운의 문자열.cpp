#include <iostream>
#include <algorithm>
using namespace std;

string s;
int cnt;
bool luckyString() {

    for(int i = 0; i < s.size(); i++) {
        if(i == 0 && s[i] == s[i+1]) return false;
        if(i == s.size()-1 && s[i] == s[i-1]) return false;
        if(s[i] == s[i-1] || s[i] == s[i+1]) return false;
    }
    return true;
}
int main() {
    cin >> s;
    sort(s.begin(), s.end());
    do {
        if(luckyString()) cnt++;
    } while(next_permutation(s.begin(), s.end()));
    cout << cnt;
}