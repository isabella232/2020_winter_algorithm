/*
    1. k�� ���߸鼭 ���� �� �ִ� ���ǵ� �� ��ġ�� ���� �ִ밡 �Ǵ� ��� >> dp[i] ���� ���԰� i ������ ���� ��ġ�� �ִ��� ���

    > ���濡 �ִ� ��� >> ��� �־�� �ִ밪�� ���� �� �ֳ�? >> �ٵ�������..
                                                    >> �ΰ��� ���԰� ���� ���� ��� ��������??
                                                    >> dp[i][j] ���� i ���� ���� / j��° ���� ����������
    > ���濡 ���� �ʴ� ��� >> �׳� �ȳ����� �ǰ�,
*/

#include <iostream>
#include <cstring>
using namespace std;

int n, k;
int value[101];
int weight[101];
int dp[100001][101];

void input() {
    cin >> n >> k;
    for(int i = 0; i < n; i++) {
        cin >> weight[i] >> value[i];
    }
}
// top bottom 
// bottom up
int get_dp(int remain, int idx) { 
    if(idx == n) return 0;

    int &ret = dp[remain][idx];
    if(ret != -1) return ret;
    ret = 0;
    // ���� ������ ���԰� �����ִ� �賶 ���Ժ��� ������ ��� �ְ� �����ϱ�
    // �׳� �ʳְ� ����
    if(weight[idx] <= remain) ret = max(ret, get_dp(remain - weight[idx], idx + 1) + value[idx]);
    ret = max(ret, get_dp(remain, idx + 1));
    return ret;
}

void pro() {
    memset(dp, -1, sizeof(dp));
    cout << get_dp(k, 0);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    input();
    pro();
    return 0;
}