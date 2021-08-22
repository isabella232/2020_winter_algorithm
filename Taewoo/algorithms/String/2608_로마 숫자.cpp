#include <bits/stdc++.h>
using namespace std;

map<char, int> v;
string s1, s2;
void init() {
    v['I'] = 1; v['V'] = 5; v['X'] = 10; v['L'] = 50;
    v['C'] = 100; v['D'] = 500; v['M'] = 1000;
}

int arabia(string s) {
    int val = 0;
    for(int i = 0; i < s.size(); i++) {
        val += v[s[i]];
        if(i > 0 && v[s[i-1]] < v[s[i]]) {
            val = val - 2 * v[s[i-1]];
        }
    }
    return val;
}

string romaSum(int val) {
    string roma;

    int a = val / 1000;
    val %= 1000;

    for(int i = 0; i < a; i++) roma += 'M';

    int b = val / 100;
    val %= 100;
    if(b == 4) {
        roma += 'C';
        roma += 'D';
    }
    else if(b == 9) {
        roma += 'C';
        roma += 'M';
    }
    else {
        if(b >= 5) {
            roma += 'D';
            b -= 5;
        }
        for(int i = 0; i < b; i++) roma += 'C';
    }

    int c = val / 10;
    val %= 10;
    if(c == 4) {
        roma += 'X';
        roma += 'L';
    }
    else if(c == 9) {
        roma += 'X';
        roma += 'C';
    }
    else {
        if(c >= 5) {
            roma += 'L';
            c -= 5;
        }
        for(int i = 0; i < c; i++) roma += 'X';
    }

    int d = val;
    if(d == 4) {
        roma += 'I';
        roma += 'V';
    }
    else if(d == 9) {
        roma += 'I';
        roma += 'X';
    }
    else {
        if(d >= 5) {
            roma += 'V';
            d -= 5;
        }
        for(int i = 0; i < d; i++) roma += 'I';
    }
    return roma;
}

int main() {
    init();
    cin >> s1 >> s2;
    int a = arabia(s1);
    int b = arabia(s2);
    cout << a + b << '\n';
    
    string str = romaSum(a+b);
    cout << str;
}


