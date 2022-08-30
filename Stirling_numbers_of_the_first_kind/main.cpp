#include <iostream>
#include <vector>

using namespace std;

vector<int> multiply(vector<int> a, int b){
    int n = a.size();
    vector<int>c(n, 0);
    c[0] = a[0] * b;
    for (int i = 1; i < n; i++){
        c[i] = a[i] * b + a[i-1];
    }
    return c;
}

int main() {
    int n, k;
    cin>>n>>k;
    vector<int>a(n, 0);
    a[1] = 1;
    for (int i = 1; i < n; i++){
        a = multiply(a, -i);
    }
    cout<<a[k];
    return 0;
}
