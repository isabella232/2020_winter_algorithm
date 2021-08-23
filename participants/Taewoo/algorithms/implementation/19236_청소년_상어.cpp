#include <bits/stdc++.h>
using namespace std;

// 이동  : 빈칸, 다른 물고기가 있는 칸
// 불가능: 상어, 경계
// 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 45' 회전
typedef pair<int, int> Fish;
int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};
pair<int, int> fish[4][4];
int ans;
void input() {
    for(int i = 0; i < 4; i++) {
        for(int j = 0; j < 4; j++) {
            int a, b; cin >> a >> b;
            fish[i][j] = make_pair(a, b - 1);
        }
    }
}

void print() {
    cout << "\n";
    for(int i = 0; i< 4; i++) {
        for(int j = 0; j < 4; j++) {
            int num;
            num = fish[i][j].first;
            cout << num << ' ';
        }
        cout << "\n";
    }
}

bool inRange(int x, int y) {
    return x >= 0 && x < 4 && y >= 0 && y < 4;
}

bool isOk(int x, int y) {
    return inRange(x, y) && fish[x][y] != make_pair(-2, -2);
}

tuple<int, int, int> nextPos(int x, int y, int dir) {
    for(int i = 0; i < 8; i++) {
        int ndir = (dir + i) % 8;
        int nx = x + dx[ndir];
        int ny = y + dy[ndir];
        if(isOk(nx, ny)) {
            return make_tuple(nx, ny, ndir);
        }
    }
    return make_tuple(x, y, dir);
}

void changeFish(int x1, int y1, int x2, int y2) {
    pair<int, int> temp = fish[x1][y1];
    fish[x1][y1] = fish[x2][y2];
    fish[x2][y2] = temp;
    
}

void move(int num) {
    for(int x = 0; x < 4; x++) {
        for(int y = 0; y < 4; y++) {
            int fish_number, dir;
            tie(fish_number, dir) = fish[x][y];
            if(fish_number == num) {
                int nx, ny, ndir;
                tie(nx, ny, ndir) = nextPos(x, y, dir);
                fish[x][y] = make_pair(fish_number, ndir);
                changeFish(x, y, nx, ny);
                return;
            }
        }
    }
}

void moveAll() {
    for(int i = 1; i <= 16; i++) {
        move(i);
    }
}

bool isSharkGo(int x, int y) {
    return inRange(x, y) && fish[x][y] != make_pair(-1, -1);
}

bool isMaxScore(int x, int y, int dir) {
    for(int i = 1; i <= 4; i++) {
        int nx = x + dx[dir] * i;
        int ny = y + dy[dir] * i;
        if(isSharkGo(nx, ny)) return false;
    }
    return true;
}

void getMaxScore(int x, int y, int dir, int sum) {
    if(isMaxScore(x, y, dir)) {
        ans = max(ans, sum);
        return;
    }


    for(int i = 1; i <= 4; i++) {
        int nx = x + dx[dir] * i;
        int ny = y + dy[dir] * i;
        if(!isSharkGo(nx, ny)) continue;

        pair<int, int> temp[4][4];
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                temp[i][j] = fish[i][j];

        int score, next_dir;
        tie(score, next_dir) = fish[nx][ny];
        fish[nx][ny] = make_pair(-2, -2);
        fish[x][y] = make_pair(-1, -1);

        moveAll();
        getMaxScore(nx, ny, next_dir, sum + score);

        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                fish[i][j] = temp[i][j];
    }
}

void pro() {
 // 상어가 (0, 0) 물고기 먹고
    int num, dir;
    tie(num, dir) = fish[0][0];
    fish[0][0] = make_pair(-2, -2);

 // 물고기 이동
     moveAll();
     getMaxScore(0, 0, dir, num);

     cout << ans;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    input();
    pro();
    return 0;
}
