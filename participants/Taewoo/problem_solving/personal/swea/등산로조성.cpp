/*
    1. ���δ� ���� ū ���츮�������� ����
    2. ���� �� -> ���� ������(�� �� �� ��)
    3. k��ŭ ��� ���� ������
*/
#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
#define endl "\n"
using namespace std;

int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};
int n, k, answer;
int map[8][8];
int visit[8][8];
int highest;

void input() {
    cin >> n >> k;
    highest = 0;

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            cin >> map[i][j];
            highest = max(highest, map[i][j]);
        }
    }
}

void dfs(int x, int y, int cnt, int isOk) {
    answer = max(answer, cnt);

    for(int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if(nx < 0 || ny < 0 || nx == n || ny == n) continue;
        if(visit[nx][ny]) continue;

        // ���纸�� ���ų� ���� �����̸� ��Ƽ� �����Ѱ�? �������� ������?
        if(map[x][y] <= map[nx][ny]) {
            if(isOk == 0) { // ��� ���

                for(int dep = 1; dep <= k; dep++) {
                    int temp = map[nx][ny];
                    int land = map[nx][ny] - dep;

                    if(map[x][y] > land) {
                        visit[nx][ny] = true;
                        map[nx][ny] = land;
                        dfs(nx, ny, cnt + 1, 1);
                        map[nx][ny] = temp;
                        visit[nx][ny] = false;
                    }
                }
            }
        }

        // ���纸�� ���� �����̸� ����
        else {
            visit[nx][ny] = true;
            dfs(nx, ny, cnt + 1, isOk);
            visit[nx][ny] = false;
        }
    }
}

void pro() {
    memset(visit, 0, sizeof(visit));
    answer = 0;

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(map[i][j] == highest && !visit[i][j]) {
                visit[i][j] = 1;
                dfs(i, j, 1, 0);
                visit[i][j] = 0;
            }
        }
    }
}

void process() {
    int TC; cin >> TC;
    for(int tc = 1; tc <= TC; tc++) {
        input();
        pro();
        cout << "#" << tc << " " << answer << "\n";
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    process();
}