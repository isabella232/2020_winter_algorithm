#include <iostream>
#include <algorithm>
using namespace std;

int t;
int D, W, K;
int ans;
int graph[13][20];

bool isOk() {
    for(int i = 0; i < W; i++) {
        bool flag = false;
        int prev_value = graph[0][i];
        int tmp_cnt = 1;
        for(int j = 1; j < D; j++) {
            if(prev_value == graph[j][i]) tmp_cnt++;
            else {
                prev_value = graph[j][i];
                tmp_cnt = 1;
            }

            if(tmp_cnt >= K) {
                // ���� �˻� ���
                flag = true;
                break;
            }
        }
        if(flag == false) return false;
    }
    return true;
}

void dfs(int row, int cnt) { // ���° ���ΰ�, ȭ�й����� ���������?
    if(cnt >= ans) return;

    if(row == D) { // �������ٿ� �Դٸ� ��������Ѱ�?
        if(isOk()) ans = min(ans, cnt);
        return;
    }
    int tmp[20];

    // �๰ �� �� ���
    for(int i = 0; i < W; i++) tmp[i] = graph[row][i]; 
    dfs(row + 1, cnt);

    // �๰�� 0�� ���� �� ���
    for(int i = 0; i < W; i++) graph[row][i] = 0; 
    dfs(row + 1, cnt + 1);
    for(int i = 0; i < W; i++) graph[row][i] = tmp[i];

    // �๰�� 1�� ���� �� ���
    for(int i = 0; i < W; i++) tmp[i] = graph[row][i];
    for(int i = 0; i < W; i++) graph[row][i] = 1;
    dfs(row + 1, cnt + 1);
    for(int i = 0; i < W; i++) graph[row][i] = tmp[i];

}

void pro(int testcase) {
    ans = 987654321;
    dfs(0, 0);
    cout << '#' << testcase << ' ' << ans << '\n';
}

void input() {
    cin >> t;
    for(int tc = 1; tc <= t; tc++) {
        cin >> D >> W >> K;
        for(int i = 0; i < D; i++) {
            for(int j = 0; j < W; j++)
                cin >> graph[i][j];
        }
        pro(tc);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    input();
}