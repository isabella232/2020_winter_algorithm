/*
Comment : ���λ� �պκ��� �� �� �ε��� 0������ Ȯ���ϸ�Ǵµ�... 
          �׳� �κй��ڿ����� Ȯ���ҷ��� string::npos�ؼ�...�ð���...
*/
#include <bits/stdc++.h>
using namespace std;

struct Node {
    string s; int size;
    Node(string s, int size): s(s), size(size) {}
    bool operator < (const Node &node) const {
        return size < node.size;
    }
};

int n, cnt;
string s;
vector<Node> v;
int main() {
    cin >> n;

    for(int i = 0; i < n; i++) {
        cin >> s;
        v.push_back(Node(s, s.size()));
    }
    sort(v.begin(), v.end());

    for(int i = 0; i < n - 1; i++) {
        bool flag = false;
        for(int j = i+1; j < n; j++) {
            if(v[j].s.find(v[i].s) == 0) {
                flag = true;
                break;
            }
        }
        if(flag) cnt++;
    }
    cout << n - cnt;
}