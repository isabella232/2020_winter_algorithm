/*
Comment : 접두사 앞부분즉 맨 앞 인덱스 0인지만 확인하면되는데... 
          그냥 부분문자열인지 확인할려고 string::npos해서...시간끔...
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