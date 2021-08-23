#include <iostream>
#include <cmath>
#include <tuple>
using namespace std;

int n;

int target_num[201];
bool friends[201][201];
int location[21][21];

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

bool OOB(int x, int y) {
    return 1 <= x && x <= n && 1 <= y && y <= n;
}
bool isFriend(int num1, int num2) {
    return friends[num1][num2];
}
tuple<int, int, int, int> GetCurrCell(int num, int x, int y) {
    int friend_cnt = 0, blank_cnt = 0;
    for(int i = 0; i < 4; i++) {
        int nx = x + dx[i], ny = y + dy[i];
        if(!OOB(nx, ny))  continue;
        if(location[nx][ny] == 0) blank_cnt++;
        else if(isFriend(num, location[nx][ny])) friend_cnt++;
    }

    return make_tuple(-friend_cnt, -blank_cnt, x, y);
}

void Move(int num) {
    // Step1. 가장 우선순위가 높은 칸을 선택합니다.
    tuple<int, int, int, int> tp = {0, 0, n+1, n+1};
    for(int i = 1; i <= n; i++)
        for(int j = 1; j <= n; j++)
            if(location[i][j] == 0) {
                tuple<int, int, int, int> curr = GetCurrCell(num, i, j);
                tp = min(tp, curr);
            }

    // Step2. 해당 위치에 탑승합니다.
    int a, b, x, y;
    tie(a, b, x, y) = tp;
    location[x][y] = num;
}

int numScore(int x, int y) {
    int cnt = 0;
    for(int i = 0; i < 4; i++) {
        int nx = x + dx[i], ny = y + dy[i];
        if(OOB(nx, ny) && isFriend(location[x][y], location[nx][ny])) cnt++;
    }
    if(cnt == 0) return 0;
    else if(cnt == 1) return 1;
    else if(cnt == 2) return 10;
    else if(cnt == 3) return 100;
    else return 1000;
}

int getScore() {
    int score = 0;
    for(int i = 1; i <= n; i++)
        for(int j = 1; j <= n; j++)
            score += numScore(i, j);

    return score;
}

int main() {
    cin >> n;
    
    for(int i = 1; i <= n * n; i++) {
        cin >> target_num[i];

        for(int j = 1; j <= 4; j++) {
            int friend_num;
            cin >> friend_num;
            friends[target_num[i]][friend_num] = true;
        }
    }

    for(int i = 1; i <= n * n; i++) Move(target_num[i]);
    
    int ans = getScore();
    cout << ans;
}