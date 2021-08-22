#include<bits/stdc++.h>
using namespace std;

unordered_map<string, vector<int> > m;

void dfs(int idx, string key[], int score, string s) {
    if(idx == 4) {
        m[s].push_back(score);
        return;
    }

    dfs(idx+1, key, score, s+key[idx]);
    dfs(idx+1, key, score, s+"-");
}

vector<int> solution(vector<string> info, vector<string> query) {
    vector<int> answer;
    string key[4];
    int score;

    for(auto& it : info) {
        stringstream ss(it);
        ss >> key[0] >> key[1] >> key[2] >> key[3] >> score;
        dfs(0, key, score, "");
    } 

    for(auto it = m.begin(); it != m.end(); it++) sort(it->second.begin(), it->second.end());
    
    for(auto& it : query) {
        stringstream ss(it);
        string _;
        ss >> key[0] >> _ >> key[1] >> _ >> key[2] >> _ >> key[3] >> score;

        auto finder = m.find(key[0]+key[1]+key[2]+key[3]);
        if(finder == m.end()) answer.push_back(0);
        else {
            auto iter = lower_bound(finder->second.begin(), finder->second.end(), score);
            int ret = finder->second.end() - iter;
            answer.push_back(ret);
        }
    }
    for(auto it : answer) cout << it << ' ';
    return answer;
}

int main() {
    vector<string> info = { "java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50" };
    vector<string> query = { "java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150" };
    solution(info, query);
}