#include<iostream>
#include<vector>
#include<algorithm>
#include<climits>
using namespace std;

int grid[100][100], visited[100][100];
int n, dir;
int cnt = 1;
int len = 1;
int dx[] = {0, -1, 0, 1};
int dy[] = {1, 0, -1, 0};

int main() {
    cin >> n;

    int x = n/2;
    int y = n/2;

    grid[x][y] = cnt++;

    while(true) {
        if(x == n-1 && y == n-1) break;
        for(int i = 0; i < len; i++) {
            x += dx[dir];
            y += dy[dir];
            grid[x][y] = cnt++;
            if(x == n-1 && y == n-1) break; 
        }

        dir = (dir+1) % 4;
        if(dir == 0 || dir == 2) len++;
    }

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            cout << grid[i][j] << ' ';
        }
        cout << "\n";
    }

    return 0;
}