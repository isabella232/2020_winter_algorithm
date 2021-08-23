#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
using namespace std;
// 알파벳 개수 차이 없음 => 같은 단어
// 알파벳 하나 추가 또는 제거 => 알파벳 개수 1개 차이나는 경우 
// 알파벳 하나 변경 => 알파벳 개수 1개 차이나는 경우 2개 + 전체 길이 동일
/*
    1. 한 단어에서 한 문자를 더하거나
    2. 한 단어에서 한 문자를 빼거나
    3. 하나의 문자를 다른 문자로 치환해서 같은 구성 같거나
*/
int n, ans;
vector<string > v;
vector<int> alpha;
int main() {
    cin >> n;
    v.resize(n);
    alpha.resize(26);
    for(int i = 0; i < n; i++) cin >> v[i];
    for(int i = 0; i < v[0].size(); i++)
        alpha[v[0][i] - 'A']++;
    
    for(int i = 1; i < n; i++) {
        vector<int> alpha2(26);
        
        for(int j = 0; j < v[i].size(); j++)
            alpha2[v[i][j] - 'A']++;

        // 더하거나 뺼 경우의 수
         int diff = 0;
         for(int j = 0; j < alpha.size(); j++) {
             diff += abs(alpha[j] - alpha2[j]);
         }
         if(diff < 2 && abs((int)v[0].size() - (int)v[i].size()) <= 1) {
             ans++;
             continue;
         }

        // 한 문자를 치환할 경우의 수
         diff = 0;
         for(int j = 0; j < alpha.size(); j++) {
             if(abs(alpha[j] - alpha[j]) >= 2) diff++;
             diff += abs(alpha[j] - alpha2[j]);
         }
         if(diff == 2 && v[0].size() == v[i].size()) ans++;
    }
    cout << ans;
}