#include <bits/stdc++.h>

using namespace std;

int solution(string str1, string str2) {
    int answer = 0;
    vector<string> str_1;
    vector<string> str_2;
    
    transform(str1.begin(), str1.end(), str1.begin(), ::tolower);
    transform(str2.begin(), str2.end(), str2.begin(), ::tolower);
    
    for(int i = 0; i < str1.size() - 1; i++) {
        if ((str1[i] >= 97 && str1[i] <= 122) && (str1[i+1] >= 97 && str1[i+1] <= 122)) {
            string temp = str1.substr(i, 2);
            str_1.push_back(temp);
        }
    }

    
    for(int i = 0; i < str2.size() - 1; i++) {
        if ((str2[i] >= 97 && str2[i] <= 122) && (str2[i+1] >= 97 && str2[i+1] <= 122)) {
            string temp = str2.substr(i, 2);
            str_2.push_back(temp);
        }
    }

    if(str_1.size() == 0 && str_2.size() == 0) return 65536;
    int union_size = str_1.size() + str_2.size();
    
    int fre = 0;
    for(auto i = str_1.begin(); i != str_1.end(); i++) {
        auto j = find(str_2.begin(), str_2.end(), *i);
        if(j != str_2.end()) {
            str_2.erase(j);
            fre += 1;
        }
    }
    
    if(fre == 0) return 0;
    union_size -= fre;
    
    answer = floor(((double)fre / union_size) * 65536);
    return answer;
}