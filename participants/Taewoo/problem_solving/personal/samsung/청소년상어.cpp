#include<bits/stdc++.h>
using namespace std;

struct Fish {
    int x, y, dir, live;
};

int answer = 0;
int _map[4][4];
int dx[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
int dy[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
Fish fish[17];

void input() {
    for(int i = 0; i < 4; i++) {
        for(int j = 0; j < 4; j++) {
            int a, b; cin >> a >> b;
            _map[i][j] = a;
            fish[a] = {i, j, b, 1};
        }
    }
}

void move_fish() {
    for(int i = 1; i <= 16; i++) {
        /*
            1. ����Ⱑ �׾������� �Ѿ��
            2. �ƴϸ� �����δ�.
            3. ������ ����ų� �� ������ �ٸ�����
            4. ��ĭ�̰ų� �ٸ� ����Ⱑ �ִٸ� �����ϼ�������
            5. �ٸ� ����Ⱑ �ִٸ� ���� ��ġ ����
        */
       if(fish[i].live == 0) continue;

       int x = fish[i].x;
       int y = fish[i].y;
       int dir = fish[i].dir;

       int nx = x + dx[dir];
       int ny = y + dy[dir];
       bool flag = false;

       // ��������
       if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
           
           if(_map[nx][ny] == 0) { // ��ĭ�� ���
                flag = true;
                fish[i].x = nx;
                fish[i].y = ny;
                _map[nx][ny] = i;
                _map[x][y] = 0;       
           }
           else if(_map[nx][ny] != -1) { // �ٸ� ������ΰ��
                flag = true;
                int idx = _map[nx][ny];
                Fish temp = fish[idx];
                fish[idx].x = fish[i].x;
                fish[idx].y = fish[i].y;
                fish[i].x = temp.x;
                fish[i].y = temp.y;

                swap(_map[nx][ny], _map[x][y]);
           }
       }

        // �������� ���ϸ� �ٸ� ����
        if(flag == false) {
            int ddir = dir + 1;
            if(ddir == 9) ddir = 1;
            int nx = x + dx[ddir];
            int ny = y + dy[ddir];

            while(dir != ddir) {
                // ��������
                if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
                    if(_map[nx][ny] == 0) { // ��ĭ�� ���
                        fish[i].x = nx;
                        fish[i].y = ny;
                        fish[i].dir = ddir;
                        _map[nx][ny] = i;
                        _map[x][y] = 0;
                        break;         
                    }
                    else if(_map[nx][ny] != -1) { // �ٸ� ������ΰ��
                        int idx = _map[nx][ny];
                        Fish temp = fish[idx];
                        fish[idx].x = fish[i].x;
                        fish[idx].y = fish[i].y;
                        fish[i].x = temp.x;
                        fish[i].y = temp.y;
                        swap(_map[nx][ny], _map[x][y]);
                        fish[i].dir = ddir;
                        break;
                    }
                }
                ddir++;
                if(ddir == 9) ddir = 1;
                nx = x + dx[ddir];
                ny = y + dy[ddir];
            }
        }
    }
}

void dfs(int x, int y, int dir, int values) {

    answer = max(answer, values);
    
    int cmap[4][4];
    Fish cfish[17];
    for(int i = 0; i < 4; i++) for(int j = 0; j < 4; j++) cmap[i][j] = _map[i][j];
    for(int i = 1; i <= 16; i++) cfish[i] = fish[i];

    // ����� �̵�
    move_fish();

    // �����ϼ� �ִ� ���� ������ ��� �����̱�
    for(int i = 1; i <= 3; i++) {
        int nx = x + dx[dir] * i;
        int ny = y + dy[dir] * i;

        if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) { 
            if(_map[nx][ny] == 0) continue;

            int fish_num = _map[nx][ny];
            fish[fish_num].live = 0;
            _map[nx][ny] = -1;
            _map[x][y] = 0;
            dfs(nx, ny, fish[fish_num].dir, values + fish_num);
            _map[nx][ny] = fish_num;
            _map[x][y] = -1;
            fish[fish_num].live = 1;
        }
        else break;
    } 

    for(int i = 0; i < 4; i++) for(int j = 0; j < 4; j++) _map[i][j] = cmap[i][j];
    for(int i = 1; i <= 16; i++) fish[i] = cfish[i];
}

void pro() {
    int f = _map[0][0]; // ó������ �Դ� �����
    int dir = fish[f].dir; // �� �� ����
    fish[f].live = 0;
    _map[0][0] = -1; // ����� ��ġ�� -1
    // ����� ����ǥ, �� ������ ����, ���� �ִ� ��
    dfs(0, 0, dir, f);

    cout << answer;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    input();
    pro();
    return 0;
}