#include <bits/stdc++.h>
using namespace std;

void java(string &s) {
    while(s.find('_') != string::npos) {
        int pos = s.find('_');
        s.erase(pos, 1);
        s[pos] -= ('a' - 'A');
    }
}

void c(string &s) {
    for(int i = 0; i < s.size(); i++) {
        if('A' <= s[i] && s[i] <= 'Z') {
            s.insert(i, "_");
            s[i+1] += ('a'-'A');
            i++;
        }
    }
}

string solution(string s) {
    bool flag = false;
    if(s[0] < 'a' || s[0] == '_' || s[s.size()-1] == '_') flag = true;
    if(flag == false) {
        for(int i = 0; i < s.size()-1; i++) {
            if(s[i] == '_' && s[i+1] == '_') {
                flag = true;
                break;
            }
        }
    }

    if(s.find('_') != string::npos) {
        for(int i = 0; i < s.size(); i++) {
            if('A' <= s[i] && s[i] <= 'Z') {
                flag = true;
                break;
            }
        }
        java(s);
    }
    else {
        c(s);
    }
    if(flag) return "Error!";
    else return s;

}

int main() {
    string s;
    cin >> s;
    cout << solution(s);
}