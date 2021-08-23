#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
using namespace std;
// ���ĺ� ���� ���� ���� => ���� �ܾ�
// ���ĺ� �ϳ� �߰� �Ǵ� ���� => ���ĺ� ���� 1�� ���̳��� ��� 
// ���ĺ� �ϳ� ���� => ���ĺ� ���� 1�� ���̳��� ��� 2�� + ��ü ���� ����
/*
    1. �� �ܾ�� �� ���ڸ� ���ϰų�
    2. �� �ܾ�� �� ���ڸ� ���ų�
    3. �ϳ��� ���ڸ� �ٸ� ���ڷ� ġȯ�ؼ� ���� ���� ���ų�
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

        // ���ϰų� �E ����� ��
         int diff = 0;
         for(int j = 0; j < alpha.size(); j++) {
             diff += abs(alpha[j] - alpha2[j]);
         }
         if(diff < 2 && abs((int)v[0].size() - (int)v[i].size()) <= 1) {
             ans++;
             continue;
         }

        // �� ���ڸ� ġȯ�� ����� ��
         diff = 0;
         for(int j = 0; j < alpha.size(); j++) {
             if(abs(alpha[j] - alpha[j]) >= 2) diff++;
             diff += abs(alpha[j] - alpha2[j]);
         }
         if(diff == 2 && v[0].size() == v[i].size()) ans++;
    }
    cout << ans;
}