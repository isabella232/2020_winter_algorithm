#include <iostream>
#include <algorithm>
#include <tuple>
#include <queue>
using namespace std;

int n, m;
int grid[21][21], temp[21][21];
int ans, visited[21][21];
int dx[] = {0, 1, 0, -1};
int dy[] = {1, 0, -1, 0};
queue<pair<int, int> > q;

void print() {
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            cout << grid[i][j] << ' ';
        }
        cout << '\n';
    }
}

bool OOB(int x, int y, int target) {
    return 0 <= x && x < n && 0 <= y && y < n && !visited[x][y] && (grid[x][y] == target || grid[x][y] == 0);
}

void bfs(int x, int y, int target) {
    for(int i = 0; i < n; i++) fill(visited[i], visited[i] + n, 0);

    visited[x][y] = true;
    q.push({x, y});

    while(!q.empty()) {
        int cur_x; int cur_y;
        tie(cur_x, cur_y) = q.front();
        q.pop();

        for(int i = 0; i < 4; i++) {
            int nx = cur_x + dx[i];
            int ny = cur_y + dy[i];

            if(OOB(nx, ny, target)) {
                q.push({nx, ny});
                visited[nx][ny] = true;
            }
        }
    }
}

tuple<int, int, int, int> getGroup(int x, int y) {
    bfs(x, y, grid[x][y]);

    int groupCount = 0; int rainbowCount = 0;
    pair<int, int> xy = make_pair(21, 21);

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(!visited[i][j]) continue;
            groupCount++;
            if(grid[i][j] == 0) rainbowCount++;
            else xy = min(xy, make_pair(i, j));
        }
    }
    int sx, sy;
    tie(sx, sy) = xy;
    return make_tuple(groupCount, rainbowCount, sx, sy);
}

tuple<int, int, int, int> findGroup() {
    tuple<int, int, int, int> group = make_tuple(-1, -1, -1, -1);
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(grid[i][j] >= 1) {
                tuple<int, int, int, int> tp = getGroup(i, j);
                group = max(group, tp);
            }
        }
    }
    return group;
}

void gravity() {
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            temp[i][j] = -2;

    for(int j = 0; j < n; j++) {
        int last_idx = n - 1;
        for(int i = n-1; i >= 0; i--) {
            if(grid[i][j] == -2) continue;
            if(grid[i][j] == -1) last_idx = i;
            temp[last_idx--][j] = grid[i][j];
        }
    }
    
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            grid[i][j] = temp[i][j];
}

void clearGroup(int x, int y) {
    bfs(x, y, grid[x][y]);
    
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(visited[i][j]) {
                grid[i][j] = -2;
            } 
        }
    }
    gravity();
}

void rotateGrid() {
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            temp[i][j] = -2;

    for(int j = n-1; j >= 0; j--)
        for(int i = 0; i < n; i++)
            temp[n-1-j][i] = grid[i][j];

    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            grid[i][j] = temp[i][j];
}

bool pro() {
    tuple<int, int, int, int> group = findGroup(); // 气藕农扁, 气藕荐, 青, 凯
    int groupCount, x, y;
    tie(groupCount, ignore, x, y) = group;

    if(groupCount <= 1 || group == make_tuple(-1, -1, -1, -1)) return false;

    ans += groupCount * groupCount;

    clearGroup(x, y);
    rotateGrid();
    gravity();
    return true;
}

int main() {
    cin >> n >> m;
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            cin >> grid[i][j];

    while(true) {
        bool flag = pro();
        if(!flag) break;
    }
    cout << ans;
}