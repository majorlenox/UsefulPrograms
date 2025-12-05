#include <bits/stdc++.h>

#define ll long long
#define ull unsigned long long

using namespace std;

pair<ull, ull> prob_next_hcn(ull val, ull divisors, vector<ull>& p, unordered_map<ull, vector<ull>>& c, ull i){
    vector<ull> c2 = c[val];
    val *= p[i];
    if (i == c2.size()) {
        divisors *= 2;
        c2.push_back(1);
    }else{
        divisors = (divisors/(c2[i]+1))*(c2[i]+2);
        c2[i]++;
    }
    c[val] = c2;
    return {val, divisors};
}

vector<ull> hcn_generator(ull n, int mode){ // mode = 0 -> n is count of hcns, mode = 1 -> get list of all hcn <= n
    ull val;
    vector<ull> p = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53};
    vector<ull> q = {1, 2, 4, 6, 12, 24, 36}; // n = 4 and n = 36 - are exceptions
    priority_queue<pair<ull, ull>, vector<pair<ull, ull>>, greater<>> pq; // (val, divisors)
    unordered_map<ull, vector<ull>> c; // val -> c
    pq.emplace(48, 5*2);
    pq.emplace(60, 3*2*2);
    pq.emplace(120, 4*2*2);
    pq.emplace(180, 3*3*2);
    c[48] = {4, 1};
    c[60] = {2, 1, 1};
    c[120] = {3, 1, 1};
    c[180] = {2, 2, 1};
    ull divisors;
    ull mn_divisors = 0;
    n = n - 7;
    while (n > 0){
        val = pq.top().first;
        divisors = pq.top().second;
        pq.pop();
        if (mn_divisors < divisors){
            mn_divisors = divisors;
        }else{
            continue;
        }
        if (mode == 1 and val > n) break;
        q.push_back(val);
        pq.push(prob_next_hcn(val, divisors, p, c, c[val].size()));
        for (ll i = c[val].size() - 1; i >= 0; --i){
            pq.push(prob_next_hcn(val, divisors, p, c, i));
        }
        c.erase(val);
        n--;
    }
    return q;
}

int main()
{
    ull n;
    cin>>n;
    vector<ull> q = hcn_generator(n, 0);
    for (int i = 1; i < q.size(); ++i)
        cout<<q[i]<<'\n';
    return 0;
}

