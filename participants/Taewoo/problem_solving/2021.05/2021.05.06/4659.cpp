#include <iostream>
using namespace std;

/*
    ����(a,e,i,o,u) �ϳ��� �ݵ�� �����Ͽ��� �Ѵ�.
    ������ 3�� Ȥ�� ������ 3�� �������� ���� �� �ȴ�.
    ���� ���ڰ� ���������� �ι� ���� �ȵǳ�, ee �� oo�� ����Ѵ�.
*/


bool isOk(char c) {
    if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
        return true;
    return false;
}

int main() {
    string s;

    while(true) {
        cin >> s;
        if(s == "end") break;

        // case 1
        bool flag = false;
        for(int i = 0 ; i < s.size(); i++) {
            if(isOk(s[i])) flag = true;
        }
        if(!flag) {
            cout << "<" << s << "> is not acceptable.\n";
            continue; 
        }

        // case 2
        int jaeum = 0, moeum = 0;
        bool right = false;
        for(int i = 0; i < s.size(); i++) {
            if(isOk(s[i])) {
                jaeum = 0, moeum++;
            }
            else {
                jaeum++, moeum = 0;
            }
            if(jaeum == 3 || moeum == 3) {
                right = true;
                break;
            }
        }
        if(right) {
            cout << "<" << s << "> is not acceptable.\n";
            continue;
        }

        // case 3
        bool ok = false;
        for(int i = 1; i < s.size(); i++) {
            if(s[i] == s[i - 1] && s[i] != 'e' && s[i] != 'o') {
                ok = true;
                break;
            }
        }
        if(ok) {
            cout << "<" << s << "> is not acceptable.\n";
            continue;
        }

        cout << "<" << s << "> is acceptable.\n";
    }    
}