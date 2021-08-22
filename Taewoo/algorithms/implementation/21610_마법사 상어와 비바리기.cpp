#include <iostream>
#include <vector>
#include <algorithm>
#include <tuple>
#define MAX 51
using namespace std;

int n, m;
int grid[MAX][MAX], groom[MAX][MAX], nextGroom[MAX][MAX];
int dx[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
int dy[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
int cdx[] = {-1, -1, 1, 1};
int cdy[] = {-1, 1, 1, -1};
void input() {
    cin >> n >> m;
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            cin >> grid[i][j];
}

void makeGroom() {
    for(int i = n-2; i < n; i++)
        for(int j = 0; j < 2; j++)
            groom[i][j] = 1;
}

pair<int, int> nextPos(int x, int y, int d, int s) {
    int nx = (x + dx[d] * s + n * s) % n;
    int ny = (y + dy[d] * s + n * s) % n;
    return make_pair(nx, ny);
}

void move(int d, int s) {
    fill(&nextGroom[0][0], &nextGroom[0][0] + MAX*MAX, 0);

    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            if(groom[i][j]) {
                int x, y;
                tie(x, y) = nextPos(i, j, d, s);
                nextGroom[x][y] = 1;
            }

    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            groom[i][j] = nextGroom[i][j];
}

void increaseWater() {
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(groom[i][j]) {
                grid[i][j] += 1;
            }
        }
    }
}

bool inRange(int x, int y) {
    return 0 <= x && x < n && 0 <= y && y <= n;
}

int getPos(int x, int y) {
    int cnt = 0;
    for(int i = 0; i< 4; i++) {
        int nx = x + cdx[i], ny = y + cdy[i];
        if(inRange(nx, ny) && grid[nx][ny] > 0) {
            cnt++;
        }
    }
    return cnt;
}

void copyWater() {
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(groom[i][j]) {
                int waterCnt = getPos(i, j);
                grid[i][j] += waterCnt;
            }
        }
    }
}

void updateGroom() {
    fill(&nextGroom[0][0], &nextGroom[0][0] + MAX*MAX, 0);

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(grid[i][j] >= 2 && groom[i][j] == 0) {
                nextGroom[i][j] = 1;
                grid[i][j] -= 2;
            }
        }
    }

    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            groom[i][j] = nextGroom[i][j];
}

void simulate(int d, int s) {
    // 구름 움직이기
    move(d, s);
    // 바구니 물의양 +1 증가
    increaseWater(); 
    // 물복사 시전
    copyWater();
    // 구름 생성 및 물의양 줄어들이기
    updateGroom();
}

void process() {
    makeGroom();
    while(m--) {
        int d, s;
        cin >> d >> s;
        simulate(d, s);
    }

    int ans = 0;
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            if(grid[i][j]) ans += grid[i][j];

    cout << ans;
}

int main() {
    input();
    process();
}