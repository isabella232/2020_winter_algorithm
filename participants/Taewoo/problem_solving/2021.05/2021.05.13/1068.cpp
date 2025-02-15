#include <iostream>
#include <vector>
using namespace std;
vector<int> tree[51];
int N, del, answer;

void dfs(int idx) {
    bool isOk = false;
    for(auto next: tree[idx]) {
        isOk = true;
        dfs(next);
    }
    if(!isOk) answer++;
}

int main() {
    cin >> N;
    int root = 0;
    for(int i = 0; i < N; i++) {
        int tmp;
        cin >> tmp;
        if(tmp == -1) {
            root = i;
            continue;
        }
        tree[tmp].push_back(i);
    }
    cin >> del;
    tree[del].clear();
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < tree[i].size(); j++) {
            if(del == tree[i][j]) tree[i].erase(tree[i].begin() + j);
        }
    }
    if(del != root) dfs(root);
    cout << answer;
}